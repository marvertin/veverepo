package marvertin.mmm

import scala.io.Source

/**
 * Hello world!
 *
 */
object Mmm extends App {
  val českéDiakritickéZnaky = "žščřďťň áéíóúý ŕĺľ äěôů ŽŠČŘĎŤŇ ÁÉÍÓÚÝ ÄĚÔŮ ŔĹĽ „“–«»" filterNot (' ' ==)
    
  def jeToZnakHodnyZacatkuRetezce(z : Char) : Boolean = {
    Character.isAlphabetic(z) || z == '%'
    //z >= 'a' && z < 'z' || (českéDiakritickéZnaky contains z);
  }
  
  def bezBinarnihoPrefixu(s : String) : String = {
    s dropWhile { ! jeToZnakHodnyZacatkuRetezce (_) } 
  }
  
  def jeToZnakRetezce(z : Char) : Boolean = {
    z >= ' ' && (z < 127 || (českéDiakritickéZnaky contains z)) ||
    z == '\n' || z =='\r' || z =='\t' || z == '\u0007'  
  }
  
  def jeToRetezecJenZeZnaku(s : String) : Boolean = {
    s forall jeToZnakRetezce 
  }
  
  val source = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("mmm2.exe"), "Cp852")
  val hrubeRozdeleneDle0 = (source . toList . mkString . split('\0') toSeq)
  
  
  val hrubeStringy = (hrubeRozdeleneDle0 map bezBinarnihoPrefixu)  . filter { _ . length > 0};


  //hrubeStringy foreach { println }
  
  val (textyHrube, binarky) = hrubeStringy partition jeToRetezecJenZeZnaku 
    
  val (texty1, blbeTexty1) = textyHrube partition { x => 
    x.length > 2 && (x.length > 3 || x.forall { Character.isAlphabetic(_) } ) &&
     ! (Set("data", "bss", "text") contains x) &&
     ! x.startsWith("idata")
  }

  val (texty2, blbeTexty2) = texty1 partition { x => 
    ! (x contains "__")
  }

  val (texty3, blbeTexty3) = texty2 partition { x => 
    ! (x contains "_")
  }

  val (texty4, blbeTexty4) = texty3 partition { x => 
    ! (x.contains("@") && ! (x contains "@."))
  }

  texty4 foreach { println }
  
  //println ( jeToRetezecJenZeZnaku("abc \ndef"))  
  
}
