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
import com.whfrp3.database.WarHammerDatabaseHelper;
import com.whfrp3.database.dao.CharacteristicsDao;
import com.whfrp3.database.dao.PlayerDao;
import com.whfrp3.ihm.adapters.PlayerPagerAdapter;
import com.whfrp3.ihm.fragments.CharacteristicsFragment;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.constants.IPlayerConstants;

/**
 * The main activity of WHFRP3 :
 * It contains a CharacteristicsFragment, a SkillsFragment and an InventoryFragment.
 */
public class PlayerActivity extends AppCompatActivity implements IPlayerConstants {

    private PlayerPagerAdapter mPlayerPagerAdapter;
    private Menu mMenu;

    //region Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean inEdition = extras.getBoolean(IPlayerConstants.IS_IN_EDITION_KEY);
            PlayerContext.setIsInEdition(inEdition);
        }

        initDaos();
        initVisualElements();

        hideKeyboard();

        PlayerContext.setContext(this);
    }

    //region Instances
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IPlayerConstants.IS_IN_EDITION_KEY, PlayerContext.isInEdition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean b = savedInstanceState.getBoolean(IPlayerConstants.IS_IN_EDITION_KEY);
        setIsInEdition(b);
    }
    //endregion

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyboard();
    }

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
            setIsInEdition(!PlayerContext.isInEdition());
            return true;
        }
        if (id == R.id.action_launch) {
            startLaunchActivity(null);
            return true;
        }
        if (id == R.id.action_update_player) {
            PlayerContext.updatePlayer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //endregion

    //region Init

    /**
     * Initialize the Daos for the PlayerActivity and the PlayerContext.
     */
    private void initDaos() {
        WarHammerDatabaseHelper databaseHelper = new WarHammerDatabaseHelper(this);
        PlayerContext.setPlayerDao(new PlayerDao(databaseHelper));
        PlayerContext.setCharacteristicsDao(new CharacteristicsDao(databaseHelper));
    }

    /**
     * Initialize the various visual elements (Toolbar, ViewPager...).
     */
    private void initVisualElements() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlayerPagerAdapter = new PlayerPagerAdapter(this);

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
     * @param bundle
     */
    private void startLaunchActivity(@Nullable Bundle bundle) {
        Intent launchIntent = new Intent(PlayerActivity.this, LaunchActivity.class);
        if (bundle != null) {
            launchIntent.putExtras(bundle);
        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(PlayerActivity.this);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivity(launchIntent);
    }
    //endregion

    //region Edition

    /**
     * Effectively changes the edition mode in PlayerContext and update the UI.
     *
     * @param isInEdition
     */
    private void setIsInEdition(boolean isInEdition) {
        PlayerContext.setIsInEdition(isInEdition);
        changeEdition();
    }

    /**
     * Replace the icon of the concerned buttons by an open/close lock.
     */
    private void changeEdition() {
        if (mMenu != null) {
            if (PlayerContext.isInEdition()) {
                mMenu.findItem(R.id.action_in_edition_true).setVisible(true);
                mMenu.findItem(R.id.action_in_edition_false).setVisible(false);

            } else {
                mMenu.findItem(R.id.action_in_edition_true).setVisible(false);
                mMenu.findItem(R.id.action_in_edition_false).setVisible(true);

                hideKeyboard();
            }
        }

        CharacteristicsFragment characteristicsFragment = mPlayerPagerAdapter.getCharacteristicsFragment();
        if (characteristicsFragment != null && characteristicsFragment.isVisible()) {
            characteristicsFragment.changeEdition();
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
            Skill skill = PlayerContext.getPlayerInstance().getSkillByName(skillName);

            Bundle bundle = new Bundle();
            bundle.putSerializable(SKILL_TAG, skill);
            startLaunchActivity(bundle);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage(), e);
        }
    }
}