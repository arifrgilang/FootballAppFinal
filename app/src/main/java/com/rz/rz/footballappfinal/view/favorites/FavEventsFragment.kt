package com.rz.rz.footballappfinal.view.favorites

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
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.view.activities.EventDetailActivity
import com.rz.rz.footballappfinal.model.db.FavMatch
import com.rz.rz.footballappfinal.model.db.database
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.rvAdapter.fav.FavEventsAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavEventsFragment : Fragment(), AnkoComponent<Context> {

    private var favoritesList: MutableList<FavMatch> = mutableListOf()
    // Layout lateinit
    private lateinit var mRV: RecyclerView
    private lateinit var mLoading: ProgressBar
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    // -
    private lateinit var mAdapter: FavEventsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        showFavorite()
        mRefreshLayout.onRefresh{
            favoritesList.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            mRefreshLayout.isRefreshing = false
            val result = select(FavMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavMatch>())
            mLoading.invisible()
            favoritesList.addAll(favorite)
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter(){
        mAdapter = FavEventsAdapter(favoritesList) {
            ctx.startActivity<EventDetailActivity>(
                "id" to "${it.teamId}",
                "HOME_NAME" to "${it.homeName}",
                "AWAY_NAME" to "${it.awayName}"
            )
        }
        mRV.adapter = mAdapter
    }
    // Anko Inflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
    ///////////////////// Anko layout /////////////////////
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            //
            mRefreshLayout = swipeRefreshLayout {
                id = R.id.favRefresh
                setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
                //
                relativeLayout {
                    // RV
                    mRV = recyclerView {
                        id = R.id.favRv
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
