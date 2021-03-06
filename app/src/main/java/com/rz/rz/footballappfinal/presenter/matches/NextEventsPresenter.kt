package com.rz.rz.footballappfinal.presenter.matches

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.matches.FootballEventResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

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

    fun requestEventBySearch(str: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getEventBySearch(str)),
                FootballEventResponse::class.java
            )
            uiThread {
                view.hideLoading()
                println("Matches Responses => ${data.searchEvent}")
                view.setListRV(data.searchEvent)
            }
        }
    }
}