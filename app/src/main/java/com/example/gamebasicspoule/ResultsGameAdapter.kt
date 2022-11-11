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
        var tv_Position : TextView
        var btn_TeamResults : Button
        var tv_Points : TextView

        init {
            tv_Position = itemView.findViewById(R.id.tvPosition)
            btn_TeamResults = itemView.findViewById(R.id.btnTeamResults)
            tv_Points = itemView.findViewById(R.id.tvPoints)
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

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return totalGames.size
    }

    interface TeamResultsInterface
    {
        //stuff
    }
}