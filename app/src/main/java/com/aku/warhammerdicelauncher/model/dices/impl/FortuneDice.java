package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;

/**
 * The FortuneDice (White).
 */
public class FortuneDice extends AbstractDice {
    public FortuneDice() {
        super(6);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
