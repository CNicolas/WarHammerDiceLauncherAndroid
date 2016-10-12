package com.aku.warhammerdicelauncher;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.MediumTest;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.CharacteristicsDao;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.database.dao.SkillDao;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;
import com.aku.warhammerdicelauncher.model.dto.PlayerDto;
import com.aku.warhammerdicelauncher.model.dto.SkillDto;
import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public class DaoTests extends AndroidTestCase {
    private WarHammerDatabaseHelper warhammerDataBaseHelper;
    private CharacteristicsDao _characteristicsDao;
    private PlayerDao _playerDao;
    private SkillDao _skillDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        warhammerDataBaseHelper = new WarHammerDatabaseHelper(context);

        _characteristicsDao = new CharacteristicsDao(warhammerDataBaseHelper);
        _playerDao = new PlayerDao(warhammerDataBaseHelper);
        _skillDao = new SkillDao(warhammerDataBaseHelper);

        insertCharacteristicDto();
        insertPlayerDto();
        long[] val = insertSkillDto();
    }

    @Override
    public void tearDown() throws Exception {
        warhammerDataBaseHelper.close();
        super.tearDown();
    }

    @MediumTest
    public void testCharacteristicsDao() throws Exception {
        List<CharacteristicsDto> res = _characteristicsDao.findAll();

        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @MediumTest
    public void testPlayerDao() throws Exception {
        List<PlayerDto> res = _playerDao.findAll();

        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @MediumTest
    public void testSkillDao() throws Exception {
        List<SkillDto> res = _skillDao.findAllByPlayer(_playerDao.findById(1));
        assertNotNull(res);
        assertEquals(3, res.size());
    }

    private long insertCharacteristicDto() {
        CharacteristicsDto dto = new CharacteristicsDto();
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

        return _characteristicsDao.insert(dto);
    }

    private long insertPlayerDto() {
        PlayerDto dto = new PlayerDto();

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

        dto.setCharacteristics(_characteristicsDao.findById(1));

        return _playerDao.insert(dto);
    }

    private long[] insertSkillDto() {
        SkillDto dto1 = new SkillDto();
        dto1.setCharacteristic(Characteristic.AGILITY);
        dto1.setLevel(1);
        dto1.setName("Capacité de Tir");

        SkillDto dto2 = new SkillDto();
        dto2.setCharacteristic(Characteristic.INTELLIGENCE);
        dto2.setLevel(0);
        dto2.setName("Observation");

        SkillDto dto3 = new SkillDto();
        dto3.setCharacteristic(Characteristic.WILLPOWER);
        dto3.setLevel(0);
        dto3.setName("Discipline");

        return new long[]{_skillDao.insert(dto1, _playerDao.findById(1)),
                _skillDao.insert(dto2, _playerDao.findById(1)),
                _skillDao.insert(dto3, _playerDao.findById(1))};
    }
}
