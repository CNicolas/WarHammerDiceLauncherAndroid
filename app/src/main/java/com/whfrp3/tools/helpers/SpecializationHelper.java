package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.LongSparseArray;

import com.whfrp3.R;
import com.whfrp3.model.Skill;
import com.whfrp3.model.Specialization;
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
    private List<Specialization> specializations;

    /**
     * Loaded specializations by id.
     */
    private LongSparseArray<Specialization> specialisationsById;

    /**
     * Loaded specializations by skill id.
     */
    private LongSparseArray<List<Specialization>> specialisationsBySkillId;

    /**
     * Private constructor.
     */
    private SpecializationHelper() {
        specializations = new ArrayList<>();
        specialisationsById = new LongSparseArray<>();
        specialisationsBySkillId = new LongSparseArray<>();
        for (Skill skill : SkillHelper.getInstance().getSkills()) {
            specialisationsBySkillId.put(skill.getId(), new ArrayList<Specialization>());
        }
    }

    /**
     * Getter of the unique instance of the specialisation helper.
     *
     * @return Unique instance of the specialisation helper.
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
    public void loadSpecialisations() {
        try {
            XmlResourceParser xmlParser = WHFRP3Application.getAppContext().getResources().getXml(R.xml.specialisations);

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
                        specialisationsById.put(specialization.getId(), specialization);
                        specialisationsBySkillId.get(specialization.getSkill().getId()).add(specialization);
                    }
                }

                eventType = xmlParser.next();
            }

            specialisationsById.size();
        } catch (Exception e) {
            Log.e("SPECIALISATIONS_LOAD", "Erreur de chargement des spécialisations.", e);
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
     * Return the specialisation with de given id.
     *
     * @param id Id of the specialisation.
     * @return Specialization with the given id.
     */
    public Specialization getSpecialisation(long id) {
        return specialisationsById.get(id);
    }

    /**
     * Return the specializations of the given skill.
     *
     * @param skillId Id of the skill.
     * @return Specialisations of the given skill.
     */
    public List<Specialization> getSpecialisationsBySkillId(long skillId) {
        return specialisationsBySkillId.get(skillId);
    }
}
