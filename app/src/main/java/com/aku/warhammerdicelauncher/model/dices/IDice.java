package com.aku.warhammerdicelauncher.model.dices;

import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;

import java.util.List;

/**
 * Created by cnicolas on 04/05/2016.
 */
public interface IDice {
    List<DiceFaces> roll();
}
