package com.rz.rz.footballappfinal.api

import com.rz.rz.footballappfinal.BuildConfig
import com.rz.rz.footballappfinal.utils.Const.ALL_TEAMS
import com.rz.rz.footballappfinal.utils.Const.DETAIL_TEAM
import com.rz.rz.footballappfinal.utils.Const.NEXT_LEAGUE
import com.rz.rz.footballappfinal.utils.Const.PAST_LEAGUE
import com.rz.rz.footballappfinal.utils.Const.PLAYER_TEAM
import com.rz.rz.footballappfinal.utils.Const.SEARCH_EVENTS
import com.rz.rz.footballappfinal.utils.Const.SEARCH_TEAMS

object TheSportDBAPI {

    fun getPastEventsResponse(leagueId: String?): String{
        return BuildConfig.BASE_URL + PAST_LEAGUE + leagueId
    }

    fun getFutureEventsResponse(leagueId: String?): String{
        return BuildConfig.BASE_URL + NEXT_LEAGUE + leagueId
    }

    fun getEventsResponse(eventId: String?): String {
        return BuildConfig.BASE_URL + SEARCH_EVENTS + eventId
    }

    fun getTeamsResponse(teamName: String?): String {
        return BuildConfig.BASE_URL + SEARCH_TEAMS + teamName
    }

    fun getAllTeamResponse(league: String?): String {
        return BuildConfig.BASE_URL + ALL_TEAMS + league
    }

    fun getTeamDetailResponse(teamId: String?): String{
        return BuildConfig.BASE_URL + DETAIL_TEAM + teamId
    }

    fun getTeamPlayersResponse(teamId: String?): String{
        return BuildConfig.BASE_URL + PLAYER_TEAM + teamId
    }
}