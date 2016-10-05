package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class FortuneDice extends AbstractDice {
    public FortuneDice() {
        super(6);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.BENEFIT});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
