package marvertin.analyzaslov

object F {

   def find(str : String,  c : Char) : String = {
    str dropWhile { _ != c }
  } 

  def main(args: Array[String]): Unit = {
  
     println ("{", find ("abcdefgeh", 'x'), "}");
  
     val u = new Usporadanik("abcdefghijkl")
     println(u vyhovuje "abcdil")
  }
}

class Usporadanik(poradi : String) {

   def find(str : String,  c : Char) : String = {
    str dropWhile { _ != c }
  } 
   
  def ukrat(str : String, pora : String) : Boolean = {
    if (str == "") true
    else {
      val p = find(pora, str head)
      if (p == "") false
      else ukrat(str tail, p tail)
    }
  }
  
  def vyhovuje(str : String) : Boolean = {
    ukrat (str, poradi)
  }
  
}

