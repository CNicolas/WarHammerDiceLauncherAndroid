package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LongSparseArray;

import com.whfrp3.R;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.Skill;
import com.whfrp3.model.Specialization;
import com.whfrp3.ihm.model.SpecializationSearchFields;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Specialization helper used to manage specializations.
 */
public class SpecializationHelper {

    /**
     * Unique instance of the helper.
     */
    private static SpecializationHelper instance;

    /**
     * Loaded specializations.
     */
    private final List<Specialization> specializations;

    /**
     * Loaded specializations by id.
     */
    private final LongSparseArray<Specialization> specializationsById;

    /**
     * Loaded specializations by skill id.
     */
    private final LongSparseArray<List<Specialization>> specializationsBySkillId;

    /**
     * Private constructor.
     */
    private SpecializationHelper() {
        specializations = new ArrayList<>();
        specializationsById = new LongSparseArray<>();
        specializationsBySkillId = new LongSparseArray<>();
        for (Skill skill : SkillHelper.getInstance().getSkills()) {
            specializationsBySkillId.put(skill.getId(), new ArrayList<Specialization>());
        }
    }

    /**
     * Getter of the unique instance of the specialization helper.
     *
     * @return Unique instance of the specialization helper.
     */
    public static SpecializationHelper getInstance() {
        if (instance == null) {
            instance = new SpecializationHelper();
        }

        return instance;
    }

    /**
     * Loads specializations stored in specializations.xml file.
     */
    public void loadSpecializations() {
        try {
            XmlResourceParser xmlParser = WHFRP3Application.getAppContext().getResources().getXml(R.xml.specializations);

            Specialization specialization = null;

            int eventType = xmlParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xmlParser.getName().equals(Specialization.class.getSimpleName())) {
                        long skillId = Long.valueOf(xmlParser.getAttributeValue(1));

                        specialization = new Specialization();
                        specialization.setId(Long.valueOf(xmlParser.getAttributeValue(0)));
                        specialization.setSkill(SkillHelper.getInstance().getSkill(skillId));
                    } else if ("Name".equals(xmlParser.getName())) {
                        specialization.setName(xmlParser.nextText());
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Specialization.class.getSimpleName()) && specialization != null) {
                        specializations.add(specialization);
                        specializationsById.put(specialization.getId(), specialization);
                        specializationsBySkillId.get(specialization.getSkill().getId()).add(specialization);
                    }
                }

                eventType = xmlParser.next();
            }

            specializationsById.size();
        } catch (Exception e) {
            Log.e("SPECIALIZATIONS_LOAD", "Erreur de chargement des spécialisations.", e);
        }
    }

    /**
     * Return all the specializations.
     *
     * @return All the specializations.
     */
    public List<Specialization> getSpecializations() {
        return specializations;
    }

    /**
     * Return the specialization with de given id.
     *
     * @param id Id of the specialization.
     * @return Specialization with the given id.
     */
    public Specialization getSpecialization(long id) {
        return specializationsById.get(id);
    }

    /**
     * Return the specializations of the given skill.
     *
     * @param skillId Id of the skill.
     * @return Specializations of the given skill.
     */
    public List<Specialization> getSpecializationsBySkillId(long skillId) {
        return specializationsBySkillId.get(skillId);
    }

    public List<String> getSpecializationsName(List<Specialization> specializations) {
        List<String> res = new ArrayList<>();

        for (Specialization specialization : specializations) {
            res.add(specialization.getName());
        }

        return res;
    }

    //region Search
    public List<Specialization> search(SpecializationSearchFields fields) {
        List<Specialization> specializations = getSpecializations();

        List<Specialization> res = new ArrayList<>();
        for (Specialization specialization : specializations) {
            if (isSimilarCharacteristicOrNull(specialization, fields.getCharacteristic())
                    && isSimilarSkillOrNull(specialization, fields.getSkill())
                    && isSpecializationNameContainingOrNull(specialization, fields.getName())) {
                res.add(specialization);
            }
        }

        return res;
    }

    private boolean isSimilarCharacteristicOrNull(Specialization specialization, @Nullable Characteristic characteristic) {
        return characteristic == null || specialization.getSkill().getCharacteristic().equals(characteristic);
    }

    private boolean isSpecializationNameContainingOrNull(Specialization specialization, @Nullable String textToSearch) {
        return isNullOrEmpty(textToSearch) || specialization.getName().toLowerCase().contains(textToSearch.toLowerCase());
    }

    private boolean isSimilarSkillOrNull(Specialization specialization, @Nullable Skill skill) {
        return skill == null || specialization.getSkill().equals(skill);
    }

    private boolean isNullOrEmpty(@Nullable String text) {
        return text == null || text.isEmpty();
    }
    //endregion
}
