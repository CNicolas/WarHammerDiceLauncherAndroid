package com.aku.warhammerdicelauncher.database.entries;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface ICharacteristicsEntryConstants extends IEntryConstants {
    String TABLE_NAME = "characteristics";

    String COLUMN_NAME_STRENGTH = "strength";
    String COLUMN_NAME_TOUGHNESS = "toughness";
    String COLUMN_NAME_AGILITY = "agility";
    String COLUMN_NAME_INTELLIGENCE = "intelligence";
    String COLUMN_NAME_WILLPOWER = "willpower";
    String COLUMN_NAME_FELLOWSHIP = "fellowship";

    String COLUMN_NAME_STRENGTH_FORTUNE = "strength_fortune";
    String COLUMN_NAME_TOUGHNESS_FORTUNE = "toughness_fortune";
    String COLUMN_NAME_AGILITY_FORTUNE = "agility_fortune";
    String COLUMN_NAME_INTELLIGENCE_FORTUNE = "intelligence_fortune";
    String COLUMN_NAME_WILLPOWER_FORTUNE = "willpower_fortune";
    String COLUMN_NAME_FELLOWSHIP_FORTUNE = "fellowship_fortune";
}
