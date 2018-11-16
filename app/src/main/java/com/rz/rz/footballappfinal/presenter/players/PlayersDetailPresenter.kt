package com.rz.rz.footballappfinal.presenter.players

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.players.PlayerResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class PlayersDetailPresenter(private val view: PlayersDetailView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson
) {

    fun getPlayerDetail(playerId: String) {
        async(UI){
            val data = bg{
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBAPI.getPlayerDetail(playerId)),
                    PlayerResponse::class.java
                )
            }
            view.showPlayerDetail(data.await().players)
            view.hideLoading()
        }
    }
}