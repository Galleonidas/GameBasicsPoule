package com.example.gamebasicspoule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
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

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener{
            val intent = Intent(this, PouleSimActivity::class.java)
            startActivity(intent)
        }

        teamLayoutManager = LinearLayoutManager(this)
        gameLayoutManager = LinearLayoutManager(this)

        teamRecyclerView.layoutManager = teamLayoutManager
        resultsTeamAdapter = ResultsTeamAdapter(Poule.teams)
        teamRecyclerView.adapter = resultsTeamAdapter;

        gameRecyclerView.layoutManager = gameLayoutManager
        resultsGameAdapter = ResultsGameAdapter(Poule.games)
        gameRecyclerView.adapter = resultsGameAdapter
    }

    fun loadPouleData(teams: ArrayList<Team>)
    {
        for(team in teams)
        {
            team.goalsFor = 0
            team.goalsAgainst = 0
            team.goalsDifference = 0
            team.points = 0


            for(game in Poule.games)
            {
                if(team.teamName == game.teamA.teamName)
                {
                    team.goalsFor += game.teamAGoals
                    team.goalsAgainst += game.teamBGoals
                    team.goalsDifference += (game.teamAGoals - game.teamBGoals)
                    if(game.teamAGoals > game.teamBGoals)
                    {
                        team.points += 3
                    }
                    else if(game.teamAGoals == game.teamBGoals)
                    {
                        team.points += 1
                    }
                }
                else if(team.teamName == game.teamB.teamName)
                {
                    team.goalsFor += game.teamBGoals
                    team.goalsAgainst += game.teamAGoals
                    team.goalsDifference += (game.teamBGoals - game.teamAGoals)
                    if(game.teamBGoals > game.teamAGoals)
                    {
                        team.points += 3
                    }
                    else if(game.teamBGoals == game.teamAGoals)
                    {
                        team.points += 1
                    }
                }
            }
        }
    }
}