package com.rz.rz.footballappfinal.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.R.id.*
import com.rz.rz.footballappfinal.view.favorites.FavTabLayoutFragment
import com.rz.rz.footballappfinal.view.matches.EventsTabLayoutFragment
import com.rz.rz.footballappfinal.view.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(EventsTabLayoutFragment())
        setBottomBarListener()
    }

    private fun setBottomBarListener(){
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                bottom_navigation.selectedItemId -> return@setOnNavigationItemSelectedListener false

                matches_menu -> {
                    loadFragment(EventsTabLayoutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                teams_menu -> {
                    loadFragment(TeamsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                fav_menu -> {
                    loadFragment(FavTabLayoutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.main_container, fragment)
            commit()
        }
    }
}
