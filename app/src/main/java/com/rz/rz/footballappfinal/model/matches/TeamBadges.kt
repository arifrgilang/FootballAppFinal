package com.rz.rz.footballappfinal.model.matches

import com.google.gson.annotations.SerializedName

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

data class TeamBadges(
    @SerializedName("strTeamBadge") var strTeamBadge: String = ""
)