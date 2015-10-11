import scala.collection.mutable
import scala.io.Source

/** 
  * A Simple Context Free Grammar based Text Generator
  */
object CFGPoemGenerator {
  val r = scala.util.Random
  // A HashMap to store the production rules
  val grammar = new mutable.HashMap[String, String]

    /**
     *   Generates random text from a given stack of input tokens by applying production rules on the tokens.
     *   The generated text is guaranteed to be in the language
     *   Recursive implementation which should be optimized for tail recursion.
     *   @return Generated text
     */
  def generateText(acc: String, tokenStack: mutable.Stack[String]):String = {
    if (tokenStack.isEmpty) acc
    else tokenStack.pop().trim match {
      case el if el.contains(" ") => generateText(acc, tokenStack.pushAll(el.split("\\s").reverse))
      case el if el.contains("|") =>
        val subEls = el.split("\\|")
        generateText(acc, tokenStack.push(subEls(r.nextInt(subEls.size))))
      case el if el.matches("<.+>") => generateText(acc, tokenStack.pushAll(grammar.get(el.substring(1, el.length - 1))))
      case "$LINEBREAK" => generateText(acc + "\n", tokenStack)
      case "$END" => generateText(acc, tokenStack)
      case token => generateText(acc + " " + token, tokenStack)
    }
  }
  def main(args: Array[String]): Unit = {
    Source.fromFile(args(0)).getLines().foreach(line => {
      val parts = line.split(":")
      if (parts.size == 2) grammar.put(parts(0), parts(1))
    })
    println(generateText("", mutable.Stack[String](args(1))).trim.replaceAll("\n ", "\n") )
  }
}