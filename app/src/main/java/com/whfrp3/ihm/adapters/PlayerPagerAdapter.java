package com.whfrp3.ihm.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.whfrp3.R;
import com.whfrp3.ihm.fragments.player.AdventureFragment;
import com.whfrp3.ihm.fragments.player.CharacteristicsFragment;
import com.whfrp3.ihm.fragments.player.InventoryFragment;
import com.whfrp3.ihm.fragments.player.SkillsFragment;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;

/**
 * The PlayerPagerAdapter creates and manages the Fragments from the PlayerActivity.
 */
public class PlayerPagerAdapter extends FragmentPagerAdapter {

    private int mCurrentPosition;

    private CharacteristicsFragment mCharacteristicsFragment;
    private SkillsFragment mSkillsFragment;
    private InventoryFragment mInventoryFragment;
    private AdventureFragment mAdventureFragment;

    public PlayerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        mCurrentPosition = position;

        switch (position) {
            case IPlayerActivityConstants.CHARACTERISTICS_FRAGMENT_POSITION:
                if (mCharacteristicsFragment == null) {
                    mCharacteristicsFragment = new CharacteristicsFragment();
                }
                return mCharacteristicsFragment;
            case IPlayerActivityConstants.ADVENTURE_FRAGMENT_POSITION:
                if (mAdventureFragment == null) {
                    mAdventureFragment = new AdventureFragment();
                }
                return mAdventureFragment;
            case IPlayerActivityConstants.SKILLS_FRAGMENT_POSITION:
                if (mSkillsFragment == null) {
                    mSkillsFragment = new SkillsFragment();
                }
                return mSkillsFragment;
            case IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION:
                if (mInventoryFragment == null) {
                    mInventoryFragment = new InventoryFragment();
                }
                return mInventoryFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        Context context = WHFRP3Application.getAppContext();
        switch (position) {
            case IPlayerActivityConstants.CHARACTERISTICS_FRAGMENT_POSITION:
                Drawable image = ContextCompat.getDrawable(context, R.drawable.ic_person_black);
                return setTabIcon(image);
            case IPlayerActivityConstants.ADVENTURE_FRAGMENT_POSITION:
                image = ContextCompat.getDrawable(context, R.drawable.ic_explore_black);
                return setTabIcon(image);
            case IPlayerActivityConstants.SKILLS_FRAGMENT_POSITION:
                image = ContextCompat.getDrawable(context, R.drawable.ic_skills_black);
                return setTabIcon(image);
            case IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION:
                image = ContextCompat.getDrawable(context, R.drawable.ic_bag_black);
                return setTabIcon(image);
        }
        return null;
    }

    /**
     * Sets the icon in the tab.
     *
     * @param image the image.
     * @return the page title to show in the tablayout.
     */
    private CharSequence setTabIcon(Drawable image) {
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
