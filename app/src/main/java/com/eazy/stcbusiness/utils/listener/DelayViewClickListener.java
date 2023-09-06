package com.eazy.stcbusiness.utils.listener;

import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;

public class DelayViewClickListener implements View.OnClickListener, DelayClickListener {

    private boolean mIsClickable = true;
    private int mDelayInterval = DELAY_DURATION;

    public DelayViewClickListener() {
    }

    public DelayViewClickListener(int delayInterval) {
        mDelayInterval = delayInterval;
    }

    @Override
    public final void onClick(@NonNull View widget) {
        if (mIsClickable) {
            mIsClickable = false;
            onDelayClick(widget);
            new Handler().postDelayed(() -> mIsClickable = true, mDelayInterval);
        }
    }

    @Override
    public void onDelayClick(@NonNull View widget) {
        //Empty Impl....
    }
}
