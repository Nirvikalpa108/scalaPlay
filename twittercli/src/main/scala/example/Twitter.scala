package example

import scala.io.Source
import scala.util.control.NonFatal

object Twitter {
  def main(args: Array[String]): Unit = {
    try {
      val input = args.head
      val userId = processUserId(input)
      val followers = getFollowers(userId)
      val tweetsFromFollowers = getTweetsFromFollowers(followers)
      val tweetsFromFollowersWithDisplayName = getDisplayName(tweetsFromFollowers)
      val tweetsWithParsedTimestamp = parseTimestamp(tweetsFromFollowersWithDisplayName)
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
  def processUserId(userId: String): Int = userId.toInt
  // who does the user id follow? (follows.csv) get the destinationID
  def getFollowers(userID: Int): List[Long] = new FollowsRead(followsFile).read().flatMap(_.get(userID))
  // which tweets are from destinationID? destinationID, should match authorID in (tweets.csv)
  def getTweetsFromFollowers(destinationIds: List[Long]) = {
    val tweetsFromFollowers = new TweetsRead(tweetsFile).read()
    for {
      destinationId <- destinationIds
      tweet <- tweetsFromFollowers
      mapWithDestId <- tweet.filter(_._1 == destinationId)
    } yield mapWithDestId
  }
  // get display name - match author id (tweets csv) to id in (users.csv)
  def getDisplayName(tweetsWithoutDisplayName: List[(Long, List[String])]): List[(String, List[String])] = {
    val users = new UsersRead(usersFile).read()
    for (user <- users) yield {
      def swapUserIdForDisplayName(list: List[(Long, List[String])]): List[(String, List[String])] = {
        list match {
          case s :: rest =>
            if (s._1.toString == user._1)
              (user._1, s._2) :: swapUserIdForDisplayName(rest)
          case _ => swapUserIdForDisplayName(list)
        }
      }
      swapUserIdForDisplayName(tweetsWithoutDisplayName)
    }
  }
  // parse timestamp in tweets.csv
  def parseTimestamp(tweetsWithUnparsedTimestamp: List[(String, List[String])]): List[ParsedTimeStampTweet] = ???
  // sort tweets by most recent and take first 10 from that list
  def getMostRecentTweets(unsortedTweets: List[ParsedTimeStampTweet]): List[ParsedTimeStampTweet] = ???
  // make timestamp human readable
  def makeTimestampHumanReadable(tweetsWithoutHumanReadableTimestamp: List[ParsedTimeStampTweet]): List[HumanReadableTweet] = ???
  // amalgamate results: display name, timestamp human readable, tweet text
  def results(tweets: List[HumanReadableTweet]): List[String] = ???

  // case class Follows(sourceUserId: Long, destinationUserId: Long)

  case class RawTweet(tweetId: Long, authorId: Long, timestamp: Long, text: String)

  case class ParsedTimeStampTweet()

  case class HumanReadableTweet()

  case class User(userId: Int, twitterScreenName: String, fullDisplayName: String)

  class FollowsRead(val fileName: String) {
    def read(): List[Map[Long, Long]] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield Map(values(0).toLong -> values(1).toLong)
    }
  }

  class TweetsRead(val fileName: String) {
    def read(): List[Map[Long, List[String]]] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield Map(values(1).toLong -> List(values(0), values(2), values(3)))
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


