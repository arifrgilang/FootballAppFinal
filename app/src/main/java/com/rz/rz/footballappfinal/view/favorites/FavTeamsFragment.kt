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
import com.rz.rz.footballappfinal.R.color.colorAccent
import com.rz.rz.footballappfinal.model.db.FavTeam
import com.rz.rz.footballappfinal.model.db.database
import com.rz.rz.footballappfinal.view.activities.TeamDetailActivity
import com.rz.rz.footballappfinal.utils.rv_adapter.fav.FavTeamsAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class FavTeamsFragment : Fragment(), AnkoComponent<Context> {
    private var favTeams: MutableList<FavTeam> = mutableListOf()
    private lateinit var adapter: FavTeamsAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavTeamsAdapter(favTeams) {
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listEvent.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favTeams.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavTeam>())
            favTeams.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                listEvent = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}