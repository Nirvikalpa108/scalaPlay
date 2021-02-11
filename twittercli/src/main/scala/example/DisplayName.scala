package example

import example.Model.{RawTweet, TweetWithDisplayName}

object DisplayName {
  val usersFile: String = "src/main/resources/data/users.csv"

  // get display name - match author id (RawTweet) to id in (users.csv)
  //TODO pretty sure this function can be refactored
  def getDisplayName(tweetsWithoutDisplayName: List[RawTweet]): List[TweetWithDisplayName] = {
    val users = CsvParser.usersRead(usersFile)
     val displayNameToRawTweet = for {
       // give me each RawTweet, so I can get the authorId of each tweet
       tweet <- tweetsWithoutDisplayName
       // give me each user, so I can get the userID of each user
       user <- users
     } yield {
       if (tweet.authorId == user.userId) {
         TweetWithDisplayName(tweet.tweetId, tweet.authorId, tweet.timestamp, tweet.text, user.displayName)
       } else
         TweetWithDisplayName(tweet.tweetId, tweet.authorId, tweet.timestamp, tweet.text, "")
     }
    displayNameToRawTweet.filter(_.displayName != "")
  }

}
