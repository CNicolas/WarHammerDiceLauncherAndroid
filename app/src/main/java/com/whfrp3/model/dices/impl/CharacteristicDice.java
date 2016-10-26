package com.whfrp3.model.dices.impl;

import com.whfrp3.model.dices.DiceFaces;

/**
 * The CharacteristicDice (Blue).
 */
public class CharacteristicDice extends AbstractDice {
    public CharacteristicDice() {
        super(8);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.BOON});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
