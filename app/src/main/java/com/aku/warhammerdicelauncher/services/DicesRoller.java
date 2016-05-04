package com.aku.warhammerdicelauncher.services;

import android.util.Log;

import com.aku.warhammerdicelauncher.model.IDice;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cnicolas on 04/05/2016.
 */
public class DicesRoller {

    public static Map<DiceFace, Integer> rollDices(List<IDice> pool) {
        List<DiceFace> tmp = new ArrayList<>();
        for (IDice dice : pool) {
            tmp.addAll(dice.roll());
        }

        Log.d("DiceRoller", "rollDices: " + tmp);
        Map<DiceFace, Integer> res = reduceV2(tmp);
        Log.d("DiceRoller", "rollDices: " + res);

        return res;
    }

    private static Map<DiceFace, Integer> reduceV2(List<DiceFace> faces) {
        Map<DiceFace, Integer> res = new HashMap<>();
        for (DiceFace element : DiceFace.values()) {
            DiceFace inverse = Constants.inversionMap.get(element);
            if (faces.contains(element)) {
                if (inverse == null) {
                    res.put(element, Collections.frequency(faces, element));
                } else {
                    int nbElement = Collections.frequency(faces, element);
                    int nbInverse = Collections.frequency(faces, inverse);
                    if (nbElement > nbInverse) {
                        res.put(element, nbElement - nbInverse);
                    } else if (nbElement < nbInverse) {
                        res.put(inverse, nbInverse - nbElement);
                    }
                }
            }
        }
        return res;
    }

    private static List<DiceFace> removeSomeElements(List<DiceFace> faces, DiceFace element, int number) {
        List<DiceFace> tmpFaces = new ArrayList<>(faces);
        for (DiceFace face : tmpFaces) {
            if (face == element) {
                faces.remove(face);
                number--;
                if (number == 0) {
                    break;
                }
            }
        }
        return faces;
    }

    /*private static DiceFace[] relaunch(IDice dice) {
        DiceFace[] res = dice.roll();
        if (res[0] != DiceFace.SUCCESS_PLUS) {
            return res;
        } else {
            return (DiceFace[]) ArrayUtils.addAll(new DiceFace[]{DiceFace.SUCCESS}, relaunch(dice));
        }
    }*/

}
