package com.rz.rz.footballappfinal.model.players

import com.google.gson.annotations.SerializedName

class Player (
    @SerializedName("strFanart1")
    var strFanart1: String? = null,

    @SerializedName("strPlayer")
    var strPlayer: String? = null,

    @SerializedName("strWeight")
    var strWeight: String? = null,

    @SerializedName("strHeight")
    var strHeight: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null
    )
