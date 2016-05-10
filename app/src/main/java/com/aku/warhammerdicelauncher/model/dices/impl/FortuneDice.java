package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class FortuneDice extends AbstractDice {
    public FortuneDice() {
        super(6);

        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.VOID});
        faces.add(new DiceFace[]{DiceFace.VOID});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
