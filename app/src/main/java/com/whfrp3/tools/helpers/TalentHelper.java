package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LongSparseArray;

import com.whfrp3.R;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.model.talents.TalentSearchFields;
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
     * Loaded talents.
     */
    private final List<Talent> talents;

    /**
     * Loaded talents by id.
     */
    private final LongSparseArray<Talent> talentsById;

    /**
     * Loaded talents by type.
     */
    private final Map<TalentType, List<Talent>> talentsByType;

    /**
     * Default private constructor.
     */
    private TalentHelper() {
        talents = new ArrayList<>();
        talentsById = new LongSparseArray<>();
        talentsByType = new HashMap<>();
        for (TalentType type : TalentType.values()) {
            talentsByType.put(type, new ArrayList<Talent>());
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
     * Loads talents stored in search.xmlfile.
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
                        talent.setId(Long.valueOf(xmlParser.getAttributeValue(0)));
                        talent.setType(TalentType.valueOf(xmlParser.getAttributeValue(1)));
                        talent.setCooldown(CooldownType.valueOf(xmlParser.getAttributeValue(2)));
                    } else if ("Name".equals(xmlParser.getName())) {
                        talent.setName(xmlParser.nextText());
                    } else if ("Description".equals(xmlParser.getName())) {
                        talent.setDescription(xmlParser.nextText());
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Talent.class.getSimpleName()) && talent != null) {
                        talents.add(talent);
                        talentsById.put(talent.getId(), talent);
                        talentsByType.get(talent.getType()).add(talent);

                        talent = null;
                    }
                }

                eventType = xmlParser.next();
            }

            talentsByType.size();
        } catch (Exception e) {
            Log.e("TALENT_LOAD", "Erreur de chargement des search.", e);
        }
    }

    /**
     * Returns all loaded talents.
     *
     * @return All loaded talents.
     */
    public List<Talent> getTalents() {
        return talents;
    }

    /**
     * Return the talent with the given id.
     *
     * @param talentId Id of the talent to return.
     * @return Talent with the given id.
     */
    public Talent getTalentById(long talentId) {
        return talentsById.get(talentId);
    }

    /**
     * Return the talents of the given type.
     *
     * @param type Type of the talents to return.
     * @return Talents of the given type.
     */
    public List<Talent> getTalentsByType(TalentType type) {
        return talentsByType.get(type);
    }

    //region Search
    public List<Talent> search(TalentSearchFields fields) {
        List<Talent> talents;
        if (fields.getTalentType() == null) {
            talents = getTalents();
        } else {
            talents = getTalentsByType(fields.getTalentType());
        }

        List<Talent> res = new ArrayList<>();
        for (Talent talent : talents) {
            if (isSimilarCooldownOrNull(talent, fields.getCooldownType())
                    && isTalentNameContainingOrNull(talent, fields.getName())
                    && isTalentDescriptionContainingOrNull(talent, fields.getDescription())) {
                res.add(talent);
            }
        }

        return res;
    }

    private boolean isSimilarCooldownOrNull(Talent talent, @Nullable CooldownType cooldownType) {
        return cooldownType == null || talent.getCooldown().equals(cooldownType);
    }

    private boolean isTalentNameContainingOrNull(Talent talent, @Nullable String textToSearch) {
        return isNullOrEmpty(textToSearch) || talent.getName().toLowerCase().contains(textToSearch.toLowerCase());
    }

    private boolean isTalentDescriptionContainingOrNull(Talent talent, @Nullable String textToSearch) {
        return isNullOrEmpty(textToSearch) || talent.getDescription().toLowerCase().contains(textToSearch.toLowerCase());
    }

    private boolean isNullOrEmpty(@Nullable String text) {
        return text == null || text.isEmpty();
    }
    //endregion
}
