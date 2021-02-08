package example

import example.Model.RawTweet

object DisplayName {
  val usersFile: String = "src/main/resources/data/users.csv"

  // get display name - match author id (tweets csv) to id in (users.csv)
  def getDisplayName(tweetsWithoutDisplayName: List[RawTweet]): List[(String, List[String])] = {
    val users = CsvParser.usersRead(usersFile)
    ???
  }

}
