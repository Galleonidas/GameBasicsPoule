package com.example.gamebasicspoule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.exp


class PouleSimActivity : AppCompatActivity(), PouleSimAdapter.PouleSimInterface {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var pouleSimAdapter: RecyclerView.Adapter<PouleSimAdapter.ViewHolder>? = null

    //holds an arraylist of soccer teams
    private var teams : ArrayList<Team> = arrayListOf(
        Team("Team 1", 6, 2,0,0,0,0),
        Team("Team 2", 3, 1,0,0,0,0),
        Team("Team 3", 4, 5,0,0,0,0),
        Team("Team 4", 2, 7,0,0,0,0)
    )

    //holds an arraylist of games
    private var games : ArrayList<Game> = arrayListOf(
        Game(teams[0], 0,
             teams[1], 0,
            false),
        Game(teams[2], 0,
             teams[3], 0,
            false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        pouleSimAdapter = PouleSimAdapter(games, this)
        recyclerView.adapter = pouleSimAdapter;

        for(team in teams)
        {
            Poule.addTeam(team)
        }

    }

    override fun factorial(num: Int): Long {
        var result = 1L
        for (i in 2..num) result *= i
        return result
    }

    override fun calculateGoalProbability(avgGoalsForTeamA: Int, avgGoalsAgainstTeamB: Int) : Int
    {
        //2.5 being the average amount of goals scored.
        val teamAStrength = avgGoalsForTeamA / 2.5

        val teamBDefense = avgGoalsAgainstTeamB / 2.5

        val teamAGoalChance = 2.5 * teamAStrength * teamBDefense

        var goalOccurrences = 0

        var l = exp(-teamAGoalChance)

        var p = 1.0

        do {
            goalOccurrences++
            p *= Math.random();
        } while (p > l)

        //val probability = (teamAGoalChance.pow(goalOccurrences) * exp(teamAGoalChance) ) /  factorial(goalOccurrences)

        return goalOccurrences - 1;
    }

    override fun addGameToPoule(currentGame: Game, teamAGoals : Int, teamBGoals : Int)
    {
        currentGame.teamAGoals = teamAGoals
        currentGame.teamBGoals = teamBGoals
        Poule.addGame(currentGame);
    }


}