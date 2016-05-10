package com.aku.warhammerdicelauncher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.model.database.DicesHandContract.HandEntry;
import com.aku.warhammerdicelauncher.model.database.helper.WarHammerDatabaseHelper;
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        String[] projection = {HandEntry.COLUMN_NAME_TITLE};
        Cursor cursor = db.query(HandEntry.TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_TITLE));
                menu.add(name);
                cursor.moveToNext();
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        String[] selectionArgs = {item.getTitle().toString()};
        Cursor cursor = db.query(HandEntry.TABLE_NAME, null, HandEntry.COLUMN_NAME_TITLE + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
            characteristicPicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CHARACTERISTIC)));
            NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
            recklessPicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_RECKLESS)));
            NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
            conservativePicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CONSERVATIVE)));
            NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
            expertisePicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_EXPERTISE)));
            NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
            fortunePicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_FORTUNE)));
            NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
            misfortunePicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_MISFORTUNE)));
            NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);
            challengePicker.setValue(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CHALLENGE)));
        }

        return false;
    }

    //-----------CUSTOM METHODS-----------

    /**
     * React to a click on the rollButton. Roll dices and show the results in a popup
     *
     * @param v the view
     */
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

    public void saveHand(View v) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int characteristic = ((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue();
                    int reckless = ((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue();
                    int conservative = ((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue();
                    int expertise = ((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue();
                    int fortune = ((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue();
                    int misfortune = ((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue();
                    int challenge = ((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue();

                    WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(MainActivity.this);
                    SQLiteDatabase db = whdHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(HandEntry.COLUMN_NAME_TITLE, input.getText().toString());
                    values.put(HandEntry.COLUMN_NAME_CHARACTERISTIC, characteristic);
                    values.put(HandEntry.COLUMN_NAME_RECKLESS, reckless);
                    values.put(HandEntry.COLUMN_NAME_CONSERVATIVE, conservative);
                    values.put(HandEntry.COLUMN_NAME_EXPERTISE, expertise);
                    values.put(HandEntry.COLUMN_NAME_FORTUNE, fortune);
                    values.put(HandEntry.COLUMN_NAME_MISFORTUNE, misfortune);
                    values.put(HandEntry.COLUMN_NAME_CHALLENGE, challenge);

                    long newRowId = db.insert(HandEntry.TABLE_NAME, null, values);

                    invalidateOptionsMenu();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        } catch (Exception e) {
            Log.e("MainActivity", "saveHand: ", e);
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
                textView.setText(map.get(face).toString());
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
