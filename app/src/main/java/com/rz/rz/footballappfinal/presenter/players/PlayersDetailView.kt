package com.rz.rz.footballappfinal.presenter.players

import com.rz.rz.footballappfinal.model.players.Player

interface PlayersDetailView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<Player>)
}
