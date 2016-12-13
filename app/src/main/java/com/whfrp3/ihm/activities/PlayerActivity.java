package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.PlayerPagerAdapter;
import com.whfrp3.ihm.fragments.player.InventoryFragment;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.PlayerHelper;

/**
 * The main activity of WHFRP3 :
 * It contains a CharacteristicsFragment, a SkillsFragment and an InventoryFragment.
 */
public class PlayerActivity extends AppCompatActivity implements IPlayerActivityConstants {

    private PlayerPagerAdapter mPlayerPagerAdapter;
    private Menu mMenu;

    //region Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean inEdition = extras.getBoolean(IPlayerActivityConstants.IS_IN_EDITION_BUNDLE_TAG);
            WHFRP3Application.getPlayer().setInEdition(inEdition);
        }

        mPlayerPagerAdapter = new PlayerPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.player_pager_container);
        viewPager.setAdapter(mPlayerPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(WHFRP3Application.getPlayer().getName());

        WHFRP3Application.setActivity(this);
    }

    @Override
    protected void onPause() {
        PlayerHelper.savePlayer(WHFRP3Application.getPlayer());
        super.onPause();
    }

    @Override
    protected void onStop() {
        PlayerHelper.savePlayer(WHFRP3Application.getPlayer());
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        PlayerHelper.savePlayer(WHFRP3Application.getPlayer());
        super.onBackPressed();
    }

    //region Instances
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_CANCELED) {
            if (requestCode == LAUNCH_REQUEST) {
                mPlayerPagerAdapter.getItem(intent.getIntExtra(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, CHARACTERISTICS_FRAGMENT_POSITION));
            } else if (requestCode == InventoryFragment.ADD_ITEM_REQUEST || requestCode == EDIT_ITEM_REQUEST) {
                mPlayerPagerAdapter.getItem(intent.getIntExtra(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, INVENTORY_FRAGMENT_POSITION));
            }
        }
    }
    //endregion

    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player, menu);
        mMenu = menu;
        changeEdition();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_in_edition_true || id == R.id.action_in_edition_false) {
            setIsInEdition(!WHFRP3Application.getPlayer().isInEdition());
        } else if (id == R.id.action_launch) {
            startLaunchActivity();
        }

        return true;
    }
    //endregion

    //endregion

    /**
     * Start a new LaunchActivity with(out) a bundle and add it to the TaskStack.
     */
    private void startLaunchActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, mPlayerPagerAdapter.getCurrentPosition());

        Intent launchIntent = new Intent(PlayerActivity.this, LaunchActivity.class);
        launchIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(PlayerActivity.this);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivityForResult(launchIntent, LAUNCH_REQUEST);
    }

    //region Edition

    /**
     * Effectively changes the edition mode in PlayerHelper and update the UI.
     *
     * @param isInEdition
     */
    private void setIsInEdition(boolean isInEdition) {
        WHFRP3Application.getPlayer().setInEdition(isInEdition);
        changeEdition();
    }

    /**
     * Replace the icon of the concerned buttons by an open/close lock.
     */
    private void changeEdition() {
        if (mMenu != null) {
            if (WHFRP3Application.getPlayer().isInEdition()) {
                mMenu.findItem(R.id.action_in_edition_true).setVisible(true);
                mMenu.findItem(R.id.action_in_edition_false).setVisible(false);

            } else {
                mMenu.findItem(R.id.action_in_edition_true).setVisible(false);
                mMenu.findItem(R.id.action_in_edition_false).setVisible(true);

                hideKeyboard();
            }
        }
    }
    //endregion

    /**
     * Hide the soft keyboard.
     */
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.player_pager_container).getWindowToken(), 0);
    }
}
