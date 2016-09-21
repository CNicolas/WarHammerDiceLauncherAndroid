package com.aku.warhammerdicelauncher.utils.helpers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.LaunchFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.StatisticsFragment;
import com.aku.warhammerdicelauncher.model.dto.HandDto;

/**
 * Created by cnicolas on 21/09/2016.
 */

public abstract class FragmentHelper {

    private static String FRAGMENT_TAG = "fragmentContent";

    public static Fragment replaceByLaunchFragment(FragmentManager fragmentManager) {
        Fragment fragmentContent = new LaunchFragment();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentContent, FRAGMENT_TAG).commit();
        fragmentManager.executePendingTransactions();

        return fragmentContent;
    }

    public static Fragment replaceByStatisticsFragment(HandDto dto, int times, FragmentManager fragmentManager) {
        Fragment fragmentContent = new StatisticsFragment();

        Bundle args = new Bundle();
        args.putSerializable(StatisticsFragment.HAND_TAG, dto);
        args.putInt(StatisticsFragment.TIMES_TAG, times);
        fragmentContent.setArguments(args);

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentContent, FRAGMENT_TAG).commit();

        return fragmentContent;
    }

    public static LaunchFragment getCurrentLaunchFragment(FragmentManager fragmentManager){
        return (LaunchFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
    }
}
