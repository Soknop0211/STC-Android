package com.eazy.stcbusiness.ui.happening_ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.eazy.stcbusiness.R;
import com.eazy.stcbusiness.model.ItemImageSliderModel;

import java.util.List;

public class The_Slide_items_Pager_Adapter extends PagerAdapter {

    private Context Mcontext;
    private List<ItemImageSliderModel> theSlideItemsModelClassList;

    public The_Slide_items_Pager_Adapter(Context Mcontext, List<ItemImageSliderModel> theSlideItemsModelClassList) {
        this.Mcontext = Mcontext;
        this.theSlideItemsModelClassList = theSlideItemsModelClassList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.custom_image_slide_model,null);

        ImageView featured_image = sliderLayout.findViewById(R.id.imageViewPager);

//        Glide.with(Mcontext)
//                .load(theSlideItemsModelClassList.get(position).getImage())
//
//                .into(featured_image);
        featured_image.setImageResource(R.drawable.angkor_wat_temple);


        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return theSlideItemsModelClassList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}