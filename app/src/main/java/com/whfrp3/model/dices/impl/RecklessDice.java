package com.whfrp3.model.dices.impl;

import com.whfrp3.model.dices.DiceFaces;

/**
 * The RecklessDice (Red).
 */
public class RecklessDice extends AbstractDice {
    public RecklessDice() {
        super(10);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.BOON, DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.TIREDNESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.TIREDNESS});
        faces.add(new DiceFaces[]{DiceFaces.BANE});
        faces.add(new DiceFaces[]{DiceFaces.BANE});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
