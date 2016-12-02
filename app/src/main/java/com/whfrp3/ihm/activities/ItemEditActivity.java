package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.whfrp3.R;
import com.whfrp3.databinding.ActivityItemEditBinding;
import com.whfrp3.ihm.adapters.EnumSpinnerAdapter;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemEdit;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Quality;
import com.whfrp3.model.player.inventory.Range;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.PlayerHelper;

/**
 * Activity used to modify an item.
 */
public class ItemEditActivity extends AppCompatActivity {

    /**
     * Key used to store the item id to edit in the bundle.
     */
    public static final String ITEM_ID_KEY = "ITEM_ID_KEY";

    /**
     * Default item id value.
     */
    private static final int ITEM_ID_DEFAULT = -1;

    /**
     * Player's item.
     */
    private Item item;

    /**
     * View's item.
     */
    private ItemEdit itemEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve item by its id
        if (getIntent().getExtras() != null) {
            long itemId = getIntent().getExtras().getLong(ITEM_ID_KEY, ITEM_ID_DEFAULT);
            if (itemId == ITEM_ID_DEFAULT) {
                item = new Item();
                item.setId(0);
            } else {
                item = WHFRP3Application.getPlayer().getItemById(itemId);
                if (item == null) {
                    // TODO : Add error treatment
                    Log.e(getLocalClassName(), "item_id_key given but not present in db");
                }
            }
        }

        itemEdit = new ItemEdit(item);

        // Configure binding
        ActivityItemEditBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_item_edit);
        binding.setItem(itemEdit);
        binding.setViewModel(this);

        initTypeSpinner();
        initQualitySpinner();
        initRangeSpinner();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        WHFRP3Application.setActivity(this);
    }

    //region Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            Intent intent = new Intent();
            intent.putExtra(IPlayerActivityConstants.CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION);
            setResult(RESULT_CANCELED, intent);
            finish();
        } else if (itemId == R.id.action_save_item) {
            onSave();
        }
        return true;
    }
    //endregion

    //region Initialisation
    private void initTypeSpinner() {
        Spinner typeSpinner = (Spinner) findViewById(R.id.item_edit_type_spinner);
        typeSpinner.setAdapter(new EnumSpinnerAdapter(this.getLayoutInflater(), ItemType.values()));
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                ItemType type = (ItemType) adapterView.getItemAtPosition(pos);

                itemEdit.setType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemEdit.setType(ItemType.ITEM);
            }
        });

        if (itemEdit.getType() != null) {
            typeSpinner.setSelection(itemEdit.getType().ordinal(), false);
        }
    }

    private void initQualitySpinner() {
        Spinner qualitySpinner = (Spinner) findViewById(R.id.item_edit_quality_spinner);
        qualitySpinner.setAdapter(new EnumSpinnerAdapter(this.getLayoutInflater(), Quality.values()));
        qualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Quality quality = (Quality) adapterView.getItemAtPosition(pos);

                itemEdit.setQuality(quality);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemEdit.setQuality(Quality.NORMAL);
            }
        });

        if (itemEdit.getQuality() != null) {
            qualitySpinner.setSelection(itemEdit.getQuality().ordinal(), false);
        }
    }

    private void initRangeSpinner() {
        Spinner rangeSpinner = (Spinner) findViewById(R.id.item_edit_range_spinner);
        rangeSpinner.setAdapter(new EnumSpinnerAdapter(this.getLayoutInflater(), Range.values()));
        rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Range range = (Range) adapterView.getItemAtPosition(pos);

                itemEdit.setRange(range);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemEdit.setRange(Range.ENGAGED);
            }
        });

        if (itemEdit.getRange() != null) {
            rangeSpinner.setSelection(itemEdit.getRange().ordinal(), false);
        }
    }
    //endregion

    public void onSave() {
        item.setName(itemEdit.getName());
        item.setDescription(itemEdit.getDescription());
        item.setEncumbrance(itemEdit.getEncumbrance());
        item.setQuantity(itemEdit.getQuantity());
        item.setQuality(itemEdit.getQuality());
        item.setType(itemEdit.getType());

        switch (item.getType()) {
            case ARMOR:
                Armor armor = new Armor(item);
                armor.setSoak(itemEdit.getSoak());
                armor.setDefense(itemEdit.getDefense());
                item = armor;
                break;
            case USABLE_ITEM:
                UsableItem usableItem = new UsableItem(item);
                usableItem.setLoad(itemEdit.getLoad());
                item = usableItem;
                break;
            case WEAPON:
                Weapon weapon = new Weapon(item);
                weapon.setDamage(itemEdit.getDamage());
                weapon.setCriticalLevel(itemEdit.getCriticalLevel());
                weapon.setRange(itemEdit.getRange());
                item = weapon;
                break;
        }

        Player player = WHFRP3Application.getPlayer();

        if (item.getId() == 0) {
            player.addItem(item);
        } else {
            player.updateItem(item);
        }

        PlayerHelper.savePlayer(player);

        this.setResult(RESULT_OK);
        this.finish();
    }
}
