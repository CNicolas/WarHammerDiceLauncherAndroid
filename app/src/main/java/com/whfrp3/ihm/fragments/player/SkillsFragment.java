package com.whfrp3.ihm.fragments.player;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_skills, container, false);
        mBinding.setPlayer(WHFRP3Application.getPlayer());
        
        return mBinding.getRoot();
    }
}
