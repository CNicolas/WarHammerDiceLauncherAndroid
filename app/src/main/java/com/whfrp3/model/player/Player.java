package com.whfrp3.model.player;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.content.ContextCompat;

import com.whfrp3.BR;
import com.whfrp3.R;
import com.whfrp3.model.Money;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.Race;
import com.whfrp3.model.enums.SkillType;
import com.whfrp3.model.item.Armor;
import com.whfrp3.model.item.Item;
import com.whfrp3.model.enums.ItemType;
import com.whfrp3.model.enums.Range;
import com.whfrp3.model.item.UsableItem;
import com.whfrp3.model.item.Weapon;
import com.whfrp3.model.Skill;
import com.whfrp3.model.Specialization;
import com.whfrp3.model.Talent;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.helpers.SkillHelper;
import com.whfrp3.tools.helpers.SpecializationHelper;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Player model.
 */
public class Player extends BaseObservable {

    //region Constants
    private static final int ENCUMBRANCE_BASE = 0;
    private static final int ENCUMBRANCE_BASE_DWARF = 5;
    private static final int ENCUMBRANCE_BY_STRENGTH = 5;
    private static final int ENCUMBRANCE_BY_STRENGTH_FORTUNE = 1;
    private static final int ENCUMBRANCE_OVERLOAD_TO_MAX = 5;
    //endregion

    //region Properties

    private long id;
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

    private List<PlayerCareer> careers;
    private List<PlayerAction> actions;
    private List<PlayerSkill> skills;
    private List<PlayerSpecialization> specializations;
    private List<PlayerTalent> talents;
    private List<PlayerItem> items;

    private transient boolean mInEdition;

    //endregion

    //region Constructors
    public Player() {

    }
    //endregion

    /**
     * Initialize player basics values.
     */
    public void initPlayer() {
        characteristics = new HashMap<>();
        money = new Money(0, 0, 0);

        careers = new ArrayList<>();
        actions = new ArrayList<>();
        skills = new ArrayList<>();
        specializations = new ArrayList<>();
        talents = new ArrayList<>();
        items = new ArrayList<>();

        // Initialize characteristics list
        for (Characteristic characteristic : Characteristic.values()) {
            characteristics.put(characteristic, new PlayerCharacteristic(characteristic));
        }

        // Initialize skills list
        // TODO : modifier l'emplacement de l'initialisation des compétences de base
        List<Skill> basicSkills = SkillHelper.getInstance().getSkillsByType(SkillType.BASIC);
        for (Skill basicSkill : basicSkills) {
            skills.add(new PlayerSkill(basicSkill, 0));
        }
    }

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        for (PlayerCareer career : careers) {
            career.fillTransientFields();
        }

        for (PlayerAction action : actions) {
            action.fillTransientFields();
        }

        for (PlayerSkill skill : skills) {
            skill.fillTransientFields();
        }

        for (PlayerSpecialization specialization : specializations) {
            specialization.fillTransientFields();
        }

        for (PlayerTalent talent : talents) {
            talent.fillTransientFields();
        }

        for (PlayerItem item : items) {
            item.fillTransientFields();
        }
    }

    //region Inventory Management
    //region Armor

    /**
     * Return player's armors.
     *
     * @return Player's armors.
     */
    public List<Armor> getArmors() {
        List<Armor> armors = new ArrayList<>();

        for (PlayerItem item : items) {
            if (item.getItem().getType() == ItemType.ARMOR) {
                armors.add(item.getItem().toArmor());
            }
        }

        return armors;
    }

    @Bindable
    public int getFullDefenseAmount() {
        int res = 0;

        for (PlayerItem item : items) {
            if (item.getItem().getType() == ItemType.ARMOR) {
                Armor armor = item.getItem().toArmor();
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

        for (PlayerItem item : items) {
            if (item.getItem().getType() == ItemType.ARMOR) {
                Armor armor = item.getItem().toArmor();
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

        for (PlayerItem item : items) {
            encumbrance += item.getItem().getEncumbrance() * item.getQuantity();
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

        for (PlayerItem item : items) {
            if (item.getItem().getType() == ItemType.WEAPON) {
                weapons.add(item.getItem().toWeapon());
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
            if (Range.ENGAGED == weapon.getRange()) {
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
    public void addItem(PlayerItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    /**
     * Remove the given item of the inventory.
     *
     * @param item Item to remove.
     */
    public void removeItem(PlayerItem item) {
        if (item != null) {
            items.remove(item);
        }
    }

    /**
     * Return player's item with the given id.
     *
     * @param itemId Item id.
     * @return Player's item or null if not found.
     */
    public PlayerItem getItemById(long itemId) {
        for (PlayerItem item : items) {
            if (item.getItemId() == itemId) {
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

        for (PlayerItem item : items) {
            if (item.getItem().getType() == ItemType.USABLE_ITEM) {
                usableItems.add(item.getItem().toUsableItem());
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

        for (PlayerItem item : this.items) {
            if (item.getItem().getType() == ItemType.ITEM) {
                items.add(item.getItem());
            }
        }

        return items;
    }
    //endregion

    //region Skill Management

    public void addSpecialization(Specialization specialization) {
        int indexOfSpecialization = hasSpecialization(specialization);

        if (indexOfSpecialization == -1) {
            PlayerSpecialization playerSpecialization = new PlayerSpecialization(specialization);
            specializations.add(playerSpecialization);
        }
    }

    public void removeSpecialization(Specialization specialization) {
        int indexOfSpecialization = hasSpecialization(specialization);

        if (indexOfSpecialization > -1) {
            specializations.remove(specializations.get(indexOfSpecialization));
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
        for (int i = 0; i < specializations.size(); i++) {
            if (specializations.get(i).getSpecialization().equals(specialization)) {
                return i;
            }
        }
        return -1;
    }

    //endregion

    //region Talents Management

    public void addTalent(Talent talent) {
        int indexOfTalent = hasTalent(talent);

        if (indexOfTalent == -1) {
            PlayerTalent playerTalent = new PlayerTalent(talent);
            getTalents().add(playerTalent);
        }
    }

    public int hasTalent(Talent talent) {
        for (int i = 0; i < getTalents().size(); i++) {
            if (getTalents().get(i).getTalent().equals(talent)) {
                return i;
            }
        }
        return -1;
    }

    public void removeTalent(Talent talent) {
        int indexOfTalent = hasTalent(talent);

        if (indexOfTalent > -1) {
            talents.remove(talents.get(indexOfTalent));
        }

        notifyPropertyChanged(BR.talents);
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
        return name != null && !name.isEmpty();
    }

    //region Get & Set


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Bindable
    public List<PlayerSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<PlayerSkill> skills) {
        this.skills = skills;
    }

    @Bindable
    public List<PlayerSpecialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<PlayerSpecialization> specializations) {
        this.specializations = specializations;
    }

    @Bindable
    public List<PlayerTalent> getTalents() {
        return talents;
    }

    public void setTalents(List<PlayerTalent> talents) {
        this.talents = talents;
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
        return ToStringBuilder.reflectionToString(this);
    }

    //endregion
}
