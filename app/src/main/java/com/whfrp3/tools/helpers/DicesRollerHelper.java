package com.whfrp3.tools.helpers;

import com.whfrp3.model.dices.DiceFaces;
import com.whfrp3.model.dices.Hand;
import com.whfrp3.model.dices.IDice;
import com.whfrp3.model.dices.impl.ChallengeDice;
import com.whfrp3.model.dices.impl.CharacteristicDice;
import com.whfrp3.model.dices.impl.ConservativeDice;
import com.whfrp3.model.dices.impl.ExpertiseDice;
import com.whfrp3.model.dices.impl.FortuneDice;
import com.whfrp3.model.dices.impl.MisfortuneDice;
import com.whfrp3.model.dices.impl.RecklessDice;
import com.whfrp3.tools.constants.IHandConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper that launches hands of dices.
 */
public class DicesRollerHelper {

    /**
     * Simply launch the given Hand.
     *
     * @param hand the hand to launch.
     * @return a map containing the result faces with the according number.
     */
    public static Map<DiceFaces, Integer> rollDices(Hand hand) {
        List<IDice> pool = createPool(hand);
        List<DiceFaces> tmp = new ArrayList<>();
        for (IDice dice : pool) {
            tmp.addAll(dice.roll());
        }
        return reduce(tmp);
    }

    /**
     * Check if the given map of dices is successful.
     *
     * @param handResults the result of a launch.
     * @return successful or not.
     */
    public static boolean isSuccessful(Map<DiceFaces, Integer> handResults) {
        return handResults.containsKey(DiceFaces.SUCCESS);
    }

    /**
     * Create the pool of dices from the Hand
     *
     * @param hand the Hand of dices.
     * @return the list of all the dices.
     */
    private static List<IDice> createPool(Hand hand) {
        List<IDice> pool = new ArrayList<>();

        for (int i = 0; i < hand.getCharacteristic(); i++) {
            pool.add(new CharacteristicDice());
        }
        for (int i = 0; i < hand.getReckless(); i++) {
            pool.add(new RecklessDice());
        }
        for (int i = 0; i < hand.getConservative(); i++) {
            pool.add(new ConservativeDice());
        }
        for (int i = 0; i < hand.getExpertise(); i++) {
            pool.add(new ExpertiseDice());
        }
        for (int i = 0; i < hand.getFortune(); i++) {
            pool.add(new FortuneDice());
        }
        for (int i = 0; i < hand.getMisfortune(); i++) {
            pool.add(new MisfortuneDice());
        }
        for (int i = 0; i < hand.getChallenge(); i++) {
            pool.add(new ChallengeDice());
        }

        return pool;
    }

    /**
     * Remove the opposites from all the resulting dices faces.
     *
     * @param faces all the results of a launch.
     * @return the effective result of the launch.
     */
    private static Map<DiceFaces, Integer> reduce(List<DiceFaces> faces) {
        Map<DiceFaces, Integer> res = new HashMap<>();
        for (DiceFaces element : DiceFaces.values()) {
            DiceFaces inverse = IHandConstants.inversionMap.get(element);
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
}
