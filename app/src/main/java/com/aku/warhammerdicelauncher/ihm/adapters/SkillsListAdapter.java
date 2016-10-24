package com.aku.warhammerdicelauncher.ihm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
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
    public View getView(int position, View row, ViewGroup parent) {
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

        mSkillHolders.add(holder);

        return row;
    }

    public Skill getSkillByCheckboxId(int checkboxId) {
        for (SkillHolder holder : mSkillHolders) {
            if (holder.skillLevel1.getId() == checkboxId
                    || holder.skillLevel2.getId() == checkboxId
                    || holder.skillLevel3.getId() == checkboxId) {
                return mSkills.get(holder.position);
            }
        }
        throw new Resources.NotFoundException();
    }

    private static class SkillHolder {
        int position;
        TextView skillName;
        CheckBox skillLevel1;
        CheckBox skillLevel2;
        CheckBox skillLevel3;
    }
}
