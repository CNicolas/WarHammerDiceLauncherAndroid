package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.whfrp3.R;
import com.whfrp3.databinding.ElementListTalentTypeBinding;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.notification.ToastType;
import com.whfrp3.tools.BindingUtils;

import java.util.List;

/**
 * The Adapter for the Talent Types list.
 */
public class TalentTypesListAdapter extends ArrayAdapter {
    private final List<TalentType> mTalentTypes;
    private final LayoutInflater inflater;

    public TalentTypesListAdapter(@NonNull LayoutInflater inflater, List<TalentType> talentTypes) {
        super(inflater.getContext(), R.layout.element_list_talent_type, talentTypes);

        this.inflater = inflater;
        mTalentTypes = talentTypes;
    }

    @Override
    public int getCount() {
        return mTalentTypes.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return mTalentTypes.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ElementListTalentTypeBinding binding = DataBindingUtil.inflate(inflater, R.layout.element_list_talent_type, null, false);
        binding.setTalentType(mTalentTypes.get(position));
        binding.setViewModel(this);

        return binding.getRoot();
    }

    public void startTalentActivity(TalentType talentType) {
        new ToastNotification(BindingUtils.string(talentType.getLabelId()), ToastType.INFO).show();
    }
}
