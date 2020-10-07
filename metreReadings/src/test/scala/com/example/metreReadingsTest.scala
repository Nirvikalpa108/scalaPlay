package com.example

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

// file:///Users/amina_adewusi/Downloads/Technical%20Challenge%20Full%20Stack%20(1).pdf
// https://storage.googleapis.com/bulb-interview/sample-input-readings.json
// each function should take an array of metre reading objects
// each object is a Map with 2 key value pairs:
// List(Map(("cumulative" -> Int), ("readingDate" -> ISO 8601 formatted date string in UTC)))

class metreReadingsTest extends AnyFreeSpec with Matchers {
  "MetreReading" - {
    "accept correct function type" in {
      function() shouldEqual ""
    }
  }
}