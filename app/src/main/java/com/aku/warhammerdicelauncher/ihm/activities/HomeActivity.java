package com.aku.warhammerdicelauncher.ihm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 500;
    private ListView listPlayers;
    private PlayerDao playerDao;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_home_layout);

        listPlayers = (ListView) findViewById(R.id.list_players);

        playerDao = new PlayerDao(new WarHammerDatabaseHelper(this));
        List<String> playersNames = playerDao.findAllNames();

        playersNames.add("SALUT");
        playersNames.add("LOL");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_players_list, playersNames);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                playerDao.findByName(tv.getText().toString());

                Intent mainIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

//        /* New Handler to start the MainActivity
//         * and close this Splash-Screen after some seconds.*/
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(HomeActivity.this, MainActivity.class);
//                HomeActivity.this.startActivity(mainIntent);
//                HomeActivity.this.finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);
    }
}
