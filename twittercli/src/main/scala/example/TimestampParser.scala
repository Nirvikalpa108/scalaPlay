package example

import example.Model.{HumanReadableTweet, ParsedTimeStampTweet}

object TimestampParser {
  // parse timestamp in tweets.csv
  def parseTimestamp(tweetsWithUnparsedTimestamp: List[(String, List[String])]): List[ParsedTimeStampTweet] = ???
  // sort tweets by most recent and take first 10 from that list
  def getMostRecentTweets(unsortedTweets: List[ParsedTimeStampTweet]): List[ParsedTimeStampTweet] = ???
  // make timestamp human readable
  def makeTimestampHumanReadable(tweetsWithoutHumanReadableTimestamp: List[ParsedTimeStampTweet]): List[HumanReadableTweet] = ???
  // amalgamate results: display name, timestamp human readable, tweet text
  def results(tweets: List[HumanReadableTweet]): List[String] = ???
}
