package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.WeaponsListElementBinding;
import com.whfrp3.ihm.listeners.AdventureActivityHandlers;
import com.whfrp3.model.player.inventory.Weapon;

import java.util.List;

/**
 * The Adapter for the Weapons list.
 */
public class WeaponsListAdapter extends ArrayAdapter {
    private final List<Weapon> weapons;
    private final LayoutInflater inflater;

    public WeaponsListAdapter(@NonNull LayoutInflater inflater, List<Weapon> weapons) {
        super(inflater.getContext(), R.layout.weapons_list_element, weapons);

        this.inflater = inflater;
        this.weapons = weapons;
    }

    @Override
    public int getCount() {
        return weapons.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return weapons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return weapons.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        WeaponsListElementBinding binding = DataBindingUtil.inflate(inflater, R.layout.weapons_list_element, null, false);
        binding.setWeapon(weapons.get(position));
        binding.setHandlers(new AdventureActivityHandlers());

        return binding.getRoot();
    }
}
