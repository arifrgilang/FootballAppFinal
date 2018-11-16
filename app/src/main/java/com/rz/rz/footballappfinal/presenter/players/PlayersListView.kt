package com.rz.rz.footballappfinal.presenter.players

import com.rz.rz.footballappfinal.model.players.PlayerList

interface PlayersListView {
    fun showLoading()
    fun hideLoading()
    fun showPlayersList(data: List<PlayerList>)
}
