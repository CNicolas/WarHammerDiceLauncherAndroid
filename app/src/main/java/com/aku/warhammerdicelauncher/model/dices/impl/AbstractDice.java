package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.IDice;
import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cnicolas on 04/05/2016.
 */
public abstract class AbstractDice implements IDice {
    protected int facesNumber;
    protected List<DiceFaces[]> faces;

    public AbstractDice(int facesNumber) {
        this.facesNumber = facesNumber;
        this.faces = new ArrayList<>(facesNumber);
    }

    protected static List<DiceFaces> toGoodArray(DiceFaces[] faces) {
        List<DiceFaces> res = new ArrayList<>();
        for (DiceFaces face : faces) {
            if (face == DiceFaces.SUCCESS_PLUS) {
                res.add(DiceFaces.SUCCESS);
            } else if (face != DiceFaces.VOID) {
                res.add(face);
            }
        }
        return res;
    }

    protected static boolean containsFace(DiceFaces[] lastFaces, DiceFaces faceToSeek) {
        for (DiceFaces face : lastFaces) {
            if (face == faceToSeek) {
                return true;
            }
        }
        return false;
    }

    public List<DiceFaces> roll() {
        List<DiceFaces> res = new ArrayList<>();
        DiceFaces[] lastFaces;
        do {
            lastFaces = faces.get(randomNumber());
            res.addAll(toGoodArray(lastFaces));
        } while (containsFace(lastFaces, DiceFaces.SUCCESS_PLUS));
        return res;
    }

    protected int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(facesNumber);
    }


}
