package com.aku.warhammerdicelauncher.activities;

import android.app.AlertDialog;
import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.MainFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.StatisticsFragment;
import com.aku.warhammerdicelauncher.model.dao.HandDao;
import com.aku.warhammerdicelauncher.model.database.helper.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.services.DicesRollerService;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;
import com.aku.warhammerdicelauncher.utils.helpers.DialogHelper;
import com.aku.warhammerdicelauncher.utils.helpers.FragmentHelper;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String FRAGMENT_TAG = "fragmentContent";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private Fragment fragmentContent;

    private boolean onMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        setupNavigationDrawer();
        fillHandsSpinner();

        drawerList.setOnItemClickListener(new NavigationDrawerListItemClickListener());

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

        /* LAST */
        if (savedInstanceState == null) {
            replaceByMainFragment();
        } else if (!onMainFragment) {
            fragmentContent = getFragmentManager().getFragment(savedInstanceState, FRAGMENT_TAG);
        }
        /* LAST */
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
        super.onSaveInstanceState(savedInstanceState);

        if (onMainFragment) {
            HandDto dto = currentHandToDto();
            savedInstanceState.putSerializable(StatisticsFragment.HAND_TAG, dto);
        }
        getFragmentManager().putFragment(savedInstanceState, FRAGMENT_TAG, fragmentContent);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (onMainFragment) {
            HandDto dto = (HandDto) savedInstanceState.getSerializable(StatisticsFragment.HAND_TAG);
            dtoToCurrentHand(dto);
        }
        fragmentContent = getFragmentManager().getFragment(savedInstanceState, FRAGMENT_TAG);
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
            Map<DiceFace, Integer> res = DicesRollerService.rollDices(dto);

            DialogHelper.showLaunchResults(res, this);
        } catch (Exception e) {
            Log.e(this.getLocalClassName(), "rollDices: ", e);
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
                    if (input.getText().toString().trim().isEmpty()) {
                        Toast.makeText(getApplicationContext(), R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
                    } else {
                        HandDto dto = currentHandToDto();
                        dto.setTitle(input.getText().toString());

                        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
                        HandDao dao = new HandDao(whdHelper);
                        dao.insert(dto);

                        fillHandsSpinner();

                        invalidateOptionsMenu();
                    }
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
            Log.e(this.getLocalClassName(), "saveHand: ", e);
            throw e;
        }
    }


    public void useHand(String title) {
        if (title.trim().isEmpty()) {
            resetHand();
        } else {
            WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
            HandDao dao = new HandDao(whdHelper);

            try {
                HandDto dto = dao.findByTitle(title);
                dtoToCurrentHand(dto);
            } catch (Resources.NotFoundException nfe) {
                Log.e(this.getLocalClassName(), "useHand: ", nfe);
            }
        }
    }

    //region Hand and dto helpers

    /**
     * Uses the dto's values to set pickers' value
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

    private void resetHand() {
        NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);

        characteristicPicker.setValue(0);
        recklessPicker.setValue(0);
        conservativePicker.setValue(0);
        expertisePicker.setValue(0);
        fortunePicker.setValue(0);
        misfortunePicker.setValue(0);
        challengePicker.setValue(0);
    }
    //endregion

    //region Fragments management
    private void replaceByMainFragment() {
        fragmentContent = FragmentHelper.replaceByMainFragment(getFragmentManager());
        onMainFragment = true;
        invalidateOptionsMenu();
    }

    private void replaceByStatisticsFragment(int times) {
        fragmentContent = FragmentHelper.replaceByStatisticsFragment(currentHandToDto(), times, getFragmentManager());
        onMainFragment = false;
        invalidateOptionsMenu();
    }
    //endregion

    private void fillHandsSpinner() {
        if (onMainFragment) {
            MainFragment fragment = (MainFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            fragment.fillHandsSpinner();
        }
    }

    //region Navigation Drawer
    private void setupNavigationDrawer() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.pages, R.layout.drawer_list_item);
        drawerList.setAdapter(adapter);
    }

    private class NavigationDrawerListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String title = ((TextView) view).getText().toString();
            if ("Home".equals(title)) {
                replaceByMainFragment();
            }

            drawerList.setItemChecked(position, true);
            getSupportActionBar().setTitle(title);
            drawerLayout.closeDrawer(drawerList);
        }
    }
    //endregion
}
