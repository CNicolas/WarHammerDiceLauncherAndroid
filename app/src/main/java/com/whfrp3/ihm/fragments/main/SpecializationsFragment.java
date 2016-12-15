package com.whfrp3.ihm.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.SpecializationsListAdapter;
import com.whfrp3.model.Specialisation;
import com.whfrp3.tools.BindingUtils;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.List;

public class SpecializationsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_specializations, container, false);

        List<Specialisation> specialisations = SpecializationHelper.getInstance().getSpecialisations();

        ListView specializationsListView = (ListView) rootView.findViewById(R.id.specializations_list);
        specializationsListView.setAdapter(new SpecializationsListAdapter(inflater, specialisations));

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(BindingUtils.string(R.string.page_specializations));
    }
}
