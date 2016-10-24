package com.aku.warhammerdicelauncher.ihm.adapters;

/**
 * Created by cnicolas on 17/10/2016.
 */

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.activities.PlayerActivity;
import com.aku.warhammerdicelauncher.ihm.fragments.CharacteristicsFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.InventoryFragment;
import com.aku.warhammerdicelauncher.ihm.fragments.SkillsFragment;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.constants.IPlayerConstants;

public class PlayerPagerAdapter extends FragmentPagerAdapter {

    private final PlayerActivity mContext;
    private String mCharacteristicFragmentTag;
    private String mSkillsFragmentTag;
    private String mInventoryFragmentTag;

    public PlayerPagerAdapter(PlayerActivity ctx) {
        super(ctx.getSupportFragmentManager());
        mContext = ctx;
    }

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
                CharacteristicsFragment mCharacteristicsFragment = new CharacteristicsFragment();
                mCharacteristicsFragment.setArguments(bundle);
                return mCharacteristicsFragment;
            case 1:
                mSkillsFragmentTag = makeFragmentTag(position);
                return new SkillsFragment();
            case 2:
                mInventoryFragmentTag = makeFragmentTag(position);
                return new InventoryFragment();
            default:
                return new SkillsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
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
        }
        return null;
    }

    private CharSequence setTabIcon(Drawable image) {
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public CharacteristicsFragment getCharacteristicsFragment() {
        return (CharacteristicsFragment) mContext.getSupportFragmentManager().findFragmentByTag(mCharacteristicFragmentTag);
    }

    public SkillsFragment getSkillsFragment() {
        return (SkillsFragment) mContext.getSupportFragmentManager().findFragmentByTag(mSkillsFragmentTag);
    }

    public InventoryFragment getInventoryFragment() {
        return (InventoryFragment) mContext.getSupportFragmentManager().findFragmentByTag(mInventoryFragmentTag);
    }
}