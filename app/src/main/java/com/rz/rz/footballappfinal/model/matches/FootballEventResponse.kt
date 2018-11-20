package com.rz.rz.footballappfinal.model.matches

import com.google.gson.annotations.SerializedName

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

data class FootballEventResponse (
    @SerializedName("events")
    val events: List<FootballEvent>,
    @SerializedName("event")
    val searchEvent: List<FootballEvent>
)