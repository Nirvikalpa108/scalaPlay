package example

import example.Model.{Follow, RawTweet}

object Tweets {
  val tweetsFile: String = "src/main/resources/data/tweets.csv"

  //which tweets are from destinationID? destinationID, should match authorID in (tweets.csv)
  def getTweets(follows: List[Follow]): List[RawTweet] = {
    val tweetsFromFollowers = CsvParser.tweetsRead(tweetsFile)
    for {
      follow <- follows
      result <- tweetsFromFollowers.filter(_.authorId == follow.destinationUserId)
    } yield result
  }
}
