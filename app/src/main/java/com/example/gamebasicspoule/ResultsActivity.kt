package com.example.gamebasicspoule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultsActivity : AppCompatActivity() {

    private var teamLayoutManager: RecyclerView.LayoutManager? = null
    private var gameLayoutManager: RecyclerView.LayoutManager? = null
    private var teamResultsAdapter: RecyclerView.Adapter<TeamResultsAdapter.ViewHolder>? = null
    private var gameResultsAdapter: RecyclerView.Adapter<GameResultsAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val teamRecyclerView: RecyclerView = findViewById(R.id.teamRecyclerview)
        val gameRecyclerView: RecyclerView = findViewById(R.id.gameRecyclerview)

        teamLayoutManager = LinearLayoutManager(this)
        gameLayoutManager = LinearLayoutManager(this)

        teamRecyclerView.layoutManager = teamLayoutManager
        teamResultsAdapter = TeamResultsAdapter(Poule.teams)
        teamRecyclerView.adapter = teamResultsAdapter;

        gameRecyclerView.layoutManager = gameLayoutManager
        gameResultsAdapter = GameResultsAdapter(Poule.games)
        gameRecyclerView.adapter = gameResultsAdapter
    }
}