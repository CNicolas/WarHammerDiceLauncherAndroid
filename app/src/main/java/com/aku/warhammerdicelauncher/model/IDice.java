package com.aku.warhammerdicelauncher.model;

import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.List;

/**
 * Created by cnicolas on 04/05/2016.
 */
public interface IDice {
    public List<DiceFace> roll();
}
