package com.example.gamebasicspoule

import android.widget.Button
import android.widget.TextView

// Model for the PouleSimAdapter's RecyclerView
data class PouleSimModel(val btnResimulate: Button, val tvGameNr: TextView, val btnTeamA: Button, val btnTeamB: Button, val tvVersus: TextView,
                         val btnSimulate: Button, val btnResults: Button) {

}