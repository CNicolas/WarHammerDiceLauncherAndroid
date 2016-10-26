package com.whfrp3.ihm.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.ihm.listeners.SkillLevelClickListener;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.model.player.skill.SkillHolder;

import java.util.List;

/**
 * The Adapter for the Skills list.
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

            holder = createNewSkillHolderFromRow(row);

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

    /**
     * Select the checkbox according to the level of the skill.
     *
     * @param level  the level of the skill.
     * @param holder the holder.
     */
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

    /**
     * Initialize a SkillHolder from the creating list item.
     *
     * @param row the list item.
     * @return the SkillHolder.
     */
    private SkillHolder createNewSkillHolderFromRow(View row) {
        SkillHolder holder = new SkillHolder();
        holder.setSkillName((TextView) row.findViewById(R.id.item_skills_list_name));
        holder.setCheckbox1((CheckBox) row.findViewById(R.id.item_skills_list_level1));
        holder.setCheckbox2((CheckBox) row.findViewById(R.id.item_skills_list_level2));
        holder.setCheckbox3((CheckBox) row.findViewById(R.id.item_skills_list_level3));

        return holder;
    }
}
