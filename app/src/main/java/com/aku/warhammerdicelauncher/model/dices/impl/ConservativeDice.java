package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class ConservativeDice extends AbstractDice {
    public ConservativeDice() {
        super(10);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.DELAY});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.DELAY});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
