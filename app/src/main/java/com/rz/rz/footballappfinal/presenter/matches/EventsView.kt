package com.rz.rz.footballappfinal.presenter.matches

import com.rz.rz.footballappfinal.model.matches.FootballEvent

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

interface EventsView {
    fun showLoading()
    fun hideLoading()
    fun setListRV(event: List<FootballEvent>)
}
