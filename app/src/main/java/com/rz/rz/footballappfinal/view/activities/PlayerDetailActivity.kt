package com.rz.rz.footballappfinal.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.rz.rz.footballappfinal.R
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.players.Player
import com.rz.rz.footballappfinal.presenter.players.PlayersDetailPresenter
import com.rz.rz.footballappfinal.presenter.players.PlayersDetailView
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.squareup.picasso.Picasso

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class PlayerDetailActivity : AppCompatActivity(), PlayersDetailView {
    private lateinit var presenter: PlayersDetailPresenter
    private lateinit var playerLoading: ProgressBar

    private lateinit var pImage: ImageView
    private lateinit var pWeight: TextView
    private lateinit var pHeight: TextView
    private lateinit var pRole: TextView
    private lateinit var pDesc: TextView

    private lateinit var playerId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        initView()
        getIntentValue()
        requestApi()
    }

    private fun initView(){
        playerLoading = findViewById(R.id.player_loading)
        pImage = findViewById(R.id.player_fanart)
        pWeight = findViewById(R.id.player_weight)
        pHeight = findViewById(R.id.player_height)
        pRole = findViewById(R.id.p_role)
        pDesc = findViewById(R.id.p_desc)
    }

    private fun getIntentValue(){
        val intent = intent
        playerId = intent.getStringExtra("id")
    }

    private fun requestApi(){
        presenter = PlayersDetailPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerDetail(playerId)
    }

    override fun showLoading() {
        playerLoading.visible()
    }

    override fun hideLoading() {
        playerLoading.invisible()
    }

    override fun showPlayerDetail(data: List<Player>) {
        Picasso.get().load(data[0].strFanart1).into(pImage)
        pWeight.text = data[0].strWeight
        pHeight.text = data[0].strHeight
        pRole.text = data[0].strPosition
        pDesc.text = data[0].strDescriptionEN

        supportActionBar?.title = data[0].strPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
