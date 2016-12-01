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

    private int mTimes;
    private int mSuccessfulLaunches;
    private double mAverageSuccess;
    private double mAverageBoon;
    private double mAverageSigmar;
    private double mAverageChaos;
    private double mAverageFailure;
    private double mAverageBane;

    public LaunchStatistics() {
        df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    @Bindable
    public String getLaunchesNumberString() {
        return String.format(BindingUtils.string(R.string.launches_number_format), mTimes);
    }

    @Bindable
    public String getSuccessfulLaunchesString() {
        double successPercentage = (mSuccessfulLaunches * 100) / ((double) mTimes);
        return String.format(BindingUtils.string(R.string.successful_launches_number_format), mSuccessfulLaunches, df.format(successPercentage));
    }

    @Bindable
    public String getAverageSuccessString() {
        return String.format(BindingUtils.string(R.string.average_success_format), df.format(mAverageSuccess));
    }

    @Bindable
    public String getAverageBoonString() {
        return String.format(BindingUtils.string(R.string.average_boon_format), df.format(mAverageBoon));
    }

    @Bindable
    public String getAverageSigmarString() {
        return String.format(BindingUtils.string(R.string.average_sigmar_format), df.format(mAverageSigmar));
    }

    @Bindable
    public String getAverageChaosString() {
        return String.format(BindingUtils.string(R.string.average_chaos_format), df.format(mAverageChaos));
    }

    @Bindable
    public String getAverageFailureString() {
        return String.format(BindingUtils.string(R.string.average_failure_format), df.format(mAverageFailure));
    }

    @Bindable
    public String getAverageBaneString() {
        return String.format(BindingUtils.string(R.string.average_bane_format), df.format(mAverageBane));
    }


    //region Getters and Setters

    public void setTimes(int times) {
        this.mTimes = times;
        notifyPropertyChanged(BR.launchesNumberString);
    }

    public void setSuccessfulLaunches(int successfulLaunches) {
        this.mSuccessfulLaunches = successfulLaunches;
        notifyPropertyChanged(BR.successfulLaunchesString);
    }

    public void setAverageSuccess(double averageSuccess) {
        this.mAverageSuccess = averageSuccess;
        notifyPropertyChanged(BR.averageSuccessString);
    }

    public void setAverageBoon(double averageBoon) {
        this.mAverageBoon = averageBoon;
        notifyPropertyChanged(BR.averageBoonString);
    }

    public void setAverageSigmar(double averageSigmar) {
        this.mAverageSigmar = averageSigmar;
        notifyPropertyChanged(BR.averageSigmarString);
    }

    public void setAverageChaos(double averageChaos) {
        this.mAverageChaos = averageChaos;
        notifyPropertyChanged(BR.averageChaosString);
    }

    public void setAverageFailure(double averageFailure) {
        this.mAverageFailure = averageFailure;
        notifyPropertyChanged(BR.averageFailureString);
    }

    public void setAverageBane(double averageBane) {
        this.mAverageBane = averageBane;
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
