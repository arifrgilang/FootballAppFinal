package com.rz.rz.footballappfinal.presenter.detail

import com.rz.rz.footballappfinal.model.FootballEvent
import com.rz.rz.footballappfinal.model.TeamBadges

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun setEvent(event: List<FootballEvent>)
    fun setHomeBadges(teams: List<TeamBadges>)
    fun setAwayBadges(teams: List<TeamBadges>)
}