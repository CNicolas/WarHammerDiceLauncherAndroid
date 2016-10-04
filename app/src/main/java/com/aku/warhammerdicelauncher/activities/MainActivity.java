package com.aku.warhammerdicelauncher.activities;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.LaunchFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.StatisticsFragment;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.utils.helpers.FragmentHelper;

public class MainActivity extends AppCompatActivity {

    private String FRAGMENT_TAG = "fragmentContent";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private Fragment fragmentContent;

    private boolean onLaunchFragment;

    //region Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        drawerList = (ListView) findViewById(R.id.left_drawer);
        setupNavigationDrawer();
        drawerList.setOnItemClickListener(new NavigationDrawerListItemClickListener());

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        /* LAST */
        if (savedInstanceState == null) {
            replaceByLaunchFragment();
        } else if (!onLaunchFragment) {
            fragmentContent = getFragmentManager().getFragment(savedInstanceState, FRAGMENT_TAG);
        }
        /* LAST */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.launch_statistics, menu);
        menu.findItem(R.id.action_home).setVisible(!onLaunchFragment);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * After a invalidateOptionsMenu();
     *
     * @param menu
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!onLaunchFragment) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(false);
            }
        }
        menu.findItem(R.id.action_home).setVisible(!onLaunchFragment);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_home:
                replaceByLaunchFragment();
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
            case R.id.action_launch10000:
                replaceByStatisticsFragment(10000);
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

        if (onLaunchFragment) {
            HandDto dto = currentHandToDto();
            savedInstanceState.putSerializable(StatisticsFragment.HAND_TAG, dto);
        }
        getFragmentManager().putFragment(savedInstanceState, FRAGMENT_TAG, fragmentContent);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (onLaunchFragment) {
            HandDto dto = (HandDto) savedInstanceState.getSerializable(StatisticsFragment.HAND_TAG);
            getCurrentLaunchFragment().dtoToCurrentHand(this, dto);
        }
        fragmentContent = getFragmentManager().getFragment(savedInstanceState, FRAGMENT_TAG);
    }
    //endregion

    //region Click events handlers

    /**
     * React to a click on the saveButton. Save the hand
     *
     * @param v the view
     */
    public void saveHand(View v) {
        if (onLaunchFragment) {
            getCurrentLaunchFragment().saveHand(this);
        }
    }

    /**
     * React to a click on the saveButton. Save the hand
     *
     * @param v the view
     */
    public void updateHand(View v) {
        if (onLaunchFragment) {
            getCurrentLaunchFragment().updateHand(this);
        }
    }

    /**
     * React to a click on the rollButton. Roll dices and show the results in a popup
     *
     * @param v the view
     */
    public void rollDices(View v) {
        if (onLaunchFragment) {
            getCurrentLaunchFragment().rollDices(this);
        }
    }

    /**
     * React to a click on the resetButton. Reset the numberpickers
     *
     * @param v the view
     */
    public void resetHand(View v) {
        if (onLaunchFragment) {
            getCurrentLaunchFragment().resetHand(this);
        }
    }
    //endregion

    //region Hand and dto helpers
    private HandDto currentHandToDto() {
        return getCurrentLaunchFragment().currentHandToDto(this);
    }
    //endregion

    //region Fragments management
    private LaunchFragment getCurrentLaunchFragment() {
        return FragmentHelper.getCurrentLaunchFragment(getFragmentManager());
    }

    private void replaceByLaunchFragment() {
        fragmentContent = FragmentHelper.replaceByLaunchFragment(getFragmentManager());
        onLaunchFragment = true;
        invalidateOptionsMenu();
    }

    private void replaceByStatisticsFragment(int times) {
        fragmentContent = FragmentHelper.replaceByStatisticsFragment(currentHandToDto(), times, getFragmentManager());
        onLaunchFragment = false;
        invalidateOptionsMenu();
    }

    private void replaceByCharacterFragment() {
        fragmentContent = FragmentHelper.replaceByCharacterFragment(getFragmentManager());
        onLaunchFragment = false;
        invalidateOptionsMenu();
    }
    //endregion

    //region Navigation Drawer
    private void setupNavigationDrawer() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.pages, R.layout.item_drawer_list);
        drawerList.setAdapter(adapter);
    }

    private class NavigationDrawerListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String title = ((TextView) view).getText().toString();

            if (getString(R.string.page_home).equals(title)) {
                replaceByLaunchFragment();
            } else if (getString(R.string.page_character).equals(title)) {
                replaceByCharacterFragment();
            } else {
                replaceByLaunchFragment();
            }

            drawerList.setItemChecked(position, true);
            getSupportActionBar().setTitle(title);
            drawerLayout.closeDrawer(drawerList);
        }
    }
    //endregion

}
