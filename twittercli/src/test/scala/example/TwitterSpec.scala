package example

import example.Twitter.{Follows, FollowsRead, getFollowers, processUserId}
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
    val csv = new FollowsRead("src/main/resources/data/follows.csv").read()
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
}
