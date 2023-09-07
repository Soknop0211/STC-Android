package com.eazy.stcbusiness.base_dapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.todo_ui.adapter.HighlyRecommendAdapter
import com.eazy.stcbusiness.utils.backgroundTint

class MainHomeShowItemAdapter(
    private val mList: ArrayList<CustomCategoryDataList>,
    private val context: Activity,
    private val mListener : (CustomCategoryModel) -> Unit) :
    RecyclerView.Adapter<MainHomeShowItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_home_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = mList[position]
        holder.headerTitleLayout.visibility = if (mItem.isHaveHeader) View.VISIBLE else View.GONE
        holder.headerInfoTv.text = mItem.title

        when (mItem.mainAction) {
            HomeContentFragment.ABOUT_SIEM_REAP-> {
                holder.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = CustomBannerHomeAdapter(context, mItem.mainAction, mItem.data)
                    isNestedScrollingEnabled = true
                }
            }
            HomeContentFragment.CATEGORY -> {
                holder.recyclerView.apply {
                    layoutManager =  GridLayoutManager(
                        context,
                        3
                    )
                    adapter = CustomBannerHomeAdapter(context, mItem.mainAction, mItem.data)
                    isNestedScrollingEnabled = true
                }
            }
            HomeContentFragment.DISCOUNT -> {
                holder.recyclerView.apply {
                    layoutManager = LinearLayoutManager(
                        context,
                        RecyclerView.HORIZONTAL,
                        false)
                    adapter = CustomItemHomeAdapter(mItem.data)
                    isNestedScrollingEnabled = true
                }
            }
            HomeContentFragment.MAIN_CATEGORY -> {
                holder.recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 3)
                    adapter = CustomCategoryHomeAdapter(mItem.data)
                    isNestedScrollingEnabled = true
                }
            }
            HomeContentFragment.HIGHLY_RECOMMEND -> {
                holder.recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = HighlyRecommendAdapter(mItem.mainAction, mItem.data) {

                    }
                    isNestedScrollingEnabled = true
                }

                //
                holder.mainLayout.setBackgroundResource(R.drawable.bottom_sheet_bg)
                holder.mainLayout.backgroundTint(R.color.light_white)
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
        val headerTitleLayout: LinearLayout = itemView.findViewById(R.id.headerTitleLayout)
        val headerInfoTv: TextView = itemView.findViewById(R.id.headerInfoTv)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)
    }

}