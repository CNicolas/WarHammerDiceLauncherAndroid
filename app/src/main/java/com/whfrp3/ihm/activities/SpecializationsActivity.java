package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.SpecializationsListAdapter;
import com.whfrp3.ihm.fragments.dialog.SpecializationSearchDialogFragment;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.skills.Skill;
import com.whfrp3.model.skills.Specialization;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IMainConstants;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.List;


public class SpecializationsActivity extends AppCompatActivity implements IMainConstants {
    private Characteristic mCharacteristic;
    private Skill mSkill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specializations);

        List<Specialization> specializations;
        if (getIntent().getExtras() != null) {
            setTitle(getString(R.string.page_specializations));
            if (getIntent().hasExtra(CHARACTERISTIC_BUNDLE_TAG)) {
                mCharacteristic = (Characteristic) getIntent().getExtras().getSerializable(CHARACTERISTIC_BUNDLE_TAG);
            }
            if (getIntent().hasExtra(SKILL_BUNDLE_TAG)) {
                mSkill = (Skill) getIntent().getExtras().getSerializable(SKILL_BUNDLE_TAG);
            }

            if (getIntent().hasExtra(SPECIALIZATIONS_LIST_BUNDLE_TAG)) {
                specializations = (List<Specialization>) getIntent().getExtras().getSerializable(SPECIALIZATIONS_LIST_BUNDLE_TAG);
            } else {
                specializations = SpecializationHelper.getInstance().getSpecializations();
            }
        } else {
            specializations = SpecializationHelper.getInstance().getSpecializations();
        }

        SpecializationsListAdapter adapter = new SpecializationsListAdapter(getLayoutInflater(), specializations);
        final ListView specializationsListView = (ListView) findViewById(R.id.specializations_list);
        specializationsListView.setAdapter(adapter);
        if (specializations.isEmpty()) {
            specializationsListView.setVisibility(View.GONE);
            findViewById(R.id.no_specialization_found).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.no_specialization_found).setVisibility(View.GONE);
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

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.action_search) {
            openSpecializationSearchDialog();
        }

        return true;
    }
    //endregion

    private void openSpecializationSearchDialog() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CHARACTERISTIC_BUNDLE_TAG, mCharacteristic);
        bundle.putSerializable(SKILL_BUNDLE_TAG, mSkill);

        SpecializationSearchDialogFragment dialog = new SpecializationSearchDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "SpecializationSearchDialogFragment");
    }
}
