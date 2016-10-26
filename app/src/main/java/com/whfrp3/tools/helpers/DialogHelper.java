package com.whfrp3.tools.helpers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.model.dices.DiceFaces;
import com.whfrp3.tools.constants.IHandConstants;

import java.util.Map;

/**
 * A dialog helper, creating the dialog you need.
 */
public abstract class DialogHelper {

    /**
     * Shows the result of a launch.
     *
     * @param map the final results.
     * @param ctx the context, where to open the dialog.
     */
    public static void showLaunchResults(Map<DiceFaces, Integer> map, Context ctx) {
        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.dialog_results);
        dialog.setTitle(R.string.resultsTitle);

        for (DiceFaces face : IHandConstants.popupResultsTextViews.keySet()) {
            TextView textView = (TextView) dialog.findViewById(IHandConstants.popupResultsTextViews.get(face));
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
