package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListSpecializationBinding;
import com.whfrp3.model.Specialisation;

import java.util.List;

/**
 * The Adapter for the talents list.
 */
public class SpecializationsListAdapter extends ArrayAdapter {
    private List<Specialisation> mSpecialisations;
    private final LayoutInflater inflater;

    public SpecializationsListAdapter(@NonNull LayoutInflater inflater, List<Specialisation> specialisations) {
        super(inflater.getContext(), R.layout.element_list_specialization, specialisations);

        this.inflater = inflater;
        mSpecialisations = specialisations;
    }

    @Override
    public int getCount() {
        return mSpecialisations.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return mSpecialisations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListSpecializationBinding binding = DataBindingUtil.inflate(inflater, R.layout.element_list_specialization, parent, false);
        binding.setSpecialisation(mSpecialisations.get(position));

        return binding.getRoot();
    }
}