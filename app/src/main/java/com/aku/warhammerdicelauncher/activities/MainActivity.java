package com.aku.warhammerdicelauncher.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.MainFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.StatisticsFragment;
import com.aku.warhammerdicelauncher.model.dao.HandDao;
import com.aku.warhammerdicelauncher.model.database.helper.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.services.DicesRoller;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private boolean onMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        setTitlesInDrawerList();
        drawerList.setOnItemClickListener(new HandItemClickListener());

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

        if (savedInstanceState == null) {
            replaceByMainFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        menu.findItem(R.id.action_home).setVisible(!onMainFragment);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * After a invalidateOptionsMenu();
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!onMainFragment) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(false);
            }
        }
        menu.findItem(R.id.action_home).setVisible(!onMainFragment);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_home:
                replaceByMainFragment();
                return true;
            case R.id.action_launch10:
                replaceByStatisticsFragment(10);
                return true;
            case R.id.action_launch100:
                replaceByStatisticsFragment(100);
                return true;
            case R.id.action_launch1000:
                replaceByStatisticsFragment(1000);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * After a onRestoreInstanceState
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        HandDto dto = currentHandToDto();
        savedInstanceState.putSerializable("dto", dto);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        HandDto dto = (HandDto) savedInstanceState.getSerializable("dto");
        dtoToCurrentHand(dto);
    }

    //-------------------------------------------------------------------------------------------------
    //---------------------------------CUSTOM METHODS--------------------------------------------------
    //-------------------------------------------------------------------------------------------------

    /**
     * React to a click on the rollButton. Roll dices and show the results in a popup
     *
     * @param v the view
     */
    public void rollDices(View v) {
        try {
            HandDto dto = currentHandToDto();
            Map<DiceFace, Integer> res = DicesRoller.rollDices(dto);

            showResults(res);
        } catch (Exception e) {
            Log.e("MainActivity", "rollDices: ", e);
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

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
                    HandDto dto = currentHandToDto();
                    dto.setTitle(input.getText().toString());

                    WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
                    HandDao dao = new HandDao(whdHelper);
                    dao.insert(dto);

                    setTitlesInDrawerList();
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


    private void useHand(String title) {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        HandDao dao = new HandDao(whdHelper);

        try {
            HandDto dto = dao.findByTitle(title);
            dtoToCurrentHand(dto);
        } catch (Resources.NotFoundException nfe) {
            Log.e("MainActivity", "useHand: ", nfe);
        }
    }

    /**
     * Uses the dto's values to set pickers'
     *
     * @return
     */
    private void dtoToCurrentHand(HandDto dto) {
        NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);

        characteristicPicker.setValue(dto.getCharacteristic());
        recklessPicker.setValue(dto.getReckless());
        conservativePicker.setValue(dto.getConservative());
        expertisePicker.setValue(dto.getExpertise());
        fortunePicker.setValue(dto.getFortune());
        misfortunePicker.setValue(dto.getMisfortune());
        challengePicker.setValue(dto.getChallenge());
    }

    /**
     * Get current pickers' value and create a HandDto with them
     *
     * @return
     */
    private HandDto currentHandToDto() {
        HandDto dto = new HandDto();

        dto.setCharacteristic(((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue());
        dto.setReckless(((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue());
        dto.setConservative(((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue());
        dto.setExpertise(((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue());
        dto.setFortune(((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue());
        dto.setMisfortune(((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue());
        dto.setChallenge(((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue());

        return dto;
    }

    private void replaceByMainFragment() {
        Fragment fragment = new MainFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        fragmentManager.executePendingTransactions();

        onMainFragment = true;

        invalidateOptionsMenu();
    }

    private void replaceByStatisticsFragment(int times) {
        HandDto dto = currentHandToDto();

        Fragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putSerializable(StatisticsFragment.HAND, dto);
        args.putInt(StatisticsFragment.TIMES, times);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        fragmentManager.executePendingTransactions();

        onMainFragment = false;

        invalidateOptionsMenu();
    }

    private void setTitlesInDrawerList() {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        HandDao dao = new HandDao(whdHelper);
        List<String> titles = dao.findAllTitles();

        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, titles));
    }

    //-------------------------------------------------------------------------------------------------
    //---------------------------------LISTENERS-------------------------------------------------------
    //-------------------------------------------------------------------------------------------------

    private class HandItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (!onMainFragment) {
                replaceByMainFragment();
            }

            WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
            HandDao dao = new HandDao(whdHelper);
            String title = dao.findAllTitles().get(position);
            useHand(title);

            drawerList.setItemChecked(position, true);
            getSupportActionBar().setTitle(title);
            drawerLayout.closeDrawer(drawerList);


        }
    }

}
