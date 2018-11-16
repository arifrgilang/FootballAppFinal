package com.rz.rz.footballappfinal.view.teams.teamPlayers

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.players.PlayerResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayersPresenter(private val view: PlayersView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson
) {

    fun getPlayers(teamId: String) {
        async(UI){
            val data = bg{
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBAPI.getTeamPlayersResponse(teamId)),
                    PlayerResponse::class.java
                )
            }
            view.showPlayersList(data.await().player)
            view.hideLoading()
        }
    }
}