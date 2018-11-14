package com.rz.rz.footballappfinal.view.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.rz.rz.footballappfinal.view.fragments.events.FavEventsFragment
import com.rz.rz.footballappfinal.view.fragments.events.FavTeamsFragment
import com.rz.rz.footballappfinal.view.fragments.events.NextEventsFragment
import com.rz.rz.footballappfinal.view.fragments.events.PrevEventsFragment

class FavoritesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        when(p0){
            0 -> return FavEventsFragment()
            1 -> return FavTeamsFragment()
        }
        return FavEventsFragment()
    }

    override fun getCount(): Int {
        return 2;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Fav Matches"
            1 -> "Fav Teams"
            else -> {
                return "Fav Matches"
            }
        }
    }
}