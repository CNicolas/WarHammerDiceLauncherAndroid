package com.whfrp3.tools;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.whfrp3.BR;

import java.io.Serializable;

public class BindingContext extends BaseObservable implements Serializable {
    private boolean mInEdition;

    public BindingContext() {
    }

    public BindingContext(boolean inEdition) {
        this.mInEdition = inEdition;
    }

    @Bindable
    public boolean isInEdition() {
        Log.d("IsInEdition SET", "" + mInEdition);
        return mInEdition;
    }

    public void setInEdition(boolean inEdition) {
        mInEdition = inEdition;
        notifyPropertyChanged(BR.inEdition);
        Log.d("IsInEdition SET", "" + mInEdition);
    }
}
