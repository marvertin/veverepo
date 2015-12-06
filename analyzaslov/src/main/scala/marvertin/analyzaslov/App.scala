package marvertin.analyzaslov

import scala.io.Source

/**
 * Hello world!
 *
 */
object Moje extends App {
  val ABECEDA = new Usporadanik("aábbcčdďeéěfghĦiíjklmnňoópqrřsštťuúůvwxyýzž")
  
  val MORSE = new Usporadanik("hsviüufelräapwjbdxnckytzgqmöoĦ")

  println( "Hello World!" )
  
  println (getClass.getClassLoader.getResource("slovnik/Czech.3-2-3.dic"));
  val slova = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("slovnik/Czech.3-2-3.dic"), "Windows-1250")
     .getLines map { _ replaceAll("ch", "Ħ") } toSet;
  
  val čitelnáZezasu = slova filter { x : String =>  slova(x.reverse) };
  
  println (čitelnáZezasu size)
  čitelnáZezasu foreach { println }

   val palindromy = slova filter { x : String =>  x == x.reverse };
  
  println ("-----------------------")
  println (palindromy size)
  palindromy foreach { println }

  val abecednici = slova filter { x : String => ABECEDA.vyhovuje(x) }
  println ("-----------------------")
  println (abecednici size)
  abecednici.toList.sorted.foreach { println }
  
  val morsenici = slova filter { x : String => MORSE.vyhovuje(x) }
  println ("-----------------------")
  println (morsenici size)
  morsenici foreach { println }

  morsenici.toList.sorted.foreach { println }
}
