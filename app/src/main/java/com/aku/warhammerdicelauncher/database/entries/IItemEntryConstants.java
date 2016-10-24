package com.aku.warhammerdicelauncher.database.entries;

/**
 * Interface contenant les informations sur les objets pour la gestion de la base de données.
 */
public interface IItemEntryConstants extends IEntryConstants {
    String TABLE_NAME = "item";

    //region Colonnes des objets standards
    String COLUMN_NAME = "name";
    String COLUMN_DESCRIPTION = "description";
    String COLUMN_ENCUMBRANCE = "encumbrance";
    String COLUMN_QUANTITY = "quantity";
    String COLUMN_QUALITY = "quality";
    String COLUMN_TYPE = "type";
    String COLUMN_PLAYER_ID = "player_id";
    //endregion

    //region Colonnes supplémentaires pour les objets utilisables
    String COLUMN_LOAD = "load";
    //endregion

    //region Colonnes supplémentaires pour les armures
    String COLUMN_SOAK = "soak";
    String COLUMN_DEFENSE = "defense";
    //endregion

    //region Colonnes supplémentaires pour les armures
    String COLUMN_DAMAGE = "damage";
    String COLUMN_CRITICAL_LEVEL = "critical_level";
    String COLUMN_RANGE = "range";
    //endregion

    //region Requêtes SQL
    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_ENCUMBRANCE + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    COLUMN_QUANTITY + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    COLUMN_QUALITY + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    COLUMN_TYPE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    COLUMN_PLAYER_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +

                    COLUMN_LOAD + INTEGER_TYPE + COMMA_SEP +

                    COLUMN_SOAK + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_DEFENSE + INTEGER_TYPE + COMMA_SEP +

                    COLUMN_DAMAGE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_CRITICAL_LEVEL + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_RANGE + TEXT_TYPE +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //endregion
}
