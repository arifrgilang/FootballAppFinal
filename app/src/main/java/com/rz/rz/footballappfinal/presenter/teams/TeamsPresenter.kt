package com.rz.rz.footballappfinal.presenter.teams

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.teams.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson){

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBAPI.getAllTeamResponse(league)),
                    TeamResponse::class.java
            )
            uiThread {
                view.showTeamList(data.teams)
                view.hideLoading()
            }
        }
    }

    fun getTeamBySearch(str: String?) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getTeamBySearch(str)),
                TeamResponse::class.java
            )
            uiThread {
                view.showTeamList(data.teams)
                view.hideLoading()
            }
        }
    }
}
