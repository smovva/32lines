#CFG Poem Generator
A random text generator based on context free grammar. 

## Building
```
$scalac CFGPoemGenerator.scala
```

## Running
The tool requires two inputs a grammar file similar to the [Grammar.txt](Grammar.txt) and a starting symbol
```
$scala CFGPoemGenerator <grammar-file> <start-symbol>
```
Example run
```
$scala CFGPoemGenerator Grammar.txt <POEM>
wave flows onto flower through dark murky clear
flower above his bright pasture
my bright harvest
sister behind white sea
above moon flies his black water
```
Since it's context free grammar the text generated is grammatically correct but doesn't necessarily have a meaning.

