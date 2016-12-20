package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListSkillBinding;
import com.whfrp3.ihm.listeners.SkillsHandlers;
import com.whfrp3.model.skills.Skill;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SkillsSeparatedListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private final LayoutInflater mInflater;
    private List<Skill> mSkills;
    private TreeSet<Integer> sectionHeader;

    public SkillsSeparatedListAdapter(@NonNull LayoutInflater inflater, List<Skill> basicSkills, List<Skill> advancedSkills) {
        super();

        mInflater = inflater;

        mSkills = new ArrayList<>();
        sectionHeader = new TreeSet<>();

        addSectionHeaderItem(WHFRP3Application.getResourceString(R.string.basic));
        mSkills.addAll(basicSkills);
        addSectionHeaderItem(WHFRP3Application.getResourceString(R.string.advanced));
        mSkills.addAll(advancedSkills);
    }

    private void addSectionHeaderItem(final String item) {
        mSkills.add(new Skill(0, item, null, null));
        sectionHeader.add(mSkills.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mSkills.size();
    }

    @Override
    public Skill getItem(int position) {
        return mSkills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int rowType = getItemViewType(position);

        if (rowType == TYPE_ITEM) {
            ElementListSkillBinding binding = DataBindingUtil.getBinding(convertView);

            if (binding == null) {
                binding = DataBindingUtil.inflate(mInflater, R.layout.element_list_skill, parent, false);
            }

            binding.setSkill(mSkills.get(position));
            binding.setHandlers(new SkillsHandlers());

            convertView = binding.getRoot();
        } else {
            convertView = mInflater.inflate(R.layout.element_list_skill_separator, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.skill_type_header);
            textView.setText(mSkills.get(position).getName());
        }

        return convertView;
    }
}
