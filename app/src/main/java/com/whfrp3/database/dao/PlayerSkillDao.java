package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IPlayerSkillEntryConstants;
import com.whfrp3.model.Skill;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.tools.helpers.SkillHelper;

import java.util.List;

/**
 * DAO of player skills.
 */
public class PlayerSkillDao extends AbstractDao<PlayerSkill> implements IPlayerSkillEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerSkillDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    public List<PlayerSkill> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Update

    @Override
    public void update(PlayerSkill model) {
        ContentValues values = contentValuesFromModel(model);
        String[] filters = {String.valueOf(model.getSkill().getId()), String.valueOf(model.getPlayerId())};

        mDatabase.update(mTableName, values, String.format("%s = ? AND %s = ?", COLUMN_SKILL_ID, COLUMN_PLAYER_ID), filters);
    }

    //endregion

    //region Delete

    /**
     * Removes all player skills with the given player id from the database.
     *
     * @param playerId Player id.
     */
    public void deleteAllByPlayerId(long playerId) {
        String[] filters = {String.valueOf(playerId)};

        mDatabase.delete(mTableName, String.format("%s = ?", COLUMN_PLAYER_ID), filters);
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(PlayerSkill playerSkill) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_SKILL_ID, playerSkill.getSkill().getId());
        values.put(COLUMN_PLAYER_ID, playerSkill.getPlayerId());
        values.put(COLUMN_LEVEL, playerSkill.getLevel());

        return values;
    }

    @Override
    protected PlayerSkill createModelFromCursor(Cursor cursor) {
        long skillId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_SKILL_ID));
        long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_ID));
        int level = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LEVEL));

        Skill skill = SkillHelper.getInstance().getSkill(skillId);

        return new PlayerSkill(skill, playerId, level);
    }

    //endregion
}
