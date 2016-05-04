package com.aku.warhammerdicelauncher.utils.constants;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cnicolas on 04/05/2016.
 */
public abstract class Constants {
    public static final Map<DiceFace, DiceFace> inversionMap;

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
}
