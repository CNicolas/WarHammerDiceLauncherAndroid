package com.whfrp3.ihm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.TalentTypesListAdapter;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.tools.BindingUtils;

import java.util.Arrays;

public class TalentsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents, container, false);

        TalentTypesListAdapter adapter = new TalentTypesListAdapter(inflater, Arrays.asList(TalentType.values()));
        ListView talentsList = (ListView) rootView.findViewById(R.id.talents_list);
        talentsList.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(BindingUtils.string(R.string.talents));
    }
}
