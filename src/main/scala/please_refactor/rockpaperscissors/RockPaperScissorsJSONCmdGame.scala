package please_refactor.rockpaperscissors

import benjamingarrett.rockpaperscissorstools.{Paper, RPSHistoryBasedPlayer, RPSMove, RPSTournament, Rock, Scissors}

import scala.io.StdIn.readLine

object RockPaperScissorsJSONCmdGame {
  def main(args: Array[String]): Unit = {
    // Test ParsingSeason here. If it starts working, will probably move to 
    // handleTournamentSeason and the only code here will be the function call.
    println("ParsingSeason results")
    // ParsingSeason.go

    // handleTournamentSeason
  }

  // Local Code contributions start here.
  private def handleTournamentSeason = {
    // Change to JSON parsed data for number of tournaments run. Still gets numRounds.
    // val tournaments

    // Change to JSON parsed data as input for initialization.
    // val numRounds

    // Change to JSON parsed data as input for initialization.
    // val randomSeed

    // Change to JSON parsed data as input for initializations.
    // val players

      // Change to JSON parsed data as input for initializations.
      // val name

      // Change to JSON parsed data as input for initializations.
      // val type

      // Change to JSON parsed data as input for initializations.
      // val weights

    // Stop here. Goal of above is to get list of respective players and tournaments passed to function below.
    /*
    val seasonResults =
      MixedTournamentSeason.handleTournamentSeason(players)(tournaments).toList.sortWith((firstPlayer, secondPlayer) => firstPlayer._2 > secondPlayer._2)
    println(s"Summary of tournament season:\n${seasonResults}")
    for (r <- seasonResults)
      println(s"Player: ${r._1.playerInfo}   Points: ${r._2}")
    println("===============")
    val seasonResults2 =
      MixedTournamentSeason.handleTournamentSeasonForClassLecture(players)(tournaments).toList.sortWith((firstPlayer, secondPlayer) => firstPlayer._2 > secondPlayer._2)
    for (r <- seasonResults2)
      println(s"Player: ${r._1.playerInfo}   Points: ${r._2}")
    println("===============")
    val allResults = tournaments
      .map((event: RPSTournament) => event.playTournament(players))
    val handle1 = MixedTournamentSeason.handle1(allResults)
    val handle2 = MixedTournamentSeason.handle2(allResults)
    val handle3 = MixedTournamentSeason.handle3(allResults)
    for (r <- handle1)
      println(s"Player: ${r._1.playerInfo}   Points: ${r._2}")
    println("===============")
    for (r <- handle2)
      println(s"Player: ${r._1.playerInfo}   Points: ${r._2}")
    println ("===============")
    for (r <- handle3)
      println(s"Player: ${r._1.playerInfo}   Points: ${r._2}")
    */
  }
}
