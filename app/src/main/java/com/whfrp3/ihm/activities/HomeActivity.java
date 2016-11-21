package com.whfrp3.ihm.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.model.player.Player;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * First page of WHFRP3, allowing the user to chose to use an existing Player or create a new one.
 */
public class HomeActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);

        ListView listPlayers = (ListView) findViewById(R.id.list_players);

        List<String> playersNames = new ArrayList<>();

        playersNames.add(getResources().getString(R.string.home_create_player));
        playersNames.addAll(WHFRP3Application.getDatabase().getPlayerDao().findAllNames());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_players_list, playersNames);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new PlayerListItemClickListener(this));
    }

    /**
     * Listener for the playersList.
     * Either creating a new player or loading an existing one from mDatabase.
     */
    public class PlayerListItemClickListener implements AdapterView.OnItemClickListener {
        private final Context mContext;

        public PlayerListItemClickListener(Context context) {
            mContext = context;
        }

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
                    Toast.makeText(mContext, "An error occured", Toast.LENGTH_LONG).show();
                }
            }

            Intent playerIntent = new Intent(HomeActivity.this, PlayerActivity.class);
            playerIntent.putExtra(IPlayerActivityConstants.IS_IN_EDITION_BUNDLE_TAG, isInEdition);

            startActivity(playerIntent);
            finish();
        }
    }
}
