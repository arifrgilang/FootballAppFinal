package com.rz.rz.footballappfinal.presenter.matches

import com.rz.rz.footballappfinal.model.matches.FootballEvent
import com.rz.rz.footballappfinal.model.matches.TeamBadges

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

interface EventDetailView {
    fun showLoading()
    fun hideLoading()
    fun setEvent(event: List<FootballEvent>)
    fun setHomeBadges(teams: List<TeamBadges>)
    fun setAwayBadges(teams: List<TeamBadges>)
}