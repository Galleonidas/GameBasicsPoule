package com.example.gamebasicspoule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultsActivity : AppCompatActivity() {

    private var teamLayoutManager: RecyclerView.LayoutManager? = null
    private var gameLayoutManager: RecyclerView.LayoutManager? = null
    private var resultsTeamAdapter: RecyclerView.Adapter<ResultsTeamAdapter.ViewHolder>? = null
    private var resultsGameAdapter: RecyclerView.Adapter<ResultsGameAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val teamRecyclerView: RecyclerView = findViewById(R.id.teamRecyclerview)
        val gameRecyclerView: RecyclerView = findViewById(R.id.gameRecyclerview)

        teamLayoutManager = LinearLayoutManager(this)
        gameLayoutManager = LinearLayoutManager(this)

        teamRecyclerView.layoutManager = teamLayoutManager
        resultsTeamAdapter = ResultsTeamAdapter(Poule.teams)
        teamRecyclerView.adapter = resultsTeamAdapter;

        gameRecyclerView.layoutManager = gameLayoutManager
        resultsGameAdapter = ResultsGameAdapter(Poule.games)
        gameRecyclerView.adapter = resultsGameAdapter
    }
}