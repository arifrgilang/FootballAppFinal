package com.rz.rz.footballappfinal.view.activities

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.R.color.colorPrimary
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.db.FavMatch
import com.rz.rz.footballappfinal.model.matches.FootballEvent
import com.rz.rz.footballappfinal.model.matches.TeamBadges
import com.rz.rz.footballappfinal.presenter.matches.EventDetailPresenter
import com.rz.rz.footballappfinal.presenter.matches.EventDetailView
import com.rz.rz.footballappfinal.model.db.database
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class EventDetailActivity : AppCompatActivity(), EventDetailView {
    private lateinit var mFootballEvent: FootballEvent
    private lateinit var mPresenter: EventDetailPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: ScrollView
    //
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var mId: String

    private lateinit var strHomeName: String
    private lateinit var strAwayName: String

    private lateinit var matchDate: TextView
    private lateinit var scoreText: TextView

    private lateinit var homeName: TextView
    private lateinit var homeBadges: ImageView
    private lateinit var homeGoal: TextView
    private lateinit var homeGK: TextView
    private lateinit var homeDEF: TextView
    private lateinit var homeMID: TextView
    private lateinit var homeFW: TextView

    private lateinit var awayName: TextView
    private lateinit var awayBadges: ImageView
    private lateinit var awayGoal: TextView
    private lateinit var awayGK: TextView
    private lateinit var awayDEF: TextView
    private lateinit var awayMID: TextView
    private lateinit var awayFW: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get intent values
        val intent = intent
        mId = intent.getStringExtra("id")
        strHomeName = intent.getStringExtra("HOME_NAME")
        strAwayName = intent.getStringExtra("AWAY_NAME")
        // set top bar title, set back button in the app
        supportActionBar?.title = "Event Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // check is favorite or not
        setView()
        requestAPI()
        // load fragment depend on getIntent DETAIL_TYPE
    }
    //
    override fun showLoading() {
        progressBar.visible()
    }
    override fun hideLoading() {
        progressBar.invisible()
    }
    private fun setView(){
        relativeLayout {
            lparams(width = matchParent, height = wrapContent)
            // Scroll view for detail
            scrollView = scrollView {
                id = R.id.detailView
                relativeLayout{
                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        // separator
                        view {
                            backgroundColor = R.color.cardview_dark_background
                        }.lparams {
                            width = matchParent
                            height = dip(1)
                            topMargin = dip(10)
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                        }
                        // Match Date
                        matchDate = textView {
                            setTypeface(null, Typeface.BOLD)
                        }.lparams {
                            height = wrapContent
                            width = wrapContent
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                        // separator
                        view {
                            backgroundColor = R.color.cardview_dark_background
                        }.lparams {
                            width = matchParent
                            height = dip(1)
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                        }
                        // Home and Away team name
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            // Home team
                            homeName = textView {
                                setTypeface(null, Typeface.BOLD)
                            }.lparams {
                                width = dip(0)
                                height = wrapContent
                                weight = 1f
                            }
                            // Away team
                            awayName = textView {
                                setTypeface(null, Typeface.BOLD)
                                textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            }.lparams {
                                width = dip(0)
                                height = wrapContent
                                weight = 1f
                            }
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                            margin = dip(10)
                        }
                        // home away badge
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            // Home badge
                            homeBadges = imageView {
                            }.lparams {
                                width = dip(80)
                                height = dip(80)
                                weight = 3f
                            }
                            // score
                            scoreText = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textSize = 28f
                                text = "TBD-TBD"
                                setTypeface(null, Typeface.BOLD)
                            }.lparams {
                                width = wrapContent
                                height = wrapContent
                                weight = 1f
                                gravity = Gravity.CENTER_VERTICAL
                            }
                            // Away badge
                            awayBadges = imageView {
                            }.lparams {
                                width = dip(80)
                                height = dip(80)
                                weight = 3f
                            }
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        // separator
                        view {
                            backgroundColor = R.color.cardview_dark_background
                        }.lparams {
                            width = matchParent
                            height = dip(1)
                            topMargin = dip(10)
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                        }

                        //shot
                        textView {
                            textSize = 18f
                            text = "Goals"
                            setTypeface(null, Typeface.BOLD)
                        }.lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                homeGoal = textView {
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 18f
                                    text = ""
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                awayGoal = textView {
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }
                        }.lparams(){
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                        }

                        view {
                            backgroundColor = R.color.cardview_dark_background
                        }.lparams {
                            width = matchParent
                            height = dip(1)
                            topMargin = dip(10)
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                        }

                        linearLayout{
                            orientation = LinearLayout.VERTICAL

                            textView {
                                textSize = 18f
                                text = "Lineup"
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent){
                                gravity = Gravity.CENTER_HORIZONTAL
                            }

                            //goalkeeper
                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 1f

                                relativeLayout {
                                    homeGK = textView {
                                        textSize = 16f
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.START
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(16)
                                }

                                relativeLayout {
                                    textView {
                                        textSize = 14f
                                        text = "GK"
                                        textColor = colorPrimary
                                        setTypeface(null, Typeface.BOLD)
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                    topMargin = dip(16)
                                }

                                relativeLayout {
                                    awayGK = textView {
                                        textSize = 16f
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.END
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(16)
                                }
                            }

                            //Defense
                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 1f

                                relativeLayout {
                                    homeDEF = textView {
                                        textSize = 16f
                                        text
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.START
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(8)
                                }

                                relativeLayout {
                                    textView {
                                        textSize = 14f
                                        text = "Defense"
                                        textColor = colorPrimary
                                        setTypeface(null, Typeface.BOLD)
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                    topMargin = dip(8)
                                }

                                relativeLayout {
                                    awayDEF = textView {
                                        textSize = 16f
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.END
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(8)
                                }
                            }

                            //Midfield
                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 1f

                                relativeLayout {
                                    homeMID = textView {
                                        textSize = 16f

                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.START
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(8)
                                }

                                relativeLayout {
                                    textView {
                                        textSize = 14f
                                        text = "Midfield"
                                        textColor = colorPrimary
                                        setTypeface(null, Typeface.BOLD)
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                    topMargin = dip(8)
                                }

                                relativeLayout {
                                    awayMID = textView {
                                        textSize = 16f
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.END
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(8)
                                }
                            }

                            //Forward
                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 1f

                                relativeLayout {
                                    homeFW = textView {
                                        textSize = 16f

                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.START
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(8)
                                }

                                relativeLayout {
                                    textView {
                                        textSize = 14f
                                        text = "Forward"
                                        textColor = colorPrimary
                                        setTypeface(null, Typeface.BOLD)
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                    topMargin = dip(8)
                                }

                                relativeLayout {
                                    awayFW = textView {
                                        textSize = 16f
                                    }.lparams(width = wrapContent, height = wrapContent) {
                                        gravity = Gravity.END
                                    }
                                }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                    topMargin = dip(8)
                                }
                            }
                        }.lparams(width = matchParent, height = wrapContent){
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                        }

                    }.lparams(width = matchParent, height = wrapContent)
                }.lparams(width = matchParent, height = wrapContent)
            }

        }
    }
    // API Handle
    private fun requestAPI(){
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        mPresenter = EventDetailPresenter(this, request, gson)
        mPresenter.getEventList(mId)
        mPresenter.getHomeBadges(strHomeName)
        mPresenter.getAwayBadges(strAwayName)
    }
    override fun setEvent(event: List<FootballEvent>) {
        mFootballEvent = event[0]
        matchDate.text = mFootballEvent.dateEvent
        if(mFootballEvent.intHomeScore!=null){
            scoreText.text = mFootballEvent.intHomeScore + "-" + mFootballEvent.intAwayScore
        }
        homeName.text = mFootballEvent.strHomeTeam
        homeGoal.text = mFootballEvent.strHomeGoalDetails
        homeGK.text = mFootballEvent.strHomeLineupGoalkeeper
        homeDEF.text = mFootballEvent.strHomeLineupDefense
        homeMID.text = mFootballEvent.strHomeLineupMidfield
        homeFW.text = mFootballEvent.strHomeLineupForward

        awayName.text = mFootballEvent.strAwayTeam
        awayGoal.text = mFootballEvent.strAwayGoalDetails
        awayGK.text = mFootballEvent.strAwayLineupGoalkeeper
        awayDEF.text = mFootballEvent.strAwayLineupDefense
        awayMID.text = mFootballEvent.strAwayLineupMidfield
        awayFW.text = mFootballEvent.strAwayLineupForward
    }
    override fun setHomeBadges(teams: List<TeamBadges>) {
        Picasso.get().load(teams[0].strTeamBadge).into(homeBadges)
    }
    override fun setAwayBadges(teams: List<TeamBadges>) {
        Picasso.get().load(teams[0].strTeamBadge).into(awayBadges)
    }
    // Handle Top Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_add_to_favorites)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                // if exists, remove from fav table, vice versa
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite // change the bool value
                setFavorite() // toggle icon
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // Handle Database
    private fun favoriteState(){
        database.use {
            val result = select(FavMatch.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to mId)
            //SELECT * FROM TABLE_FAVORITE WHERE TEAM_ID = mId
            val favorite = result.parseList(classParser<FavMatch>())
            //if empty, store the id inside db
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    FavMatch.TABLE_FAVORITE,
                    FavMatch.TEAM_ID to mFootballEvent.idEvent,
                    FavMatch.DATE_EVENT to mFootballEvent.dateEvent,
                    FavMatch.HOME_NAME to mFootballEvent.strHomeTeam,
                    FavMatch.HOME_SCORE to mFootballEvent.intHomeScore,
                    FavMatch.HOME_GOAL to mFootballEvent.strHomeGoalDetails,
                    FavMatch.HOME_LINEUP_GK to mFootballEvent.strHomeLineupGoalkeeper,
                    FavMatch.HOME_LINEUP_DEF to mFootballEvent.strHomeLineupDefense,
                    FavMatch.HOME_LINEUP_MID to mFootballEvent.strHomeLineupMidfield,
                    FavMatch.HOME_LINEUP_FW to mFootballEvent.strHomeLineupForward,
                    FavMatch.AWAY_NAME to mFootballEvent.strAwayTeam,
                    FavMatch.AWAY_SCORE to mFootballEvent.intAwayScore,
                    FavMatch.AWAY_GOAL to mFootballEvent.strAwayGoalDetails,
                    FavMatch.AWAY_LINEUP_GK to mFootballEvent.strAwayLineupGoalkeeper,
                    FavMatch.AWAY_LINEUP_DEF to mFootballEvent.strAwayLineupDefense,
                    FavMatch.AWAY_LINEUP_MID to mFootballEvent.strAwayLineupMidfield,
                    FavMatch.AWAY_LINEUP_FW to mFootballEvent.strAwayLineupForward
                )
            }
            scrollView.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            scrollView.snackbar(e.localizedMessage).show()

        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    FavMatch.TABLE_FAVORITE,"TEAM_ID = {id}",
                    "id" to mId)
            }
            scrollView.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            scrollView.snackbar(e.localizedMessage).show()
        }
    }
}
