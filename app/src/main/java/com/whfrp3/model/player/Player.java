package com.whfrp3.model.player;

import android.databinding.Bindable;

import com.whfrp3.BR;
import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Range;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.model.player.skill.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player model.
 */
public class Player extends AbstractModel {
    private static final int BRASS_TO_SILVER = 100;
    private static final int SILVER_TO_GOLD = 25;

    //region Fields
    private String name;
    private String race;
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

    private int money_brass;
    private int money_silver;
    private int money_gold;

    private Characteristics characteristics;
    private List<Item> inventory;
    private List<Skill> mSkills;

    /**
     * List of the item to remove of the DB.
     */
    private List<Long> mItemToRemove = new ArrayList<>();

    //endregion

    //region Constructors
    public Player() {
        characteristics = new Characteristics();
        mSkills = new ArrayList<>();
        inventory = new ArrayList<>();
    }
    //endregion

    //region Skill Management
    public void setSkillLevel(Skill skill, int level) throws Exception {
        getSkillByName(skill.getName()).setLevel(level);
    }

    public Skill getSkillByName(String name) throws Exception {
        for (Skill skill : mSkills) {
            if (skill.getName().equals(name)) {
                return skill;
            }
        }
        throw new Exception(String.format("'%s' not found in skills %s", name, mSkills.toString()));
    }
    //endregion

    //region Money Management
    public void addMoneyBrass(int brass) {
        int newBrass = brass % BRASS_TO_SILVER;
        setMoney_brass(getMoney_brass() + newBrass);

        addMoneySilver(brass / BRASS_TO_SILVER);
    }

    public void addMoneySilver(int silver) {
        int newSilver = silver % SILVER_TO_GOLD;
        setMoney_silver(getMoney_silver() + newSilver);
        addMoneyGold(silver / SILVER_TO_GOLD);
    }

    public void addMoneyGold(int gold) {
        setMoney_gold(getMoney_gold() + gold);
    }
    //endregion

    //region Inventory Management
    //region Values
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

    /**
     * Returns the first equipped weapon that can be used
     *
     * @param range
     * @return
     */
    public Weapon getEquippedWeapon(Range range) {
        List<Weapon> weapons = getWeapons();
        for (Weapon weapon : weapons) {
            if (weapon.canBeUsed(range)) {
                return weapon;
            }
        }

        return null;
    }

    /**
     * Add the given item in the inventory.
     *
     * @param item Item to add.
     */
    public void addItem(Item item) {
        if (item != null) {
            inventory.add(item);
            notifyPropertyChanged(BR.fullDefenseAmount);
            notifyPropertyChanged(BR.fullSoakAmount);
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
            notifyPropertyChanged(BR.fullDefenseAmount);
            notifyPropertyChanged(BR.fullSoakAmount);

            mItemToRemove.add(item.getId());
        }
    }

    //region Getters

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

    /**
     * Return player's weapons.
     *
     * @return Player's weapons.
     */
    public List<Weapon> getWeapons() {
        List<Weapon> weapons = new ArrayList<>();

        for (Item item : inventory) {
            if (item.getType() == ItemType.WEAPON) {
                weapons.add(item.toWeapon());
            }
        }

        return weapons;
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
    //endregion

    /**
     * Can the player be saved in the mDatabase ?
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
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
        return getCharacteristics().getWillpower() * 2;
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
        return getCharacteristics().getToughness() * 2;
    }

    public int getMoney_brass() {
        return money_brass;
    }

    public void setMoney_brass(int money_brass) {
        this.money_brass = money_brass;
    }

    public int getMoney_silver() {
        return money_silver;
    }

    public void setMoney_silver(int money_silver) {
        this.money_silver = money_silver;
    }

    public int getMoney_gold() {
        return money_gold;
    }

    public void setMoney_gold(int money_gold) {
        this.money_gold = money_gold;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Skill> getSkills() {
        return mSkills;
    }

    public void setSkills(List<Skill> skills) {
        this.mSkills = skills;
    }

    public List<Long> getItemToRemove() {
        return mItemToRemove;
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
                ", money_brass=" + money_brass +
                ", money_silver=" + money_silver +
                ", money_gold=" + money_gold +
                ", characteristics=" + characteristics +
                ", inventory=" + inventory +
                ", skills=" + mSkills +
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
        if (getMoney_brass() != player.getMoney_brass()) return false;
        if (getMoney_silver() != player.getMoney_silver()) return false;
        if (getMoney_gold() != player.getMoney_gold()) return false;
        if (getName() != null ? !getName().equals(player.getName()) : player.getName() != null)
            return false;
        if (getRace() != null ? !getRace().equals(player.getRace()) : player.getRace() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(player.getDescription()) : player.getDescription() != null)
            return false;
        if (getCareer() != null ? !getCareer().equals(player.getCareer()) : player.getCareer() != null)
            return false;
        if (getCharacteristics() != null ? !getCharacteristics().equals(player.getCharacteristics()) : player.getCharacteristics() != null)
            return false;
        if (getInventory() != null ? !getInventory().equals(player.getInventory()) : player.getInventory() != null)
            return false;
        return getSkills() != null ? getSkills().equals(player.getSkills()) : player.getSkills() == null;

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
        result = 31 * result + getMoney_brass();
        result = 31 * result + getMoney_silver();
        result = 31 * result + getMoney_gold();
        result = 31 * result + (getCharacteristics() != null ? getCharacteristics().hashCode() : 0);
        result = 31 * result + (getInventory() != null ? getInventory().hashCode() : 0);
        result = 31 * result + (getSkills() != null ? getSkills().hashCode() : 0);
        return result;
    }

    //endregion
}
