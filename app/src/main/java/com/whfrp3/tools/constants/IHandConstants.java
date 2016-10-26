package com.whfrp3.tools.constants;

import com.whfrp3.R;
import com.whfrp3.model.dices.DiceFaces;

import java.util.HashMap;
import java.util.Map;


/**
 * Constants used in the LaunchActivity and StatisticsActivity.
 */
public abstract class IHandConstants implements IConstants {
    public static final String HAND_TAG = "hand";
    public static final String TIMES_TAG = "times";

    /**
     * This map provides the different opposites sides.
     */
    public static final Map<DiceFaces, DiceFaces> inversionMap;
    /**
     * Links the dice face with the TextView in the result dialog.
     */
    public static final Map<DiceFaces, Integer> popupResultsTextViews;

    static {
        Map<DiceFaces, DiceFaces> map = new HashMap<>();
        map.put(DiceFaces.SUCCESS, DiceFaces.FAILURE);
        map.put(DiceFaces.FAILURE, DiceFaces.SUCCESS);
        map.put(DiceFaces.BOON, DiceFaces.BANE);
        map.put(DiceFaces.BANE, DiceFaces.BOON);
        inversionMap = map;
    }

    static {
        Map<DiceFaces, Integer> map = new HashMap<>();
        map.put(DiceFaces.SUCCESS, R.id.successNumberResults);
        map.put(DiceFaces.BOON, R.id.benefitNumberResults);
        map.put(DiceFaces.BANE, R.id.mischiefNumberResults);
        map.put(DiceFaces.FAILURE, R.id.failureNumberResults);
        map.put(DiceFaces.SIGMAR, R.id.sigmarNumberResults);
        map.put(DiceFaces.TIREDNESS, R.id.tirednessNumberResults);
        map.put(DiceFaces.DELAY, R.id.delayNumberResults);
        map.put(DiceFaces.CHAOS, R.id.chaosNumberResults);
        popupResultsTextViews = map;
    }
}
