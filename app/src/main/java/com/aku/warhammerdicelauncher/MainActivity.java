package com.aku.warhammerdicelauncher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.aku.warhammerdicelauncher.model.dao.HandDao;
import com.aku.warhammerdicelauncher.model.database.helper.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dices.IDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ChallengeDice;
import com.aku.warhammerdicelauncher.model.dices.impl.CharacteristicDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ConservativeDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ExpertiseDice;
import com.aku.warhammerdicelauncher.model.dices.impl.FortuneDice;
import com.aku.warhammerdicelauncher.model.dices.impl.MisfortuneDice;
import com.aku.warhammerdicelauncher.model.dices.impl.RecklessDice;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.services.DicesRoller;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        HandDao dao = new HandDao(whdHelper);
        List<String> titles = dao.findAllTitles();
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, titles));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
                HandDao dao = new HandDao(whdHelper);
                String title = dao.findAllTitles().get(position);
                useHand(title);
                drawerList.setItemChecked(position, true);
                getSupportActionBar().setTitle(title);
                drawerLayout.closeDrawer(drawerList);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(getTitle());
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.hands_list);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        Toast.makeText(this, R.string.savedToastText, Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();

        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        HandDao dao = new HandDao(whdHelper);
        List<String> titles = dao.findAllTitles();
        for (String title : titles) {
            menu.add(title);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        HandDao dao = new HandDao(whdHelper);

        try {
            HandDto hand = dao.findByTitle(item.getTitle().toString());

            NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
            characteristicPicker.setValue(hand.getCharacteristic());
            NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
            recklessPicker.setValue(hand.getReckless());
            NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
            conservativePicker.setValue(hand.getConservative());
            NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
            expertisePicker.setValue(hand.getExpertise());
            NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
            fortunePicker.setValue(hand.getFortune());
            NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
            misfortunePicker.setValue(hand.getMisfortune());
            NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);
            challengePicker.setValue(hand.getChallenge());
        } catch (Resources.NotFoundException nfe) {
            Log.e("MainActivity", "onOptionsItemSelected: ", nfe);
        }

        return false;
    }*/

    //-----------CUSTOM METHODS-----------

    /**
     * React to a click on the rollButton. Roll dices and show the results in a popup
     *
     * @param v the view
     */
    public void rollDices(View v) {
        try {
            int characteristic = ((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue();
            int reckless = ((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue();
            int conservative = ((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue();
            int expertise = ((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue();
            int fortune = ((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue();
            int misfortune = ((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue();
            int challenge = ((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue();

            List<IDice> pool = new ArrayList<>();
            for (int i = 0; i < characteristic; i++) {
                pool.add(new CharacteristicDice());
            }
            for (int i = 0; i < reckless; i++) {
                pool.add(new RecklessDice());
            }
            for (int i = 0; i < conservative; i++) {
                pool.add(new ConservativeDice());
            }
            for (int i = 0; i < expertise; i++) {
                pool.add(new ExpertiseDice());
            }
            for (int i = 0; i < fortune; i++) {
                pool.add(new FortuneDice());
            }
            for (int i = 0; i < misfortune; i++) {
                pool.add(new MisfortuneDice());
            }
            for (int i = 0; i < challenge; i++) {
                pool.add(new ChallengeDice());
            }

            Map<DiceFace, Integer> res = DicesRoller.rollDices(pool);

            showResults(res);

        } catch (Exception e) {
            Log.e("MainActivity", "rollDices: ", e);
            throw e;
        }
    }

    public void saveHand(View v) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HandDto dto = new HandDto();
                    dto.setTitle(input.getText().toString());
                    dto.setCharacteristic(((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue());
                    dto.setReckless(((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue());
                    dto.setConservative(((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue());
                    dto.setExpertise(((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue());
                    dto.setFortune(((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue());
                    dto.setMisfortune(((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue());
                    dto.setChallenge(((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue());

                    WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
                    HandDao dao = new HandDao(whdHelper);
                    long newRowId = dao.insert(dto);

                    invalidateOptionsMenu();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        } catch (Exception e) {
            Log.e("MainActivity", "saveHand: ", e);
            throw e;
        }
    }

    private void showResults(Map<DiceFace, Integer> map) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_results);
        dialog.setTitle(R.string.resultsTitle);

        for (DiceFace face : Constants.popupTextViews.keySet()) {
            TextView textView = (TextView) dialog.findViewById(Constants.popupTextViews.get(face));
            if (map.containsKey(face)) {
                textView.setText(map.get(face).toString());
            } else {
                textView.setVisibility(View.GONE);
            }
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.dismissResultsPopupButton);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void useHand(String title) {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        HandDao dao = new HandDao(whdHelper);

        try {
            HandDto hand = dao.findByTitle(title);

            NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
            characteristicPicker.setValue(hand.getCharacteristic());
            NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
            recklessPicker.setValue(hand.getReckless());
            NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
            conservativePicker.setValue(hand.getConservative());
            NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
            expertisePicker.setValue(hand.getExpertise());
            NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
            fortunePicker.setValue(hand.getFortune());
            NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
            misfortunePicker.setValue(hand.getMisfortune());
            NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);
            challengePicker.setValue(hand.getChallenge());
        } catch (Resources.NotFoundException nfe) {
            Log.e("MainActivity", "useHand: ", nfe);
        }
    }

    private void emptyPickers() {
        try {
            NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
            characteristicPicker.setValue(0);
            NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
            recklessPicker.setValue(0);
            NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
            conservativePicker.setValue(0);
            NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
            expertisePicker.setValue(0);
            NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
            fortunePicker.setValue(0);
            NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
            misfortunePicker.setValue(0);
            NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);
            challengePicker.setValue(0);
        } catch (Resources.NotFoundException nfe) {
            Log.e("MainActivity", "emptyPickers: ", nfe);
        }
    }

}
