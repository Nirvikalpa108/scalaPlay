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
      def recurse(chars: List[Char], returnString: String): String = {
        chars match {
          case Nil =>
            returnString
          case firstNum :: secondNum :: letter :: tail if firstNum.isDigit && secondNum.isDigit =>
            val combinedChars = firstNum.toString + secondNum.toString
            val number = combinedChars.toInt
            val count = letter.toString * number
            recurse(tail, returnString + count)
          case num :: letter :: tail if num.isDigit =>
            val asciiEncoding = 48
            val number = num.toInt - asciiEncoding
            val count = letter.toString * number
            recurse(tail, returnString + count)
          case head :: tail =>
            recurse(tail, returnString + head)
        }
      }
      recurse(s.toList, "")
    }
  }
}