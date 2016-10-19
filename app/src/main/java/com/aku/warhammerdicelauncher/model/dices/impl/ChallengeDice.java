package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;

/**
 * Created by cnicolas on 04/05/2016.
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
