package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;

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
        faces.add(new DiceFaces[]{DiceFaces.MISCHIEF});
        faces.add(new DiceFaces[]{DiceFaces.MISCHIEF, DiceFaces.MISCHIEF});
        faces.add(new DiceFaces[]{DiceFaces.CHAOS});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
