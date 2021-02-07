package example

import example.Twitter.{Follows, FollowsRead, RawTweet, TweetsRead, UsersRead, followsFile, getDisplayName, getFollows, getTweets, processUserId, tweetsFile, usersFile}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class TwitterSpec extends AnyFreeSpec with Matchers {
  "transform a user id parameter" - {
    "from a String to an Int" in {
      processUserId("1") shouldEqual 1
    }
    "and throw an exception if unable to transform into an Int" in {
      an[Exception] should be thrownBy processUserId("a")
    }
  }
  "get the followers of a user" - {
    val followsCsv = FollowsRead.read(followsFile)
    "load the followers csv file and check the size is correct" in {
      followsCsv.size shouldEqual 130393
    }
    "get the correct head of the file" in {
      followsCsv.head shouldEqual Follows("989489610", "10224712")
    }
    "retrieve the correct values for line 130392 in the followers csv file" in {
      followsCsv(130392) shouldEqual Follows("24257941", "4558")
    }
    "get the followers of a user successfully" in {
      getFollows(989489610) should contain (Follows("989489610", "10224712"))
      getFollows(989489610) should contain(Follows("989489610", "393537534"))
    }
  }
  "get the tweets of a user's followers" - {
    "load the tweets csv file successfully" in {
      val csv = new TweetsRead(tweetsFile).read()
      csv.size shouldEqual 51594
      val rawTweet = RawTweet("643576332438388737","989489610","1442275529","@marcua oooo I want to hear/read the story! These are my kind of bugs!")
      csv.head shouldEqual rawTweet
    }
    "get tweets from followerId 10224712" in {
      val result = ("10224712",List("644844719030337536", "1442577936", "Tech conferences... @ Skills Matter https://t.co/Rexwr0UEOz"))
      getTweets(List(Follows("989489610", "10224712"))).head shouldEqual result
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
      val tweetsWithoutDisplayName: List[RawTweet] = List(RawTweet("643576332438388737", "989489610","1442275529", "@marcua oooo I want to hear/read the story! These are my kind of bugs!"))
      getDisplayName(tweetsWithoutDisplayName) shouldEqual List(("Evan Jones", List("")))
    }
  }
}
