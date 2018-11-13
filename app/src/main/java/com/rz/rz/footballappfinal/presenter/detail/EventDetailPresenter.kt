package com.rz.rz.footballappfinal.presenter.detail

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.FootballEventResponse
import com.rz.rz.footballappfinal.model.TeamBadgesResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventDetailPresenter (private val view: DetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getEventList(eventId: String?){
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getEventsResponse(eventId)),
                FootballEventResponse::class.java
            )

            uiThread {
                println("eventDetailResponse => ${data.events}")
                view.setEvent(data.events)
            }
        }
    }
    fun getHomeBadges(teamName: String?){
        view.showLoading()
        doAsync {
            val badgeUrl = gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getTeamsResponse(teamName)),
                TeamBadgesResponse::class.java
            )
            uiThread {
                println("Response Home Badges URL=> $badgeUrl")
                view.setHomeBadges(badgeUrl.teams)
                view.hideLoading()
            }
        }
    }
    fun getAwayBadges(teamName: String?){
        view.showLoading()
        doAsync {
            val badgeUrl = gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getTeamsResponse(teamName)),
                TeamBadgesResponse::class.java
            )
            uiThread {
                println("Response Away Badges URL=> $badgeUrl")
                view.setAwayBadges(badgeUrl.teams)
                view.hideLoading()
            }
        }
    }
}