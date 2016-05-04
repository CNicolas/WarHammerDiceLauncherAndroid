package com.aku.warhammerdicelauncher.model.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class ChallengeDice extends AbstractDice {
    public ChallengeDice() {
        super(8);

        faces.add(new DiceFace[]{DiceFace.FAILURE});
        faces.add(new DiceFace[]{DiceFace.FAILURE});
        faces.add(new DiceFace[]{DiceFace.FAILURE, DiceFace.FAILURE});
        faces.add(new DiceFace[]{DiceFace.FAILURE, DiceFace.FAILURE});
        faces.add(new DiceFace[]{DiceFace.MISCHIEF});
        faces.add(new DiceFace[]{DiceFace.MISCHIEF, DiceFace.MISCHIEF});
        faces.add(new DiceFace[]{DiceFace.CHAOS});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
