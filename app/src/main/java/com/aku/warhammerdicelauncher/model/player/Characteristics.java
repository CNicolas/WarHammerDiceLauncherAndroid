package com.aku.warhammerdicelauncher.model.player;

import com.aku.warhammerdicelauncher.model.IModel;
import com.aku.warhammerdicelauncher.tools.enums.Characteristic;

/**
 * The Characteristics model.
 */
public class Characteristics implements IModel {
    private int id;

    private int strength;
    private int toughness;
    private int agility;
    private int intelligence;
    private int willpower;
    private int fellowship;

    private int strength_fortune;
    private int toughness_fortune;
    private int agility_fortune;
    private int intelligence_fortune;
    private int willpower_fortune;
    private int fellowship_fortune;

    public Hand getCharacteristicHand(Characteristic characteristic) {
        int blue = 0;
        int white = 0;

        switch (characteristic) {
            case STRENGTH:
            case STRENGTH_FORTUNE:
                blue = getStrength();
                white = getStrength_fortune();
                break;
            case TOUGHNESS:
            case TOUGHNESS_FORTUNE:
                blue = getToughness();
                white = getToughness_fortune();
                break;
            case AGILITY:
            case AGILITY_FORTUNE:
                blue = getAgility();
                white = getAgility_fortune();
                break;
            case INTELLIGENCE:
            case INTELLIGENCE_FORTUNE:
                blue = getIntelligence();
                white = getIntelligence_fortune();
                break;
            case WILLPOWER:
            case WILLPOWER_FORTUNE:
                blue = getWillpower();
                white = getWillpower_fortune();
                break;
            case FELLOWSHIP:
            case FELLOWSHIP_FORTUNE:
                blue = getFellowship();
                white = getFellowship_fortune();
                break;
            default:
                break;
        }

        Hand hand = new Hand();
        hand.setCharacteristic(blue);
        hand.setFortune(white);

        return hand;
    }

    //region Get & Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getFellowship() {
        return fellowship;
    }

    public void setFellowship(int fellowship) {
        this.fellowship = fellowship;
    }

    public int getStrength_fortune() {
        return strength_fortune;
    }

    public void setStrength_fortune(int strength_fortune) {
        this.strength_fortune = strength_fortune;
    }

    public int getToughness_fortune() {
        return toughness_fortune;
    }

    public void setToughness_fortune(int toughness_fortune) {
        this.toughness_fortune = toughness_fortune;
    }

    public int getAgility_fortune() {
        return agility_fortune;
    }

    public void setAgility_fortune(int agility_fortune) {
        this.agility_fortune = agility_fortune;
    }

    public int getIntelligence_fortune() {
        return intelligence_fortune;
    }

    public void setIntelligence_fortune(int intelligence_fortune) {
        this.intelligence_fortune = intelligence_fortune;
    }

    public int getWillpower_fortune() {
        return willpower_fortune;
    }

    public void setWillpower_fortune(int willpower_fortune) {
        this.willpower_fortune = willpower_fortune;
    }

    public int getFellowship_fortune() {
        return fellowship_fortune;
    }

    public void setFellowship_fortune(int fellowship_fortune) {
        this.fellowship_fortune = fellowship_fortune;
    }
    //endregion

    //region Usual Overrides
    @Override
    public String toString() {
        return "Characteristics{" +
                "id=" + id +
                ", strength=" + strength +
                ", toughness=" + toughness +
                ", agility=" + agility +
                ", intelligence=" + intelligence +
                ", willpower=" + willpower +
                ", fellowship=" + fellowship +
                ", strength_fortune=" + strength_fortune +
                ", toughness_fortune=" + toughness_fortune +
                ", agility_fortune=" + agility_fortune +
                ", intelligence_fortune=" + intelligence_fortune +
                ", willpower_fortune=" + willpower_fortune +
                ", fellowship_fortune=" + fellowship_fortune +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characteristics that = (Characteristics) o;

        if (getId() != that.getId()) return false;
        if (getStrength() != that.getStrength()) return false;
        if (getToughness() != that.getToughness()) return false;
        if (getAgility() != that.getAgility()) return false;
        if (getIntelligence() != that.getIntelligence()) return false;
        if (getWillpower() != that.getWillpower()) return false;
        if (getFellowship() != that.getFellowship()) return false;
        if (getStrength_fortune() != that.getStrength_fortune()) return false;
        if (getToughness_fortune() != that.getToughness_fortune()) return false;
        if (getAgility_fortune() != that.getAgility_fortune()) return false;
        if (getIntelligence_fortune() != that.getIntelligence_fortune()) return false;
        if (getWillpower_fortune() != that.getWillpower_fortune()) return false;
        return getFellowship_fortune() == that.getFellowship_fortune();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStrength();
        result = 31 * result + getToughness();
        result = 31 * result + getAgility();
        result = 31 * result + getIntelligence();
        result = 31 * result + getWillpower();
        result = 31 * result + getFellowship();
        result = 31 * result + getStrength_fortune();
        result = 31 * result + getToughness_fortune();
        result = 31 * result + getAgility_fortune();
        result = 31 * result + getIntelligence_fortune();
        result = 31 * result + getWillpower_fortune();
        result = 31 * result + getFellowship_fortune();
        return result;
    }
    //endregion
}
