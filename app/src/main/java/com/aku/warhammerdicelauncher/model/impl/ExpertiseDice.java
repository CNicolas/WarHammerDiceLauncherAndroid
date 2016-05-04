package com.aku.warhammerdicelauncher.model.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class ExpertiseDice extends AbstractDice {
    public ExpertiseDice() {
        super(6);

        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS_PLUS});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.SIGMAR});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
