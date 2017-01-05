package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListPlayerTalentBinding;
import com.whfrp3.ihm.listeners.PlayerTalentsHandlers;
import com.whfrp3.model.player.PlayerTalent;

import java.util.List;

/**
 * The Adapter for the talents   list.
 */
public class PlayerTalentsListAdapter extends ArrayAdapter<PlayerTalent> {
    private final List<PlayerTalent> mTalents;
    private final LayoutInflater mInflater;

    public PlayerTalentsListAdapter(@NonNull LayoutInflater inflater, List<PlayerTalent> talents) {
        super(inflater.getContext(), R.layout.element_list_player_talent, talents);

        mInflater = inflater;
        mTalents = talents;
    }

    @Override
    public int getCount() {
        return mTalents.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public PlayerTalent getItem(int position) {
        return mTalents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListPlayerTalentBinding binding = DataBindingUtil.getBinding(convertView);

        if (binding == null) {
            binding = DataBindingUtil.inflate(mInflater, R.layout.element_list_player_talent, parent, false);
        }

        binding.setPlayerTalent(mTalents.get(position));
        binding.setHandlers(new PlayerTalentsHandlers());

        return binding.getRoot();
    }
}