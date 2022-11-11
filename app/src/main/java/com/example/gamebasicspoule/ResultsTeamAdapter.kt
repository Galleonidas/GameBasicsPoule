package com.example.gamebasicspoule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the RecyclerView found in activity_results.xml
class ResultsTeamAdapter(private val teams : ArrayList<Team>)
    : RecyclerView.Adapter<ResultsTeamAdapter.ViewHolder>() {

    // Holds the views for buttons and text in RecyclerView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_Position : TextView
        var btn_TeamResults : Button
        var tv_Points : TextView

        init {
            tv_Position = itemView.findViewById(R.id.tvPosition)
            btn_TeamResults = itemView.findViewById(R.id.btnTeamResults)
            tv_Points = itemView.findViewById(R.id.tvPoints)
        }
    }

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsTeamAdapter.ViewHolder {
        // Inflates the card_view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.results_team_card_view, parent, false)

        return ViewHolder(view)
    }

    // Binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Load the Teams and Games in the Poule
        ResultsActivity().loadPouleData(Poule.teams)

        // Sort the team based on descending order of points, then goalDifference, then goalsFor, then goalsAgainst
        val sortedTeam = teams.sortedWith(compareBy({-it.points}, {-it.goalsDifference}, {-it.goalsFor}, {-it.goalsAgainst}))
        holder.tv_Position.text = (position + 1).toString()
        holder.btn_TeamResults.text = sortedTeam[position].teamName
        holder.tv_Points.text = sortedTeam[position].points.toString()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return teams.size
    }
}