package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentSkillsBinding;
import com.whfrp3.tools.WHFRP3Application;

/**
 * The SkillFragment.
 */
public class SkillsFragment extends Fragment {
    private FragmentSkillsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_skills, container, false);
        mBinding.setPlayer(WHFRP3Application.getPlayer());

        long difference = System.currentTimeMillis() - startTime;
        Log.d("SkillsFragment", String.format("%d = %d", startTime, difference));
        return mBinding.getRoot();
    }
}
