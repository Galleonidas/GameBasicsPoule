package com.example.gamebasicspoule

import android.widget.Button
import android.widget.TextView

// Model for the ResultsGameAdapter's gameRecyclerView
data class ResultsGameModel(val btnTeamA: Button, val btnTeamB: Button, val tvVersus: TextView,
                            val btnSimulate: Button, val btnResults: Button) {

}