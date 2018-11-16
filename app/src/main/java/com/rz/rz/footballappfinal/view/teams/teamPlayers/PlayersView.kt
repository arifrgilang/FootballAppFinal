package com.rz.rz.footballappfinal.view.teams.teamPlayers

import com.rz.rz.footballappfinal.model.players.Player

interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayersList(data: List<Player>)
}
