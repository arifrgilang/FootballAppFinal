package com.rz.rz.footballappfinal.view.fragments.events

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.view.activities.MatchPagerAdapter


class TabMatchesFragment: Fragment(){
    private lateinit var tab: TabLayout
    private lateinit var vp: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_matches, container, false)
        tab = view.findViewById(R.id.matches_tabs)
        vp = view.findViewById(R.id.matches_viewpager)

        val fragmentAdapter = MatchPagerAdapter(activity?.supportFragmentManager!!)
        vp.adapter = fragmentAdapter
        tab.setupWithViewPager(vp)

        return view
    }
}