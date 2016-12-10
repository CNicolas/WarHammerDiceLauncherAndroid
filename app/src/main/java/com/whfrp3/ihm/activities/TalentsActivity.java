package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.TalentsListAdapter;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.player.Talent;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.ITalentsConstants;
import com.whfrp3.tools.helpers.TalentHelper;

import java.util.List;


public class TalentsActivity extends AppCompatActivity {
    private List<Talent> mTalents;
    private TalentType mTalentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        if (getIntent().getExtras() != null) {
            mTalentType = (TalentType) getIntent().getExtras().getSerializable(ITalentsConstants.TALENT_TYPE_BUNDLE_TAG);
            mTalents = TalentHelper.getInstance().getTalentsByType(mTalentType);
        } else {
            mTalentType = null;
            mTalents = TalentHelper.getInstance().getAllTalents();
        }

        TalentsListAdapter adapter = new TalentsListAdapter(getLayoutInflater(), mTalents);
        ListView talentsList = (ListView) findViewById(R.id.talents_list);
        talentsList.setAdapter(adapter);

        if (mTalents.isEmpty()) {
            talentsList.setVisibility(View.GONE);
            findViewById(R.id.no_talent_found).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.no_talent_found).setVisibility(View.GONE);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(mTalentType.getLabelId()));

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }
    //endregion
}
