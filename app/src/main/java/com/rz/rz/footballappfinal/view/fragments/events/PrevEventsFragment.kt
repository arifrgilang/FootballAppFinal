package com.rz.rz.footballappfinal.view.fragments.events

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.R.color.colorAccent
import com.rz.rz.footballappfinal.view.activities.EventDetailActivity
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.matches.FootballEvent
import com.rz.rz.footballappfinal.presenter.matches.events.PrevEventsPresenter
import com.rz.rz.footballappfinal.presenter.matches.events.EventsView
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.rz.rz.footballappfinal.view.fragments.adapters.PrevEventsAdapter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PrevEventsFragment : Fragment(), AnkoComponent<Context>, EventsView {
    private var footballEventList: MutableList<FootballEvent> = mutableListOf()
    // Layout lateinit
    private lateinit var mRV: RecyclerView
    private lateinit var mLoading: ProgressBar
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    // -
    private lateinit var mPresenter: PrevEventsPresenter
    private lateinit var mAdapter: PrevEventsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        requestAPI()
        mRefreshLayout.onRefresh{
            mPresenter.requestEventList(getString(R.string.league_id))
        }
    }

    private fun setAdapter(){
        mAdapter = PrevEventsAdapter(footballEventList) {
            ctx.startActivity<EventDetailActivity>(
                "id" to "${it.idEvent}",
                "HOME_NAME" to "${it.strHomeTeam}",
                "AWAY_NAME" to "${it.strAwayTeam}"
            )
        }
        mRV.adapter = mAdapter
    }
    private fun requestAPI(){
        val request = ApiRepository()
        val gson = Gson()
        mPresenter = PrevEventsPresenter(this, request, gson)
        mPresenter.requestEventList(getString(R.string.league_id))
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
    ///////////////////// Anko layout /////////////////////
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            //
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
                        id = R.id.prevRv
                    }.lparams(width = matchParent, height = wrapContent)
                    // Loading bar
                    mLoading = progressBar {
                        id = R.id.loading
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
}

