package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;

/**
 * The ChallengeDice (Purple).
 */
public class ChallengeDice extends AbstractDice {
    public ChallengeDice() {
        super(8);

        faces.add(new DiceFaces[]{DiceFaces.FAILURE});
        faces.add(new DiceFaces[]{DiceFaces.FAILURE});
        faces.add(new DiceFaces[]{DiceFaces.FAILURE, DiceFaces.FAILURE});
        faces.add(new DiceFaces[]{DiceFaces.FAILURE, DiceFaces.FAILURE});
        faces.add(new DiceFaces[]{DiceFaces.BANE});
        faces.add(new DiceFaces[]{DiceFaces.BANE, DiceFaces.BANE});
        faces.add(new DiceFaces[]{DiceFaces.CHAOS});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
