package com.rz.rz.footballappfinal.view.matches

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.R.color.colorAccent
import com.rz.rz.footballappfinal.view.activities.EventDetailActivity
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.matches.FootballEvent
import com.rz.rz.footballappfinal.presenter.matches.EventsView
import com.rz.rz.footballappfinal.presenter.matches.NextEventsPresenter
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.rz.rz.footballappfinal.utils.rv_adapter.matches.NextEventsAdapter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class NextEventsFragment : Fragment(), AnkoComponent<Context>, EventsView {
    private var footballEventList: MutableList<FootballEvent> = mutableListOf()
    // Layout lateinit
    private lateinit var mRV: RecyclerView
    private lateinit var mLoading: ProgressBar
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    // -
    private lateinit var mPresenter: NextEventsPresenter
    private lateinit var mAdapter: NextEventsAdapter
    //
    private lateinit var leagueId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        setSpinner()
        setRvAdapter()
        requestAPI()

        mRefreshLayout.onRefresh{
            mPresenter.requestEventList(leagueId)
        }
    }

    private fun setSpinner(){
        val strLeagueId = resources.getStringArray(R.array.leagueId)
        leagueId = strLeagueId[0].toString()

        val spinnerItems = resources.getStringArray(R.array.leagueName)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Retrieve the team name that was clicked by user
                leagueId = strLeagueId[position].toString()
                mPresenter.requestEventList(leagueId)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setRvAdapter(){
        mAdapter = NextEventsAdapter(footballEventList) {
            ctx.startActivity<EventDetailActivity>(
                "id" to "${it.idEvent}",
                "HOME_NAME" to "${it.strHomeTeam}",
                "AWAY_NAME" to "${it.strAwayTeam}"
            )
        }
        mRV.adapter = mAdapter
    }

    private fun requestAPI(){
        mPresenter = NextEventsPresenter(this, ApiRepository(), Gson())
        mPresenter.requestEventList(leagueId)
    }
    // Anko Inflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
    // Interface Method(s)
    override fun showLoading() {
        mLoading.visible()
    }
    override fun hideLoading() {
        mLoading.invisible()
    }
    override fun setListRV(event: List<FootballEvent>) {
        mRefreshLayout.isRefreshing = false
        mRV.recycledViewPool.clear()
        // Insert response
        footballEventList.clear()
        footballEventList.addAll(event)
        //
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search Next Events"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mPresenter.requestEventBySearch(query)
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean = true
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    ///////////////////// Anko layout /////////////////////
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            //
            spinner = spinner()
            mRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
                //
                relativeLayout {
                    // RV
                    mRV = recyclerView {
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(width = matchParent, height = wrapContent)
                    // Loading bar
                    mLoading = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
}
