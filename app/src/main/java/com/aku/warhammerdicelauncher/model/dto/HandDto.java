package com.aku.warhammerdicelauncher.model.dto;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HandDto implements IDto {
    private int id;

    private String title;
    private int characteristic;
    private int reckless;
    private int conservative;
    private int expertise;
    private int fortune;
    private int misfortune;
    private int challenge;

    //region Get & Set

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
    }

    public int getReckless() {
        return reckless;
    }

    public void setReckless(int reckless) {
        this.reckless = reckless;
    }

    public int getConservative() {
        return conservative;
    }

    public void setConservative(int conservative) {
        this.conservative = conservative;
    }

    public int getExpertise() {
        return expertise;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }

    public int getFortune() {
        return fortune;
    }

    public void setFortune(int fortune) {
        this.fortune = fortune;
    }

    public int getMisfortune() {
        return misfortune;
    }

    public void setMisfortune(int misfortune) {
        this.misfortune = misfortune;
    }

    public int getChallenge() {
        return challenge;
    }

    public void setChallenge(int challenge) {
        this.challenge = challenge;
    }
    //endregion

    @Override
    public String toString() {
        return "HandDto{" +
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

        HandDto handDto = (HandDto) o;

        if (getId() != handDto.getId()) return false;
        if (getCharacteristic() != handDto.getCharacteristic()) return false;
        if (getReckless() != handDto.getReckless()) return false;
        if (getConservative() != handDto.getConservative()) return false;
        if (getExpertise() != handDto.getExpertise()) return false;
        if (getFortune() != handDto.getFortune()) return false;
        if (getMisfortune() != handDto.getMisfortune()) return false;
        if (getChallenge() != handDto.getChallenge()) return false;
        return getTitle() != null ? getTitle().equals(handDto.getTitle()) : handDto.getTitle() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
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
