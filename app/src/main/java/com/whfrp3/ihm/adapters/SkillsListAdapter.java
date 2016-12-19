package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListSkillBinding;
import com.whfrp3.ihm.listeners.SkillsHandlers;
import com.whfrp3.model.skills.Skill;

import java.util.List;

/**
 * The Adapter for the skills list.
 */
public class SkillsListAdapter extends ArrayAdapter<Skill> {
    private List<Skill> mSkills;
    private final LayoutInflater inflater;

    public SkillsListAdapter(@NonNull LayoutInflater inflater, List<Skill> skills) {
        super(inflater.getContext(), R.layout.element_list_skill, skills);

        this.inflater = inflater;
        mSkills = skills;
    }

    @Override
    public int getCount() {
        return mSkills.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Skill getItem(int position) {
        return mSkills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListSkillBinding binding = DataBindingUtil.inflate(inflater, R.layout.element_list_skill, parent, false);
        binding.setSkill(mSkills.get(position));
        binding.setHandlers(new SkillsHandlers());

        return binding.getRoot();
    }
}