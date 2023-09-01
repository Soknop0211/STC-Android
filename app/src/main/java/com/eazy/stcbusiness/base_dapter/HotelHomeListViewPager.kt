package com.eazy.stcbusiness.base_dapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.eazy.stcbusiness.base.SampleBaseFragment


class HotelHomeListViewPager(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private val fragments: MutableList<SampleBaseFragment> = ArrayList()
    private val titles: MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    fun addFragment(fragment: SampleBaseFragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

    fun removeFragment(fragment: SampleBaseFragment, title: String) {
        fragments.remove(fragment)
        titles.remove(title)
    }

    fun removeItem(index: Int) {
        fragments.removeAt(index)
    }
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}