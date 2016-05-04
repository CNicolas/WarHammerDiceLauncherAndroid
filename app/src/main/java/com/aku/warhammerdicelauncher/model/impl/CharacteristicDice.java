package com.aku.warhammerdicelauncher.model.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class CharacteristicDice extends AbstractDice {
    public CharacteristicDice() {
        super(8);

        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.SUCCESS});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.BENEFIT});
        faces.add(new DiceFace[]{DiceFace.VOID});
        faces.add(new DiceFace[]{DiceFace.VOID});
    }
}
