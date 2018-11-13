package com.rz.rz.footballappfinal.api

import com.rz.rz.footballappfinal.BuildConfig

object TheSportDBAPI {
    private const val PAST_LEAGUE: String = "eventspastleague.php?id="
    private const val NEXT_LEAGUE: String = "eventsnextleague.php?id="
    private const val SEARCH_EVENTS: String = "lookupevent.php?id="
    private const val SEARCH_TEAMS: String = "searchteams.php?t="

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
}