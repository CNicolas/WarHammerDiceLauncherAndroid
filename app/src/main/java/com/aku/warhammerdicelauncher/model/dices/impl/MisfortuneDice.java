package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class MisfortuneDice extends AbstractDice {
    public MisfortuneDice() {
        super(6);

        faces.add(new DiceFace[]{DiceFace.FAILURE});
        faces.add(new DiceFace[]{DiceFace.FAILURE});
        faces.add(new DiceFace[]{DiceFace.MISCHIEF});
        faces.add(new DiceFace[]{DiceFace.VOID});
        faces.add(new DiceFace[]{DiceFace.VOID});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
