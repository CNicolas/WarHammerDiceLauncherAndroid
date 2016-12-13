package com.whfrp3.ihm.fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.TalentsActivity;
import com.whfrp3.ihm.adapters.TalentTypesListAdapter;
import com.whfrp3.ihm.fragments.dialog.TalentSearchDialogFragment;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.tools.BindingUtils;
import com.whfrp3.tools.constants.IMainConstants;

import java.util.ArrayList;
import java.util.List;

public class TalentTypesFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<TalentType> mTalentTypesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents_type, container, false);

        mTalentTypesList = new ArrayList<>();
        mTalentTypesList.add(TalentType.AFFINITY);
        mTalentTypesList.add(TalentType.REPUTATION);
        mTalentTypesList.add(TalentType.TACTICS);
        mTalentTypesList.add(TalentType.TOUR);

        TalentTypesListAdapter adapter = new TalentTypesListAdapter(inflater, mTalentTypesList);
        ListView talentsList = (ListView) rootView.findViewById(R.id.talents_list);
        talentsList.setAdapter(adapter);
        talentsList.setOnItemClickListener(this);

        FloatingActionButton searchButton = (FloatingActionButton) rootView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentSearchDialogFragment dialog = new TalentSearchDialogFragment();
                dialog.show(getActivity().getSupportFragmentManager(), "TalentSearchDialogFragment");
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.talents, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_search_talent) {
            openTalentSearchDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(BindingUtils.string(R.string.page_talents));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMainConstants.TALENT_TYPE_BUNDLE_TAG, mTalentTypesList.get(position));

        Intent talentsIntent = new Intent(getActivity(), TalentsActivity.class);
        talentsIntent.putExtras(bundle);

        getActivity().startActivityForResult(talentsIntent, IMainConstants.TALENTS_REQUEST);
    }

    private void openTalentSearchDialog() {
        TalentSearchDialogFragment dialog = new TalentSearchDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "TalentSearchDialogFragment");
    }

    private void openTalentSearchDialog() {
        TalentSearchDialogFragment dialog = new TalentSearchDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "TalentSearchDialogFragment");
    }
}
