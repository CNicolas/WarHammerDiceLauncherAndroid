package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.SkillsListElementBinding;
import com.whfrp3.ihm.listeners.SkillHandlers;
import com.whfrp3.model.player.Skill;
import com.whfrp3.tools.WHFRP3Application;

import java.util.List;

/**
 * The Adapter for the Skills list.
 */
public class SkillsListAdapter extends ArrayAdapter {
    private final List<Skill> skills;
    private final LayoutInflater inflater;

    public SkillsListAdapter(@NonNull LayoutInflater inflater, List<Skill> skills) {
        super(inflater.getContext(), R.layout.weapons_list_element, skills);

        this.inflater = inflater;
        this.skills = skills;
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return skills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        SkillsListElementBinding binding = DataBindingUtil.inflate(inflater, R.layout.skills_list_element, parent, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setSkill(skills.get(position));
        binding.setHandlers(new SkillHandlers());

        return binding.getRoot();
    }
}