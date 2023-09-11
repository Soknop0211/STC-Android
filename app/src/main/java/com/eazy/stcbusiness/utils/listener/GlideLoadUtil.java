package com.eazy.stcbusiness.utils.listener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by he.rotha on 5/18/16.
 */
public final class GlideLoadUtil {

    public static void loadCircle(ImageView imageView, String url, Drawable placeHolder) {
        if (isValidContext(imageView.getContext())) {
            Glide.with(imageView)
                    .load(url)
                    .placeholder(placeHolder)
                    .error(placeHolder)
                    .circleCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }
    }

    public static boolean isValidContext(Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                return !((Activity) context).isDestroyed() && !((Activity) context).isFinishing();
            }

            return true;
        }

        return false;
    }

}
