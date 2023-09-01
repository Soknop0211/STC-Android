package com.eazy.stcbusiness.base_dapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AbsoluteFitLayoutManager extends GridLayoutManager {

    private final int spanColumnCount;

    public AbsoluteFitLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout , int spanColumnCount) {
        super(context, spanCount, orientation, reverseLayout);
        this.spanColumnCount = spanColumnCount;
    }
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return spanLayoutSize(super.generateDefaultLayoutParams());
    }

    @Override
    public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
        return spanLayoutSize(super.generateLayoutParams(c, attrs));
    }

    @Override
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return spanLayoutSize(super.generateLayoutParams(lp));
    }

    private RecyclerView.LayoutParams spanLayoutSize(RecyclerView.LayoutParams layoutParams){
        if(getOrientation() == HORIZONTAL){
            layoutParams.width = Math.round( getHorizontalSpace() / spanColumnCount);
            // its the margin between the items
            layoutParams.setMargins(2,2,2,4);
        }
        return layoutParams;
    }
    @Override
    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        return super.checkLayoutParams(lp);
    }

    private int getHorizontalSpace() {
        return getWidth() - (getPaddingRight()) - (getPaddingLeft()) ;
    }
}

