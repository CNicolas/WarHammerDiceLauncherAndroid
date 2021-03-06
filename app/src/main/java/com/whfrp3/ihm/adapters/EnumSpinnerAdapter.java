package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementSpinnerEnumBinding;

import java.util.Arrays;

/**
 * Adapter used to display enum's label in a spinner.
 */
public class EnumSpinnerAdapter extends ArrayAdapter<IEnumSpinner> {

    /**
     * Inflater.
     */
    private final LayoutInflater mInflater;

    /**
     * Values displayed in the spinner.
     */
    private final IEnumSpinner[] mValues;


    public EnumSpinnerAdapter(LayoutInflater inflater, IEnumSpinner[] values) {
        super(inflater.getContext(), R.layout.element_spinner_enum, Arrays.asList(values));

        mInflater = inflater;
        mValues = values;
    }

    @Override
    public int getCount() {
        return mValues.length;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public IEnumSpinner getItem(int position) {
        return mValues[position];
    }

    @Override
    public long getItemId(int position) {
        if (mValues[position] != null) {
            return mValues[position].getLabelId();
        } else {
            return R.string.empty_string;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementSpinnerEnumBinding binding = DataBindingUtil.getBinding(convertView);

        if (binding == null) {
            binding = DataBindingUtil.inflate(mInflater, R.layout.element_spinner_enum, null, false);
        }

        binding.setSpin(mValues[position]);

        return binding.getRoot();
    }
}
