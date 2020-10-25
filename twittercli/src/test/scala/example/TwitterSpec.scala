package example

import example.Twitter.{FollowsRead, RawTweet, TweetsRead, UsersRead, followsFile, getDisplayName, getFollowers, getTweetsFromFollowers, processUserId, tweetsFile, usersFile}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TwitterSpec extends AnyFreeSpec with Matchers {
  "transform a user id parameter" - {
    "into an Int from a String" in {
      processUserId("1") shouldEqual 1
    }
    "and throw an exception if unable to transform into an Int" in {
      an[Exception] should be thrownBy processUserId("a")
    }
  }
  "get the followers of a user" - {
    val csv = new FollowsRead(followsFile).read()
    "load the followers csv file" in {
      csv.size shouldEqual 130393
      csv.head shouldEqual Map(989489610 -> 10224712)
      csv(130392) shouldEqual Map(24257941 -> 4558)
    }
    "get the followers of a user successfully" in {
      getFollowers(989489610).head shouldEqual 10224712
      getFollowers(989489610).last shouldEqual 393537534
    }
  }
  "get the tweets of a user's followers" - {
    "load the tweets csv file successfully" in {
      val csv = new TweetsRead(tweetsFile).read()
      csv.size shouldEqual 51594
      val rawTweet = Map(989489610 -> List("643576332438388737", "1442275529", "@marcua oooo I want to hear/read the story! These are my kind of bugs!"))
      csv.head shouldEqual rawTweet
    }
    "get tweets from followerId 10224712" in {
      val followerId = 10224712
      val result = (10224712,List("644844719030337536", "1442577936", "Tech conferences... @ Skills Matter https://t.co/Rexwr0UEOz"))
      getTweetsFromFollowers(List(followerId)).head shouldEqual result
    }
  }
  "get the tweet author's display name" - {
    "load the users csv file successfully" in {
      val csv = new UsersRead(usersFile).read()
      csv.size shouldEqual 286
      val user = ("989489610","Evan Jones")
      csv.head shouldEqual user
    }
    "find users with matching ids" in {

    }
    "get the display name using the user id" in {
      val tweetsWithoutDisplayName: List[(Long, List[String])] = List((989489610, List("")))
      getDisplayName(tweetsWithoutDisplayName) shouldEqual List(("Evan Jones", List("")))
    }
  }
}
