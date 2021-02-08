package example

import example.Model.{Follows, RawTweet}

object Tweets {
  val tweetsFile: String = "src/main/resources/data/tweets.csv"

  //which tweets are from destinationID? destinationID, should match authorID in (tweets.csv)
  def getTweets(destinationIds: List[Follows]): List[RawTweet] = {
    val tweetsFromFollowers = CsvParser.tweetsRead(tweetsFile)
    for {
      destinationId <- destinationIds
      result <- tweetsFromFollowers.filter(_.authorId == destinationId.toString)
    } yield result
  }
}
