package com.aku.warhammerdicelauncher.ihm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.SkillDao;
import com.aku.warhammerdicelauncher.ihm.adapters.SkillsListAdapter;
import com.aku.warhammerdicelauncher.model.player.skill.Skill;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.helpers.OnPlayerUpdateListener;

import java.util.List;

/**
 * The SkillFragment.
 */
public class SkillsFragment extends Fragment implements OnPlayerUpdateListener {

    private ListView mSkillsListView;
    private SkillDao mSkillDao;
    private List<Skill> mSkills;

    public SkillsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);

        mSkillDao = new SkillDao(new WarHammerDatabaseHelper(getActivity()));
        mSkills = mSkillDao.findAllByPlayer(PlayerContext.getPlayerInstance());

        mSkillsListView = (ListView) rootView.findViewById(R.id.skills_list);
        mSkillsListView.setAdapter(new SkillsListAdapter(getActivity(), R.layout.item_skills_list, mSkills));

        PlayerContext.registerPlayerUpdateListener(this);

        return rootView;
    }

    @Override
    public void onPlayerUpdate() {
        Context context = getActivity();
        if (context != null) {
            mSkills = mSkillDao.findAllByPlayer(PlayerContext.getPlayerInstance());
            mSkillsListView.setAdapter(new SkillsListAdapter(context, R.layout.item_skills_list, mSkills));
        }
    }
}
