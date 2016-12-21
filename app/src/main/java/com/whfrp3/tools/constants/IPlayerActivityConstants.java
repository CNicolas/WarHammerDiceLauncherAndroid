package com.whfrp3.tools.constants;

/**
 * Constants used mainly in PlayerActivity.
 */
public interface IPlayerActivityConstants {
    String IS_IN_EDITION_BUNDLE_TAG = "isInEdition";
    String CURRENT_FRAGMENT_POSITION_BUNDLE_TAG = "fragmentPosition";
    String PLAYER_SKILL_BUNDLE_TAG = "skill";
    String WEAPON_BUNDLE_TAG = "weapon";
    String CAN_ADD_TO_PLAYER_BUNDLE_TAG = "canAddToPlayer";

    int CHARACTERISTICS_FRAGMENT_POSITION = 0;
    int ADVENTURE_FRAGMENT_POSITION = 1;
    int PLAYER_SKILLS_FRAGMENT_POSITION = 2;
    int INVENTORY_FRAGMENT_POSITION = 3;
    int PLAYER_ACTIONS_FRAGMENT_POSITION = 4;
    int PLAYER_TALENTS_FRAGMENT_POSITION = 5;


    int LAUNCH_REQUEST = 0;
    int ADD_ITEM_REQUEST = 1;
    int EDIT_ITEM_REQUEST = 2;
    int STATS_REQUEST = 3;
    int ACTIONS_REQUEST = 4;
    int TALENTS_REQUEST = 5;
}
