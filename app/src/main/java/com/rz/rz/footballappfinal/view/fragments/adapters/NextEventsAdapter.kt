package com.rz.rz.footballappfinal.view.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.model.FootballEvent
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class NextEventsAdapter(private val events: List<FootballEvent>,
                        private val listener: (FootballEvent) -> Unit)
    : RecyclerView.Adapter<NextEventsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextEventsViewHolder {
        return NextEventsViewHolder(
            NextViewHolder().createView(AnkoContext.create(parent.context, parent))
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: NextEventsViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}

class NextEventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val matchDate: TextView = view.findViewById(R.id.match_date)
    private val homeName: TextView = view.findViewById(R.id.team_home_name)
    private val awayName: TextView = view.findViewById(R.id.team_away_name)

    fun bindItem(event: FootballEvent, listener: (FootballEvent) -> Unit){
        matchDate.text = event.dateEvent
        homeName.text = event.strHomeTeam
        awayName.text = event.strAwayTeam
        itemView.onClick { listener(event) }
    }
}

class NextViewHolder : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(6)
                }
                linearLayout {
                    padding = dip(8)
                    orientation = LinearLayout.VERTICAL
                    // Match Date
                    textView {
                        id = R.id.match_date
                        textColor = R.color.colorAccent
                    }.lparams {
                        height = wrapContent
                        width = wrapContent
                        gravity = Gravity.CENTER
                        bottomMargin = dip(6)
                    }
                    // Team A vs Team B
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        // Team A Name
                        textView {
                            id = R.id.team_home_name
                            textSize = 18f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams {
                            height = wrapContent
                            width = dip(0)
                            weight = 5f
                        }
                        // space
                        view {
                        }.lparams {
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // vs
                        textView {
                            id = R.id.vs
                            textSize = 16f
                            text = "vs"
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // space
                        view {
                        }.lparams {
                            height = wrapContent
                            width = dip(0)
                            weight = 1f
                        }
                        // Team B Name
                        textView {
                            id = R.id.team_away_name
                            textSize = 18f
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams {
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