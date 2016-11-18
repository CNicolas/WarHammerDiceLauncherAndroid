package com.whfrp3.ihm.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.whfrp3.R;
import com.whfrp3.databinding.ItemEditActivityBinding;
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
import com.whfrp3.tools.PlayerHelper;
import com.whfrp3.tools.WHFRP3Application;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Retrieve item by its id
        if (getIntent().getExtras() != null) {
            int itemId = getIntent().getExtras().getInt(ITEM_ID_KEY, ITEM_ID_DEFAULT);
            if (itemId == ITEM_ID_DEFAULT) {
                // TODO : Add error treatment
            }

            item = WHFRP3Application.getPlayer().getItemById(itemId);
            if (item == null) {
                // TODO : Add error treatment
            }
        } else {
            item = new Item();
            item.setId(0);
        }

        itemEdit = new ItemEdit(item);

        // Configure binding
        ItemEditActivityBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_edit_activity, null, false);
        binding.setItem(itemEdit);
        binding.setViewModel(this);

        setContentView(binding.getRoot());

        // Configure layout
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
                itemEdit.setType(null);
            }
        });

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
                itemEdit.setQuality(null);
            }
        });

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
                itemEdit.setRange(null);
            }
        });

        if (itemEdit.getType() != null) {
            typeSpinner.setSelection(itemEdit.getType().ordinal());
        }
        if (itemEdit.getQuality() != null) {
            qualitySpinner.setSelection(itemEdit.getQuality().ordinal());
        }
        if (itemEdit.getRange() != null) {
            rangeSpinner.setSelection(itemEdit.getRange().ordinal());
        }
    }

    public void onSave(View view) {
        if (item.getId() == 0) {
            item = Item.getItemFromType(itemEdit.getType());
            item.setId(0);
        }

        item.setName(itemEdit.getName());
        item.setDescription(itemEdit.getDescription());
        item.setEncumbrance(itemEdit.getEncumbrance());
        item.setQuantity(itemEdit.getQuantity());
        item.setQuality(itemEdit.getQuality());
        item.setType(itemEdit.getType());

        switch (item.getType()) {
            case ARMOR:
                Armor armor = item.toArmor();
                armor.setSoak(itemEdit.getSoak());
                armor.setDefense(itemEdit.getDefense());
                break;
            case USABLE_ITEM:
                UsableItem usableItem = item.toUsableItem();
                usableItem.setLoad(itemEdit.getLoad());
                break;
            case WEAPON:
                Weapon weapon = item.toWeapon();
                weapon.setDamage(itemEdit.getDamage());
                weapon.setCriticalLevel(itemEdit.getCriticalLevel());
                weapon.setRange(itemEdit.getRange());
                break;
        }

        if (item.getId() == 0) {
            Player player = WHFRP3Application.getPlayer();
            player.addItem(item);
            PlayerHelper.savePlayer(player);
        }

        this.setResult(RESULT_OK);
        this.finish();
    }
}
