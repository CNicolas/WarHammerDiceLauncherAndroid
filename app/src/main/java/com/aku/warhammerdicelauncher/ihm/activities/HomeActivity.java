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
        setContentView(R.layout.activity_home_layout);

        listPlayers = (ListView) findViewById(R.id.list_players);

        playerDao = new PlayerDao(new WarHammerDatabaseHelper(this));
        List<String> playersNames = playerDao.findAllNames();

        playersNames.add("PlayerActivity");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_players_list, playersNames);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView tv = (TextView) view;
                    playerDao.findByName(tv.getText().toString());
                } catch (SQLiteException sqle) {

                }

                Intent mainIntent = new Intent(HomeActivity.this, PlayerActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}
