package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListPlayerSkillBinding;
import com.whfrp3.ihm.listeners.PlayerSkillHandlers;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.tools.WHFRP3Application;

import java.util.List;

/**
 * The Adapter for the Skills list.
 */
public class PlayerSkillsListAdapter extends ArrayAdapter {
    private final List<PlayerSkill> playerSkills;
    private final LayoutInflater inflater;

    public PlayerSkillsListAdapter(@NonNull LayoutInflater inflater, List<PlayerSkill> playerSkills) {
        super(inflater.getContext(), R.layout.element_list_player_skill, playerSkills);

        this.inflater = inflater;
        this.playerSkills = playerSkills;
    }

    @Override
    public int getCount() {
        return playerSkills.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return playerSkills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListPlayerSkillBinding binding = DataBindingUtil.inflate(inflater, R.layout.element_list_player_skill, parent, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setPlayerSkill(playerSkills.get(position));
        binding.setHandlers(new PlayerSkillHandlers());

        return binding.getRoot();
    }
}