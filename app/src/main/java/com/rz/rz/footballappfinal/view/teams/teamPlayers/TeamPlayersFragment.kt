package com.rz.rz.footballappfinal.view.teams.teamPlayers

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class TeamPlayersFragment: Fragment(), AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            lparams(wrapContent, wrapContent)
            textView{
                text = "ini team player"
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
}