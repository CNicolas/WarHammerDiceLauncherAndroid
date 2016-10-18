package com.aku.warhammerdicelauncher;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.MediumTest;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.CharacteristicsDao;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.database.dao.SkillDao;
import com.aku.warhammerdicelauncher.model.player.Characteristics;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.Skill;
import com.aku.warhammerdicelauncher.tools.enums.Characteristic;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public class DaoTests extends AndroidTestCase {
    private WarHammerDatabaseHelper warhammerDataBaseHelper;
    private CharacteristicsDao characteristicsDao;
    private PlayerDao playerDao;
    private SkillDao skillDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        warhammerDataBaseHelper = new WarHammerDatabaseHelper(context);

        characteristicsDao = new CharacteristicsDao(warhammerDataBaseHelper);
        playerDao = new PlayerDao(warhammerDataBaseHelper);
        skillDao = new SkillDao(warhammerDataBaseHelper);

        insertCharacteristicDto();
        insertPlayerDto();
        insertSkillDto();
    }

    @Override
    public void tearDown() throws Exception {
        warhammerDataBaseHelper.close();
        super.tearDown();
    }

    @MediumTest
    public void testCharacteristicsDao() throws Exception {
        List<Characteristics> res = characteristicsDao.findAll();

        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @MediumTest
    public void testPlayerDao() throws Exception {
        List<Player> res = playerDao.findAll();

        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @MediumTest
    public void testSkillDao() throws Exception {
        List<Skill> res = skillDao.findAllByPlayer(playerDao.findById(1));
        assertNotNull(res);
        assertEquals(3, res.size());
    }

    private long insertCharacteristicDto() {
        Characteristics dto = new Characteristics();
        dto.setStrength(1);
        dto.setToughness(2);
        dto.setAgility(3);
        dto.setIntelligence(4);
        dto.setWillpower(5);
        dto.setFellowship(6);

        dto.setStrength_fortune(6);
        dto.setToughness_fortune(5);
        dto.setAgility_fortune(4);
        dto.setIntelligence_fortune(3);
        dto.setWillpower_fortune(2);
        dto.setFellowship_fortune(1);

        return characteristicsDao.insert(dto);
    }

    private long insertPlayerDto() {
        Player dto = new Player();

        dto.setName("Aku");
        dto.setRace("Elfe");
        dto.setAge(59);
        dto.setSize(200);
        dto.setDescription("OK LOL");

        dto.setRank(1);
        dto.setExperience(0);
        dto.setMax_experience(9);
        dto.setWounds(0);
        dto.setMax_wounds(12);
        dto.setReckless(0);
        dto.setMax_reckless(2);
        dto.setConservative(0);
        dto.setMax_conservative(2);

        dto.setMoney_brass(48);
        dto.setMoney_silver(13);
        dto.setMoney_gold(3);

        dto.setCharacteristics(characteristicsDao.findById(1));

        return playerDao.insert(dto);
    }

    private long[] insertSkillDto() {
        Skill dto1 = new Skill();
        dto1.setCharacteristic(Characteristic.AGILITY);
        dto1.setLevel(1);
        dto1.setName("Capacité de Tir");

        Skill dto2 = new Skill();
        dto2.setCharacteristic(Characteristic.INTELLIGENCE);
        dto2.setLevel(0);
        dto2.setName("Observation");

        Skill dto3 = new Skill();
        dto3.setCharacteristic(Characteristic.WILLPOWER);
        dto3.setLevel(0);
        dto3.setName("Discipline");

        return new long[]{skillDao.insert(dto1, playerDao.findById(1)),
                skillDao.insert(dto2, playerDao.findById(1)),
                skillDao.insert(dto3, playerDao.findById(1))};
    }
}
