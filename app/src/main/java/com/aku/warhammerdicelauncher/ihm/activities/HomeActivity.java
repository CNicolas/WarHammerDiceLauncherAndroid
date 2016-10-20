package com.aku.warhammerdicelauncher.ihm.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.tools.constants.IPlayerConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HomeActivity extends Activity {
    private ListView listPlayers;
    private PlayerDao playerDao;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);

        listPlayers = (ListView) findViewById(R.id.list_players);

        playerDao = new PlayerDao(new WarHammerDatabaseHelper(this));
        List<String> playersNames = new ArrayList<>();

        playersNames.add(getResources().getString(R.string.home_create_player));
        playersNames.addAll(playerDao.findAllNames());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_players_list, playersNames);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isInEdition = false;

                if (position == 0) {
                    isInEdition = true;
                } else {
                    try {
                        TextView tv = (TextView) view;
                        playerDao.findByName(tv.getText().toString());
                    } catch (SQLiteException sqle) {

                    }
                }

                Intent playerIntent = new Intent(HomeActivity.this, PlayerActivity.class);
                playerIntent.putExtra(IPlayerConstants.IS_IN_EDITION_KEY, isInEdition);

                startActivity(playerIntent);
                finish();
            }
        });
    }
}
