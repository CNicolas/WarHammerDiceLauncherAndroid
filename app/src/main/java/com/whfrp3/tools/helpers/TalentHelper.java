package com.whfrp3.tools.helpers;

import com.whfrp3.model.player.Talent;

import java.util.ArrayList;
import java.util.List;

/**
 * Talent helper used to manage talents.
 */
public class TalentHelper {

    /**
     * Unique instance of the helper.
     */
    private static TalentHelper instance;

    /**
     * Loaded talents.
     */
    private List<Talent> talents;

    /**
     * Default private constructor.
     */
    private TalentHelper() {
        talents = new ArrayList<>();
    }

    /**
     * Getter of the unique instance of the talent helper.
     *
     * @return Unique instance of the talent helper.
     */
    public static TalentHelper getInstance() {
        if (instance == null) {
            instance = new TalentHelper();
        }

        return instance;
    }
}
