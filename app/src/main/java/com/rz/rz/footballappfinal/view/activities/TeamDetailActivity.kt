package com.rz.rz.footballappfinal.view.activities

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.R.color.colorAccent
import com.rz.rz.footballappfinal.R.drawable.ic_add_to_favorites
import com.rz.rz.footballappfinal.R.drawable.ic_added_to_favorites
import com.rz.rz.footballappfinal.R.id.add_to_favorite
import com.rz.rz.footballappfinal.R.menu.detail_menu
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.db.Favorite
import com.rz.rz.footballappfinal.model.teams.Team
import com.rz.rz.footballappfinal.presenter.teams.TeamDetailPresenter
import com.rz.rz.footballappfinal.presenter.teams.TeamDetailView
import com.rz.rz.footballappfinal.model.db.database
import com.rz.rz.footballappfinal.utils.PagerAdapter
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.rz.rz.footballappfinal.view.matches.NextEventsFragment
import com.rz.rz.footballappfinal.view.matches.PrevEventsFragment
import com.rz.rz.footballappfinal.view.teams.TeamDetailFragment
import com.rz.rz.footballappfinal.view.teams.TeamDetailTabLayoutFragment
import com.rz.rz.footballappfinal.view.teams.TeamPlayersFragment
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        swipeRefresh = findViewById(R.id.team_detail_refreshlayout)
        progressBar = findViewById(R.id.team_detail_progressbar)

        teamBadge = findViewById(R.id.team_detail_badges)
        teamName = findViewById(R.id.team_detail_name)
        teamFormedYear = findViewById(R.id.team_detail_year)
        teamStadium = findViewById(R.id.team_detail_stadium)
//        teamDescription =
        getIntentValue()

        with(supportFragmentManager.beginTransaction()) {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.team_detail_container, TeamDetailTabLayoutFragment())
            commit()
        }

//        setView()
        favoriteState()
        requestApi()

        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)
        }
    }

    private fun getIntentValue(){
        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun requestApi(){
        presenter = TeamDetailPresenter(this, ApiRepository(), Gson())
        presenter.getTeamDetail(id)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
//        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_ID to teams.teamId,
                    Favorite.TEAM_NAME to teams.teamName,
                    Favorite.TEAM_BADGE to teams.teamBadge)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "TEAM_ID = {id}",
                    "id" to id)
            }
            snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}