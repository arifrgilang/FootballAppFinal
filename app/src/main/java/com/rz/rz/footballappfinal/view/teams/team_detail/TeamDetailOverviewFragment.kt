package com.rz.rz.footballappfinal.view.teams.team_detail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.teams.Team
import com.rz.rz.footballappfinal.presenter.teams.TeamDetailFragmentPresenter
import com.rz.rz.footballappfinal.presenter.teams.TeamDetailFragmentView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class TeamDetailOverviewFragment: Fragment(), AnkoComponent<Context>,
    TeamDetailFragmentView {

    private lateinit var id: String
    private lateinit var presenter: TeamDetailFragmentPresenter
    private lateinit var descTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = activity?.intent!!.getStringExtra("id")

        presenter = TeamDetailFragmentPresenter(
            this,
            ApiRepository(),
            Gson()
        )
        presenter.getTeamDetail(id)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            lparams(wrapContent, wrapContent)
            rightPadding = dip(10)
            leftPadding = dip(10)
            topPadding = dip(5)
            scrollView{
                descTextView = textView()
                bottomPadding = dip(5)
            }.lparams(matchParent, wrapContent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun showTeamDetail(data: List<Team>) {
        descTextView.text = data[0].teamDescription
     }
}