package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListWeaponBinding;
import com.whfrp3.ihm.listeners.AdventureActivityHandlers;
import com.whfrp3.model.item.Weapon;

import java.util.List;

/**
 * The Adapter for the Weapons list.
 */
public class WeaponsListAdapter extends ArrayAdapter<Weapon> {
    private final List<Weapon> weapons;
    private final LayoutInflater inflater;

    public WeaponsListAdapter(@NonNull LayoutInflater inflater, List<Weapon> weapons) {
        super(inflater.getContext(), R.layout.element_list_weapon, weapons);

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
    public Weapon getItem(int position) {
        return weapons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return weapons.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListWeaponBinding binding = DataBindingUtil.getBinding(convertView);

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.element_list_weapon, null, false);
        }

        binding.setWeapon(weapons.get(position));
        binding.setHandlers(new AdventureActivityHandlers());

        return binding.getRoot();
    }
}
