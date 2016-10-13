package com.aku.warhammerdicelauncher.utils.helpers;

import android.app.Fragment;
import android.app.FragmentManager;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.LaunchFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.PlayerFragment;

/**
 * Created by cnicolas on 21/09/2016.
 */

public abstract class FragmentHelper {

    private static String FRAGMENT_TAG = "fragmentContent";

    public static LaunchFragment replaceByLaunchFragment(FragmentManager fragmentManager) {
        LaunchFragment fragmentContent = new LaunchFragment();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentContent, FRAGMENT_TAG).commit();
        fragmentManager.executePendingTransactions();

        return fragmentContent;
    }

    public static PlayerFragment replaceByPlayerFragment(FragmentManager fragmentManager) {
        PlayerFragment fragmentContent = new PlayerFragment();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentContent, FRAGMENT_TAG).commit();
        fragmentManager.executePendingTransactions();

        return fragmentContent;
    }

    public static LaunchFragment getCurrentLaunchFragment(FragmentManager fragmentManager){
        return (LaunchFragment) getCurrentFragment(fragmentManager);
    }

    public static PlayerFragment getCurrentPlayerFragment(FragmentManager fragmentManager) {
        return (PlayerFragment) getCurrentFragment(fragmentManager);
    }

    private static Fragment getCurrentFragment(FragmentManager fragmentManager) {
        return fragmentManager.findFragmentByTag(FRAGMENT_TAG);
    }
}
