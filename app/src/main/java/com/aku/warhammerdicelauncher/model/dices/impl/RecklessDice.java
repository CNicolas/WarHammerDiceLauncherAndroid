package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class RecklessDice extends AbstractDice {
    public RecklessDice() {
        super(10);

        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.BENEFIT, DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.TIREDNESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.TIREDNESS});
        faces.add(new DiceFace[]{DiceFace.MISCHIEF});
        faces.add(new DiceFace[]{DiceFace.MISCHIEF});
        faces.add(new DiceFace[]{DiceFace.VOID});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
