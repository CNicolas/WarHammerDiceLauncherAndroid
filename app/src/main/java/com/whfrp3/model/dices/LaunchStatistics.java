package com.whfrp3.model.dices;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.whfrp3.BR;
import com.whfrp3.R;
import com.whfrp3.tools.BindingUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Statistics of a launch.
 */
public class LaunchStatistics extends BaseObservable {
    private final DecimalFormat df;

    private boolean mInProgress;

    private int times;
    private int successfulLaunches;
    private double averageSuccess;
    private double averageBoon;
    private double averageSigmar;
    private double averageChaos;
    private double averageFailure;
    private double averageBane;

    public LaunchStatistics() {
        df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

    }

    @Bindable
    public String getLaunchesNumberString() {
        return String.format(BindingUtils.string(R.string.launches_number_format), getTimes());
    }

    @Bindable
    public String getSuccessfulLaunchesString() {
        double successPercentage = (getSuccessfulLaunches() * 100) / ((double) getTimes());
        return String.format(BindingUtils.string(R.string.successful_launches_number_format), getSuccessfulLaunches(), df.format(successPercentage));
    }

    @Bindable
    public String getAverageSuccessString() {
        return String.format(BindingUtils.string(R.string.average_success_format), df.format(getAverageSuccess()));
    }

    @Bindable
    public String getAverageBoonString() {
        return String.format(BindingUtils.string(R.string.average_boon_format), df.format(getAverageBoon()));
    }

    @Bindable
    public String getAverageSigmarString() {
        return String.format(BindingUtils.string(R.string.average_sigmar_format), df.format(getAverageSigmar()));
    }

    @Bindable
    public String getAverageChaosString() {
        return String.format(BindingUtils.string(R.string.average_chaos_format), df.format(getAverageChaos()));
    }

    @Bindable
    public String getAverageFailureString() {
        return String.format(BindingUtils.string(R.string.average_failure_format), df.format(getAverageFailure()));
    }

    @Bindable
    public String getAverageBaneString() {
        return String.format(BindingUtils.string(R.string.average_bane_format), df.format(getAverageBane()));
    }


    //region Getters and Setters
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
        notifyPropertyChanged(BR.launchesNumberString);
    }

    public int getSuccessfulLaunches() {
        return successfulLaunches;
    }

    public void setSuccessfulLaunches(int successfulLaunches) {
        this.successfulLaunches = successfulLaunches;
        notifyPropertyChanged(BR.successfulLaunchesString);
    }

    public double getAverageSuccess() {
        return averageSuccess;
    }

    public void setAverageSuccess(double averageSuccess) {
        this.averageSuccess = averageSuccess;
        notifyPropertyChanged(BR.averageSuccessString);
    }

    public double getAverageBoon() {
        return averageBoon;
    }

    public void setAverageBoon(double averageBoon) {
        this.averageBoon = averageBoon;
        notifyPropertyChanged(BR.averageBoonString);
    }

    public double getAverageSigmar() {
        return averageSigmar;
    }

    public void setAverageSigmar(double averageSigmar) {
        this.averageSigmar = averageSigmar;
        notifyPropertyChanged(BR.averageSigmarString);
    }

    public double getAverageChaos() {
        return averageChaos;
    }

    public void setAverageChaos(double averageChaos) {
        this.averageChaos = averageChaos;
        notifyPropertyChanged(BR.averageChaosString);
    }

    public double getAverageFailure() {
        return averageFailure;
    }

    public void setAverageFailure(double averageFailure) {
        this.averageFailure = averageFailure;
        notifyPropertyChanged(BR.averageFailureString);
    }

    public double getAverageBane() {
        return averageBane;
    }

    public void setAverageBane(double averageBane) {
        this.averageBane = averageBane;
        notifyPropertyChanged(BR.averageBaneString);
    }

    @Bindable
    public boolean isInProgress() {
        return mInProgress;
    }

    public void setInProgress(boolean inProgress) {
        mInProgress = inProgress;
        notifyPropertyChanged(BR.inProgress);
    }
    //endregion
}
