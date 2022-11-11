package com.example.gamebasicspoule

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the RecyclerView found in activity_poule_sim.xml
class PouleSimAdapter(private val games : ArrayList<Game>, pouleSimInterface : PouleSimInterface)
    : RecyclerView.Adapter<PouleSimAdapter.ViewHolder>() {

    // Holds the views for buttons and text in RecyclerView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_GameNr: TextView
        var btn_TeamA : Button
        var btn_TeamB : Button
        var tv_Versus : TextView
        var btn_Simulate : Button
        var btn_Results : Button

        init {
            tv_GameNr = itemView.findViewById(R.id.tvGameNr)
            btn_TeamA = itemView.findViewById(R.id.btnTeamA)
            btn_TeamB = itemView.findViewById(R.id.btnTeamB)
            tv_Versus = itemView.findViewById(R.id.tvVersus)
            btn_Simulate= itemView.findViewById(R.id.btnSimulate)
            btn_Results = itemView.findViewById(R.id.btnResults)
        }

    }

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PouleSimAdapter.ViewHolder {
        // inflates the poule_sim_card_view.xml design
        // that is used to hold list of buttons and text
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poule_sim_card_view, parent, false)

        return ViewHolder(view)
    }

    // Binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            // Finds the gameNumber of the current holder
            val gameNumber = "Game " + games[position].gameNumber.toString()
            holder.tv_GameNr.text = gameNumber

            // Finds the names of Team A and B in the Game of the current holder
            holder.btn_TeamA.text = games[position].teamA.teamName
            holder.btn_TeamB.text = games[position].teamB.teamName

            // Unfinished setOnClickListener methods
            holder.btn_TeamA.setOnClickListener{
                //action to send user to activity that shows team strength and defense
            }
            holder.btn_TeamB.setOnClickListener{
                //action to send user to activity that shows team strength and defense
            }

            // Button to simulate a game between Team A and Team B, after use it cannot be reclicked
            holder.btn_Simulate.setOnClickListener{
            holder.btn_Simulate.visibility = View.GONE

            // Calculates the randomized goal for Team A and Team B based on the Poisson Distribution
            var teamAGoalsR1 = PouleSimActivity().calculateGoalProbability(games[position].teamA.avgGoalsFor, games[position].teamB.avgGoalsAgainst)
            var teamBGoalsR1 = PouleSimActivity().calculateGoalProbability(games[position].teamB.avgGoalsFor, games[position].teamA.avgGoalsAgainst)

            // Update the current Game data
            games[position].teamAGoals = teamAGoalsR1
            games[position].teamBGoals = teamBGoalsR1

                // And add it to the Poule data
            PouleSimActivity().addGameToPoule(games[position], teamAGoalsR1, teamBGoalsR1);

                // Simulation Button is replaced with Results Button
            holder.btn_Results.visibility = View.VISIBLE

        }

        // Sends user to the ResultsActivity to see current results of the Poule
        holder.btn_Results.setOnClickListener{
            val intent = Intent(holder.itemView.context, ResultsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

        // If the games have been simulated, only give visibility to the results button
        val isGameInPoule = Poule.games.any{ Game -> Game.gameNumber == games[position].gameNumber}
        if(isGameInPoule)
        {
            holder.btn_Simulate.visibility = View.GONE
            holder.btn_Results.visibility = View.VISIBLE

        }

        // If the games have NOT been simulated, do not show their Simulation Button
        if(!games[position].gameFinished)
        {
            holder.btn_Simulate.visibility = View.GONE
              }

        // Check which games are unfinished
        val unfinishedGames = filterUnfinishedGames(games)

        // Only show the next available unfinished Game's Simulation Button
        for(game in unfinishedGames)
        {
            if(games[position].gameNumber == game.gameNumber && game == unfinishedGames[0])
            {
                holder.btn_Simulate.visibility = View.VISIBLE
                Toast.makeText(holder.itemView.context, games[position].gameFinished.toString() , Toast.LENGTH_SHORT).show();
            }
        }

    }

    // Filter unfinished games, should go to PouleSimActivity
    fun filterUnfinishedGames(checkGames: ArrayList<Game>) : ArrayList<Game>
    {
        val unfinishedGames = arrayListOf<Game>()
        for(game in checkGames)
        {
            val isGameInPoule = Poule.games.any{ Game -> Game.gameNumber == game.gameNumber}
            if(!game.gameFinished && !isGameInPoule)
            {
                unfinishedGames.add(game)
            }
        }
        return unfinishedGames
    }

    // Return the number of the items in the games list
    override fun getItemCount(): Int {
        return games.size
    }

    // Interface for callback via MainActivity() methods to Adapter class
    interface PouleSimInterface
    {
        fun factorial(num: Int) : Long
        fun calculateGoalProbability(avgGoalsForTeamA: Int, avgGoalsAgainstTeamB: Int) : Int
        fun addGameToPoule(currentGame: Game, teamAGoals : Int, teamBGoals : Int)
    }
}