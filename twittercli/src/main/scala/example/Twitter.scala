package example

import scala.io.Source
import scala.util.control.NonFatal

// Amina, you've completed your first test and you've written a function to parse csv!
// You need to work on getting the csv into a Map that you need to look up the userID and return the destinationID

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
      val result = results(mostRecentTweetsWithDisplayName)
      println(result)
    } catch {
      case NonFatal(error) =>
        println(error.getMessage)
    }
  }

  // make sure we've got an Int from the CLI input
  def processUserId(userId: String): Int = userId.toInt
  // who does the user id follow? (follows.csv) get the destinationID
  def getFollowers(userID: Int): List[Long] = {
    new FollowsRead(followsFile).read().flatMap(_.get(userID))
  }
  // which tweets are from destinationID? destinationID, should match authorID in (tweets.csv)
  def getTweetsFromFollowers(destinationIds: List[Long]): List[RawTweet] = ???
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

  case class RawTweet()

  case class Tweet()

  case class User(userId: Int, twitterScreenName: String, fullDisplayName: String)

  case class Tweets(tweetId: Int, authorId: Int, timestamp: Int, text: String)

  case class Follows(sourceUserId: Long, destinationUserId: Long) {
    def transformToMap: Map[Long, Long] = Map(
      sourceUserId -> destinationUserId
    )
  }

  // trait responsible for reading/loading CSV file data
  trait CsvFileReader[A] {
    def read(): List[A]
  }

  // it would be great to split this out to make it reusable
  class FollowsRead(val fileName: String) {
    def read(): List[Map[Long, Long]] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield Map(values(0).toLong -> values(1).toLong)
    }
  }

  val followsFile: String = "src/main/resources/data/follows.csv"
  val usersFile: String = "src/main/resources/data/users.csv"
  val tweetsFile: String = "src/main/resources/data/tweets.csv"
}


