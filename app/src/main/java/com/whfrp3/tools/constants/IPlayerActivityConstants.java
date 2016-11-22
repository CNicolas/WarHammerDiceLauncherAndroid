package com.whfrp3.tools.constants;

/**
 * Constants used mainly in PlayerActivity.
 */
public interface IPlayerActivityConstants extends IConstants {
    String IS_IN_EDITION_BUNDLE_TAG = "isInEdition";
    String BINDING_CONTEXT_BUNDLE_TAG = "bindingContext";
    String CURRENT_FRAGMENT_POSITION_BUNDLE_TAG = "fragmentPosition";
    String SKILL_BUNDLE_TAG = "skill";

    int CHARACTERISTICS_FRAGMENT_POSITION = 0;
    int ADVENTURE_FRAGMENT_POSITION = 1;
    int SKILLS_FRAGMENT_POSITION = 2;
    int INVENTORY_FRAGMENT_POSITION = 3;

    int LAUNCH_REQUEST = 0;
    int ADD_ITEM_REQUEST = 1;
    int EDIT_ITEM_REQUEST = 2;
    int STATS_REQUEST = 3;
}
