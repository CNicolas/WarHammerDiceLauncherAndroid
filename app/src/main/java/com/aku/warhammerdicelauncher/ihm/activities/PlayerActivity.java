package com.aku.warhammerdicelauncher.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.CharacteristicsFragment;
import com.aku.warhammerdicelauncher.ihm.tools.PlayerPagerAdapter;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.constants.IPlayerConstants;

public class PlayerActivity extends AppCompatActivity {

    private PlayerPagerAdapter mPlayerPagerAdapter;
    private FloatingActionButton mEditionFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean inEdition = extras.getBoolean(IPlayerConstants.IS_IN_EDITION_KEY);
            PlayerContext.setIsInEdition(inEdition);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditionFab = (FloatingActionButton) findViewById(R.id.edition_fab);
        mPlayerPagerAdapter = new PlayerPagerAdapter(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.player_pager_container);
        viewPager.setAdapter(mPlayerPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        changeEditionFabDrawable();
        hideKeyboard();
    }

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

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyboard();
    }

    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_launch) {
            startLaunchActivity();
            return true;
        }
        if (id == R.id.action_update_player) {
            updatePlayer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updatePlayer() {
        Player player = PlayerContext.getPlayerInstance();
        new AlertDialog.Builder(this).setTitle(player.getName()).setMessage(player.toString()).show();
    }

    private void startLaunchActivity() {
        Intent launchIntent = new Intent(PlayerActivity.this, LaunchActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(PlayerActivity.this);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivity(launchIntent);
    }
    //endregion

    public void setEdition(View v) {
        setIsInEdition(!PlayerContext.isInEdition());
    }

    public void setIsInEdition(boolean isInEdition) {
        PlayerContext.setIsInEdition(isInEdition);
        changeEditionFabDrawable();

        CharacteristicsFragment characteristicsFragment = mPlayerPagerAdapter.getCharacteristicsFragment();
        if (characteristicsFragment != null && characteristicsFragment.isVisible()) {
            characteristicsFragment.changeEdition();
        }
    }

    private void changeEditionFabDrawable() {
        if (PlayerContext.isInEdition()) {
            mEditionFab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_lock_open_white_24dp));
        } else {
            mEditionFab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_lock_outline_white_24dp));
            hideKeyboard();
        }
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.player_pager_container).getWindowToken(), 0);
    }
}
