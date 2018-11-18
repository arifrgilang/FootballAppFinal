package com.rz.rz.footballappfinal.view.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.rz.rz.footballappfinal.R

import com.rz.rz.footballappfinal.R.color.colorAccent
import com.rz.rz.footballappfinal.api.ApiRepository
import com.rz.rz.footballappfinal.model.teams.Team
import com.rz.rz.footballappfinal.presenter.teams.TeamsView
import com.rz.rz.footballappfinal.presenter.teams.TeamsPresenter
import com.rz.rz.footballappfinal.utils.invisible
import com.rz.rz.footballappfinal.utils.visible
import com.rz.rz.footballappfinal.view.activities.TeamDetailActivity
import com.rz.rz.footballappfinal.utils.rvAdapter.teams.TeamsAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamsView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        setSpinner()
        setRvAdapter()
        requestAPI()

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    private fun setSpinner(){
        val spinnerItems = resources.getStringArray(R.array.leagueName)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Retrieve the team name that was clicked by user
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
    private fun setRvAdapter(){
        adapter = TeamsAdapter(teams) {
            // "it", is in the bind method inside adapter
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listTeam.adapter = adapter
    }

    private fun requestAPI(){
        presenter = TeamsPresenter(this, ApiRepository(), Gson())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        // Anko layout
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search Team"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getTeamBySearch(query)
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean = true
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
