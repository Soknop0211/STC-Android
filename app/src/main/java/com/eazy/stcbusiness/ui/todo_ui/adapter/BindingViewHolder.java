package com.eazy.stcbusiness.ui.todo_ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by He Rotha on 8/28/17.
 */

public class BindingViewHolder extends RecyclerView.ViewHolder implements ViewModelLifeCycle {

    private ViewDataBinding mBinding;
    private ViewModelLifeCycle mViewModelLifeCycle;
    private Object mViewModel;

    public BindingViewHolder(ViewDataBinding itemView) {
        super(itemView.getRoot());
        mBinding = itemView;
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }

    public Object getViewModel() {
        return mViewModel;
    }

    public void setVariable(int variableId, Object value) {
        mViewModel = value;
        getBinding().setVariable(variableId, value);
        getBinding().executePendingBindings();
    }

    public void setViewModelLifeCycle(ViewModelLifeCycle viewModelLifeCycle) {
        mViewModelLifeCycle = viewModelLifeCycle;
    }

    @Override
    public void onPause() {
        if (mViewModelLifeCycle != null) {
            mViewModelLifeCycle.onPause();
        }
    }

    @Override
    public void onResume() {
        if (mViewModelLifeCycle != null) {
            mViewModelLifeCycle.onResume();
        }
    }


    public Context getContext() {
        return getBinding().getRoot().getContext();
    }

    public static class Builder {
        private ViewGroup mParent;
        @LayoutRes
        private int mLayoutRes;

        public Builder(ViewGroup parent, @LayoutRes int layoutRes) {
            mParent = parent;
            mLayoutRes = layoutRes;
        }

        public BindingViewHolder build() {
            final LayoutInflater inflater = LayoutInflater.from(mParent.getContext());
            final ViewDataBinding binding = DataBindingUtil.inflate(inflater, mLayoutRes, mParent, false);
            return new BindingViewHolder(binding);
        }
    }
}
