package com.whfrp3.model.player;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.dices.Hand;
import com.whfrp3.tools.enums.Characteristic;

/**
 * The Characteristics model.
 */
public class Characteristics extends AbstractModel {
    private int strength;
    private int toughness;
    private int agility;
    private int intelligence;
    private int willpower;
    private int fellowship;

    private int strengthFortune;
    private int toughnessFortune;
    private int agilityFortune;
    private int intelligenceFortune;
    private int willpowerFortune;
    private int fellowshipFortune;

    public Hand getCharacteristicHand(Characteristic characteristic) {
        int blue = 0;
        int white = 0;

        switch (characteristic) {
            case STRENGTH:
            case STRENGTH_FORTUNE:
                blue = getStrength();
                white = getStrengthFortune();
                break;
            case TOUGHNESS:
            case TOUGHNESS_FORTUNE:
                blue = getToughness();
                white = getToughnessFortune();
                break;
            case AGILITY:
            case AGILITY_FORTUNE:
                blue = getAgility();
                white = getAgilityFortune();
                break;
            case INTELLIGENCE:
            case INTELLIGENCE_FORTUNE:
                blue = getIntelligence();
                white = getIntelligenceFortune();
                break;
            case WILLPOWER:
            case WILLPOWER_FORTUNE:
                blue = getWillpower();
                white = getWillpowerFortune();
                break;
            case FELLOWSHIP:
            case FELLOWSHIP_FORTUNE:
                blue = getFellowship();
                white = getFellowshipFortune();
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

    public int getStrengthFortune() {
        return strengthFortune;
    }

    public void setStrengthFortune(int strengthFortune) {
        this.strengthFortune = strengthFortune;
    }

    public int getToughnessFortune() {
        return toughnessFortune;
    }

    public void setToughnessFortune(int toughnessFortune) {
        this.toughnessFortune = toughnessFortune;
    }

    public int getAgilityFortune() {
        return agilityFortune;
    }

    public void setAgilityFortune(int agilityFortune) {
        this.agilityFortune = agilityFortune;
    }

    public int getIntelligenceFortune() {
        return intelligenceFortune;
    }

    public void setIntelligenceFortune(int intelligenceFortune) {
        this.intelligenceFortune = intelligenceFortune;
    }

    public int getWillpowerFortune() {
        return willpowerFortune;
    }

    public void setWillpowerFortune(int willpowerFortune) {
        this.willpowerFortune = willpowerFortune;
    }

    public int getFellowshipFortune() {
        return fellowshipFortune;
    }

    public void setFellowshipFortune(int fellowshipFortune) {
        this.fellowshipFortune = fellowshipFortune;
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
                ", strengthFortune=" + strengthFortune +
                ", toughnessFortune=" + toughnessFortune +
                ", agilityFortune=" + agilityFortune +
                ", intelligenceFortune=" + intelligenceFortune +
                ", willpowerFortune=" + willpowerFortune +
                ", fellowshipFortune=" + fellowshipFortune +
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
        if (getStrengthFortune() != that.getStrengthFortune()) return false;
        if (getToughnessFortune() != that.getToughnessFortune()) return false;
        if (getAgilityFortune() != that.getAgilityFortune()) return false;
        if (getIntelligenceFortune() != that.getIntelligenceFortune()) return false;
        if (getWillpowerFortune() != that.getWillpowerFortune()) return false;
        return getFellowshipFortune() == that.getFellowshipFortune();

    }

    @Override
    public int hashCode() {
        int result = getStrength();
        result = 31 * result + getToughness();
        result = 31 * result + getAgility();
        result = 31 * result + getIntelligence();
        result = 31 * result + getWillpower();
        result = 31 * result + getFellowship();
        result = 31 * result + getStrengthFortune();
        result = 31 * result + getToughnessFortune();
        result = 31 * result + getAgilityFortune();
        result = 31 * result + getIntelligenceFortune();
        result = 31 * result + getWillpowerFortune();
        result = 31 * result + getFellowshipFortune();
        return result;
    }
    //endregion
}
