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
  }

//

//  test("decode - single characters with repeated characters") {
//    pending
//    RunLengthEncoding.decode("10WB12W3B24WB") should be ("WWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB")
//  }
//
//  test("decode - multiple whitespace mixed in string") {
//    pending
//    RunLengthEncoding.decode("2 hs2q q2w2 ") should be ("  hsqq qww  ")
//  }
//
//  test("decode - lower case string") {
//    pending
//    RunLengthEncoding.decode("2a3b4c") should be ("aabbbcccc")
//  }
//
//  test("consistency - encode followed by decode gives original string") {
//    pending
//    RunLengthEncoding.decode(RunLengthEncoding.encode("zzz ZZ  zZ")) should be ("zzz ZZ  zZ")
//  }
}

