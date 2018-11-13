package com.rz.rz.footballappfinal.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FootballEvent(
    @SerializedName("idEvent") var idEvent: String? = null,
    @SerializedName("strHomeTeam") var strHomeTeam: String? = null,
    @SerializedName("strAwayTeam") var strAwayTeam: String? = null,
    @SerializedName("intHomeScore") var intHomeScore: String? = null,
    @SerializedName("intAwayScore") var intAwayScore: String? = null,
    @SerializedName("dateEvent") var dateEvent: String? = null
) : Serializable