package com.example.gamebasicspoule

data class Game(var teamA:Team, var teamAGoals: Int,
                var teamB:Team, var teamBGoals: Int,
                var gameFinished: Boolean) {
}