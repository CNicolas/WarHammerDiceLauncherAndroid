package com.aku.warhammerdicelauncher.ihm.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.tools.SkillLevelClickListener;
import com.aku.warhammerdicelauncher.model.player.skill.Skill;
import com.aku.warhammerdicelauncher.model.player.skill.SkillHolder;

import java.util.List;

/**
 * Created by cnicolas on 24/10/2016.
 */

public class SkillsListAdapter extends ArrayAdapter<Skill> {

    private final Context mContext;
    private final int mItemLayoutId;
    private final List<Skill> mSkills;

    public SkillsListAdapter(Context context, int itemLayoutId, List<Skill> skills) {
        super(context, itemLayoutId, skills);
        mContext = context;
        mItemLayoutId = itemLayoutId;
        mSkills = skills;
    }

    @Override
    public View getView(int position, View row, @NonNull ViewGroup parent) {
        SkillHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mItemLayoutId, parent, false);

            holder = new SkillHolder();
            holder.setSkillName((TextView) row.findViewById(R.id.item_skills_list_name));
            holder.setCheckbox1((CheckBox) row.findViewById(R.id.item_skills_list_level1));
            holder.setCheckbox2((CheckBox) row.findViewById(R.id.item_skills_list_level2));
            holder.setCheckbox3((CheckBox) row.findViewById(R.id.item_skills_list_level3));

            row.setTag(holder);
        } else {
            holder = (SkillHolder) row.getTag();
        }

        Skill skill = mSkills.get(position);
        holder.getSkillName().setText(skill.getName());
        holder.setPosition(position);

        preSelectLevelOfSkill(skill.getLevel(), holder);

        holder.getCheckbox1().setOnClickListener(new SkillLevelClickListener(skill, holder));
        holder.getCheckbox2().setOnClickListener(new SkillLevelClickListener(skill, holder));
        holder.getCheckbox3().setOnClickListener(new SkillLevelClickListener(skill, holder));

        return row;
    }

    private void preSelectLevelOfSkill(int level, SkillHolder holder) {
        switch (level) {
            case 1:
                holder.getCheckbox1().setChecked(true);
                holder.getCheckbox2().setChecked(false);
                holder.getCheckbox3().setChecked(false);
                break;
            case 2:
                holder.getCheckbox1().setChecked(true);
                holder.getCheckbox2().setChecked(true);
                holder.getCheckbox3().setChecked(false);
                break;
            case 3:
                holder.getCheckbox1().setChecked(true);
                holder.getCheckbox2().setChecked(true);
                holder.getCheckbox3().setChecked(true);
                break;
            default:
                holder.getCheckbox1().setChecked(false);
                holder.getCheckbox2().setChecked(false);
                holder.getCheckbox3().setChecked(false);
                break;
        }
    }
}
