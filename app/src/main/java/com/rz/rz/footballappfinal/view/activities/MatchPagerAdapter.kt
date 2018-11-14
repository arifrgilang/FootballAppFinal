package com.rz.rz.footballappfinal.view.activities

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.rz.rz.footballappfinal.view.fragments.events.NextEventsFragment
import com.rz.rz.footballappfinal.view.fragments.events.PrevEventsFragment

class MatchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        when(p0){
            0 -> return PrevEventsFragment()
            1 -> return NextEventsFragment()
        }
        return PrevEventsFragment()
    }

    override fun getCount(): Int {
        return 2;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Prev Matches"
            1 -> "Next Matches"
            else -> {
                return "Prev Matches"
            }
        }
    }
}