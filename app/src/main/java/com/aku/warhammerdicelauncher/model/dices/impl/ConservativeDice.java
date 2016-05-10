package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class ConservativeDice extends AbstractDice {
    public ConservativeDice() {
        super(10);

        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.DELAY});
        faces.add(new DiceFace[]{DiceFace.SUCCESS, DiceFace.DELAY});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
