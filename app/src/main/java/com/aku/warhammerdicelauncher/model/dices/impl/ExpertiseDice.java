package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;

/**
 * Created by cnicolas on 04/05/2016.
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
