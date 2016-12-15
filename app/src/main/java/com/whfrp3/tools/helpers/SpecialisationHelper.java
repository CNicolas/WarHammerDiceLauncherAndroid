package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.LongSparseArray;

import com.whfrp3.R;
import com.whfrp3.model.Skill;
import com.whfrp3.model.Specialisation;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Specialisation helper used to manage specialisations.
 */
public class SpecialisationHelper {

    /**
     * Unique instance of the helper.
     */
    private static SpecialisationHelper instance;

    /**
     * Loaded specialisations.
     */
    private List<Specialisation> specialisations;

    /**
     * Loaded specialisations by id.
     */
    private LongSparseArray<Specialisation> specialisationsById;

    /**
     * Loaded specialisations by skill id.
     */
    private LongSparseArray<List<Specialisation>> specialisationsBySkillId;

    /**
     * Private constructor.
     */
    private SpecialisationHelper() {
        specialisations = new ArrayList<>();
        specialisationsById = new LongSparseArray<>();
        specialisationsBySkillId = new LongSparseArray<>();
        for (Skill skill : SkillHelper.getInstance().getSkills()) {
            specialisationsBySkillId.put(skill.getId(), new ArrayList<Specialisation>());
        }
    }

    /**
     * Getter of the unique instance of the specialisation helper.
     *
     * @return Unique instance of the specialisation helper.
     */
    public static SpecialisationHelper getInstance() {
        if (instance == null) {
            instance = new SpecialisationHelper();
        }

        return instance;
    }

    /**
     * Loads specialisations stored in specialisations.xml file.
     */
    public void loadSpecialisations() {
        try {
            XmlResourceParser xmlParser = WHFRP3Application.getAppContext().getResources().getXml(R.xml.specialisations);

            Specialisation specialisation = null;

            int eventType = xmlParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xmlParser.getName().equals(Specialisation.class.getSimpleName())) {
                        long skillId = Long.valueOf(xmlParser.getAttributeValue(1));

                        specialisation = new Specialisation();
                        specialisation.setId(Long.valueOf(xmlParser.getAttributeValue(0)));
                        specialisation.setSkill(SkillHelper.getInstance().getSkill(skillId));
                    } else if ("Name".equals(xmlParser.getName())) {
                        specialisation.setName(xmlParser.nextText());
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Specialisation.class.getSimpleName()) && specialisation != null) {
                        specialisations.add(specialisation);
                        specialisationsById.put(specialisation.getId(), specialisation);
                        specialisationsBySkillId.get(specialisation.getSkill().getId()).add(specialisation);
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
     * Return all the specialisations.
     *
     * @return All the specialisations.
     */
    public List<Specialisation> getSpecialisations() {
        return specialisations;
    }

    /**
     * Return the specialisation with de given id.
     *
     * @param id Id of the specialisation.
     * @return Specialisation with the given id.
     */
    public Specialisation getSpecialisation(long id) {
        return specialisationsById.get(id);
    }

    /**
     * Return the specialisations of the given skill.
     *
     * @param skillId Id of the skill.
     * @return Specialisations of the given skill.
     */
    public List<Specialisation> getSpecialisationsBySkillId(long skillId) {
        return specialisationsBySkillId.get(skillId);
    }
}
