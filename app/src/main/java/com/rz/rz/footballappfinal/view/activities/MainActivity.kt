package com.rz.rz.footballappfinal.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.R.id.*
import com.rz.rz.footballappfinal.view.favorites.FavoritesTabLayoutFragment
import com.rz.rz.footballappfinal.view.fragments.events.FavEventsFragment
import com.rz.rz.footballappfinal.view.fragments.events.NextEventsFragment
import com.rz.rz.footballappfinal.view.matches.MatchesTabLayoutFragment
import com.rz.rz.footballappfinal.view.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Tab layout
//        val fragmentAdapter = MatchesPagerAdapter(supportFragmentManager)
//        matches_viewpager.adapter = fragmentAdapter
//        matches_tabs.setupWithViewPager(matches_viewpager)
        // Toggle bottom navigation bar
        setBottomBarListener(savedInstanceState)
        bottom_navigation.selectedItemId = prev_menu
    }

    private fun setBottomBarListener(savedInstanceState: Bundle?){
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                prev_menu -> {
                    loadMatchesFragment(savedInstanceState)
                }
                next_menu -> {
                    loadTeamsFragment(savedInstanceState)
                }
                fav_menu -> {
                    loadFavFragment(savedInstanceState)
                }
            }
            true
        }
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    MatchesTabLayoutFragment(),
                    MatchesTabLayoutFragment::class.java.simpleName)
                .commit()
        }
    }
    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    TeamsFragment(),
                    TeamsFragment::class.java.simpleName)
                .commit()
        }
    }
    private fun loadFavFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoritesTabLayoutFragment(),
                    FavoritesTabLayoutFragment::class.java.simpleName)
                .commit()
        }
    }
}
