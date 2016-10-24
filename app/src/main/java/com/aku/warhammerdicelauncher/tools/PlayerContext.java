package com.aku.warhammerdicelauncher.tools;

import android.content.Context;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.inventory.Armor;
import com.aku.warhammerdicelauncher.model.player.inventory.Item;
import com.aku.warhammerdicelauncher.model.player.inventory.Quality;
import com.aku.warhammerdicelauncher.model.player.inventory.Range;
import com.aku.warhammerdicelauncher.model.player.inventory.UsableItem;
import com.aku.warhammerdicelauncher.model.player.inventory.Weapon;

import java.util.ArrayList;

/**
 * Created by cnicolas on 13/10/2016.
 */

public abstract class PlayerContext {
    private static Player mPlayer;
    private static boolean isInEdition;

    public static Player getPlayerInstance() {
        if (mPlayer == null) {
            mPlayer = createEmptyPlayer();
        }
        return mPlayer;
    }

    public static Player createEmptyPlayer() {
        mPlayer = new Player();
        return mPlayer;
    }

    public static void setPlayer(Player player) {
        mPlayer = player;
    }

    public static void updatePlayerInDatabase(Context context) {
        if (!mPlayer.getName().isEmpty()) {
            PlayerDao playerDao = new PlayerDao(new WarHammerDatabaseHelper(context));
            if (mPlayer.getId() == 0) {
                mPlayer.setId(playerDao.getNextId());
                playerDao.insert(mPlayer);
            } else {
                playerDao.update(mPlayer);
            }
        }
    }

    //region Edition
    public static boolean isInEdition() {
        return isInEdition;
    }

    public static void setIsInEdition(boolean isInEdition) {
        PlayerContext.isInEdition = isInEdition;
    }
    //endregion

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
}
