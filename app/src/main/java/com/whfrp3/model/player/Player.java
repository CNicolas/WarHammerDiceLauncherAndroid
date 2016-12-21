package com.whfrp3.model.player;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.content.ContextCompat;

import com.whfrp3.BR;
import com.whfrp3.R;
import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.Race;
import com.whfrp3.model.enums.SkillType;
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Range;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.model.skills.Skill;
import com.whfrp3.model.skills.Specialization;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.helpers.SkillHelper;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Player model.
 */
public class Player extends AbstractModel {

    //region Constants
    private static final int ENCUMBRANCE_BASE = 0;
    private static final int ENCUMBRANCE_BASE_DWARF = 5;
    private static final int ENCUMBRANCE_BY_STRENGTH = 5;
    private static final int ENCUMBRANCE_BY_STRENGTH_FORTUNE = 1;
    private static final int ENCUMBRANCE_OVERLOAD_TO_MAX = 5;
    //endregion

    //region Fields
    private String name;
    private Race race;
    private int age;
    private int size;
    private String description;

    private String career;
    private int rank;
    private int experience;
    private int max_experience;
    private int wounds;
    private int max_wounds;
    private int corruption;
    private int max_corruption;
    private int reckless;
    private int max_reckless;
    private int conservative;
    private int max_conservative;
    private int stress;
    private int exertion;

    private Map<Characteristic, PlayerCharacteristic> characteristics;
    private Money money;
    private List<Item> inventory;

    private List<PlayerSkill> playerSkills;
    private List<PlayerSpecialization> playerSpecializations;
    private List<PlayerTalent> playerTalents;

    /**
     * List of the item to remove of the DB.
     */
    private final List<Long> mItemToRemove = new ArrayList<>();

    private boolean mInEdition;

    //endregion

    //region Constructors
    public Player() {
        characteristics = new HashMap<>();
        money = new Money(0, 0, 0);
        inventory = new ArrayList<>();

        playerSkills = new ArrayList<>();
        playerSpecializations = new ArrayList<>();
        playerTalents = new ArrayList<>();

        // Initialize characteristics list
        for (Characteristic characteristic : Characteristic.values()) {
            characteristics.put(characteristic, new PlayerCharacteristic(characteristic, this.getId()));
        }

        // Initialize playerSkills list
        // TODO : modifier l'emplacement de l'initialisation des compétences de base
        List<Skill> basicSkills = SkillHelper.getInstance().getSkillsByType(SkillType.BASIC);
        for (Skill basicSkill : basicSkills) {
            playerSkills.add(new PlayerSkill(basicSkill, this, 0));
        }
    }
    //endregion

    //region Inventory Management
    //region Armor

    /**
     * Return player's armors.
     *
     * @return Player's armors.
     */
    public List<Armor> getArmors() {
        List<Armor> armors = new ArrayList<>();

        for (Item item : inventory) {
            if (item.getType() == ItemType.ARMOR) {
                armors.add(item.toArmor());
            }
        }

        return armors;
    }

    @Bindable
    public int getFullDefenseAmount() {
        int res = 0;

        for (Item item : inventory) {
            if (item.getType() == ItemType.ARMOR) {
                Armor armor = item.toArmor();
                if (armor.isEquipped()) {
                    res += armor.getDefense();
                }
            }
        }

        return res;
    }

    @Bindable
    public int getFullSoakAmount() {
        int res = 0;

        for (Item item : inventory) {
            if (item.getType() == ItemType.ARMOR) {
                Armor armor = item.toArmor();
                if (armor.isEquipped()) {
                    res += armor.getSoak();
                }
            }
        }

        return res;
    }
    //endregion

    //region Encumbrance
    @Bindable
    public int getEncumbranceOverload() {
        int encumbrance = (race == Race.DWARF) ? ENCUMBRANCE_BASE_DWARF : ENCUMBRANCE_BASE;

        // Add strength
        encumbrance += characteristics.get(Characteristic.STRENGTH).getValue() * ENCUMBRANCE_BY_STRENGTH;

        // Add strength fortune
        encumbrance += characteristics.get(Characteristic.STRENGTH).getFortuneValue() * ENCUMBRANCE_BY_STRENGTH_FORTUNE;

        return encumbrance;
    }

    @Bindable
    public int getEncumbranceMax() {
        return getEncumbranceOverload() + ENCUMBRANCE_OVERLOAD_TO_MAX;
    }

    @Bindable
    public int getCurrentEncumbrance() {
        int encumbrance = 0;

        for (Item item : inventory) {
            encumbrance += item.getEncumbrance() * item.getQuantity();
        }

        return encumbrance;
    }

    @Bindable
    public int getEncumbranceColor() {
        Context context = WHFRP3Application.getAppContext();
        int value = getCurrentEncumbrance();

        int color;
        if (value < getEncumbranceOverload()) {
            color = ContextCompat.getColor(context, R.color.conservative);
        } else if (value < getEncumbranceMax()) {
            color = ContextCompat.getColor(context, R.color.orange);
        } else {
            color = ContextCompat.getColor(context, R.color.reckless);
        }

        return color;
    }
    //endregion

    //region Weapons

    /**
     * Return player's weapons.
     *
     * @return Player's weapons.
     */
    public ObservableList<Weapon> getWeapons() {
        ObservableList<Weapon> weapons = new ObservableArrayList<>();

        for (Item item : inventory) {
            if (item.getType() == ItemType.WEAPON) {
                weapons.add(item.toWeapon());
            }
        }

        return weapons;
    }

    @Bindable
    public List<Weapon> getEquippedWeapons() {
        List<Weapon> allWeapons = getWeapons();
        List<Weapon> res = new ArrayList<>();
        for (Weapon weapon : allWeapons) {
            if (weapon.isEquipable() && weapon.isEquipped()) {
                res.add(weapon);
            }
        }

        return res;
    }

    public List<Weapon> getMeleeUsableWeapons() {
        List<Weapon> res = new ArrayList<>();

        List<Weapon> weapons = getEquippedWeapons();
        for (Weapon weapon : weapons) {
            if (Range.ENGAGED.equals(weapon.getRange())) {
                res.add(weapon);
            }
        }

        return res;
    }

    public List<Weapon> getDistanceUsableWeapons() {
        List<Weapon> res = new ArrayList<>();

        List<Weapon> weapons = getEquippedWeapons();
        for (Weapon weapon : weapons) {
            if (weapon.isDistance()) {
                res.add(weapon);
            }
        }

        return res;
    }
    //endregion

    /**
     * Add the given item in the inventory.
     *
     * @param item Item to add.
     */
    public void addItem(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    /**
     * Update the given item in the inventory.
     *
     * @param item Item to update.
     */
    public void updateItem(Item item) {
        if (item != null) {
            Item oldItem = getItemById(item.getId());
            inventory.remove(oldItem);
            inventory.add(item);
        }
    }

    /**
     * Remove the given item of the inventory.
     *
     * @param item Item to remove.
     */
    public void removeItem(Item item) {
        if (item != null) {
            inventory.remove(item);
            mItemToRemove.add(item.getId());
        }
    }

    /**
     * Return player's item with the given id.
     *
     * @param itemId Item id.
     * @return Player's item or null if not found.
     */
    public Item getItemById(long itemId) {
        for (Item item : inventory) {
            if (item.getId() == itemId) {
                return item;
            }
        }

        return null;
    }

    /**
     * Return player's usable items.
     *
     * @return Player's usable items.
     */
    public List<UsableItem> getUsableItems() {
        List<UsableItem> usableItems = new ArrayList<>();

        for (Item item : inventory) {
            if (item.getType() == ItemType.USABLE_ITEM) {
                usableItems.add(item.toUsableItem());
            }
        }

        return usableItems;
    }

    /**
     * Return player's simple items.
     *
     * @return Player's simple items.
     */
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        for (Item item : inventory) {
            if (item.getType() == ItemType.ITEM) {
                items.add(item);
            }
        }

        return items;
    }
    //endregion

    //region Skill Management
    public void addSpecialization(Specialization specialization) {
        int indexOfSpecialization = hasSpecialization(specialization);

        if (indexOfSpecialization == -1) {
            PlayerSpecialization playerSpecialization = new PlayerSpecialization(specialization, this);
            playerSpecializations.add(playerSpecialization);
        }
    }

    public void removeSpecialization(Specialization specialization) {
        int indexOfSpecialization = hasSpecialization(specialization);

        if (indexOfSpecialization > -1) {
            PlayerSpecialization playerSpecialization = playerSpecializations.get(indexOfSpecialization);
            playerSpecializations.remove(playerSpecializations.get(indexOfSpecialization));
        }
    }

    public boolean isSpecializedSkill(PlayerSkill playerSkill) {
        List<Specialization> specializations = SpecializationHelper.getInstance().getSpecializationsBySkillId(playerSkill.getSkill().getId());
        for (Specialization specialization : specializations) {
            int indexOfSpecialization = hasSpecialization(specialization);
            if (indexOfSpecialization > -1) {
                return true;
            }
        }
        return false;
    }

    public int hasSpecialization(Specialization specialization) {
        for (int i = 0; i < playerSpecializations.size(); i++) {
            if (playerSpecializations.get(i).getSpecialization().equals(specialization)) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region Edition
    @Bindable
    public boolean isInEdition() {
        return mInEdition;
    }

    public void setInEdition(boolean inEdition) {
        mInEdition = inEdition;
        notifyPropertyChanged(BR.inEdition);
    }
    //endregion

    /**
     * Can the player be saved in the Database ?
     *
     * @return yes or no
     */
    public boolean isUpdatable() {
        return getName() != null && !getName().isEmpty();
    }

    //region Get & Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMax_experience() {
        return max_experience;
    }

    public void setMax_experience(int max_experience) {
        this.max_experience = max_experience;
    }

    @Bindable
    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    @Bindable
    public int getMax_wounds() {
        return max_wounds;
    }

    public void setMax_wounds(int max_wounds) {
        this.max_wounds = max_wounds;
        notifyPropertyChanged(BR.max_wounds);
    }

    public int getCorruption() {
        return corruption;
    }

    public void setCorruption(int corruption) {
        this.corruption = corruption;
    }

    public int getMax_corruption() {
        return max_corruption;
    }

    public void setMax_corruption(int max_corruption) {
        this.max_corruption = max_corruption;
    }

    public int getReckless() {
        return reckless;
    }

    public void setReckless(int reckless) {
        this.reckless = reckless;
    }

    @Bindable
    public int getMax_reckless() {
        return max_reckless;
    }

    public void setMax_reckless(int max_reckless) {
        this.max_reckless = max_reckless;
        notifyPropertyChanged(BR.max_reckless);
    }

    public int getConservative() {
        return conservative;
    }

    public void setConservative(int conservative) {
        this.conservative = conservative;
    }

    @Bindable
    public int getMax_conservative() {
        return max_conservative;
    }

    public void setMax_conservative(int max_conservative) {
        this.max_conservative = max_conservative;
        notifyPropertyChanged(BR.max_conservative);
    }

    @Bindable
    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
        notifyPropertyChanged(BR.stress);
    }

    public int getMaxStressBeforeComa() {
        return characteristics.get(Characteristic.WILLPOWER).getValue() * 2;
    }

    @Bindable
    public int getExertion() {
        return exertion;
    }

    public void setExertion(int exertion) {
        this.exertion = exertion;
        notifyPropertyChanged(BR.exertion);
    }

    public int getMaxExertionBeforeComa() {
        return characteristics.get(Characteristic.TOUGHNESS).getValue() * 2;
    }

    @Bindable
    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    @Bindable
    public List<PlayerSkill> getPlayerSkills() {
        return playerSkills;
    }

    public void setPlayerSkills(List<PlayerSkill> playerSkills) {
        this.playerSkills = playerSkills;
    }

    @Bindable
    public List<PlayerSpecialization> getPlayerSpecializations() {
        return playerSpecializations;
    }

    public void setPlayerSpecializations(List<PlayerSpecialization> playerSpecializations) {
        this.playerSpecializations = playerSpecializations;
    }

    @Bindable
    public List<PlayerTalent> getPlayerTalents() {
        return playerTalents;
    }

    public void setPlayerTalents(List<PlayerTalent> playerTalents) {
        this.playerTalents = playerTalents;
    }

    public List<Long> getItemToRemove() {
        return mItemToRemove;
    }

    public PlayerCharacteristic getCharacteristic(Characteristic characteristic) {
        return characteristics.get(characteristic);
    }

    public void setPlayerCharacteristics(List<PlayerCharacteristic> characteristics) {
        this.characteristics = new HashMap<>();
        for (PlayerCharacteristic playerCharacteristic : characteristics) {
            this.characteristics.put(playerCharacteristic.getCharacteristic(), playerCharacteristic);
        }
    }

    public List<PlayerCharacteristic> getPlayerCharacteristics() {
        return new ArrayList<>(characteristics.values());
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", age=" + age +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", career='" + career + '\'' +
                ", rank=" + rank +
                ", experience=" + experience +
                ", max_experience=" + max_experience +
                ", wounds=" + wounds +
                ", max_wounds=" + max_wounds +
                ", corruption=" + corruption +
                ", max_corruption=" + max_corruption +
                ", reckless=" + reckless +
                ", max_reckless=" + max_reckless +
                ", conservative=" + conservative +
                ", max_conservative=" + max_conservative +
                ", money=" + money.toString() +
                ", characteristics=" + characteristics +
                ", inventory=" + inventory +
                ", playerSkills=" + playerSkills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (getId() != player.getId()) return false;
        if (getAge() != player.getAge()) return false;
        if (Double.compare(player.getSize(), getSize()) != 0) return false;
        if (getRank() != player.getRank()) return false;
        if (getExperience() != player.getExperience()) return false;
        if (getMax_experience() != player.getMax_experience()) return false;
        if (getWounds() != player.getWounds()) return false;
        if (getMax_wounds() != player.getMax_wounds()) return false;
        if (getCorruption() != player.getCorruption()) return false;
        if (getMax_corruption() != player.getMax_corruption()) return false;
        if (getReckless() != player.getReckless()) return false;
        if (getMax_reckless() != player.getMax_reckless()) return false;
        if (getConservative() != player.getConservative()) return false;
        if (getMax_conservative() != player.getMax_conservative()) return false;
        if (getName() != null ? !getName().equals(player.getName()) : player.getName() != null)
            return false;
        if (getRace() != null ? !getRace().equals(player.getRace()) : player.getRace() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(player.getDescription()) : player.getDescription() != null)
            return false;
        if (getCareer() != null ? !getCareer().equals(player.getCareer()) : player.getCareer() != null)
            return false;
        if (getInventory() != null ? !getInventory().equals(player.getInventory()) : player.getInventory() != null)
            return false;
        return getPlayerSkills() != null ? getPlayerSkills().equals(player.getPlayerSkills()) : player.getPlayerSkills() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getRace() != null ? getRace().hashCode() : 0);
        result = 31 * result + getAge();
        temp = Double.doubleToLongBits(getSize());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getCareer() != null ? getCareer().hashCode() : 0);
        result = 31 * result + getRank();
        result = 31 * result + getExperience();
        result = 31 * result + getMax_experience();
        result = 31 * result + getWounds();
        result = 31 * result + getMax_wounds();
        result = 31 * result + getCorruption();
        result = 31 * result + getMax_corruption();
        result = 31 * result + getReckless();
        result = 31 * result + getMax_reckless();
        result = 31 * result + getConservative();
        result = 31 * result + getMax_conservative();
        result = 31 * result + (getInventory() != null ? getInventory().hashCode() : 0);
        result = 31 * result + (getPlayerSkills() != null ? getPlayerSkills().hashCode() : 0);
        return result;
    }

    //endregion
}
