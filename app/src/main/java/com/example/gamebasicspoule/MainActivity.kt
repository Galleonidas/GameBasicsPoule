package com.example.gamebasicspoule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var pouleSimAdapter: RecyclerView.Adapter<PouleSimAdapter.ViewHolder>? = null

    //private val itemsList = ArrayList<String>()
    //private lateinit var pouleSimAdapter: PouleSimAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)

        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        pouleSimAdapter = PouleSimAdapter()
        recyclerView.adapter = pouleSimAdapter;

        //recyclerView.adapter = pouleSimAdapterAdapter
        //prepareItems()



        //val btnSim = findViewById<Button>(R.id.btnSimulate)

        //var team1 = Team("Team 1", 6, 0, 0, 0, 0);
        //var team2 = Team("Team 2", 8, 0, 0, 0, 0);
        //var team3 = Team("Team 3", 4, 0, 0, 0, 0);
        //var team4 = Team("Team 4", 2, 0, 0, 0, 0);

        //btnSim.setOnClickListener{
        //    btnSim.visibility = View.GONE
        //}
    }
}