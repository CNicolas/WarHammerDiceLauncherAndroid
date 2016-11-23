package com.whfrp3.model.dices;

import android.databinding.Bindable;

import com.whfrp3.BR;
import com.whfrp3.model.AbstractModel;

/**
 * The Hand of dices model.
 */
public class Hand extends AbstractModel {
    private String title;
    private int characteristic;
    private int reckless;
    private int conservative;
    private int expertise;
    private int fortune;
    private int misfortune;
    private int challenge;

    public void setFromHand(Hand otherHand) {
        setTitle(otherHand.getTitle());

        setCharacteristic(otherHand.getCharacteristic());
        setReckless(otherHand.getReckless());
        setConservative(otherHand.getConservative());
        setExpertise(otherHand.getExpertise());
        setFortune(otherHand.getFortune());
        setMisfortune(otherHand.getMisfortune());
        setChallenge(otherHand.getChallenge());
    }

    public void reset() {
        setCharacteristic(0);
        setReckless(0);
        setConservative(0);
        setExpertise(0);
        setFortune(0);
        setMisfortune(0);
        setChallenge(0);
    }

    public int getDicesNumber() {
        return getCharacteristic() + getConservative() + getReckless() + getExpertise() + getFortune() + getMisfortune() + getChallenge();
    }

    @Bindable
    public boolean isNotEmpty() {
        return getDicesNumber() > 0;
    }

    private void updateNotEmpty() {
        notifyPropertyChanged(BR.notEmpty);
    }

    //region Get & Set
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
        notifyPropertyChanged(BR.characteristic);
        updateNotEmpty();
    }

    @Bindable
    public int getReckless() {
        return reckless;
    }

    public void setReckless(int reckless) {
        this.reckless = reckless;
        notifyPropertyChanged(BR.reckless);
        updateNotEmpty();
    }

    @Bindable
    public int getConservative() {
        return conservative;
    }

    public void setConservative(int conservative) {
        this.conservative = conservative;
        notifyPropertyChanged(BR.conservative);
        updateNotEmpty();
    }

    @Bindable
    public int getExpertise() {
        return expertise;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
        notifyPropertyChanged(BR.expertise);
        updateNotEmpty();
    }

    @Bindable
    public int getFortune() {
        return fortune;
    }

    public void setFortune(int fortune) {
        this.fortune = fortune;
        notifyPropertyChanged(BR.fortune);
        updateNotEmpty();
    }

    @Bindable
    public int getMisfortune() {
        return misfortune;
    }

    public void setMisfortune(int misfortune) {
        this.misfortune = misfortune;
        notifyPropertyChanged(BR.misfortune);
        updateNotEmpty();
    }

    @Bindable
    public int getChallenge() {
        return challenge;
    }

    public void setChallenge(int challenge) {
        this.challenge = challenge;
        notifyPropertyChanged(BR.challenge);
        updateNotEmpty();
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "Hand{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", characteristic=" + characteristic +
                ", reckless=" + reckless +
                ", conservative=" + conservative +
                ", expertise=" + expertise +
                ", fortune=" + fortune +
                ", misfortune=" + misfortune +
                ", challenge=" + challenge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hand hand = (Hand) o;

        if (getId() != hand.getId()) return false;
        if (getCharacteristic() != hand.getCharacteristic()) return false;
        if (getReckless() != hand.getReckless()) return false;
        if (getConservative() != hand.getConservative()) return false;
        if (getExpertise() != hand.getExpertise()) return false;
        if (getFortune() != hand.getFortune()) return false;
        if (getMisfortune() != hand.getMisfortune()) return false;
        if (getChallenge() != hand.getChallenge()) return false;
        return getTitle() != null ? getTitle().equals(hand.getTitle()) : hand.getTitle() == null;

    }

    @Override
    public int hashCode() {
        int result = (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getCharacteristic();
        result = 31 * result + getReckless();
        result = 31 * result + getConservative();
        result = 31 * result + getExpertise();
        result = 31 * result + getFortune();
        result = 31 * result + getMisfortune();
        result = 31 * result + getChallenge();
        return result;
    }
    //endregion
}
