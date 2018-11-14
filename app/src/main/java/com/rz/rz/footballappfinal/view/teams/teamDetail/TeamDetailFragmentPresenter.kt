package com.rz.rz.footballappfinal.view.teams.teamDetail

import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.api.TheSportDBAPI
import com.rz.rz.footballappfinal.model.teams.TeamResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class TeamDetailFragmentPresenter(private val view: TeamDetailFragmentView,
                                  private val apiRepository: ApiRepository,
                                  private val gson: Gson
) {

    fun getTeamDetail(teamId: String) {
        async(UI){
            val data = bg{
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBAPI.getTeamDetailResponse(teamId)),
                    TeamResponse::class.java
                )
            }
            view.showTeamDetail(data.await().teams)
        }
    }
}