package com.rz.rz.footballappfinal.model.teams

import com.google.gson.annotations.SerializedName

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class Team (
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null
)
