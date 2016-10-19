package com.aku.warhammerdicelauncher.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.tools.PlayerPagerAdapter;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.tools.PlayerContext;

public class PlayerActivity extends AppCompatActivity {

    private PlayerPagerAdapter mPlayerPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlayerPagerAdapter = new PlayerPagerAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPlayerPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_unlock_edition || id == R.id.action_lock_edition) {
            mPlayerPagerAdapter.getCharacteristicsFragment().setEdition();
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

    public void startLaunchActivity(View v) {
        Intent launchIntent = new Intent(PlayerActivity.this, LaunchActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(PlayerActivity.this);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivity(launchIntent);
    }
}
