package com.whfrp3.ihm.listeners;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;

import com.whfrp3.R;
import com.whfrp3.tools.WHFRP3Application;


public class TalentSearchClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WHFRP3Application.getAppContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.layout.dialog_talent_search);
        } else {
            builder.setView(WHFRP3Application.getActivity().getLayoutInflater().inflate(R.layout.dialog_talent_search, null));
        }

        builder.setTitle(R.string.search);
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
