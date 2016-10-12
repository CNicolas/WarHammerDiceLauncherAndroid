package com.aku.warhammerdicelauncher.model.dto;

import com.aku.warhammerdicelauncher.model.dto.inventory.ArmourDto;
import com.aku.warhammerdicelauncher.model.dto.inventory.InventoryItemDto;
import com.aku.warhammerdicelauncher.model.dto.inventory.WeaponDto;
import com.aku.warhammerdicelauncher.utils.constants.IPlayerConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class PlayerDto implements IDto, IPlayerConstants {
    //region Fields
    private int id;

    private String name;
    private String race;
    private int age;
    private double size;
    private String description;

    private int rank;
    private int experience;
    private int max_experience;
    private int wounds;
    private int max_wounds;
    private int reckless;
    private int max_reckless;
    private int conservative;
    private int max_conservative;

    private int money_brass;
    private int money_silver;
    private int money_gold;

    private CharacteristicsDto characteristics;
    private List<InventoryItemDto> inventory;
    private List<ArmourDto> armour;
    private List<WeaponDto> weapons;
    private List<SkillDto> skills;
    //endregion

    //region Constructors
    public PlayerDto() {
        skills = new ArrayList<>();
    }

    public PlayerDto(String name, String race) {
        this();
        this.name = name;
        this.race = race;
    }
    //endregion

    public List<SkillDto> addSkill(SkillDto skillDto) {
        if (!skills.contains(skillDto)) {
            skills.add(skillDto);
        }
        return skills;
    }

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

    //region Get & Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getMax_wounds() {
        return max_wounds;
    }

    public void setMax_wounds(int max_wounds) {
        this.max_wounds = max_wounds;
    }

    public int getReckless() {
        return reckless;
    }

    public void setReckless(int reckless) {
        this.reckless = reckless;
    }

    public int getMax_reckless() {
        return max_reckless;
    }

    public void setMax_reckless(int max_reckless) {
        this.max_reckless = max_reckless;
    }

    public int getConservative() {
        return conservative;
    }

    public void setConservative(int conservative) {
        this.conservative = conservative;
    }

    public int getMax_conservative() {
        return max_conservative;
    }

    public void setMax_conservative(int max_conservative) {
        this.max_conservative = max_conservative;
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

    public CharacteristicsDto getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(CharacteristicsDto characteristics) {
        this.characteristics = characteristics;
    }

    public List<InventoryItemDto> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItemDto> inventory) {
        this.inventory = inventory;
    }

    public List<ArmourDto> getArmour() {
        return armour;
    }

    public void setArmour(List<ArmourDto> armour) {
        this.armour = armour;
    }

    public List<WeaponDto> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponDto> weapons) {
        this.weapons = weapons;
    }

    public List<SkillDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDto> skills) {
        this.skills = skills;
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", age=" + age +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", rank=" + rank +
                ", experience=" + experience +
                ", max_experience=" + max_experience +
                ", wounds=" + wounds +
                ", max_wounds=" + max_wounds +
                ", reckless=" + reckless +
                ", max_reckless=" + max_reckless +
                ", conservative=" + conservative +
                ", max_conservative=" + max_conservative +
                ", money_brass=" + money_brass +
                ", money_silver=" + money_silver +
                ", money_gold=" + money_gold +
                ", characteristics=" + characteristics +
                ", inventory=" + inventory +
                ", armour=" + armour +
                ", weapons=" + weapons +
                ", skills=" + skills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDto playerDto = (PlayerDto) o;

        if (getId() != playerDto.getId()) return false;
        if (getAge() != playerDto.getAge()) return false;
        if (Double.compare(playerDto.getSize(), getSize()) != 0) return false;
        if (getRank() != playerDto.getRank()) return false;
        if (getExperience() != playerDto.getExperience()) return false;
        if (getMax_experience() != playerDto.getMax_experience()) return false;
        if (getWounds() != playerDto.getWounds()) return false;
        if (getMax_wounds() != playerDto.getMax_wounds()) return false;
        if (getReckless() != playerDto.getReckless()) return false;
        if (getMax_reckless() != playerDto.getMax_reckless()) return false;
        if (getConservative() != playerDto.getConservative()) return false;
        if (getMax_conservative() != playerDto.getMax_conservative()) return false;
        if (getMoney_brass() != playerDto.getMoney_brass()) return false;
        if (getMoney_silver() != playerDto.getMoney_silver()) return false;
        if (getMoney_gold() != playerDto.getMoney_gold()) return false;
        if (getName() != null ? !getName().equals(playerDto.getName()) : playerDto.getName() != null)
            return false;
        if (getRace() != null ? !getRace().equals(playerDto.getRace()) : playerDto.getRace() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(playerDto.getDescription()) : playerDto.getDescription() != null)
            return false;
        if (getCharacteristics() != null ? !getCharacteristics().equals(playerDto.getCharacteristics()) : playerDto.getCharacteristics() != null)
            return false;
        if (getInventory() != null ? !getInventory().equals(playerDto.getInventory()) : playerDto.getInventory() != null)
            return false;
        if (getArmour() != null ? !getArmour().equals(playerDto.getArmour()) : playerDto.getArmour() != null)
            return false;
        if (getWeapons() != null ? !getWeapons().equals(playerDto.getWeapons()) : playerDto.getWeapons() != null)
            return false;
        return getSkills() != null ? getSkills().equals(playerDto.getSkills()) : playerDto.getSkills() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getRace() != null ? getRace().hashCode() : 0);
        result = 31 * result + getAge();
        temp = Double.doubleToLongBits(getSize());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getRank();
        result = 31 * result + getExperience();
        result = 31 * result + getMax_experience();
        result = 31 * result + getWounds();
        result = 31 * result + getMax_wounds();
        result = 31 * result + getReckless();
        result = 31 * result + getMax_reckless();
        result = 31 * result + getConservative();
        result = 31 * result + getMax_conservative();
        result = 31 * result + getMoney_brass();
        result = 31 * result + getMoney_silver();
        result = 31 * result + getMoney_gold();
        result = 31 * result + (getCharacteristics() != null ? getCharacteristics().hashCode() : 0);
        result = 31 * result + (getInventory() != null ? getInventory().hashCode() : 0);
        result = 31 * result + (getArmour() != null ? getArmour().hashCode() : 0);
        result = 31 * result + (getWeapons() != null ? getWeapons().hashCode() : 0);
        result = 31 * result + (getSkills() != null ? getSkills().hashCode() : 0);
        return result;
    }
//endregion
}
