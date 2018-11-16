package com.rz.rz.footballappfinal.model.players

import com.google.gson.annotations.SerializedName


class PlayerList (
    @SerializedName("idPlayer")
    var idPlayer: String? = null,

    @SerializedName("strCutout")
    var strCutout: String? = null,

    @SerializedName("strPlayer")
    var strPlayer: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null
)