package com.aku.warhammerdicelauncher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import com.aku.warhammerdicelauncher.model.IDice;
import com.aku.warhammerdicelauncher.model.impl.ChallengeDice;
import com.aku.warhammerdicelauncher.model.impl.CharacteristicDice;
import com.aku.warhammerdicelauncher.model.impl.ConservativeDice;
import com.aku.warhammerdicelauncher.model.impl.ExpertiseDice;
import com.aku.warhammerdicelauncher.model.impl.FortuneDice;
import com.aku.warhammerdicelauncher.model.impl.MisfortuneDice;
import com.aku.warhammerdicelauncher.model.impl.RecklessDice;
import com.aku.warhammerdicelauncher.services.DicesRoller;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
    }

    public void rollDices(View v) {
        try {
            int characteristic = ((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue();
            int reckless = ((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue();
            int conservative = ((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue();
            int expertise = ((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue();
            int fortune = ((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue();
            int misfortune = ((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue();
            int challenge = ((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue();

            List<IDice> pool = new ArrayList<>();
            for (int i = 0; i < characteristic; i++) {
                pool.add(new CharacteristicDice());
            }
            for (int i = 0; i < reckless; i++) {
                pool.add(new RecklessDice());
            }
            for (int i = 0; i < conservative; i++) {
                pool.add(new ConservativeDice());
            }
            for (int i = 0; i < expertise; i++) {
                pool.add(new ExpertiseDice());
            }
            for (int i = 0; i < fortune; i++) {
                pool.add(new FortuneDice());
            }
            for (int i = 0; i < misfortune; i++) {
                pool.add(new MisfortuneDice());
            }
            for (int i = 0; i < challenge; i++) {
                pool.add(new ChallengeDice());
            }

            Map<DiceFace, Integer> res = DicesRoller.rollDices(pool);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.resultsTitle)
                    .setMessage(res.toString())
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (Exception e) {
            Log.e("MainActivity", "rollDices: ", e);
            throw e;
        }
    }

}
