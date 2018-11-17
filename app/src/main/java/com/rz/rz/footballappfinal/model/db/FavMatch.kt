package com.rz.rz.footballappfinal.model.db

data class FavMatch(val id: Long?,
                    val teamId: String?,
                    val homeName: String?,
                    val awayName: String?,
                    val homeScore: String?,
                    val awayScore: String?,
                    val dateEvent: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
    }
}