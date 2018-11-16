package com.rz.rz.footballappfinal.presenter.teams

import com.rz.rz.footballappfinal.model.teams.Team

interface TeamDetailFragmentView {
    fun showTeamDetail(data: List<Team>)
}