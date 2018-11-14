package com.rz.rz.footballappfinal.presenter.teams

import com.rz.rz.footballappfinal.model.teams.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}

