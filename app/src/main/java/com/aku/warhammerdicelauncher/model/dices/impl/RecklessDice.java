package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class RecklessDice extends AbstractDice {
    public RecklessDice() {
        super(10);

        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.SUCCESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.BENEFIT});
        faces.add(new DiceFaces[]{DiceFaces.BENEFIT, DiceFaces.BENEFIT});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.TIREDNESS});
        faces.add(new DiceFaces[]{DiceFaces.SUCCESS, DiceFaces.TIREDNESS});
        faces.add(new DiceFaces[]{DiceFaces.MISCHIEF});
        faces.add(new DiceFaces[]{DiceFaces.MISCHIEF});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
        faces.add(new DiceFaces[]{DiceFaces.VOID});
    }
}
