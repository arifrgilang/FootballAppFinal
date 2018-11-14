package com.rz.rz.footballappfinal.presenter.matches.events

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.matches.FootballEventResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextEventsPresenter(private val view: EventsView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {
    fun requestEventList(leagueId: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getFutureEventsResponse(leagueId)),
                FootballEventResponse::class.java
            )
            uiThread {
                view.hideLoading()
                println("Next Matches Responses => ${data.events}")
                view.setListRV(data.events)
            }
        }
    }
}