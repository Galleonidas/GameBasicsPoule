package com.example.gamebasicspoule

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.exp

// The launcher page for simulating the Poule
class PouleSimActivity : AppCompatActivity(), PouleSimAdapter.PouleSimInterface {

    // The layout and adapter of the recyclerview in activity_poule_sim.xml that displays the simulated games
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var pouleSimAdapter: RecyclerView.Adapter<PouleSimAdapter.ViewHolder>? = null

    // Holds an arraylist of (soccer) Teams
    // avgGoalsFor and avgGoalsAgainst are equal to the team's strength and defense, these can be manipulated for different simulated results
    private var teams : ArrayList<Team> = arrayListOf(
        Team("Team 1", 6, 2,0,0,0,0),
        Team("Team 2", 3, 1,0,0,0,0),
        Team("Team 3", 4, 5,0,0,0,0),
        Team("Team 4", 2, 7,0,0,0,0)
    )

    // Holds an arraylist of Games
    private var games : ArrayList<Game> = arrayListOf(
        // First 3 games are played by Team 1 vs. Team 2
        Game(1, teams[0], 0, teams[1], 0,false),
        Game(2, teams[0], 0, teams[1], 0,false),
        Game(3, teams[0], 0, teams[1], 0,false),

        // Last 3 games are played by Team 3 vs. Team 4
        Game(4, teams[2], 0, teams[3], 0,false),
        Game(5, teams[2], 0, teams[3], 0,false),
        Game(6, teams[2], 0, teams[3], 0,false)
    )

    // Is created when PouleSimActivity is started
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poule_sim)

        // Holds button to resimulate the Poule if user wishes to
        val btn_Resimulate = findViewById<Button>(R.id.btnResimulate)

        // Resets the Poule and reloads this Activity
        btn_Resimulate.setOnClickListener{
            Poule.teams.clear()
            Poule.games.clear()
            finish()
            val intent = Intent(this, PouleSimActivity::class.java)
            startActivity(intent)
        }

        // RecyclerView in activity_poule_sim.xml
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        pouleSimAdapter = PouleSimAdapter(games, this)
        recyclerView.adapter = pouleSimAdapter;

    }

    // Finds the factorial of an integer, for example 5! = 1 * 2 * 3 * 4 * 5 = 120
    override fun factorial(num: Int): Long {
        var result = 1L
        for (i in 2..num) result *= i
        return result
    }

    // Based on the Poisson Distribution, more in the documentation
    override fun calculateGoalProbability(avgGoalsForTeamA: Int, avgGoalsAgainstTeamB: Int) : Int
    {
        //2.5 being the average amount of goals scored in a World Cup soccer match
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

        return goalOccurrences - 1;
    }

    // Add the currently simulated game into the Poule object's games list
    override fun addGameToPoule(currentGame: Game, teamAGoals : Int, teamBGoals : Int)
    {
        currentGame.teamAGoals = teamAGoals
        currentGame.teamBGoals = teamBGoals
        currentGame.gameFinished = true;
        Poule.addGame(currentGame);

        // Also, add teams to Poule if they do not exist in its team list yet
        val isTeamAInPoule = Poule.teams.any{ Team -> Team.teamName == currentGame.teamA.teamName }
        val isTeamBInPoule = Poule.teams.any{ Team -> Team.teamName == currentGame.teamB.teamName }
        if(!isTeamAInPoule)
        {
            Poule.teams.add(currentGame.teamA)
        }
        if(!isTeamBInPoule)
        {
            Poule.teams.add(currentGame.teamB)
        }
    }


}