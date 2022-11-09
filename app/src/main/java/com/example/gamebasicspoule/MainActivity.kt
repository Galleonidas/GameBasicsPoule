package com.example.gamebasicspoule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSim1 = findViewById<Button>(R.id.btnSimulate1)

        var team1 = Team("Team 1", 6, 0, 0, 0, 0);
        var team2 = Team("Team 2", 8, 0, 0, 0, 0);
        var team3 = Team("Team 3", 4, 0, 0, 0, 0);
        var team4 = Team("Team 4", 2, 0, 0, 0, 0);

        btnSim1.setOnClickListener{
            btnSim1.visibility = View.GONE
        }
    }
}