package com.aku.warhammerdicelauncher.ihm.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterFragment extends Fragment {

    public CharacterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity context = ((MainActivity) getActivity());
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        FragmentTabHost tabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);

        tabHost.setup(context, context.getSupportFragmentManager(), android.R.id.tabcontent);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("fragment1").setIndicator(getString(R.string.page_characteristics));
        TabHost.TabSpec tab2 = tabHost.newTabSpec("fragment2").setIndicator(getString(R.string.page_inventory));

        tabHost.addTab(tab1, CharacteristicsFragment.class, null);
        tabHost.addTab(tab2, InventoryFragment.class, null);

        tabHost.bringChildToFront(rootView);

        return rootView;
    }

}
