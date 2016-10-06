package com.aku.warhammerdicelauncher.utils.constants;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cnicolas on 04/05/2016.
 */
public abstract class HandConstants implements IConstants {
    public static final String HAND_TAG = "hand";
    public static final String TIMES_TAG = "times";

    public static final Map<DiceFaces, DiceFaces> inversionMap;
    public static final Map<DiceFaces, Integer> popupResultsTextViews;

    static {
        Map<DiceFaces, DiceFaces> map = new HashMap<>();
        map.put(DiceFaces.SUCCESS, DiceFaces.FAILURE);
        map.put(DiceFaces.FAILURE, DiceFaces.SUCCESS);
        map.put(DiceFaces.BENEFIT, DiceFaces.MISCHIEF);
        map.put(DiceFaces.MISCHIEF, DiceFaces.BENEFIT);
        inversionMap = map;
    }

    static {
        Map<DiceFaces, Integer> map = new HashMap<>();
        map.put(DiceFaces.SUCCESS, R.id.successNumberResults);
        map.put(DiceFaces.BENEFIT, R.id.benefitNumberResults);
        map.put(DiceFaces.MISCHIEF, R.id.mischiefNumberResults);
        map.put(DiceFaces.FAILURE, R.id.failureNumberResults);
        map.put(DiceFaces.SIGMAR, R.id.sigmarNumberResults);
        map.put(DiceFaces.TIREDNESS, R.id.tirednessNumberResults);
        map.put(DiceFaces.DELAY, R.id.delayNumberResults);
        map.put(DiceFaces.CHAOS, R.id.chaosNumberResults);
        popupResultsTextViews = map;
    }
}
