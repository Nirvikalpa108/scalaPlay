package example

import example.Model.Follow

object Follows {
  val followsFile: String = "src/main/resources/data/follows.csv"
  // make sure we've got an Int from the CLI input
  def processUserId(userId: String): Int = userId.toInt

  //who does the user id follow? (follows.csv) get the destinationID
  def getFollows(userId: Int): List[Follow] = {
    val follows = CsvParser.followsRead(followsFile)
    for {
      follows <- follows.filter(_.sourceUserId == userId.toString)
    } yield follows
  }
}
