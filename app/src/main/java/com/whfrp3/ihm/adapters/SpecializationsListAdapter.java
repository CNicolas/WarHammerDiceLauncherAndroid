package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListSpecializationBinding;
import com.whfrp3.model.skills.Specialization;

import java.util.List;

/**
 * The Adapter for the specializations list.
 */
public class SpecializationsListAdapter extends ArrayAdapter<Specialization> {
    private List<Specialization> mSpecializations;
    private final LayoutInflater inflater;

    public SpecializationsListAdapter(@NonNull LayoutInflater inflater, List<Specialization> specializations) {
        super(inflater.getContext(), R.layout.element_list_specialization, specializations);

        this.inflater = inflater;
        mSpecializations = specializations;
    }

    @Override
    public int getCount() {
        return mSpecializations.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Specialization getItem(int position) {
        return mSpecializations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListSpecializationBinding binding = DataBindingUtil.inflate(inflater, R.layout.element_list_specialization, parent, false);
        binding.setSpecialization(mSpecializations.get(position));

        return binding.getRoot();
    }
}