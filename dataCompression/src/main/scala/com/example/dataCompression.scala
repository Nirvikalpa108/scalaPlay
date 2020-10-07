package com.example

object DataCompression {
  def encode(s: String): String = {
    if (s == "") "" else {
      def recurse(chars: List[Char], tally: Int, returnString: String): String = {
        chars match {
          case Nil =>
            returnString
          case head :: Nil if tally > 1 =>
            returnString + tally.toString + head
          case head :: Nil =>
            returnString + head
          case first :: second :: _ if first == second =>
            recurse(chars.tail, tally + 1, returnString)
          case _ if tally == 1 =>
            recurse(chars.tail, tally, returnString + chars.head)
          case _ =>
            recurse(chars.tail, 1, returnString + tally.toString + chars.head)
        }
      }
      recurse(s.toList, 1, "")
    }
  }

  def decode(s: String): String = {
    if (s.isEmpty) "" else {
      def recurseAgain(chars: List[Char], returnString: String): String = {
        chars match {
          case Nil =>
            returnString
//          case head :: tail :: rest if head.toInt % head.toInt == 1 =>
//            val count = tail.toString * head
//            recurseAgain(rest, returnString + count)
          case head :: tail =>
            recurseAgain(tail, returnString + head)
        }
      }
      recurseAgain(s.toList, "")
    }
  }
}