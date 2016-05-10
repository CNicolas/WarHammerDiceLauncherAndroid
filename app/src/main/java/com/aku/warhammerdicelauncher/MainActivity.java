package com.aku.warhammerdicelauncher;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.model.dices.IDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ChallengeDice;
import com.aku.warhammerdicelauncher.model.dices.impl.CharacteristicDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ConservativeDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ExpertiseDice;
import com.aku.warhammerdicelauncher.model.dices.impl.FortuneDice;
import com.aku.warhammerdicelauncher.model.dices.impl.MisfortuneDice;
import com.aku.warhammerdicelauncher.model.dices.impl.RecklessDice;
import com.aku.warhammerdicelauncher.services.DicesRoller;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //-----------CUSTOM METHODS-----------
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

            showResults(res);

        } catch (Exception e) {
            Log.e("MainActivity", "rollDices: ", e);
            throw e;
        }
    }

    private void showResults(Map<DiceFace, Integer> map) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_results);
        dialog.setTitle(R.string.resultsTitle);

        for (DiceFace face : Constants.popupTextViews.keySet()) {
            TextView textView = (TextView) dialog.findViewById(Constants.popupTextViews.get(face));
            if (map.containsKey(face)) {
                textView.setText(String.format("%d", map.get(face)));
            } else {
                textView.setVisibility(View.GONE);
            }
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.dismissResultsPopupButton);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
