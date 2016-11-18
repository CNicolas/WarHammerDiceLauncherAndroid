package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.SpinLayoutBinding;

import java.util.Arrays;

/**
 * Adapter used to display enum's label in a spinner.
 */
public class EnumSpinnerAdapter extends ArrayAdapter {

    /**
     * Inflater.
     */
    private LayoutInflater inflater;

    /**
     * Values displayed in the spinner.
     */
    private IEnumSpinner[] values;


    public EnumSpinnerAdapter(LayoutInflater inflater, IEnumSpinner[] values) {
        super(inflater.getContext(), R.layout.spin_layout, Arrays.asList(values));

        this.inflater = inflater;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return values[position].getLabelId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.spin_layout, null, false);
        binding.setSpin(values[position]);

        return binding.getRoot();
    }
}
