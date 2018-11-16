package com.rz.rz.footballappfinal.view.rvAdapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.model.players.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayersAdapter (private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun getItemCount(): Int = players.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val playerIcon: ImageView = view.findViewById(R.id.player_icon)
    private val playerName: TextView = view.findViewById(R.id.players_name)
    private val playerRole: TextView = view.findViewById(R.id.players_role)

    fun bindItem(player: Player, listener: (Player) -> Unit){
        Picasso.get().load(player.strCutout).into(playerIcon)
        playerName.text = player.strPlayer
        playerRole.text = player.strPosition
        itemView.onClick { listener(player) }

    }
}

class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_icon
                }.lparams{
                    topMargin = dip(15)
                    height = dip(50)
                    width = dip(50)
                }

                linearLayout{
                    lparams(wrapContent, wrapContent)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = R.id.players_name
                        textSize = 16f
                    }.lparams{
                        topMargin = dip(15)
                        leftMargin = dip(15)
                        rightMargin = dip(15)
                        bottomMargin = dip(5)
                    }

                    textView {
                        id = R.id.players_role
                        textSize = 14f
                        textColor = Color.GRAY
                    }.lparams{
                        leftMargin = dip(15)
                        rightMargin = dip(15)
                        bottomMargin = dip(15)
                    }
                }
            }
        }
    }
}