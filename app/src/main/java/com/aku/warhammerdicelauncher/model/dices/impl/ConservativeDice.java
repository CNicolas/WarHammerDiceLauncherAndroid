package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;

/**
 * The ConservativeDice (Green).
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
