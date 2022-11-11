package com.example.gamebasicspoule

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class PouleSimAdapter(private val games : ArrayList<Game>, pouleSimInterface : PouleSimInterface)
    : RecyclerView.Adapter<PouleSimAdapter.ViewHolder>() {


    // Holds the views for adding it to buttons and text
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn_TeamA : Button
        var btn_TeamB : Button
        var tv_Versus : TextView
        var btn_Simulate : Button
        var btn_Results : Button

        init {
            btn_TeamA = itemView.findViewById(R.id.btnTeamA)
            btn_TeamB = itemView.findViewById(R.id.btnTeamB)
            tv_Versus = itemView.findViewById(R.id.tvVersus)
            btn_Simulate= itemView.findViewById(R.id.btnSimulate)
            btn_Results = itemView.findViewById(R.id.btnResults)
        }

    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PouleSimAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poule_sim_card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn_TeamA.setOnClickListener{
            //action
        }
        holder.btn_TeamB.setOnClickListener{
            //action
        }
        holder.btn_Simulate.setOnClickListener{
            holder.btn_Simulate.visibility = View.GONE
            var teamAGoals = PouleSimActivity().calculateGoalProbability(games[position].teamA.avgGoalsFor, games[position].teamB.avgGoalsAgainst)
            var teamBGoals = PouleSimActivity().calculateGoalProbability(games[position].teamB.avgGoalsFor, games[position].teamA.avgGoalsAgainst)
            PouleSimActivity().addGameToPoule(games[position], teamAGoals, teamBGoals);
            holder.btn_Results.visibility = View.VISIBLE
            Toast.makeText(holder.itemView.context, "Team A: $teamAGoals Team B: $teamBGoals" , Toast.LENGTH_SHORT).show();
            Toast.makeText(holder.itemView.context, "Number of games in Poule" + Poule.games.size , Toast.LENGTH_SHORT).show();


        }
        holder.btn_Results.setOnClickListener{
            val intent = Intent(holder.itemView.context, ResultsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
        holder.btn_TeamA.text = games[position].teamA.teamName
        holder.btn_TeamB.text = games[position].teamB.teamName
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return games.size
    }

    interface PouleSimInterface
    {
        fun factorial(num: Int) : Long
        fun calculateGoalProbability(avgGoalsForTeamA: Int, avgGoalsAgainstTeamB: Int) : Int
        fun addGameToPoule(currentGame: Game, teamAGoals : Int, teamBGoals : Int)
    }
}