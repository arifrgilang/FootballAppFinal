package com.rz.rz.footballappfinal.presenter.matches.events

import com.rz.rz.footballappfinal.model.matches.FootballEvent

interface EventsView {
    fun showLoading()
    fun hideLoading()
    fun setListRV(event: List<FootballEvent>)
}
