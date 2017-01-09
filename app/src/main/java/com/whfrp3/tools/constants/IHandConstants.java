package com.whfrp3.tools.constants;

import com.whfrp3.model.dices.DiceFaces;

import java.util.HashMap;
import java.util.Map;


/**
 * Constants used in the LaunchActivity and StatisticsActivity.
 */
public abstract class IHandConstants {
    public static final String HAND_BUNDLE_TAG = "hand";
    public static final String TIMES_BUNDLE_TAG = "times";

    /**
     * This map provides the different opposites sides.
     */
    public static final Map<DiceFaces, DiceFaces> inversionMap;

    static {
        Map<DiceFaces, DiceFaces> map = new HashMap<>();
        map.put(DiceFaces.SUCCESS, DiceFaces.FAILURE);
        map.put(DiceFaces.FAILURE, DiceFaces.SUCCESS);
        map.put(DiceFaces.BOON, DiceFaces.BANE);
        map.put(DiceFaces.BANE, DiceFaces.BOON);
        inversionMap = map;
    }
}
