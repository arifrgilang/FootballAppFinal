package com.rz.rz.footballappfinal.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.view.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)
    private val wait: Long = 2000

    @Test
    fun testFavoriteFeature(){
        Thread.sleep(wait)
        onView(withId(R.id.prevRv)).check(matches(isDisplayed()))

        onView(withId(R.id.prevRv)).perform(RecyclerViewActions
            .scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.prevRv)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        Thread.sleep(wait)
        onView(withId(R.id.detailView)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(wait)
        pressBack()

        Thread.sleep(wait)
        onView(withId(R.id.fav_menu)).perform(click())

        Thread.sleep(wait)
        onView(withId(R.id.favRv)).check(matches(isDisplayed()))

        onView(withId(R.id.favRv)).perform(RecyclerViewActions
            .scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.favRv)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(wait)
        onView(withId(R.id.detailView)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(wait)
        pressBack()

        Thread.sleep(wait)
        onView(withId(R.id.favRv)).check(matches(isDisplayed()))

        Thread.sleep(wait)
        onView(withId(R.id.favRefresh)).perform(swipeDown())

        Thread.sleep(wait)
    }
}
