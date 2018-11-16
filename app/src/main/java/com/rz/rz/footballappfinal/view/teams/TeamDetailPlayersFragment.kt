package com.rz.rz.footballappfinal.view.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.players.PlayerList
import com.rz.rz.footballappfinal.presenter.players.PlayersListPresenter
import com.rz.rz.footballappfinal.presenter.players.PlayersListView
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.rz.rz.footballappfinal.view.activities.PlayerDetailActivity
import com.rz.rz.footballappfinal.view.rvAdapter.PlayersAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class TeamDetailPlayersFragment: Fragment(), AnkoComponent<Context>,
    PlayersListView {
    private var playersList: MutableList<PlayerList> = mutableListOf()
    private lateinit var mRV: RecyclerView
    private lateinit var mLoading: ProgressBar
    private lateinit var playerAdapter: PlayersAdapter
    private lateinit var mListPresenter: PlayersListPresenter
    private lateinit var teamId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamId = activity?.intent!!.getStringExtra("id")
        setRvAdapter()
        requestAPI()
    }

    private fun setRvAdapter(){
        playerAdapter = PlayersAdapter(playersList) {
            // "it", is in the bind method inside adapter
            ctx.startActivity<PlayerDetailActivity>("id" to "${it.idPlayer}")
        }
        mRV.adapter = playerAdapter
    }

    private fun requestAPI(){
        mListPresenter = PlayersListPresenter(this, ApiRepository(), Gson())
        mListPresenter.getPlayers(teamId)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            lparams(matchParent, wrapContent)
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                mRV = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                mLoading = progressBar {
                }.lparams {
                    centerHorizontally()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun showLoading() {
        mLoading.visible()
    }

    override fun hideLoading() {
        mLoading.invisible()
    }

    override fun showPlayersList(data: List<PlayerList>) {
        playersList.clear()
        playersList.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }
}