package com.example.gamebasicspoule

// The Poule is a singleton object
// It holds the data of the currently simulated Poule and can be accessed across all Activities
object Poule
{
    // Holds data for Teams
    var teams = arrayListOf<Team>()
        private set

    // Adds a new Team
    fun addTeam(team: Team) {
        teams += team
    }

    // Clears the Teams (for resimulation purposes)
    fun clearTeams() {
        teams.clear()
    }

    // Holds data for Games
    var games= arrayListOf<Game>()
        private set

    // Adds a new Game
    fun addGame(game: Game) {
        games += game
    }

    // Clears the Games (for resimulation purposes)
    fun clearGames() {
        games.clear()
    }
}