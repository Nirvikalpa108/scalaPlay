package example

import scala.util.control.NonFatal

object Twitter {
  def main(args: Array[String]): Unit = {
    try {
      val input = args.head
      val userId = processUserId(input)
      val followers = getFollowers(userId)
      val tweetsFromFollowers = getTweetsFromFollowers(followers)
      val tweetsWithParsedTimestamp = parseTimestamp(tweetsFromFollowers)
      val tweetsWithHumanReadableTimestamp = makeTimestampHumanReadable(tweetsWithParsedTimestamp)
      val mostRecentTweets = getMostRecentTweets(tweetsWithHumanReadableTimestamp)
      val mostRecentTweetsWithDisplayName = getDisplayName(mostRecentTweets)
      val results = results(mostRecentTweetsWithDisplayName)
      println(results)
    } catch {
      case NonFatal(error) =>
        println(error.getMessage)
    }
  }

  // make sure we've got an Int from the CLI input
  def processUserId(userId: String): Int = ???
  // who does the user id follow? (follows.csv) get the destinationID
  def getFollowers(userID: Int): Int = ???
  // which tweets are from destinationID? destinationID, should match authorID in (tweets.csv)
  def getTweetsFromFollowers(destinationId: Int): List[RawTweet] = ???
  // parse timestamp in tweets.csv
  def parseTimestamp(tweetsWithUnparsedTimestamp: List[RawTweet]): List[Tweet] = ???
  // make timestamp human readable
  def makeTimestampHumanReadable(tweetsWithoutHumanReadableTimestamp: List[Tweet]): List[Tweet] = ???
  // sort tweets by most recent and take first 10 from that list
  def getMostRecentTweets(unsortedTweets: List[Tweet]): List[Tweet] = ???
  // get display name - match author id (tweets csv) to id in (users.csv)
  def getDisplayName(tweetsWithoutDisplayName: List[Tweet]): List[Tweet] = ???
  // amalgamate results: display name, timestamp human readable, tweet text
  def results(tweets: List[Tweet]): List[String] = ???
}


