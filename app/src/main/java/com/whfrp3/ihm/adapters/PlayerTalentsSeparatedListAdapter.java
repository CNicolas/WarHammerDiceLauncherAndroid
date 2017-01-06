package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListPlayerTalentBinding;
import com.whfrp3.ihm.listeners.PlayerTalentsHandlers;
import com.whfrp3.model.player.PlayerTalent;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PlayerTalentsSeparatedListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private final LayoutInflater mInflater;
    private final List<PlayerTalent> mPlayerTalents;
    private final TreeSet<Integer> sectionHeader;

    public PlayerTalentsSeparatedListAdapter(@NonNull LayoutInflater inflater, List<PlayerTalent> equippedTalents, List<PlayerTalent> unequippedTalents) {
        super();

        mInflater = inflater;

        mPlayerTalents = new ArrayList<>();
        sectionHeader = new TreeSet<>();

        addSectionHeaderItem(WHFRP3Application.getResourceString(R.string.equipped));
        mPlayerTalents.addAll(equippedTalents);
        addSectionHeaderItem(WHFRP3Application.getResourceString(R.string.unequipped));
        mPlayerTalents.addAll(unequippedTalents);
    }

    private void addSectionHeaderItem(final String headerTitle) {
        Talent talent = new Talent();
        talent.setName(headerTitle);
        mPlayerTalents.add(new PlayerTalent(talent, -1));
        sectionHeader.add(mPlayerTalents.size() - 1);
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
        return mPlayerTalents.size();
    }

    @Override
    public PlayerTalent getItem(int position) {
        return mPlayerTalents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int rowType = getItemViewType(position);

        if (rowType == TYPE_ITEM) {
            ElementListPlayerTalentBinding binding = DataBindingUtil.getBinding(convertView);

            if (binding == null) {
                binding = DataBindingUtil.inflate(mInflater, R.layout.element_list_player_talent, parent, false);
            }

            binding.setPlayerTalent(mPlayerTalents.get(position));
            binding.setHandlers(new PlayerTalentsHandlers());

            convertView = binding.getRoot();
        } else {
            convertView = mInflater.inflate(R.layout.element_separated_list_header, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.separated_list_header);
            textView.setText(mPlayerTalents.get(position).getTalent().getName());
        }

        return convertView;
    }
}
