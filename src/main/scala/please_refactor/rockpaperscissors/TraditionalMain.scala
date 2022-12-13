import scala.annotation.tailrec
import scala.io.StdIn.readLine

// Due to issues with JSONCmdGame not recognizing ParsingSeason.scala, stripped down 
// TraditionalMain from csci395demos to test parsing season. It seems to work to this 
// level. So:
//  1. Need to deliver it to be instantiated, including making a call work in CmdGame.
//  2. Need instantiated items to be passed to the rest of the program.
//  3. Need to make this work at the next level down, then repeat step 2.
//     Also, still need to get this to read from JSON config file rather than local 
//     version.
//     Need to use toInt on tournaments, roundsPerMatch, and randomSeed values when
//     initializing.
 
object TraditionalMain {
  def main(args: Array[String]): Unit = {
    println("Parsing Season Result")
    ParsingSeason.go
  }
}
