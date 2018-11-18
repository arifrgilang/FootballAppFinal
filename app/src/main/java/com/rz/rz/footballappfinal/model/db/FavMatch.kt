package com.rz.rz.footballappfinal.model.db

data class FavMatch(val id: Long?,
                    val teamId: String?,
                    val dateEvent: String?,
                    val homeName: String?,
                    val homeScore: String?,
                    val homeGoal: String?,
                    val homeGK: String?,
                    val homeDEF: String?,
                    val homeMID: String?,
                    val homeFW: String?,
                    val awayName: String?,
                    val awayScore: String?,
                    val awayGoal: String?,
                    val awayGK: String?,
                    val awayDEF: String?,
                    val awayMID: String?,
                    val awayFW: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_GOAL: String = "HOME_GOAL"
        const val HOME_LINEUP_GK: String = "HOME_LINEUP_GK"
        const val HOME_LINEUP_DEF: String = "HOME_LINEUP_DEF"
        const val HOME_LINEUP_MID: String = "HOME_LINEUP_MID"
        const val HOME_LINEUP_FW: String = "HOME_LINEUP_FW"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_GOAL: String = "AWAY_GOAL"
        const val AWAY_LINEUP_GK: String = "AWAY_LINEUP_GK"
        const val AWAY_LINEUP_DEF: String = "AWAY_LINEUP_DEF"
        const val AWAY_LINEUP_MID: String = "AWAY_LINEUP_MID"
        const val AWAY_LINEUP_FW: String = "AWAY_LINEUP_FW"
    }
}