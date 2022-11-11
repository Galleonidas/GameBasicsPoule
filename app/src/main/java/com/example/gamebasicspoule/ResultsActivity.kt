package com.example.gamebasicspoule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// This Activity holds the results of the current Poule data of already simulated games
class ResultsActivity : AppCompatActivity() {

    // There are two RecyclerViews in the activity_results.xml
    // One holds a list of already simulated teams in the Poule
    private var teamLayoutManager: RecyclerView.LayoutManager? = null
    private var resultsTeamAdapter: RecyclerView.Adapter<ResultsTeamAdapter.ViewHolder>? = null

    // The other holds a list of already simulated games in the Poule
    private var gameLayoutManager: RecyclerView.LayoutManager? = null
    private var resultsGameAdapter: RecyclerView.Adapter<ResultsGameAdapter.ViewHolder>? = null


    // Is created when ResultsActivity is started
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val teamRecyclerView: RecyclerView = findViewById(R.id.teamRecyclerview)
        val gameRecyclerView: RecyclerView = findViewById(R.id.gameRecyclerview)

        // Holds button to go back to PouleSimActivity
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Sends us back to PouleSimActivity
        btnBack.setOnClickListener{
            val intent = Intent(this, PouleSimActivity::class.java)
            startActivity(intent)
        }

        // Adapter logic for both the teamRecyclerView and gameRecyclerView
        teamLayoutManager = LinearLayoutManager(this)
        gameLayoutManager = LinearLayoutManager(this)

        teamRecyclerView.layoutManager = teamLayoutManager
        resultsTeamAdapter = ResultsTeamAdapter(Poule.teams)
        teamRecyclerView.adapter = resultsTeamAdapter;

        gameRecyclerView.layoutManager = gameLayoutManager
        resultsGameAdapter = ResultsGameAdapter(Poule.games)
        gameRecyclerView.adapter = resultsGameAdapter
    }

    // Loads the already simulated games, teams and their results from the Poule
    fun loadPouleData(teams: ArrayList<Team>)
    {
        // Reset the Team data
        for(team in teams)
        {
            team.goalsFor = 0
            team.goalsAgainst = 0
            team.goalsDifference = 0
            team.points = 0

            // Then re-populate the data
            for(game in Poule.games)
            {
                if(team.teamName == game.teamA.teamName)
                {
                    // Add all scored goals per game
                    team.goalsFor += game.teamAGoals

                    // Add all conceded goals per game
                    team.goalsAgainst += game.teamBGoals

                    // Add the goalsDifference
                    team.goalsDifference += (game.teamAGoals - game.teamBGoals)

                    // Give score based on Victory = 3, Tie = 1, Loss = 0
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
                    // Add all scored goals per game
                    team.goalsFor += game.teamBGoals

                    // Add all conceded goals per game
                    team.goalsAgainst += game.teamAGoals

                    // Add the goalsDifference
                    team.goalsDifference += (game.teamBGoals - game.teamAGoals)

                    // Give score based on Victory = 3, Tie = 1, Loss = 0
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