package com.whfrp3.ihm.fragments.main;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.PlayerActivity;
import com.whfrp3.model.player.Player;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IMainConstants;
import com.whfrp3.tools.constants.IPlayerActivityConstants;

import java.util.ArrayList;
import java.util.List;

public class PlayersListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_players_list, container, false);

        ListView listPlayers = (ListView) rootView.findViewById(R.id.list_players);

        List<String> playersNames = new ArrayList<>();

        playersNames.add(getString(R.string.home_create_player));
        playersNames.addAll(WHFRP3Application.getDatabase().getPlayerDao().findAllNames());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.element_list_player, playersNames);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new PlayerListItemClickListener());

        getActivity().setTitle(getString(R.string.page_players));

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.page_home));
    }

    private class PlayerListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            boolean isInEdition = false;

            if (position == 0) {
                isInEdition = true;
                WHFRP3Application.initEmptyPlayer();
            } else {
                try {
                    TextView tv = (TextView) view;
                    Player player = WHFRP3Application.getDatabase().getPlayerDao().findByName(tv.getText().toString());
                    WHFRP3Application.setPlayer(player);
                } catch (SQLiteException sqle) {
                    Toast.makeText(WHFRP3Application.getAppContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }

            Intent playerIntent = new Intent(getActivity(), PlayerActivity.class);
            playerIntent.putExtra(IPlayerActivityConstants.IS_IN_EDITION_BUNDLE_TAG, isInEdition);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
            stackBuilder.addParentStack(PlayerActivity.class);
            stackBuilder.addNextIntent(playerIntent);

            getActivity().startActivityForResult(playerIntent, IMainConstants.PLAYER_REQUEST);
        }
    }
}
