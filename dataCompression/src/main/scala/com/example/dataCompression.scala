package com.example

object DataCompression {
  def encode(s: String): String = {
    if (s == "") "" else {
      def recurse(chars: List[String], tally: Int, returnString: String): String = {
        if (chars.isEmpty) returnString else {
          if (chars.tail.isEmpty) returnString + chars.head else {
            if (chars.head == chars.tail.head) recurse(chars.tail, tally + 1, returnString) else {
              if (tally == 1) recurse(chars.tail, tally, returnString + chars.head) else {
                recurse(chars.tail, 1, returnString + tally.toString + chars.head)
              }
            }
          }
        }
      }
      recurse(s.split("").toList, 1, "")
    }
  }

  def decode(s: String): String = s
}