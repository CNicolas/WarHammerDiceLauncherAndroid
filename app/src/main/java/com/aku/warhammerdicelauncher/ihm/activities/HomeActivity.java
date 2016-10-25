package com.aku.warhammerdicelauncher.ihm.activities;

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

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.constants.IPlayerConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * First page of WHFRP3, allowing the user to chose to use an existing Player or create a new one.
 */
public class HomeActivity extends Activity {
    private PlayerDao mPlayerDao;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);

        ListView listPlayers = (ListView) findViewById(R.id.list_players);

        mPlayerDao = new PlayerDao(new WarHammerDatabaseHelper(this));
        List<String> playersNames = new ArrayList<>();

        playersNames.add(getResources().getString(R.string.home_create_player));
        playersNames.addAll(mPlayerDao.findAllNames());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_players_list, playersNames);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new PlayerListItemClickListener(this));

        PlayerContext.setContext(this);
    }

    /**
     * Listener for the playersList.
     * Either creating a new player or loading an existing one from database.
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
                PlayerContext.createEmptyPlayer();
//                    PlayerContext.createTestPlayer();
            } else {
                try {
                    TextView tv = (TextView) view;
                    Player player = mPlayerDao.findByName(tv.getText().toString());
                    PlayerContext.setPlayer(player);
                } catch (SQLiteException sqle) {
                    Toast.makeText(mContext, "An error occured", Toast.LENGTH_LONG).show();
                }
            }

            Intent playerIntent = new Intent(HomeActivity.this, PlayerActivity.class);
            playerIntent.putExtra(IPlayerConstants.IS_IN_EDITION_KEY, isInEdition);

            startActivity(playerIntent);
            finish();
        }
    }
}
