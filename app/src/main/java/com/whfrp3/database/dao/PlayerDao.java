package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IEntryConstants;
import com.whfrp3.database.entries.IPlayerEntryConstants;
import com.whfrp3.model.enums.MoneyType;
import com.whfrp3.model.enums.Race;
import com.whfrp3.model.player.Money;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.PlayerCharacteristic;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.model.player.PlayerSpecialization;
import com.whfrp3.model.player.PlayerTalent;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.tools.WHFRP3Application;

import java.util.List;

/**
 * DAO of players.
 */
public class PlayerDao extends AbstractDaoWithId<Player> implements IPlayerEntryConstants {

    //region DAOs

    /**
     * DAO of player characteristics.
     */
    private final PlayerCharacteristicDao mPlayerCharacteristicDao;

    /**
     * DAO of player skills.
     */
    private final PlayerSkillDao mPlayerSkillDao;

    /**
     * DAO of player specializations.
     */
    private final PlayerSpecializationDao mPlayerSpecializationDao;

    /**
     * DAO of player talents.
     */
    private final PlayerTalentDao mPlayerTalentDao;

    /**
     * DAO of items.
     */
    private final ItemDao mItemDao;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);

        mPlayerCharacteristicDao = WHFRP3Application.getDatabase().getmPlayerCharacteristic();
        mPlayerSkillDao = WHFRP3Application.getDatabase().getPlayerSkillDao();
        mPlayerSpecializationDao = WHFRP3Application.getDatabase().getPlayerSpecializationDao();
        mPlayerTalentDao = WHFRP3Application.getDatabase().getPlayerTalentDao();
        mItemDao = WHFRP3Application.getDatabase().getItemDao();
    }

    //endregion

    //region Find

    /**
     * Find all players names.
     *
     * @return Players names.
     */
    public List<String> findAllNames() {
        return findAllValuesOfColumn(COLUMN_NAME);
    }

    /**
     * Find a player by its name.
     *
     * @param name Player name.
     * @return Player found.
     */
    public Player findByName(String name) {
        Player player = findByColumn(COLUMN_NAME, name);
        player.setPlayerSkills(mPlayerSkillDao.findAllByPlayerId(player.getId()));

        return player;
    }

    //endregion

    //region Insert

    @Override
    public void insert(Player player) {
        // Insert player
        super.insert(player);

        // Insert characteristics
        for (PlayerCharacteristic playerCharacteristic : player.getPlayerCharacteristics()) {
            playerCharacteristic.setPlayerId(player.getId());
            mPlayerCharacteristicDao.insert(playerCharacteristic);
        }

        // Insert skills
        for (PlayerSkill playerSkill : player.getPlayerSkills()) {
            playerSkill.setPlayerId(player.getId());
            mPlayerSkillDao.insert(playerSkill);
        }

        // Insert specializations
        for (PlayerSpecialization playerSpecialization : player.getPlayerSpecializations()) {
            playerSpecialization.setPlayerId(player.getId());
            mPlayerSpecializationDao.insert(playerSpecialization);
        }

        // Insert talents
        for (PlayerTalent playerTalent : player.getPlayerTalents()) {
            playerTalent.setPlayerId(player.getId());
            mPlayerTalentDao.insert(playerTalent);
        }

        // Insert items
        for (Item item : player.getInventory()) {
            item.setPlayerId(player.getId());
            mItemDao.insert(item);
        }
    }

    //endregion

    //region Update

    @Override
    public void update(Player player) {
        // Update player
        super.update(player);

        // Update characteristics
        mPlayerCharacteristicDao.deleteAllByPlayerId(player.getId());
        for (PlayerCharacteristic playerCharacteristic : player.getPlayerCharacteristics()) {
            mPlayerCharacteristicDao.insert(playerCharacteristic);
        }

        // Update skills
        mPlayerSkillDao.deleteAllByPlayerId(player.getId());
        for (PlayerSkill skill : player.getPlayerSkills()) {
            mPlayerSkillDao.insert(skill);
        }

        // Update specializations
        mPlayerSpecializationDao.deleteAllByPlayerId(player.getId());
        for (PlayerSpecialization playerSpecialization : player.getPlayerSpecializations()) {
            mPlayerSpecializationDao.insert(playerSpecialization);
        }

        // Update talents
        mPlayerTalentDao.deleteAllByPlayerId(player.getId());
        for (PlayerTalent playerTalent : player.getPlayerTalents()) {
            mPlayerTalentDao.insert(playerTalent);
        }

        // Update items
        for (Item item : player.getInventory()) {
            item.setPlayerId(player.getId());

            if (item.getId() == 0) {
                mItemDao.insert(item);
            } else {
                mItemDao.update(item);
            }
        }

        // Remove obsolete items
        for (long idItem : player.getItemToRemove()) {
            mItemDao.delete(idItem);
        }
        player.getItemToRemove().clear();
    }

    //endregion

    //region Private methods

    @Override
    protected ContentValues contentValuesFromModel(Player player) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, player.getName());
        values.put(COLUMN_RACE, player.getRace().toString());
        values.put(COLUMN_AGE, player.getAge());
        values.put(COLUMN_SIZE, player.getSize());
        values.put(COLUMN_DESCRIPTION, player.getDescription());

        values.put(COLUMN_CAREER, player.getCareer());
        values.put(COLUMN_RANK, player.getRank());
        values.put(COLUMN_EXPERIENCE, player.getExperience());
        values.put(COLUMN_MAX_EXPERIENCE, player.getMax_experience());
        values.put(COLUMN_WOUNDS, player.getWounds());
        values.put(COLUMN_MAX_WOUNDS, player.getMax_wounds());
        values.put(COLUMN_CORRUPTION, player.getCorruption());
        values.put(COLUMN_MAX_CORRUPTION, player.getMax_corruption());
        values.put(COLUMN_RECKLESS, player.getReckless());
        values.put(COLUMN_MAX_RECKLESS, player.getMax_reckless());
        values.put(COLUMN_CONSERVATIVE, player.getConservative());
        values.put(COLUMN_MAX_CONSERVATIVE, player.getMax_conservative());
        values.put(COLUMN_STRESS, player.getStress());
        values.put(COLUMN_EXERTION, player.getExertion());

        values.put(COLUMN_MONEY_BRASS, player.getMoney().getAmount(MoneyType.BRASS));
        values.put(COLUMN_MONEY_SILVER, player.getMoney().getAmount(MoneyType.SILVER));
        values.put(COLUMN_MONEY_GOLD, player.getMoney().getAmount(MoneyType.GOLD));

        return values;
    }

    @Override
    protected Player createModelFromCursor(Cursor cursor) {
        Player model = new Player();

        model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(IEntryConstants.COLUMN_ID)));

        model.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
        model.setRace(Race.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RACE))));
        model.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)));
        model.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SIZE)));
        model.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));

        model.setCareer(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAREER)));
        model.setRank(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RANK)));
        model.setExperience(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPERIENCE)));
        model.setMax_experience(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_EXPERIENCE)));
        model.setWounds(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WOUNDS)));
        model.setMax_wounds(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_WOUNDS)));
        model.setCorruption(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CORRUPTION)));
        model.setMax_corruption(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_CORRUPTION)));
        model.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RECKLESS)));
        model.setMax_reckless(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_RECKLESS)));
        model.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSERVATIVE)));
        model.setMax_conservative(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_CONSERVATIVE)));
        model.setStress(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STRESS)));
        model.setExertion(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERTION)));

        // Set player's money
        int brassAmount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MONEY_BRASS));
        int silverAmount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MONEY_SILVER));
        int goldAmount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MONEY_GOLD));

        model.setMoney(new Money(goldAmount, silverAmount, brassAmount));

        // Find playerCharacteristics
        List<PlayerCharacteristic> playerCharacteristics = mPlayerCharacteristicDao.findAllByPlayerId(model.getId());
        model.setPlayerCharacteristics(playerCharacteristics);

        // Find playerSkills
        List<PlayerSkill> playerSkills = mPlayerSkillDao.findAllByPlayerId(model.getId());
        model.setPlayerSkills(playerSkills);

        // Find playerSpecializations
        List<PlayerSpecialization> playerSpecializations = mPlayerSpecializationDao.findAllByPlayerId(model.getId());
        model.setPlayerSpecializations(playerSpecializations);

        // Find playerTalents
        List<PlayerTalent> playerTalents = mPlayerTalentDao.findAllByPlayerId(model.getId());
        model.setPlayerTalents(playerTalents);

        // Find items
        List<Item> items = mItemDao.findAllByPlayerId(model.getId());
        model.setInventory(items);

        return model;
    }

    //endregion
}
