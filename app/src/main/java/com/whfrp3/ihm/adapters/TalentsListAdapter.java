package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListTalentBinding;
import com.whfrp3.ihm.listeners.TalentsHandlers;
import com.whfrp3.model.talents.Talent;

import java.util.List;

/**
 * The Adapter for the talents   list.
 */
public class TalentsListAdapter extends ArrayAdapter<Talent> {
    private final List<Talent> mTalents;
    private final LayoutInflater mInflater;
    private final boolean mShowOptions;

    public TalentsListAdapter(@NonNull LayoutInflater inflater, List<Talent> talents, boolean showOptions) {
        super(inflater.getContext(), R.layout.element_list_talent, talents);

        mInflater = inflater;
        mTalents = talents;
        mShowOptions = showOptions;
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
            binding = DataBindingUtil.inflate(mInflater, R.layout.element_list_talent, parent, false);
        }

        binding.setTalent(mTalents.get(position));
        binding.setHandlers(new TalentsHandlers());
        binding.setCanAddToPlayer(false);
        binding.setShowOptions(mShowOptions);

        return binding.getRoot();
    }
}