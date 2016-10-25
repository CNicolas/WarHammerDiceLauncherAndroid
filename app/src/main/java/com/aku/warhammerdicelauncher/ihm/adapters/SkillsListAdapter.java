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
import com.aku.warhammerdicelauncher.model.player.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 24/10/2016.
 */

public class SkillsListAdapter extends ArrayAdapter<Skill> {

    private final Context mContext;
    private final int mItemLayoutId;
    private final List<Skill> mSkills;
    private final List<SkillHolder> mSkillHolders;

    public SkillsListAdapter(Context context, int itemLayoutId, List<Skill> skills) {
        super(context, itemLayoutId, skills);
        mContext = context;
        mItemLayoutId = itemLayoutId;
        mSkills = skills;
        mSkillHolders = new ArrayList<>();
    }

    @Override
    public View getView(int position, View row, @NonNull ViewGroup parent) {
        SkillHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mItemLayoutId, parent, false);

            holder = new SkillHolder();
            holder.skillName = (TextView) row.findViewById(R.id.item_skills_list_name);
            holder.skillLevel1 = (CheckBox) row.findViewById(R.id.item_skills_list_level1);
            holder.skillLevel2 = (CheckBox) row.findViewById(R.id.item_skills_list_level2);
            holder.skillLevel3 = (CheckBox) row.findViewById(R.id.item_skills_list_level3);

            row.setTag(holder);
        } else {
            holder = (SkillHolder) row.getTag();
        }

        Skill skill = mSkills.get(position);
        holder.skillName.setText(skill.getName());
        holder.position = position;

        preSelectLevelOfSkill(skill.getLevel(), holder);

        holder.skillLevel1.setOnClickListener(new SkillLevelClickListener(skill, holder));
        holder.skillLevel2.setOnClickListener(new SkillLevelClickListener(skill, holder));
        holder.skillLevel3.setOnClickListener(new SkillLevelClickListener(skill, holder));

        mSkillHolders.add(holder);

        return row;
    }

    private void preSelectLevelOfSkill(int level, SkillHolder holder) {
        switch (level) {
            case 1:
                holder.skillLevel1.setChecked(true);
                holder.skillLevel2.setChecked(false);
                holder.skillLevel3.setChecked(false);
                break;
            case 2:
                holder.skillLevel1.setChecked(true);
                holder.skillLevel2.setChecked(true);
                holder.skillLevel3.setChecked(false);
                break;
            case 3:
                holder.skillLevel1.setChecked(true);
                holder.skillLevel2.setChecked(true);
                holder.skillLevel3.setChecked(true);
                break;
            default:
                holder.skillLevel1.setChecked(false);
                holder.skillLevel2.setChecked(false);
                holder.skillLevel3.setChecked(false);
                break;
        }
    }

    public static class SkillHolder {
        public int position;
        public TextView skillName;
        public CheckBox skillLevel1;
        public CheckBox skillLevel2;
        public CheckBox skillLevel3;
    }
}
