package com.aku.warhammerdicelauncher.utils.constants;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cnicolas on 04/05/2016.
 */
public abstract class Constants {
    public static final Map<DiceFace, DiceFace> inversionMap;
    public static final Map<DiceFace, Integer> popupTextViews;

    static {
        Map<DiceFace, DiceFace> map = new HashMap<>();
        map.put(DiceFace.SUCCESS, DiceFace.FAILURE);
        map.put(DiceFace.FAILURE, DiceFace.SUCCESS);
        map.put(DiceFace.BENEFIT, DiceFace.MISCHIEF);
        map.put(DiceFace.MISCHIEF, DiceFace.BENEFIT);
        map.put(DiceFace.SIGMAR, DiceFace.CHAOS);
        map.put(DiceFace.CHAOS, DiceFace.SIGMAR);
        inversionMap = map;
    }

    static {
        Map<DiceFace, Integer> map = new HashMap<>();
        map.put(DiceFace.SUCCESS, R.id.successNumberResults);
        map.put(DiceFace.BENEFIT, R.id.benefitNumberResults);
        map.put(DiceFace.MISCHIEF, R.id.mischiefNumberResults);
        map.put(DiceFace.FAILURE, R.id.failureNumberResults);
        map.put(DiceFace.SIGMAR, R.id.sigmarNumberResults);
        map.put(DiceFace.TIREDNESS, R.id.tirednessNumberResults);
        map.put(DiceFace.DELAY, R.id.delayNumberResults);
        map.put(DiceFace.CHAOS, R.id.chaosNumberResults);
        popupTextViews = map;
    }
}
