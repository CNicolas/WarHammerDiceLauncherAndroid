package com.aku.warhammerdicelauncher.model.impl;

import com.aku.warhammerdicelauncher.model.IDice;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cnicolas on 04/05/2016.
 */
public abstract class AbstractDice implements IDice {
    protected int facesNumber;
    protected List<DiceFace[]> faces;

    public AbstractDice(int facesNumber) {
        this.facesNumber = facesNumber;
        this.faces = new ArrayList<>(facesNumber);
    }

    protected static List<DiceFace> toGoodArray(DiceFace[] faces) {
        List<DiceFace> res = new ArrayList<>();
        for (DiceFace face : faces) {
            if (face == DiceFace.SUCCESS_PLUS) {
                res.add(DiceFace.SUCCESS);
            } else if (face != DiceFace.VOID) {
                res.add(face);
            }
        }
        return res;
    }

    protected static boolean containsFace(DiceFace[] lastFaces, DiceFace faceToSeek) {
        for (DiceFace face : lastFaces) {
            if (face == faceToSeek) {
                return true;
            }
        }
        return false;
    }

    public List<DiceFace> roll() {
        List<DiceFace> res = new ArrayList<>();
        DiceFace[] lastFaces;
        do {
            lastFaces = faces.get(randomNumber());
            res.addAll(toGoodArray(lastFaces));
        } while (containsFace(lastFaces, DiceFace.SUCCESS_PLUS));
        return res;
    }

    protected int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(facesNumber);
    }


}
