package example

object Model {

  case class Follows(sourceUserId: String, destinationUserId: String)

  case class RawTweet(tweetId: String, authorId: String, timestamp: String, text: String)

  case class ParsedTimeStampTweet()

  case class HumanReadableTweet()

  case class User(userId: String, twitterScreenName: String, fullDisplayName: String)

}
