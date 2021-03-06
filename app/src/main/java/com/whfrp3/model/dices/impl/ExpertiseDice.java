package com.whfrp3.model.dices.impl;

import com.whfrp3.model.dices.DiceFaces;

/**
 * The ExpertiseDice (Yellow).
 */
public class ExpertiseDice extends AbstractDice {
    public ExpertiseDice() {
        super(6);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS_PLUS});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.SIGMAR});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
