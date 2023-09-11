package com.eazy.stcbusiness.utils.listener;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import com.eazy.stcbusiness.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class BindingUtil {
    @BindingAdapter("setSelectedOptionBackground")
    public static void setSelectedOptionBackground(LinearLayout container, boolean isSelected) {
        if (isSelected) {
            container.setBackgroundResource(R.drawable.shape_border);
        } else {
            container.setBackground(null);
        }
    }

    @BindingAdapter({"setVisibility"})
    public final void setVisibility(@NotNull View view, boolean isVisible) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }

    @BindingAdapter({"setVisibility"})
    public final void setVisibility(@NotNull View view, int visibility) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setVisibility(visibility);
    }

    @BindingAdapter({"android:visibility"})
    public final void visibility(@NotNull View view, boolean isVisible) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }

    @BindingAdapter({"android:src"})
    public final void setImageViewResource(@NotNull ImageView imageView, int resource) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"paddingRight"})
    public final void paddingRight(@NotNull View textView, int dimen) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        textView.setPadding(0, 0, dimen, 0);
    }

    @BindingAdapter({"layout_marginTop"})
    public final void addMarginTop(@NotNull View view, int dimen) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.LayoutParams var10000 = view.getLayoutParams();
        if (var10000 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        } else {
            ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)var10000;
            param.setMargins(0, dimen, 0, param.bottomMargin);
        }
    }

    @BindingAdapter({"layout_marginBottom"})
    public final void addMarginBottom(@NotNull View view, int dimen) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.LayoutParams var10000 = view.getLayoutParams();
        if (var10000 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        } else {
            ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)var10000;
            param.setMargins(param.leftMargin, param.topMargin, param.rightMargin, dimen);
        }
    }

    @BindingAdapter({"invisibleView"})
    public final void invisibleView(@NotNull View view, boolean isInvisible) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (isInvisible) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

    }

    @BindingAdapter({"setElevation"})
    public final void setElevation(@NotNull View view, int value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setElevation((float)value);
    }

    @BindingAdapter({"addMargin"})
    public final void addMargin(@NotNull View view, int dimen) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.LayoutParams var10000 = view.getLayoutParams();
        if (var10000 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        } else {
            ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)var10000;
            param.setMargins(dimen, dimen, dimen, dimen);
        }
    }

    @BindingAdapter({"editTextError"})
    public final void setEditTextError(@NotNull EditText editText, @Nullable String error) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        editText.setError((CharSequence)error);
    }

    @BindingAdapter({"delayClickListener"})
    public static void setDelayClickListener(@NotNull View view, @Nullable final View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setOnClickListener((View.OnClickListener)(new DelayViewClickListener() {
            public void onDelayClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                View.OnClickListener var10000 = listener;
                if (var10000 != null) {
                    var10000.onClick(view);
                }

            }
        }));
    }

    @BindingAdapter({"delayInterval", "delayClickListener"})
    public static void setDelayClickListener(@NotNull View view, final int delayInterval, @Nullable final View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setOnClickListener((View.OnClickListener)(new DelayViewClickListener(delayInterval) {
            public void onDelayClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                View.OnClickListener var10000 = listener;
                if (var10000 != null) {
                    var10000.onClick(view);
                }

            }
        }));
    }

    @BindingAdapter(value = {"setRoundImage", "setPlaceholder"}, requireAll = false)
    public static void setRoundImage(ImageView imageView, String url, Drawable placeholder) {
        GlideLoadUtil.loadCircle(imageView, url, placeholder);
    }

}
