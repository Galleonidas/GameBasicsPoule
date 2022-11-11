package com.example.gamebasicspoule

// Example of how the data class should be created
//fun main(args:Array<String>){
//    var team1 = Team("Team 1", 6, 0,0,0,0,0);
//    var team2 = Team("Team 2", 8, 0,0,0,0,0);
//    var team3 = Team("Team 3", 4, 0,0,0,0,0);
//    var team4 = Team("Team 4", 2, 0,0,0,0,0);
//}


data class Team(var teamName:String, var avgGoalsFor:Int, var avgGoalsAgainst: Int, var points:Int, var goalsDifference: Int, var goalsFor: Int, var goalsAgainst: Int)