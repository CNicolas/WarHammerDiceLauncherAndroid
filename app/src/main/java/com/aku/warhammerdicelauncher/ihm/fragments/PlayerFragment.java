package com.aku.warhammerdicelauncher.ihm.fragments;


import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost.TabSpec;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.activities.MainActivity;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.utils.PlayerContext;

public class PlayerFragment extends Fragment {

    final static String CHARACTERISTICS_FRAGMENT = "characteristicsFragment";
    final static String SKILLS_FRAGMENT = "skillsFragment";
    final static String INVENTORY_FRAGMENT = "inventoryFragment";

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity context = ((MainActivity) getActivity());
        View rootView = inflater.inflate(R.layout.fragment_player, container, false);
        FragmentTabHost tabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);

        tabHost.setup(context, context.getSupportFragmentManager(), android.R.id.tabcontent);

        addCharacteristicsFragmentTab(context, tabHost);
        addSkillsFragmentTab(context, tabHost);
        addInventoryFragmentTab(context, tabHost);

        tabHost.bringChildToFront(rootView);

        return rootView;
    }

    public void updatePlayer(MainActivity context) {
        Player player = PlayerContext.getPlayerInstance();
        new AlertDialog.Builder(context).setTitle(player.getName()).setMessage(player.toString()).show();
    }

    //region Fragments to add
    private void addCharacteristicsFragmentTab(MainActivity context, FragmentTabHost tabHost) {
        String indicatorTitle = getString(R.string.page_characteristics);
        Drawable indicatorIcon = getIconByIdByAndroidVersion(context, R.drawable.ic_characteristics_black);

        TabSpec characteristicsTab = tabHost.newTabSpec(CHARACTERISTICS_FRAGMENT).setIndicator("", indicatorIcon);
        tabHost.addTab(characteristicsTab, CharacteristicsFragment.class, null);
    }

    private void addSkillsFragmentTab(MainActivity context, FragmentTabHost tabHost) {
        String indicatorTitle = getString(R.string.page_skills);
        Drawable indicatorIcon = getIconByIdByAndroidVersion(context, R.drawable.ic_skills_black);

        TabSpec skillsTab = tabHost.newTabSpec(SKILLS_FRAGMENT).setIndicator("", indicatorIcon);
        tabHost.addTab(skillsTab, SkillsFragment.class, null);
    }

    private void addInventoryFragmentTab(MainActivity context, FragmentTabHost tabHost) {
        String indicatorTitle = getString(R.string.page_inventory);
        Drawable indicatorIcon = getIconByIdByAndroidVersion(context, R.drawable.ic_rucksack_black);

        TabSpec inventoryTab = tabHost.newTabSpec(INVENTORY_FRAGMENT).setIndicator("", indicatorIcon);
        tabHost.addTab(inventoryTab, InventoryFragment.class, null);
    }
    //endregion

    private Drawable getIconByIdByAndroidVersion(MainActivity context, int iconId) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(iconId);
        } else {
            return getResources().getDrawable(iconId);
        }
    }
}
