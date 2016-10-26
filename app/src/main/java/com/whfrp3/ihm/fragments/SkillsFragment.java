package com.whfrp3.ihm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.database.WarHammerDatabaseHelper;
import com.whfrp3.database.dao.SkillDao;
import com.whfrp3.ihm.adapters.SkillsListAdapter;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.helpers.OnPlayerUpdateListener;

import java.util.List;

/**
 * The SkillFragment.
 */
public class SkillsFragment extends Fragment implements OnPlayerUpdateListener {

    private ListView mSkillsListView;
    private SkillDao mSkillDao;
    private List<Skill> mSkills;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);

        mSkillDao = new SkillDao(new WarHammerDatabaseHelper(getActivity()));
        mSkills = mSkillDao.findAllByPlayer(PlayerContext.getPlayerInstance());

        mSkillsListView = (ListView) rootView.findViewById(R.id.skills_list);
        mSkillsListView.setAdapter(new SkillsListAdapter(getActivity(), R.layout.item_skills_list, mSkills));

        PlayerContext.registerPlayerUpdateListener(this);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("SkillsFragment", String.format("%d = %d", startTime, difference));
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
