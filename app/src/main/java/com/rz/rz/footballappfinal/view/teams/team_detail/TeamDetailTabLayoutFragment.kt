package com.rz.rz.footballappfinal.view.teams.team_detail

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.utils.PagerAdapter

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class TeamDetailTabLayoutFragment: Fragment(){
    private lateinit var tab: TabLayout
    private lateinit var vp: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_teams, container, false)
        tab = view.findViewById(R.id.team_detail_tabs)
        vp = view.findViewById(R.id.team_detail_viewpager)

        pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(TeamDetailOverviewFragment(), "Details")
        pagerAdapter.addFragment(TeamDetailPlayersFragment(), "Players")

        vp.adapter = pagerAdapter
        tab.setupWithViewPager(vp)
        // optional
//        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {}
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
        return view
    }
}