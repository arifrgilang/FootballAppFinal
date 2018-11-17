package com.rz.rz.footballappfinal.presenter.teams

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.teams.TeamResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson){

    fun getTeamList(league: String?) {
        view.showLoading()
        async(UI){
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBAPI.getAllTeamResponse(league)),
                    TeamResponse::class.java)
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}
