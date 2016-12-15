package com.whfrp3.ihm.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.SkillsListAdapter;
import com.whfrp3.model.Skill;
import com.whfrp3.tools.BindingUtils;
import com.whfrp3.tools.helpers.SkillHelper;

import java.util.List;

public class SkillsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);

        final List<Skill> skills = SkillHelper.getInstance().getSkills();

        ListView skillsListView = (ListView) rootView.findViewById(R.id.skills_list);
        skillsListView.setAdapter(new SkillsListAdapter(inflater, skills));

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(BindingUtils.string(R.string.page_skills));
    }
}
