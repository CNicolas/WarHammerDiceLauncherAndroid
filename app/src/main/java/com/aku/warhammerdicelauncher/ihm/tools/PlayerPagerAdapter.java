package com.aku.warhammerdicelauncher.ihm.tools;

/**
 * Created by cnicolas on 17/10/2016.
 */

import android.graphics.drawable.Drawable;
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

public class PlayerPagerAdapter extends FragmentPagerAdapter {

    private final PlayerActivity mContext;
    private CharacteristicsFragment mCharacteristicsFragment;
    private SkillsFragment mSkillsFragment;
    private InventoryFragment mInventoryFragment;

    public PlayerPagerAdapter(PlayerActivity ctx) {
        super(ctx.getSupportFragmentManager());
        mContext = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mCharacteristicsFragment = new CharacteristicsFragment();
                return mCharacteristicsFragment;
            case 1:
                mSkillsFragment = new SkillsFragment();
                return mSkillsFragment;
            case 2:
                mInventoryFragment = new InventoryFragment();
                return mInventoryFragment;
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
        return mCharacteristicsFragment;
    }

    public SkillsFragment getSkillsFragment() {
        return mSkillsFragment;
    }

    public InventoryFragment getInventoryFragment() {
        return mInventoryFragment;
    }
}
