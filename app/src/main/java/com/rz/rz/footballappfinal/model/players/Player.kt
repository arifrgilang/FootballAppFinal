package com.rz.rz.footballappfinal.model.players

import com.google.gson.annotations.SerializedName

class Player (
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strCutout")
    var strCutout: String? = null,

    @SerializedName("strFanart2")
    var strFanart2: String? = null,

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
