package com.rz.rz.footballappfinal.view.favorites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rz.rz.footballappfinal.R

class FavoritesTabLayoutFragment: Fragment(){
    private lateinit var tab: TabLayout
    private lateinit var vp: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_favorites, container, false)
        tab = view.findViewById(R.id.favorites_tabs)
        vp = view.findViewById(R.id.favorites_viewpager)

        val fragmentAdapter = FavoritesPagerAdapter(activity?.supportFragmentManager!!)
        vp.adapter = fragmentAdapter
        tab.setupWithViewPager(vp)
        return view
    }
}