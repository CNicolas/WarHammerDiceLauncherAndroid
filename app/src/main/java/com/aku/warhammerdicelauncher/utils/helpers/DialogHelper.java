package com.aku.warhammerdicelauncher.utils.helpers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.Map;

/**
 * Created by cnicolas on 21/09/2016.
 */

public abstract class DialogHelper {

    public static void showLaunchResults(Map<DiceFace, Integer> map, Context ctx) {
        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.popup_results);
        dialog.setTitle(R.string.resultsTitle);

        for (DiceFace face : Constants.popupResultsTextViews.keySet()) {
            TextView textView = (TextView) dialog.findViewById(Constants.popupResultsTextViews.get(face));
            if (map.containsKey(face)) {
                textView.setText(map.get(face).toString());
            } else {
                textView.setVisibility(View.GONE);
            }
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.dismissResultsPopupButton);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
