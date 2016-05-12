package com.aku.warhammerdicelauncher.services;

import com.aku.warhammerdicelauncher.model.dices.IDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ChallengeDice;
import com.aku.warhammerdicelauncher.model.dices.impl.CharacteristicDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ConservativeDice;
import com.aku.warhammerdicelauncher.model.dices.impl.ExpertiseDice;
import com.aku.warhammerdicelauncher.model.dices.impl.FortuneDice;
import com.aku.warhammerdicelauncher.model.dices.impl.MisfortuneDice;
import com.aku.warhammerdicelauncher.model.dices.impl.RecklessDice;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
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

    public static Map<DiceFace, Integer> rollDices(HandDto dto) {
        List<IDice> pool = createPool(dto);
        List<DiceFace> tmp = new ArrayList<>();
        for (IDice dice : pool) {
            tmp.addAll(dice.roll());
        }
        return reduce(tmp);
    }

    private static List<IDice> createPool(HandDto dto) {
        List<IDice> pool = new ArrayList<>();

        for (int i = 0; i < dto.getCharacteristic(); i++) {
            pool.add(new CharacteristicDice());
        }
        for (int i = 0; i < dto.getReckless(); i++) {
            pool.add(new RecklessDice());
        }
        for (int i = 0; i < dto.getConservative(); i++) {
            pool.add(new ConservativeDice());
        }
        for (int i = 0; i < dto.getExpertise(); i++) {
            pool.add(new ExpertiseDice());
        }
        for (int i = 0; i < dto.getFortune(); i++) {
            pool.add(new FortuneDice());
        }
        for (int i = 0; i < dto.getMisfortune(); i++) {
            pool.add(new MisfortuneDice());
        }
        for (int i = 0; i < dto.getChallenge(); i++) {
            pool.add(new ChallengeDice());
        }

        return pool;
    }

    private static Map<DiceFace, Integer> reduce(List<DiceFace> faces) {
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

}
