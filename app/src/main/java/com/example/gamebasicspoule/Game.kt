package com.example.gamebasicspoule

// Game holds a number, two teams, their simulated goal scores, and if it is finished or not
data class Game(var gameNumber: Int,
                var teamA:Team, var teamAGoals: Int,
                var teamB:Team, var teamBGoals: Int,
                var gameFinished: Boolean) {
}