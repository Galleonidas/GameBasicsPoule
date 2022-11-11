package com.example.gamebasicspoule

object Poule
{
    var teams = arrayListOf<Team>()
        private set

    fun addTeam(team: Team) {
        teams += team
    }

    fun clearTeams() {
        teams.clear()
    }
    var games= arrayListOf<Game>()
        private set


    fun addGame(game: Game) {
        games += game
    }
    fun clearGames() {
        games.clear()
    }
}