package com.eazy.stcbusiness.utils.listener;

import android.view.View;


public class CustomSetOnClickViewListener implements View.OnClickListener {
    private final CustomResponseOnClickListener customResponseOnClickListener;

    public CustomSetOnClickViewListener(CustomResponseOnClickListener customResponseOnClickListener){
        this.customResponseOnClickListener = customResponseOnClickListener;
    }

    @Override
    public void onClick(View view) {
        view.setEnabled(false);
        view.postDelayed(() -> view.setEnabled(true),500);
        customResponseOnClickListener.onClick(view);
    }
}
