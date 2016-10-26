package com.aku.warhammerdicelauncher.tools;

import android.content.Context;
import android.util.Log;

import com.aku.warhammerdicelauncher.database.dao.CharacteristicsDao;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.inventory.Armor;
import com.aku.warhammerdicelauncher.model.player.inventory.Item;
import com.aku.warhammerdicelauncher.model.player.inventory.Quality;
import com.aku.warhammerdicelauncher.model.player.inventory.Range;
import com.aku.warhammerdicelauncher.model.player.inventory.UsableItem;
import com.aku.warhammerdicelauncher.model.player.inventory.Weapon;
import com.aku.warhammerdicelauncher.model.player.skill.Skill;
import com.aku.warhammerdicelauncher.tools.helpers.OnPlayerUpdateListener;
import com.aku.warhammerdicelauncher.tools.helpers.SkillsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Carries the current player, as a Singleton like.
 */
public abstract class PlayerContext {
    private static Player mPlayer;
    private static boolean isInEdition;

    private static List<OnPlayerUpdateListener> mListeners;

    private static PlayerDao mPlayerDao;
    private static CharacteristicsDao mCharacteristicsDao;
    private static Context mContext;

    //region Player
    public static Player getPlayerInstance() {
        return mPlayer;
    }

    public static Player createEmptyPlayer() {
        mPlayer = new Player();
        return mPlayer;
    }

    public static void updatePlayer() {
        if (mPlayer == null || !mPlayer.isUpdatable()) {
            return;
        }

        if (mPlayer.getId() == 0) {
            mPlayer.setId(mPlayerDao.getNextId());
            mPlayer.getCharacteristics().setId(mCharacteristicsDao.getNextId());

            if (mPlayer.getSkills().size() == 0) {
                List<Skill> basicSkills = SkillsHelper.createBasicSkills(mContext);
                mPlayer.setSkills(basicSkills);
            }

            mPlayerDao.insert(mPlayer);
        } else {
            mPlayerDao.update(mPlayer);
        }
        notifyListeners();

        Log.e("Player Context", mPlayer.toString());
    }

    public static void setPlayer(Player player) {
        mPlayer = player;
    }

    public static Player createTestPlayer() {
        mPlayer = createEmptyPlayer();

        mPlayer.setCareer("Ratier");
        mPlayer.setExperience(0);
        mPlayer.setMax_experience(16);
        mPlayer.getCharacteristics().setFellowship(3);
        mPlayer.getCharacteristics().setFellowship_fortune(1);
        mPlayer.getCharacteristics().setStrength(5);
        mPlayer.getCharacteristics().setStrength_fortune(1);
        mPlayer.getCharacteristics().setToughness(4);
        mPlayer.setDescription("Trop tanky !");

        // Ajout des objets de test
        mPlayer.setInventory(new ArrayList<Item>());

        Armor armor = new Armor(mPlayer);
        armor.setName("Slip de combat");
        armor.setDescription("Slip en titane renforcé, pour les nains vénères.");
        armor.setEncumbrance(5);
        armor.setQuantity(1);
        armor.setQuality(Quality.SUPERIOR);
        armor.setSoak(5);
        armor.setDefense(2);
        mPlayer.getInventory().add(armor);

        Weapon weapon = new Weapon(mPlayer);
        weapon.setName("Epée en bois");
        weapon.setDescription("Parce qu'un nain vénère ça utilise une épée en bois !");
        weapon.setEncumbrance(3);
        weapon.setQuantity(1);
        weapon.setQuality(Quality.LOW);
        weapon.setDamage(2);
        weapon.setCriticalLevel(4);
        weapon.setRange(Range.ENGAGED);
        mPlayer.getInventory().add(weapon);

        UsableItem usableItem = new UsableItem(mPlayer);
        usableItem.setName("Potion sent bon");
        usableItem.setDescription("Parce qu'il faut bien compenser le manque de savon.");
        usableItem.setEncumbrance(3);
        usableItem.setQuantity(1);
        usableItem.setQuality(Quality.NORMAL);
        usableItem.setLoad(2);
        mPlayer.getInventory().add(usableItem);

        Item item = new Item(mPlayer);
        item.setName("Oreille de gobelin");
        item.setEncumbrance(0);
        item.setQuantity(1);
        item.setQuality(Quality.LOW);
        mPlayer.getInventory().add(item);

        return mPlayer;
    }

    //endregion

    //region Edition
    public static boolean isInEdition() {
        return isInEdition;
    }

    public static void setIsInEdition(boolean isInEdition) {
        PlayerContext.isInEdition = isInEdition;
    }
    //endregion

    public static void registerPlayerUpdateListener(OnPlayerUpdateListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        mListeners.add(listener);
    }

    private static void notifyListeners() {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        for (OnPlayerUpdateListener listener : mListeners) {
            listener.onPlayerUpdate();
        }
    }

    //region Daos

    public static void setPlayerDao(PlayerDao playerDao) {
        PlayerContext.mPlayerDao = playerDao;
    }

    public static void setCharacteristicsDao(CharacteristicsDao characteristicsDao) {
        PlayerContext.mCharacteristicsDao = characteristicsDao;
    }

    //endregion


    public static void setContext(Context context) {
        PlayerContext.mContext = context;
    }
}
