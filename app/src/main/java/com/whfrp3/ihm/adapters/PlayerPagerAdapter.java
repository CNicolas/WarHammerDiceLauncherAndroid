package com.whfrp3.ihm.adapters;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.PlayerActivity;
import com.whfrp3.ihm.fragments.AdventureFragment;
import com.whfrp3.ihm.fragments.CharacteristicsFragment;
import com.whfrp3.ihm.fragments.InventoryFragment;
import com.whfrp3.ihm.fragments.SkillsFragment;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.constants.IPlayerConstants;

/**
 * The PlayerPagerAdapter creates and manages the Fragments from the PlayerActivity.
 */
public class PlayerPagerAdapter extends FragmentPagerAdapter {

    private final PlayerActivity mContext;

    private String mCharacteristicFragmentTag;
    private String mSkillsFragmentTag;
    private String mInventoryFragmentTag;
    private String mAdventureFragmentTag;

    private CharacteristicsFragment mCharacteristicsFragment;
    private SkillsFragment mSkillsFragment;
    private InventoryFragment mInventoryFragment;
    private AdventureFragment mAdventureFragment;


    public PlayerPagerAdapter(PlayerActivity ctx) {
        super(ctx.getSupportFragmentManager());
        mContext = ctx;
    }

    /**
     * Get the fragment tag for the fragment at the given index.
     *
     * @param index the index of the fragment in the tabbedActivity.
     * @return the fragment tag.
     */
    private static String makeFragmentTag(int index) {
        return "android:switcher:" + R.id.player_pager_container + ":" + index;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IPlayerConstants.IS_IN_EDITION_KEY, PlayerContext.isInEdition());

        switch (position) {
            case 0:
                mCharacteristicFragmentTag = makeFragmentTag(position);
                if (mCharacteristicsFragment == null) {
                    mCharacteristicsFragment = new CharacteristicsFragment();
                    mCharacteristicsFragment.setArguments(bundle);
                }
                return mCharacteristicsFragment;
            case 1:
                mSkillsFragmentTag = makeFragmentTag(position);
                if (mSkillsFragment == null) {
                    mSkillsFragment = new SkillsFragment();
                }
                return mSkillsFragment;
            case 2:
                mInventoryFragmentTag = makeFragmentTag(position);
                if (mInventoryFragment == null) {
                    mInventoryFragment = new InventoryFragment();
                }
                return mInventoryFragment;
            case 3:
                mAdventureFragmentTag = makeFragmentTag(position);
                if (mAdventureFragment == null) {
                    mAdventureFragment = new AdventureFragment();
                }
                return mAdventureFragment;
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
        switch (position) {
            case 0:
                Drawable image = ContextCompat.getDrawable(mContext, R.drawable.ic_characteristics_black);
                return setTabIcon(image);
            case 1:
                image = ContextCompat.getDrawable(mContext, R.drawable.ic_skills_black);
                return setTabIcon(image);
            case 2:
                image = ContextCompat.getDrawable(mContext, R.drawable.ic_rucksack_black);
                return setTabIcon(image);
            case 3:
                image = ContextCompat.getDrawable(mContext, R.drawable.ic_adventure_black);
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

    /**
     * Provides the current CharacteristicsFragment.
     *
     * @return the CharacteristicsFragment.
     */
    public CharacteristicsFragment getCharacteristicsFragment() {
        return (CharacteristicsFragment) mContext.getSupportFragmentManager().findFragmentByTag(mCharacteristicFragmentTag);
    }

    /**
     * Provides the current SkillsFragment.
     *
     * @return the SkillsFragment.
     */
    public SkillsFragment getSkillsFragment() {
        return (SkillsFragment) mContext.getSupportFragmentManager().findFragmentByTag(mSkillsFragmentTag);
    }

    /**
     * Provides the current InventoryFragment.
     *
     * @return the InventoryFragment.
     */
    public InventoryFragment getInventoryFragment() {
        return (InventoryFragment) mContext.getSupportFragmentManager().findFragmentByTag(mInventoryFragmentTag);
    }

    /**
     * Provides the current AdventureFragment.
     *
     * @return the AdventureFragment.
     */
    public AdventureFragment getAdventureFragment() {
        return (AdventureFragment) mContext.getSupportFragmentManager().findFragmentByTag(mAdventureFragmentTag);
    }
}
