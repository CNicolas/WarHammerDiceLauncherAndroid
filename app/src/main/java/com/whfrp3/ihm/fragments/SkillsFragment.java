package com.whfrp3.ihm.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentSkillsBinding;
import com.whfrp3.model.player.skill.SkillsList;
import com.whfrp3.tools.BindingContext;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.constants.IPlayerConstants;
import com.whfrp3.tools.helpers.OnPlayerUpdateListener;

/**
 * The SkillFragment.
 */
public class SkillsFragment extends Fragment implements OnPlayerUpdateListener {
    private BindingContext mBindingContext;
    private FragmentSkillsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBindingContext = (BindingContext) bundle.getSerializable(IPlayerConstants.BINDING_CONTEXT_KEY);
        } else {
            mBindingContext = new BindingContext(false);
        }

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_skills, container, false);
        mBinding.setSkills(new SkillsList(PlayerContext.getPlayerInstance().getSkills()));

        PlayerContext.registerPlayerUpdateListener(this);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("SkillsFragment", String.format("%d = %d", startTime, difference));
        return mBinding.getRoot();
    }

    @Override
    public void onPlayerUpdate() {
        Context context = getActivity();
        if (context != null) {
            mBinding.setSkills(new SkillsList(PlayerContext.getPlayerInstance().getSkills()));

            Log.d("SKILL", PlayerContext.getPlayerInstance().toString());
        }
    }
}
