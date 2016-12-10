package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.util.Log;

import com.whfrp3.R;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.player.Talent;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Talent helper used to manage talents.
 */
public class TalentHelper {

    /**
     * Unique instance of the helper.
     */
    private static TalentHelper instance;

    /**
     * Loaded talents by type.
     */
    private Map<TalentType, List<Talent>> talents;

    /**
     * Default private constructor.
     */
    private TalentHelper() {
        talents = new HashMap<>();
        for (TalentType type : TalentType.values()) {
            talents.put(type, new ArrayList<Talent>());
        }
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

    /**
     * Loads talents stored in talents.xml file.
     */
    public void loadTalents() {
        try {
            XmlResourceParser xmlParser = WHFRP3Application.getAppContext().getResources().getXml(R.xml.talents);

            Talent talent = null;

            int eventType = xmlParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xmlParser.getName().equals(Talent.class.getSimpleName())) {
                        talent = new Talent();
                        talent.setType(TalentType.valueOf(xmlParser.getAttributeValue(0)));
                        talent.setCooldown(CooldownType.valueOf(xmlParser.getAttributeValue(1)));
                    } else if ("Name".equals(xmlParser.getName())) {
                        talent.setName(xmlParser.nextText());
                    } else if ("Description".equals(xmlParser.getName())) {
                        talent.setDescription(xmlParser.nextText());
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Talent.class.getSimpleName()) && talent != null) {
                        talents.get(talent.getType()).add(talent);
                        talent = null;
                    }
                }

                eventType = xmlParser.next();
            }

            talents.size();
        } catch (Exception e) {
            Log.e("TALENT_LOAD", "Erreur de chargement des talents.", e);
        }
    }

    /**
     * Return the talents of the given type.
     *
     * @param type Type of the talents to return.
     * @return Talents of the given type.
     */
    public List<Talent> getTalentsByType(TalentType type) {
        return talents.get(type);
    }

    /**
     * Returns all loaded talents.
     *
     * @return All loaded talents.
     */
    public List<Talent> getAllTalents() {
        List<Talent> rTalents = new ArrayList<>();

        for (TalentType type : TalentType.values()) {
            rTalents.addAll(talents.get(type));
        }

        return rTalents;
    }
}
