package com.aku.warhammerdicelauncher.model.dices.impl;

import com.aku.warhammerdicelauncher.model.dices.DiceFaces;
import com.aku.warhammerdicelauncher.model.dices.IDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gather the methods common to each dice.
 */
abstract class AbstractDice implements IDice {
    protected List<DiceFaces[]> faces;
    private int facesNumber;

    AbstractDice(int facesNumber) {
        this.facesNumber = facesNumber;
        this.faces = new ArrayList<>(facesNumber);
    }

    /**
     * Simplifies the given faces array : removing the blank faces, replacing Success+ by simple success.
     *
     * @param faces the faces array to simplify.
     * @return Simplified List.
     */
    private static List<DiceFaces> toGoodArray(DiceFaces[] faces) {
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

    /**
     * Does the given list of dice faces contains given face ?
     *
     * @param lastFaces  faces list.
     * @param faceToSeek face to seek.
     * @return Yes or no.
     */
    private static boolean containsFace(DiceFaces[] lastFaces, DiceFaces faceToSeek) {
        for (DiceFaces face : lastFaces) {
            if (face == faceToSeek) {
                return true;
            }
        }
        return false;
    }

    /**
     * Roll the dice.
     *
     * @return the resulting faces.
     */
    public List<DiceFaces> roll() {
        List<DiceFaces> res = new ArrayList<>();
        DiceFaces[] lastFaces;
        do {
            lastFaces = faces.get(randomNumber());
            res.addAll(toGoodArray(lastFaces));
        } while (containsFace(lastFaces, DiceFaces.SUCCESS_PLUS));
        return res;
    }

    /**
     * Get a random number between the facesNumber.
     *
     * @return face number.
     */
    private int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(facesNumber);
    }


}
