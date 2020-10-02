package com.example

import com.example.DataCompression.{encode, decode}
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

  }
//
//  test("encode - single characters mixed with repeated characters") {
//    pending
//    RunLengthEncoding.encode("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB") should be ("12WB12W3B24WB")
//  }
//
//  test("encode - multiple whitespace mixed in string") {
//    pending
//    RunLengthEncoding.encode("  hsqq qww  ") should be ("2 hs2q q2w2 ")
//  }
//
//  test("encode - lowercase characters") {
//    pending
//    RunLengthEncoding.encode("aabbbcccc") should be ("2a3b4c")
//  }
//
//  test("decode - empty string") {
//    pending
//    RunLengthEncoding.decode("") should be ("")
//  }
//
//  test("decode - single characters only") {
//    pending
//    RunLengthEncoding.decode("XYZ") should be ("XYZ")
//  }
//
//  test("decode - string with no single characters") {
//    pending
//    RunLengthEncoding.decode("2A3B4C") should be ("AABBBCCCC")
//  }
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

