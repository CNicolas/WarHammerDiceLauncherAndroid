package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.TalentsListAdapter;
import com.whfrp3.ihm.fragments.dialog.TalentSearchDialogFragment;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IMainConstants;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.TalentHelper;

import java.util.List;


public class TalentsActivity extends AppCompatActivity implements IMainConstants {
    private TalentType mTalentType;
    private boolean canSearchFurther = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        List<Talent> talents;
        if (getIntent().getExtras() != null) {
            setTitle(getString(R.string.page_talents));
            if (getIntent().hasExtra(TALENT_TYPE_BUNDLE_TAG)) {
                mTalentType = (TalentType) getIntent().getExtras().getSerializable(TALENT_TYPE_BUNDLE_TAG);
                setTitle(getString(mTalentType.getLabelId()));
            }

            if (getIntent().hasExtra(TALENTS_LIST_BUNDLE_TAG)) {
                talents = (List<Talent>) getIntent().getExtras().getSerializable(TALENTS_LIST_BUNDLE_TAG);
            } else {
                talents = TalentHelper.getInstance().getTalentsByType(mTalentType);
            }
        } else {
            talents = TalentHelper.getInstance().getTalents();
        }

        ArrayAdapter<Talent> adapter;
        if (getIntent().getExtras() != null
                && getIntent().hasExtra(IPlayerActivityConstants.CAN_ADD_TO_PLAYER_BUNDLE_TAG)
                && getIntent().getExtras().getBoolean(IPlayerActivityConstants.CAN_ADD_TO_PLAYER_BUNDLE_TAG)) {
            adapter = new TalentsListAdapter(getLayoutInflater(), talents, true);
        } else {
            canSearchFurther = true;
            adapter = new TalentsListAdapter(getLayoutInflater(), talents, false);
        }

        final ListView talentsListView = (ListView) findViewById(R.id.talents_list);
        talentsListView.setAdapter(adapter);
        if (talents.isEmpty()) {
            talentsListView.setVisibility(View.GONE);
            findViewById(R.id.no_talent_found).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.no_talent_found).setVisibility(View.GONE);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        WHFRP3Application.setActivity(this);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        super.finish();
    }

    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        if (!canSearchFurther) {
            menu.findItem(R.id.action_search).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.action_search) {
            openTalentSearchDialog();
        }

        return true;
    }
    //endregion

    private void openTalentSearchDialog() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TALENT_TYPE_BUNDLE_TAG, mTalentType);

        TalentSearchDialogFragment dialog = new TalentSearchDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "TalentSearchDialogFragment");
    }
}
