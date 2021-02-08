package example

import example.Model.{Follows, RawTweet}

import scala.io.Source

object CsvParser {
    def followsRead(fileName: String): List[Follows] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines.toList
        values = line.split(",")
      } yield {
        Follows(values(0), values(1))
      }
    }

    def tweetsRead(fileName: String): List[RawTweet] = {
      val file = Source.fromFile(fileName)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield RawTweet(values(0), values(1), values(2), values(3))
    }

    def usersRead(filename: String): List[(String, String)] = {
      val file = Source.fromFile(filename)
      for {
        line <- file.getLines().toList
        values = line.split(",")
      } yield (values(0), values(2))
    }
}
