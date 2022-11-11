package com.example.gamebasicspoule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the RecyclerView found in activity_results.xml
class ResultsGameAdapter(private val totalGames : ArrayList<Game>)
    : RecyclerView.Adapter<ResultsGameAdapter.ViewHolder>() {

    // Holds the views for buttons and text in RecyclerView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_GameNumber : TextView
        var btn_GameTeamA : Button
        var btn_GameTeamB : Button
        var tv_TeamAGoals : TextView
        var tv_TeamBGoals : TextView
        var tv_TeamAPoints : TextView
        var tv_TeamBPoints : TextView

        init {
            tv_GameNumber = itemView.findViewById(R.id.tvGameNumber)
            btn_GameTeamA = itemView.findViewById(R.id.btnGameTeamA)
            btn_GameTeamB = itemView.findViewById(R.id.btnGameTeamB)
            tv_TeamAGoals = itemView.findViewById(R.id.tvTeamAGoals)
            tv_TeamBGoals = itemView.findViewById(R.id.tvTeamBGoals)
            tv_TeamAPoints = itemView.findViewById(R.id.tvTeamAPoints)
            tv_TeamBPoints = itemView.findViewById(R.id.tvTeamBPoints)
        }

    }

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsGameAdapter.ViewHolder {
        // Inflates the card_view_design view
        // That is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.results_game_card_view, parent, false)

        return ViewHolder(view)
    }

    // Binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    // Load the Teams and Games in the Poule
        ResultsActivity().loadPouleData(Poule.teams)

        val gameNumberString = "Game" + totalGames[position].gameNumber.toString()

        holder.tv_GameNumber.text = gameNumberString
        holder.btn_GameTeamA.text = totalGames[position].teamA.teamName
        holder.btn_GameTeamB.text = totalGames[position].teamB.teamName
        holder.tv_TeamAGoals.text = totalGames[position].teamAGoals.toString()
        holder.tv_TeamBGoals.text = totalGames[position].teamBGoals.toString()

        val teamAGoals = totalGames[position].teamAGoals
        val teamBGoals = totalGames[position].teamBGoals

        // Give score based on Victory = 3, Tie = 1, Loss = 0
        if(teamAGoals > teamBGoals)
        {
            holder.tv_TeamAPoints.text = "3";
            holder.tv_TeamBPoints.text = "0";
        }
        else if(teamAGoals == teamBGoals)
        {
            holder.tv_TeamAPoints.text = "1";
            holder.tv_TeamBPoints.text = "1";
        }
        else
        {
            holder.tv_TeamBPoints.text = "3";
            holder.tv_TeamAPoints.text = "0";
        }
    }

    // Return the number of the items in the list
    override fun getItemCount(): Int {
        return totalGames.size
    }
}