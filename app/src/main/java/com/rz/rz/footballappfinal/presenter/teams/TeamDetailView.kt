package com.rz.rz.footballappfinal.presenter.teams

import com.rz.rz.footballappfinal.model.teams.Team

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

interface TeamDetailView {
    fun showTeamDetail(data: List<Team>)
}