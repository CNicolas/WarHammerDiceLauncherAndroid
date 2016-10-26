package com.whfrp3.model.dices.impl;

import com.whfrp3.model.dices.DiceFaces;

/**
 * The MisfortuneDice (Black).
 */
public class MisfortuneDice extends AbstractDice {
    public MisfortuneDice() {
        super(6);

        faces.add(new DiceFaces[]{DiceFaces.FAILURE});
        faces.add(new DiceFaces[]{DiceFaces.FAILURE});
        faces.add(new DiceFaces[]{DiceFaces.BANE});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
