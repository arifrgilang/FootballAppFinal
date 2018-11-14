package com.rz.rz.footballappfinal.view.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.model.matches.FootballEvent
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PrevEventsAdapter(private val events: List<FootballEvent>,
                        private val listener: (FootballEvent) -> Unit)
    : RecyclerView.Adapter<PrevEventsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevEventsViewHolder {
        return PrevEventsViewHolder(
            PrevViewHolder().createView(AnkoContext.create(parent.context, parent))
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: PrevEventsViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}

class PrevEventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val matchDate: TextView = view.findViewById(R.id.match_date)
    private val homeName: TextView = view.findViewById(R.id.team_home_name)
    private val homeScore: TextView = view.findViewById(R.id.team_home_score)
    private val awayName: TextView = view.findViewById(R.id.team_away_name)
    private val awayScore: TextView = view.findViewById(R.id.team_away_score)

    fun bindItem(event: FootballEvent, listener: (FootballEvent) -> Unit){
        matchDate.text = event.dateEvent
        homeName.text = event.strHomeTeam
        homeScore.text = event.intHomeScore
        awayName.text = event.strAwayTeam
        awayScore.text = event.intAwayScore
        itemView.onClick { listener(event) }
    }
}

class PrevViewHolder : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView{
                lparams(width = matchParent, height = wrapContent){
                    margin = dip(6)
                }
                linearLayout{
                    padding = dip(8)
                    orientation = LinearLayout.VERTICAL
                    // Match Date
                    textView{
                        id = R.id.match_date
                        textColor = R.color.colorAccent
                    }.lparams{
                        height = wrapContent
                        width = wrapContent
                        gravity = Gravity.CENTER
                        bottomMargin = dip(6)
                    }
                    // Team A vs Team B score
                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL
                        // Team A Name
                        textView{
                            id = R.id.team_home_name
                            textSize = 18f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 5f
                        }
                        // Team A Score
                        textView{
                            id = R.id.team_home_score
                            textSize = 20f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // vs
                        textView{
                            id = R.id.vs
                            textSize = 16f
                            text = "vs"
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // Team B Score
                        textView{
                            id = R.id.team_away_score
                            textSize = 20f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // Team B Name
                        textView{
                            id = R.id.team_away_name
                            textSize = 18f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            height = wrapContent
                            width = dip(0)
                            weight = 5f
                        }
                    }.lparams(width = matchParent, height = wrapContent)
                }.lparams(width = matchParent, height = wrapContent)
            }
        }
    }
}
