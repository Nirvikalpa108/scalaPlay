package example

import example.Model.Follows

object Follows {
  val followsFile: String = "src/main/resources/data/follows.csv"
  // make sure we've got an Int from the CLI input
  //TODO: check how I want to manage user id being string, but checking it's an int string value?!
  // what if the input is a long and not an Int??
  def processUserId(userId: String): Int = userId.toInt

  //who does the user id follow? (follows.csv) get the destinationID
  def getFollows(userId: Int): List[Follows] = {
    val follows = CsvParser.followsRead(followsFile)
    for {
      follows <- follows.filter(_.sourceUserId == userId.toString)
    } yield follows
  }
}
