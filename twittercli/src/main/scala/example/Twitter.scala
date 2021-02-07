package example

import scala.io.Source
import scala.util.control.NonFatal

// Good work!
// Next steps: figure out why the tests are not passing.
// complete the getDisplayName function with the advice of Will!
// you're winning!

object Twitter {
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

  // make sure we've got an Int from the CLI input
  //TODO: check how I want to manage user id being string, but checking it's an int string value?!
  // what if the input is a long and not an Int??
  def processUserId(userId: String): Int = userId.toInt
   //who does the user id follow? (follows.csv) get the destinationID
  def getFollows(userId: Int): List[Follows] = {
    val follows = FollowsRead.read(followsFile)
    for {
      follows <- follows.filter(_.sourceUserId == userId.toString)
    } yield follows
  }
   //which tweets are from destinationID? destinationID, should match authorID in (tweets.csv)
  def getTweets(destinationIds: List[Follows]): List[RawTweet] = {
    val tweetsFromFollowers = new TweetsRead(tweetsFile).read()
    for {
      destinationId <- destinationIds
      result <- tweetsFromFollowers.filter(_.authorId == destinationId.toString)
    } yield result
  }
  // get display name - match author id (tweets csv) to id in (users.csv)
  def getDisplayName(tweetsWithoutDisplayName: List[RawTweet]): List[(String, List[String])] = {
    val users = new UsersRead(usersFile).read()
    ???
  }
  // parse timestamp in tweets.csv
  def parseTimestamp(tweetsWithUnparsedTimestamp: List[(String, List[String])]): List[ParsedTimeStampTweet] = ???
  // sort tweets by most recent and take first 10 from that list
  def getMostRecentTweets(unsortedTweets: List[ParsedTimeStampTweet]): List[ParsedTimeStampTweet] = ???
  // make timestamp human readable
  def makeTimestampHumanReadable(tweetsWithoutHumanReadableTimestamp: List[ParsedTimeStampTweet]): List[HumanReadableTweet] = ???
  // amalgamate results: display name, timestamp human readable, tweet text
  def results(tweets: List[HumanReadableTweet]): List[String] = ???

  case class Follows(sourceUserId: String, destinationUserId: String)

  case class RawTweet(tweetId: String, authorId: String, timestamp: String, text: String)

  case class ParsedTimeStampTweet()

  case class HumanReadableTweet()

  case class User(userId: String, twitterScreenName: String, fullDisplayName: String)

  object FollowsRead {
    def read(fileName: String): List[Follows] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines.toList
        values = line.split(",")
      } yield {
        Follows(values(0), values(1))
      }
    }
  }

  class TweetsRead(val fileName: String) {
    def read(): List[RawTweet] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield RawTweet(values(0), values(1), values(2), values(3))
    }
  }

  class UsersRead(val filename: String) {
    def read(): List[(String, String)] = {
      val file = Source.fromFile(filename)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield (values(0), values(2))
    }
  }

  val followsFile: String = "src/main/resources/data/follows.csv"
  val usersFile: String = "src/main/resources/data/users.csv"
  val tweetsFile: String = "src/main/resources/data/tweets.csv"
}


