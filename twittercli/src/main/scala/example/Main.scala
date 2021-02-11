package example

import example.DisplayName.getDisplayName
import example.Follows.{getFollows, processUserId}
import example.TimestampParser.{cliOutput, formatTweets, getMostRecentTweets, parseTimestamp}
import example.Tweets._

import scala.util.control.NonFatal

//This is looking great!
// TODO fix code: Should be able to run in CLI with $ run 989489610
// I might have got the user id and another id confused?
// add README
// Could consider outputting json?

object Main {
  def main(args: Array[String]): Unit = {
    try {
      val input = args.head
      val userId = processUserId(input)
      val follows = getFollows(userId)
      val tweetsFromFollows = getTweets(follows)
      val tweetsFromFollowsWithDisplayName = getDisplayName(tweetsFromFollows)
      val tweetsWithParsedTimestamp = parseTimestamp(tweetsFromFollowsWithDisplayName)
      val mostRecentTweets = getMostRecentTweets(tweetsWithParsedTimestamp)
      val formattedTweets = formatTweets(mostRecentTweets)
      println(s"${formattedTweets.map(cliOutput)}")
    } catch {
      case NonFatal(error) =>
        println(error.getMessage)
    }
  }
}


