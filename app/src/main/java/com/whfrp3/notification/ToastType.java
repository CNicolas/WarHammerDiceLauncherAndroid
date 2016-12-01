package com.whfrp3.notification;


import com.whfrp3.R;

public enum ToastType {
    INFO(R.drawable.ic_info_outline_white, R.color.conservative),
    WARNING(R.drawable.ic_warning_white, R.color.orange),
    ERROR(R.drawable.ic_error_outline_white, R.color.reckless);

    private final int mDrawableId;
    private final int mColorId;

    ToastType(int drawableId, int colorId) {
        mDrawableId = drawableId;
        mColorId = colorId;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public int getColorId() {
        return mColorId;
    }
}
