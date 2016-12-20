package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementSpinnerSkillsBinding;
import com.whfrp3.model.skills.Skill;

import java.util.List;

/**
 * The Adapter for the Talent Types list.
 */
public class SkillsSpinnerAdapter extends ArrayAdapter<Skill> {
    private final List<Skill> mSkills;
    private final LayoutInflater inflater;

    public SkillsSpinnerAdapter(@NonNull LayoutInflater inflater, List<Skill> skills) {
        super(inflater.getContext(), R.layout.element_spinner_skills, skills);

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

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementSpinnerSkillsBinding binding = DataBindingUtil.getBinding(convertView);

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.element_spinner_skills, null, false);
        }

        binding.setIsNull(false);
        if (mSkills.get(position) == null) {
            binding.setIsNull(true);
        } else {
            binding.setSkill(mSkills.get(position));
        }

        return binding.getRoot();
    }
}
