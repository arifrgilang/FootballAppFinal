package com.rz.rz.footballappfinal.presenter.teams

import com.rz.rz.footballappfinal.model.teams.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}