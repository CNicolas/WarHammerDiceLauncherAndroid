package com.whfrp3.ihm.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ItemSkillsListBinding;
import com.whfrp3.ihm.listeners.SkillHandlers;
import com.whfrp3.model.player.skill.Skill;

/**
 * The Adapter for the Skills list.
 */
public class SkillsListAdapter extends BaseAdapter {
    private ObservableArrayList<Skill> skills;
    private LayoutInflater inflater;

    public SkillsListAdapter(ObservableArrayList<Skill> l) {
        skills = l;
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public Object getItem(int position) {
        return skills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        ItemSkillsListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_skills_list, parent, false);
        binding.setSkill(skills.get(position));
        binding.setHandlers(new SkillHandlers());

        return binding.getRoot();
    }
}