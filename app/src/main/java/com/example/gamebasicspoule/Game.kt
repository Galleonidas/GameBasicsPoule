package com.example.gamebasicspoule

data class Game(var teamA:Team, var teamAGoalsFor: Int, var teamAGoalDifference: Int,
                var teamB:Team, var teamBGoalsFor: Int, var teamBGoalDifference: Int,
                var gameFinished: Boolean) {

}