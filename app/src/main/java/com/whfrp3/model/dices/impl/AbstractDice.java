package com.whfrp3.model.dices.impl;

import com.whfrp3.model.dices.DiceFaces;
import com.whfrp3.model.dices.IDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gather the methods common to each dice.
 */
abstract class AbstractDice implements IDice {
    final List<DiceFaces[]> faces;
    private final int facesNumber;

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
     * @param lastFaces faces list.
     * @return Yes or no.
     */
    private static boolean containsSuccessPlus(DiceFaces[] lastFaces) {
        for (DiceFaces face : lastFaces) {
            if (face == DiceFaces.SUCCESS_PLUS) {
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
        } while (containsSuccessPlus(lastFaces));
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

    @Override
    public String toString() {
        return "AbstractDice{" + "faces=" + faces + ", facesNumber=" + facesNumber + '}';
    }
}
