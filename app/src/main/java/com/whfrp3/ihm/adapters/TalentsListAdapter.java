package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListTalentBinding;
import com.whfrp3.model.talents.Talent;

import java.util.List;

/**
 * The Adapter for the talents   list.
 */
public class TalentsListAdapter extends ArrayAdapter<Talent> {
    private final List<Talent> mTalents;
    private final LayoutInflater inflater;

    public TalentsListAdapter(@NonNull LayoutInflater inflater, List<Talent> talents) {
        super(inflater.getContext(), R.layout.element_list_talent, talents);

        this.inflater = inflater;
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
    public Talent getItem(int position) {
        return mTalents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListTalentBinding binding = DataBindingUtil.getBinding(convertView);

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.element_list_talent, parent, false);
        }

        binding.setTalent(mTalents.get(position));

        return binding.getRoot();
    }
}