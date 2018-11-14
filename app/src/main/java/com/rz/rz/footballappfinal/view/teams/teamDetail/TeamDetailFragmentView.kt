package com.rz.rz.footballappfinal.view.teams.teamDetail

import com.rz.rz.footballappfinal.model.teams.Team

interface TeamDetailFragmentView {
    fun showTeamDetail(data: List<Team>)
}