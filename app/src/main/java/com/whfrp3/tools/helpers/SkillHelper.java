package com.whfrp3.tools.helpers;

import android.content.res.XmlResourceParser;
import android.util.Log;

import com.whfrp3.R;
import com.whfrp3.model.Skill;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
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
    private Map<Long, Skill> skills;

    /**
     * Private constructor;
     */
    private SkillHelper() {

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

            Skill skill = null;

            int eventType = xmlParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    if (xmlParser.getName().equals(Skill.class.getSimpleName())) {
                        skill = new Skill();
                        skill.setId(Long.valueOf(xmlParser.getAttributeValue(0)));
                        skill.setCharacteristic(Characteristic.valueOf(xmlParser.getAttributeValue(1)));
                    } else if ("Name".equals(xmlParser.getName())) {
                        skill.setName(xmlParser.nextText());
                    }
                } else if (eventType == XmlResourceParser.END_TAG) {
                    if (xmlParser.getName().equals(Skill.class.getSimpleName()) && skill != null) {
                        skills.put(skill.getId(), skill);
                        skill = null;
                    }
                }

                eventType = xmlParser.next();
            }

            skills.size();
        } catch (Exception e) {
            Log.e("SKILL_LOAD", "Erreur de chargement des compétences.", e);
        }
    }
}
