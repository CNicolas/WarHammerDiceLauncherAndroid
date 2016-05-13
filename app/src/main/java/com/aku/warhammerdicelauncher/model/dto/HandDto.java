package com.aku.warhammerdicelauncher.model.dto;

import java.io.Serializable;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HandDto implements Serializable {
    private String title;
    private int characteristic;
    private int reckless;
    private int conservative;
    private int expertise;
    private int fortune;
    private int misfortune;
    private int challenge;

    public HandDto() {

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HandDto{");
        sb.append("title='").append(title).append('\'');
        sb.append(", characteristic=").append(characteristic);
        sb.append(", reckless=").append(reckless);
        sb.append(", conservative=").append(conservative);
        sb.append(", expertise=").append(expertise);
        sb.append(", fortune=").append(fortune);
        sb.append(", misfortune=").append(misfortune);
        sb.append(", challenge=").append(challenge);
        sb.append('}');
        return sb.toString();
    }
}
