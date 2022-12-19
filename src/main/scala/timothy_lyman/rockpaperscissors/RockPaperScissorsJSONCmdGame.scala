package timothy_lyman.rockpaperscissors

import benjamingarrett.rockpaperscissorstools.{Paper, RPSHistoryBasedPlayer, RPSMove, RPSTournament, Rock, Scissors}

import scala.io.StdIn.readLine

import fpinscala.parsing.JSON.{JArray, JNumber, JObject, JString}
import fpinscala.parsing.{JSON, Location, ParseError}

import scala.::
import scala.collection.IterableOnce.iterableOnceExtensionMethods

object ParsingSeason {
  // Outermost object having inside: String, String, Boolean, Number, Number, List of Strings in which order doesn't matter.
  // Testing value. Theoretically could be replaced with a direct call to the JSON config file. 
  // So, how would we make that call?
  val jsonTxt = //"tournamentConfig.json"
    
    """
{
  "tournaments" : 3,
  "roundsPerMatch"  : 7,
  "players" : [ "Losing 1", "Losing 2", "Winning 1", "Random 1", "Last Winning 1" ]
}
"""


  def go = {
    // fpinscala is stuff from book. This takes their parsing, reference.
    val P = fpinscala.parsing.Reference
    import fpinscala.parsing.ReferenceTypes.Parser
    val json: Parser[JSON] = JSON.jsonParser(P)
    val resultOfParsing = P.run(json)(jsonTxt) // this parses JSON input into a JSON object
    // Going with flatMap since it was reviewed in class.
    // Instead of println, should this be a return statement?
    // If so, that should mean this will handle the parsing.
    resultOfParsing.flatMap(j => betterUnpackUsingFlatMap(j)).map(dto => println(dto)).map(_ => ())
    // resultOfParsing.flatMap(j => betterUnpackUsingFlatMap(j)).map(dto => return(dto)).map(_ => ())
    }


  // Starting with one level, which matches original parsing demo's depth and use of a list of strings.
  // Can possibly convert Doubles to Ints when initializing?
  case class SeasonDTO(
      tournaments: Double,
      roundsPerMatch: Double,
      players: List[String])

  def unpackList(c: List[JSON], r: Either[ParseError,List[String]]): Either[ParseError,List[String]] =
    c match {
      case ::(head, next) => head match {
        case JString(v) => unpackList(next, r.flatMap(list => Right(v :: list)))
        case p: ParseError => Left(p)
      }
      case Nil => r
    }

  // flatMap unpacking. Other versions removed for simplicity.
  def betterUnpackUsingFlatMap(json: JSON): Either[ParseError, SeasonDTO] = {
    json match {
      case jObject: JObject =>
        unpackNumber(jObject, "tournaments")
          .flatMap(tournaments => unpackNumber(jObject, "roundsPerMatch")
          .flatMap(roundsPerMatch => unpackArray(jObject, "players")
          .map(players => SeasonDTO(tournaments, roundsPerMatch, players))))
      case _ => Left(ParseError(List((Location("Could not unpack JSON contents"), "Could not unpack JSON contents"))))
    }
  }

  def unpackString(jObject: JObject, key: String): Either[ParseError,String] = jObject.get(key) match {
    case jString: JString => Right(jString.get)
    case _ => Left(ParseError(List((Location("Could not unpack ticker"), "ticker"))))
  }

  //No boolean values in DTO, so I've removed it.

  /*
  def unpackNumber(jObject: JObject, key: String): Either[ParseError, Int] = jObject.get(key) match {
    case jNumber: JNumber => Right(jNumber.get)
    case _ => Left(ParseError(List((Location("Could not unpack ticker"), "ticker"))))
  }
  */

  def unpackNumber(jObject: JObject, key: String): Either[ParseError, Double] = jObject.get(key) match {
    case jNumber: JNumber => Right(jNumber.get)
    case _ => Left(ParseError(List((Location("Could not unpack weights"), "weights"))))
  }

  def unpackArray(jObject: JObject, key: String): Either[ParseError, List[String]] = {
    for {
      playersPacked <- jObject.get(key) match {
        case jArray: JArray => Right(jArray.get)
        case _ => Left(ParseError(List((Location("Could not unpack players"), "players"))))
      }
      players <- unpackList(playersPacked.toList, Right(List.empty))
    } yield players
  }
}

object RockPaperScissorsJSONCmdGame {
  def main(args: Array[String]): Unit = {
    handleTournamentSeason
  }

  // Local Code contributions start here.
  private def handleTournamentSeason = {

    // Change to JSON parsed data as input for initializations.
    val weights1 = Map[RPSMove, Double](Rock -> 0.75, Paper -> 0.13, Scissors -> 0.12)
    val weights2 = Map[RPSMove, Double](Rock -> 0.1, Paper -> 0.8, Scissors -> 0.1)
    val weights3 = Map[RPSMove, Double](Rock -> 0.05, Paper -> 0.05, Scissors -> 0.9)
    val weights4 = Map[RPSMove, Double](Rock -> 0.6, Paper -> 0.2, Scissors -> 0.2)
    val weights5 = Map[RPSMove, Double](Rock -> 0.5, Paper -> 0.25, Scissors -> 0.25)

    // Change to JSON parsed data as input for initializations.

    // println("ParsingSeason results")
    /* val seasonValues = ParsingSeason.go */

    // Change to JSON parsed data as input for initialization.
    /* val numRounds = seasonValues.roundsPerMatch */
    val numRounds = 3

    // Change to JSON parsed data for number of tournaments run. Still gets numRounds.
    /* 
    // val tournaments
    // for(_ <- seasonValues.tournaments) {
    //  tournaments.toList(numRounds)
    // } 
    */
      val tournaments = List(
      new IndividualMatchRoundRobinTournament(numRounds),
      new IndividualMatchRoundRobinTournament(numRounds),
      new IndividualMatchRoundRobinTournament(numRounds),
      new IndividualMatchRoundRobinTournament(numRounds)
    )

    // Change to JSON parsed data as input for initializations.
    /* val players = seasonValues.players */
      val players = List(
      new LastLosingMovePlayer("Last Losing Move Player"),
      new BiasedRandomMovePlayer(s"Biased Random Move Player: ${weights1}", weights1),
      new LastWinningMovePlayer("Last Winning Move Player"),
      new RandomMovePlayer("Random Move Player 1"),
      new BiasedRandomMovePlayer(s"Biased Random Move Player: ${weights2}", weights2),
      new MajorityWinningMovePlayer("Majority Winning Move Player"),
      new BiasedRandomMovePlayer(s"Biased Random Move Player: ${weights3}", weights3),
      new MajorityLosingMovePlayer("Majority Losing Move Player"),
      new BiasedRandomMovePlayer(s"Biased Random Move Player: ${weights4}", weights4),
      new RandomMovePlayer("Random Move Player 1"),
      new BiasedRandomMovePlayer(s"Biased Random Move Player: ${weights5}", weights5),
    )

      // Change to JSON parsed data as input for initializations.
      // val name

      // Change to JSON parsed data as input for initializations.
      // val type

      // Change to JSON parsed data as input for initializations.
      // val weights

    // Stop here. Goal of above is to get list of respective players and tournaments passed to function below.
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
  }
}
