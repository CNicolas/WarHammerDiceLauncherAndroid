package com.whfrp3.ihm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.TalentsActivity;
import com.whfrp3.ihm.adapters.TalentTypesListAdapter;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.tools.BindingUtils;
import com.whfrp3.tools.constants.ITalentsConstants;

import java.util.ArrayList;
import java.util.List;

public class TalentsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<TalentType> mTalentTypesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents, container, false);

        mTalentTypesList = new ArrayList<>();
        mTalentTypesList.add(TalentType.AFFINITY);
        mTalentTypesList.add(TalentType.REPUTATION);
        mTalentTypesList.add(TalentType.TACTICS);
        mTalentTypesList.add(TalentType.TOUR);

        TalentTypesListAdapter adapter = new TalentTypesListAdapter(inflater, mTalentTypesList);
        ListView talentsList = (ListView) rootView.findViewById(R.id.talents_list);
        talentsList.setAdapter(adapter);
        talentsList.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(BindingUtils.string(R.string.page_talents));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITalentsConstants.TALENT_TYPE_BUNDLE_TAG, mTalentTypesList.get(position));

        Intent talentsIntent = new Intent(getActivity(), TalentsActivity.class);
        talentsIntent.putExtras(bundle);

        getActivity().startActivityForResult(talentsIntent, ITalentsConstants.TALENTS_REQUEST);
    }
}
