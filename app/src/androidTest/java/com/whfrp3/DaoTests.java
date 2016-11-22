package com.whfrp3;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.MediumTest;

import com.whfrp3.database.Database;
import com.whfrp3.model.enums.Race;
import com.whfrp3.model.player.Characteristics;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.enums.Characteristic;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public class DaoTests extends AndroidTestCase {
    private Database database;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        database = new Database(context);
        database.open();

        insertCharacteristicDto();
        insertPlayerDto();
        insertSkillDto();
    }

    @Override
    public void tearDown() throws Exception {
        database.close();
        super.tearDown();
    }

    @MediumTest
    public void testCharacteristicsDao() throws Exception {
        List<Characteristics> res = database.getCharacteristicsDao().findAll();

        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @MediumTest
    public void testPlayerDao() throws Exception {
        List<Player> res = database.getPlayerDao().findAll();

        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @MediumTest
    public void testSkillDao() throws Exception {
        List<Skill> res = database.getSkillDao().findAllByPlayerId(database.getPlayerDao().findById(1).getId());
        assertNotNull(res);
        assertEquals(3, res.size());
    }

    private void insertCharacteristicDto() {
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

        database.getCharacteristicsDao().insert(dto);
    }

    private void insertPlayerDto() {
        Player dto = new Player();

        dto.setName("Aku");
        dto.setRace(Race.WOOD_ELF);
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

        dto.setCharacteristics(database.getCharacteristicsDao().findById(1));

        database.getPlayerDao().insert(dto);
    }

    private void insertSkillDto() {
        Player player = database.getPlayerDao().findById(1);

        Skill dto1 = new Skill();
        dto1.setCharacteristic(Characteristic.AGILITY);
        dto1.setLevel(1);
        dto1.setName("Capacité de Tir");
        dto1.setPlayerId(player.getId());

        Skill dto2 = new Skill();
        dto2.setCharacteristic(Characteristic.INTELLIGENCE);
        dto2.setLevel(0);
        dto2.setName("Observation");
        dto2.setPlayerId(player.getId());

        Skill dto3 = new Skill();
        dto3.setCharacteristic(Characteristic.WILLPOWER);
        dto3.setLevel(0);
        dto3.setName("Discipline");
        dto3.setPlayerId(player.getId());

        database.getSkillDao().insert(dto1);
        database.getSkillDao().insert(dto2);
        database.getSkillDao().insert(dto3);
    }
}
