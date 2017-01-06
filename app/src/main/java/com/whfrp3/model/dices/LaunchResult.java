package com.whfrp3.model.dices;

import android.support.annotation.Nullable;

public class LaunchResult {
    private int successNumber;
    private int boonNumber;
    private int sigmarNumber;
    private int failureNumber;
    private int baneNumber;
    private int delayNumber;
    private int exhaustionNumber;
    private int chaosNumber;

    public int getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(@Nullable Integer successNumber) {
        if (successNumber != null) {
            this.successNumber = successNumber;
        }
    }

    public int getBoonNumber() {
        return boonNumber;
    }

    public void setBoonNumber(@Nullable Integer boonNumber) {
        if (boonNumber != null) {
            this.boonNumber = boonNumber;
        }
    }

    public int getSigmarNumber() {
        return sigmarNumber;
    }

    public void setSigmarNumber(@Nullable Integer sigmarNumber) {
        if (sigmarNumber != null) {
            this.sigmarNumber = sigmarNumber;
        }
    }

    public int getFailureNumber() {
        return failureNumber;
    }

    public void setFailureNumber(@Nullable Integer failureNumber) {
        if (failureNumber != null) {
            this.failureNumber = failureNumber;
        }
    }

    public int getBaneNumber() {
        return baneNumber;
    }

    public void setBaneNumber(@Nullable Integer baneNumber) {
        if (baneNumber != null) {
            this.baneNumber = baneNumber;
        }
    }

    public int getDelayNumber() {
        return delayNumber;
    }

    public void setDelayNumber(@Nullable Integer delayNumber) {
        if (delayNumber != null) {
            this.delayNumber = delayNumber;
        }
    }

    public int getExhaustionNumber() {
        return exhaustionNumber;
    }

    public void setExhaustionNumber(@Nullable Integer exhaustionNumber) {
        if (exhaustionNumber != null) {
            this.exhaustionNumber = exhaustionNumber;
        }
    }

    public int getChaosNumber() {
        return chaosNumber;
    }

    public void setChaosNumber(@Nullable Integer chaosNumber) {
        if (chaosNumber != null) {
            this.chaosNumber = chaosNumber;
        }
    }
}
