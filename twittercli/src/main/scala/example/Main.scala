package example

import example.DisplayName.getDisplayName
import example.Follows.{getFollows, processUserId}
import example.TimestampParser.{getMostRecentTweets, makeTimestampHumanReadable, parseTimestamp, results}
import example.Tweets._

import scala.util.control.NonFatal

// Good work!
// Next steps: figure out why the tests are not passing.
// complete the getDisplayName function with the advice of Will!
// you're winning!

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
      val tweetsWithHumanReadableTimestamp = makeTimestampHumanReadable(mostRecentTweets)
      val result = results(tweetsWithHumanReadableTimestamp)
      println(result)
    } catch {
      case NonFatal(error) =>
        println(error.getMessage)
    }
  }
}


