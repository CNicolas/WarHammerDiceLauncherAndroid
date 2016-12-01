package com.whfrp3.notification;


import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.databinding.ToastNotificationBinding;
import com.whfrp3.tools.WHFRP3Application;

/**
 * Exception thrown when the bundle given on new Activity is not well formed.
 */
public class ToastNotification {
    private final String mMessage;
    private final ToastType mType;

    public ToastNotification(String message, ToastType type) {
        this.mMessage = message;
        this.mType = type;
    }

    public void show() {
        ToastNotificationBinding toastBinding = DataBindingUtil.inflate(WHFRP3Application.getActivity().getLayoutInflater(), R.layout.toast_notification, null, false);
        toastBinding.setToast(this);

        Toast toast = new Toast(WHFRP3Application.getAppContext());
        toast.setView(toastBinding.getRoot());
        toast.show();
    }

    public String getMessage() {
        return mMessage;
    }

    public ToastType getType() {
        return mType;
    }


}
