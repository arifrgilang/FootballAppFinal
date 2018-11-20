package com.rz.rz.footballappfinal.presenter.players

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.players.PlayerListResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class PlayersListPresenter(private val view: PlayersListView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
) {

    fun getPlayers(teamId: String) {
        async(UI){
            val data = bg{
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBAPI.getTeamPlayersResponse(teamId)),
                    PlayerListResponse::class.java
                )
            }
            view.showPlayersList(data.await().player)
            view.hideLoading()
        }
    }
}