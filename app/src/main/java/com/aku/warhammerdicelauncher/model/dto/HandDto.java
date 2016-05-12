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

    public HandDto(String title, int characteristic, int reckless, int conservative, int expertise, int fortune, int misfortune, int challenge) {
        this.title = title;
        this.characteristic = characteristic;
        this.reckless = reckless;
        this.conservative = conservative;
        this.expertise = expertise;
        this.fortune = fortune;
        this.misfortune = misfortune;
        this.challenge = challenge;
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
}
