package com.rz.rz.footballappfinal.model.matches

import com.google.gson.annotations.SerializedName

data class FootballEventResponse (
    @SerializedName("events")
    val events: List<FootballEvent>,
    @SerializedName("event")
    val searchEvent: List<FootballEvent>
)