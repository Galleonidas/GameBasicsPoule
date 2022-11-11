package com.example.gamebasicspoule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultsGameAdapter(private val totalGames : ArrayList<Game>)
    : RecyclerView.Adapter<ResultsGameAdapter.ViewHolder>() {

    // Holds the views for adding it to buttons and text
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_GameNumber : TextView
        var btn_GameTeamA : Button
        var btn_GameTeamB : Button
//        var tv_TeamGoals : TextView
        var tv_TeamAGoals : TextView
        var tv_TeamBGoals : TextView
//        var tv_TeamPoints : TextView
        var tv_TeamAPoints : TextView
        var tv_TeamBPoints : TextView

        init {
            tv_GameNumber = itemView.findViewById(R.id.tvGameNumber)
            btn_GameTeamA = itemView.findViewById(R.id.btnGameTeamA)
            btn_GameTeamB = itemView.findViewById(R.id.btnGameTeamB)
//            tv_TeamGoals = itemView.findViewById(R.id.tvTeamGoals)
            tv_TeamAGoals = itemView.findViewById(R.id.tvTeamAGoals)
            tv_TeamBGoals = itemView.findViewById(R.id.tvTeamBGoals)
//            tv_TeamPoints = itemView.findViewById(R.id.tvTeamPoints)
            tv_TeamAPoints = itemView.findViewById(R.id.tvTeamAPoints)
            tv_TeamBPoints = itemView.findViewById(R.id.tvTeamBPoints)
        }

    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsGameAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.results_game_card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    //Load the Teams and Games in the Poule
        ResultsActivity().loadPouleData(Poule.teams)

        val gameNumberString = "Game" + (position + 1).toString()

        holder.tv_GameNumber.text = gameNumberString
        holder.btn_GameTeamA.text = totalGames[position].teamA.teamName
        holder.btn_GameTeamB.text = totalGames[position].teamB.teamName
        holder.tv_TeamAGoals.text = totalGames[position].teamAGoals.toString()
        holder.tv_TeamBGoals.text = totalGames[position].teamBGoals.toString()
        val teamAGoals = totalGames[position].teamAGoals
        val teamBGoals = totalGames[position].teamBGoals
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

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return totalGames.size
    }

    interface ResultsGameInterface
    {
        //stuff
    }
}