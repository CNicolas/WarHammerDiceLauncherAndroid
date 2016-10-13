package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aku.warhammerdicelauncher.R;

/**
 * Created by cnicolas on 23/09/2016.
 */

public class SkillsFragment extends Fragment {

    private ListView skillsList;

    public SkillsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);

//        skillsList = (ListView) rootView.findViewById(R.id.skills_list);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.pages, R.layout.item_skills_list);
//        skillsList.setAdapter(adapter);

        return rootView;
    }
}
