package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.PlayerPagerAdapter;
import com.whfrp3.ihm.fragments.InventoryFragment;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.BindingContext;
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
    private BindingContext mBindingContext;

    //region Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mBindingContext = new BindingContext(false);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean inEdition = extras.getBoolean(IPlayerActivityConstants.IS_IN_EDITION_BUNDLE_TAG);
            mBindingContext.setInEdition(inEdition);
        }

        initVisualElements();
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

    //region Instances
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IPlayerActivityConstants.IS_IN_EDITION_BUNDLE_TAG, mBindingContext.isInEdition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean b = savedInstanceState.getBoolean(IPlayerActivityConstants.IS_IN_EDITION_BUNDLE_TAG);
        setIsInEdition(b);
    }

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
        getMenuInflater().inflate(R.menu.menu_player, menu);
        mMenu = menu;
        changeEdition();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_in_edition_true || id == R.id.action_in_edition_false) {
            setIsInEdition(!mBindingContext.isInEdition());
            return true;
        }
        if (id == R.id.action_launch) {
            startLaunchActivity(null);
            return true;
        }
        if (id == R.id.action_update_player) {
            PlayerHelper.savePlayer(WHFRP3Application.getPlayer());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //endregion

    //region Init

    /**
     * Initialize the various visual elements (Toolbar, ViewPager...).
     */
    private void initVisualElements() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlayerPagerAdapter = new PlayerPagerAdapter(this, mBindingContext);

        ViewPager viewPager = (ViewPager) findViewById(R.id.player_pager_container);
        viewPager.setAdapter(mPlayerPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    //endregion

    /**
     * Start a new LaunchActivity with(out) a bundle and add it to the TaskStack.
     *
     * @param skill
     */
    private void startLaunchActivity(@Nullable Skill skill) {
        Intent launchIntent = new Intent(PlayerActivity.this, LaunchActivity.class);
        Bundle bundle = new Bundle();
        if (skill != null) {
            bundle.putSerializable(SKILL_BUNDLE_TAG, skill);
        }
        bundle.putInt(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, mPlayerPagerAdapter.getCurrentPosition());
        launchIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(PlayerActivity.this);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivityForResult(launchIntent, LAUNCH_REQUEST);
    }
    //endregion

    //region Edition

    /**
     * Effectively changes the edition mode in PlayerHelper and update the UI.
     *
     * @param isInEdition
     */
    private void setIsInEdition(boolean isInEdition) {
        mBindingContext.setInEdition(isInEdition);
        changeEdition();
    }

    /**
     * Replace the icon of the concerned buttons by an open/close lock.
     */
    private void changeEdition() {
        if (mMenu != null) {
            if (mBindingContext.isInEdition()) {
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

    /**
     * Event click on the skillsList which launches the LaunchActivity
     * with a standard hand for this player and this skill
     *
     * @param view
     */
    public void launchSkill(View view) {
        try {
            TextView tv = (TextView) view;
            String skillName = tv.getText().toString();
            Skill skill = WHFRP3Application.getPlayer().getSkillByName(skillName);

            startLaunchActivity(skill);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage(), e);
        }
    }
}
