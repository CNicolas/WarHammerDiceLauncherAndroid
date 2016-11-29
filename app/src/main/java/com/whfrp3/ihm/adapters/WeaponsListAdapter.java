package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.WeaponsListElementBinding;
import com.whfrp3.model.player.inventory.Weapon;

import java.util.Arrays;
import java.util.List;


public class WeaponsListAdapter extends ArrayAdapter {
    private final List<Weapon> values;
    private final LayoutInflater inflater;

    public WeaponsListAdapter(@NonNull LayoutInflater inflater, List<Weapon> values) {
        super(inflater.getContext(), R.layout.weapons_list_element, Arrays.asList(values));

        this.inflater = inflater;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        WeaponsListElementBinding binding = DataBindingUtil.inflate(inflater, R.layout.weapons_list_element, null, false);
        binding.setWeapon(values.get(position));

        return binding.getRoot();
    }
}
