package com.example

import com.example.DataCompression.{decode, encode}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class dataCompressionTest extends AnyFreeSpec with Matchers {
  "DataCompression" - {
    "encode - empty string" in {
      encode("") shouldEqual ""
    }
    "encode - single characters only are encoded without count" in {
      encode("XYZ") shouldEqual "XYZ"
    }
    "encode - string with no single characters" in {
      encode("AABBBCCCC") shouldEqual "2A3B4C"
    }
    "encode - single characters mixed with repeated characters" in {
      encode("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB") shouldEqual "12WB12W3B24WB"
    }
    "encode - multiple whitespace mixed in string" in {
      encode("  hsqq qww  ") shouldEqual "2 hs2q q2w2 "
    }
    "encode - lowercase characters" in {
      encode("aabbbcccc") shouldEqual "2a3b4c"
    }
    "decode - empty string" in {
      decode("") shouldEqual ""
    }
    "decode - single characters only" in {
      decode("XYZ") shouldEqual "XYZ"
    }
    "decode - string with no single characters" in {
      decode("2A3B4C") shouldEqual "AABBBCCCC"
    }
    "decode - single characters with repeated characters" in {
      decode("10WB12W3B24WB") shouldEqual "WWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB"
    }
    "decode - multiple whitespace mixed in string" in {
      decode("2 hs2q q2w2 ") shouldEqual "  hsqq qww  "
    }
    "decode - lower case string" in {
      decode("2a3b4c") shouldEqual "aabbbcccc"
    }
    "consistency - encode followed by decode gives original string" in {
      decode(encode("zzz ZZ  zZ")) shouldEqual "zzz ZZ  zZ"
    }
  }
}

