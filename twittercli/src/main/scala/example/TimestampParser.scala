package example

import example.Model.{FormattedTweet, TweetWithDisplayName}
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object TimestampParser {
  val dateTimeFormatPattern = "yyyy-MM-dd'T'HH:mm:ss"

  // parse timestamp in tweets.csv
  def parseTimestamp(tweetsWithUnparsedTimestamp: List[TweetWithDisplayName]): List[TweetWithDisplayName] = {
    tweetsWithUnparsedTimestamp.map{ tweet =>
      val timestamp = new DateTime(tweet.timestamp.toLong * 1000).toLocalDateTime.toString(dateTimeFormatPattern)
      TweetWithDisplayName(tweet.tweetId, tweet.authorId, timestamp, tweet.text, tweet.displayName)
    }
  }

  // take a list of joda dateTimes and sort by most recent
  private def sortDateTime(dateTime: String): DateTime =
    DateTimeFormat.forPattern(dateTimeFormatPattern).parseDateTime(dateTime)

  // sort tweets by most recent and take first 10 from that list
  def getMostRecentTweets(unsortedTweets: List[TweetWithDisplayName]): List[TweetWithDisplayName] = {
    unsortedTweets.sortBy(tweet => sortDateTime(tweet.timestamp)).reverse
  }

  // amalgamate results: display name, timestamp human readable, tweet text
  def formatTweets(tweets: List[TweetWithDisplayName]): List[FormattedTweet] = {
    tweets.map{ tweet =>
      FormattedTweet(tweet.displayName, tweet.timestamp, tweet.text)
    }
  }

  def cliOutput(tweet: FormattedTweet): String =  "\n" + tweet.displayName + " " + tweet.timestamp + " " + tweet.text
}
