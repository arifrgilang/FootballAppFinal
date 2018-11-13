package com.rz.rz.footballappfinal.presenter.events

import com.rz.rz.footballappfinal.model.FootballEvent

interface EventsView {
    fun showLoading()
    fun hideLoading()
    fun setListRV(event: List<FootballEvent>)
}
