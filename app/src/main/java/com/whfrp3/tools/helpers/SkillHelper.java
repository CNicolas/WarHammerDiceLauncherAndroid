package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.LongSparseArray;

import com.whfrp3.R;
import com.whfrp3.model.Skill;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.SkillType;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Skill helper used to manage skills.
 */
public class SkillHelper {

    /**
     * Unique instance of the helper.
     */
    private static SkillHelper instance;

    /**
     * Loaded skills by id.
     */
    private LongSparseArray<Skill> skillsById;

    /**
     * Loaded skills by type.
     */
    private Map<SkillType, List<Skill>> skillsByType;

    /**
     * Private constructor;
     */
    private SkillHelper() {
        skillsById = new LongSparseArray<>();
        skillsByType = new HashMap<>();
        for (SkillType type : SkillType.values()) {
            skillsByType.put(type, new ArrayList<Skill>());
        }
    }

    /**
     * Getter of the unique instance of the skill helper.
     *
     * @return Unique instance of the skill helper.
     */
    public static SkillHelper getInstance() {
        if (instance == null) {
            instance = new SkillHelper();
        }

        return instance;
    }

    /**
     * Loads skills stored in skills.xml file.
     */
    public void loadSkills() {
        try {
            XmlResourceParser xmlParser = WHFRP3Application.getAppContext().getResources().getXml(R.xml.skills);

            Long skillId = null;
            String skillName = null;
            Characteristic skillCharacteristic = null;
            SkillType skillType = null;

            int eventType = xmlParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xmlParser.getName().equals(Skill.class.getSimpleName())) {
                        skillId = Long.valueOf(xmlParser.getAttributeValue(0));
                        skillCharacteristic = Characteristic.valueOf(xmlParser.getAttributeValue(1));
                        skillType = SkillType.valueOf(xmlParser.getAttributeValue(2));
                    } else if ("Name".equals(xmlParser.getName())) {
                        skillName = xmlParser.nextText();
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Skill.class.getSimpleName())
                            && skillId != null && skillName != null && skillCharacteristic != null) {
                        Skill skill = new Skill(skillId, skillName, skillCharacteristic, skillType);

                        skillsById.put(skill.getId(), skill);
                        skillsByType.get(skill.getType()).add(skill);

                        skillId = null;
                        skillName = null;
                        skillCharacteristic = null;
                        skillType = null;
                    }
                }

                eventType = xmlParser.next();
            }

            skillsById.size();
        } catch (Exception e) {
            Log.e("SKILL_LOAD", "Erreur de chargement des compétences.", e);
        }
    }

    /**
     * Return the skill with de given id.
     *
     * @param id Id of the skill.
     * @return Skill with the given id.
     */
    public Skill getSkill(long id) {
        return skillsById.get(id);
    }

    /**
     * Return the skills with the given type.
     *
     * @param type Type of the skills.
     * @return Skills with the given type.
     */
    public List<Skill> getSkillsByType(SkillType type) {
        return skillsByType.get(type);
    }
}
