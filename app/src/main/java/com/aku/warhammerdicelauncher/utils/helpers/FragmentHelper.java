package com.aku.warhammerdicelauncher.utils.helpers;

import android.app.FragmentManager;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.fragments.CharacterFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.LaunchFragment;

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

    public static LaunchFragment getCurrentLaunchFragment(FragmentManager fragmentManager){
        return (LaunchFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
    }

    public static CharacterFragment replaceByCharacterFragment(FragmentManager fragmentManager) {
        CharacterFragment fragmentContent = new CharacterFragment();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentContent, FRAGMENT_TAG).commit();
        fragmentManager.executePendingTransactions();

        return fragmentContent;
    }
}
