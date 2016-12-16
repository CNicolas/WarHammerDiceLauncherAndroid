package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.whfrp3.R;
import com.whfrp3.ihm.fragments.main.CareersFragment;
import com.whfrp3.ihm.fragments.main.ItemsFragment;
import com.whfrp3.ihm.fragments.main.PlayersListFragment;
import com.whfrp3.ihm.fragments.main.SkillsFragment;
import com.whfrp3.ihm.fragments.main.SpecialisationsFragment;
import com.whfrp3.ihm.fragments.main.TalentTypesFragment;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IMainConstants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedFragment(R.id.nav_home);

        WHFRP3Application.setActivity(this);
    }

    @Override
    protected void onResume() {
        WHFRP3Application.setActivity(this);
        super.onResume();
    }

    //region Menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_CANCELED) {
            if (requestCode == IMainConstants.TALENTS_REQUEST) {
                displaySelectedFragment(R.id.nav_talents);
            } else if (requestCode == IMainConstants.PLAYER_REQUEST) {
                displaySelectedFragment(R.id.nav_home);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedFragment(item.getItemId());
        return true;
    }

    private void displaySelectedFragment(int id) {
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new PlayersListFragment();
        } else if (id == R.id.nav_talents) {
            fragment = new TalentTypesFragment();
        } else if (id == R.id.nav_careers) {
            fragment = new CareersFragment();
        } else if (id == R.id.nav_items) {
            fragment = new ItemsFragment();
        } else if (id == R.id.nav_skills) {
            fragment = new SkillsFragment();
        } else if (id == R.id.nav_specialisations) {
            fragment = new SpecialisationsFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
