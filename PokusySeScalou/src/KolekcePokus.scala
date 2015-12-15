import scala.collection.mutable.StringBuilder
import scala.collection.mutable.Buffer
import scala.collection.mutable.ListBuffer

object KolekcePokus {

  def rozdel[T](list: List[T], f: (T) => Boolean): (List[T], List[T]) = {
    if (list == Nil) (Nil, Nil)
    else {
      val (x, y) = rozdel(list.tail, f)
      if (f(list.head)) (list.head :: x, y) else (x, list.head :: y)
    }
  }

  def main(args: Array[String]): Unit = {

    println(rozdel(List(1, 3, 7, 2, 4, 8, 9, 15, 13, 82, 0, 4, 6, 6, 8), (x: Int) => x % 2 == 0))
    val ll: List[Int] = List(1, 3, 7, 2, 4, 8, 9, 15, 13, 82, 0, 4, 6, 6, 8)
    //   println(rozdel (ll,  _  % 2 == 0 ))

    println(Set(5, 3, 2) product)

    val x = List(5, 3, 9, 4)

    val hu = (2 /: x)((y1, y2) => y1 + y2)
    val hu2 = x reduceLeft ((y1, y2) => y1 + y2)
    println(hu);
    println(hu2);

    val sb = new StringBuilder
    x addString (sb, "/", "**", "\\")

    println(sb)

    println(3 +: List(2, 4, 6))
    println(List(2, 4, 6) padTo (2, 8))
    println(List(2, 4, 6, 8, 10, 12, 14, 16) patch (3, List(100, 101, 102), -1) sortWith (_ > _) sortBy (_ - 80))

    sb += 'X'
    sb ++= "xxx"
    println(sb)

    val bu = Buffer(1, 2, 3, 4, 5, 6, 7, 8)
    10 +=: bu
    List(100, 200, 300) ++=: bu
    println(bu)
    bu -= 200
    bu remove 7
    println(bu)

    println(bu.toSet(4))
    println(bu.toSet(5))

    println(Set(1, 2, 2, 3) + 6)
    //println (List(1,2,2,3) + 6)
    //println (List(1,2,2,3) + 6)
    println(Set(1, 2, 3) ++ List(6, 6, 12))
    println(List(1, 2, 3) ++ List(6, 6, 12))
    println((Set(1, 2, 3) | Set(6, 6, 12)) & Set(2, 6, 15, 18))

    println(Set(1, 2, 3) + (8, 11, 16))

    println((List(2, 4, 6, 8, 10, 12, 14, 16) patch (3, List(100, 101, 102), -1) sortWith (_ > _) sortBy (_ - 80)).toSet)

    def posl(s: String): Stream[String] = {
      s #:: posl("y" + s + "x")
    }

    println((posl("A").take(6)).toList)

    val lb = ListBuffer(5, 6, 7, 8)
    lb += 4
    lb ++= List(100, 200, 600)

    println(lb toList)

    def kk() = {
      (1 to 5) map (13 *)

      (1 to 20) filter { x: Int => val b = x % 2 == 0; b; true } map (3*)
      type R = Double

      def compose(g: R => R, h: R => R) = (x: R) => g(h(x))
      (1.0 to 20.0 by 3.0) map compose({ 3.0+ }, { 10.0* })

      val zscore = (mean: R, sd: R) => (x: R) => (x - mean) / sd

      zscore(5.0, 6.0)(16.0)

      def zscorex(mean: R, sd: R) = (x: R) => (x - mean) / sd
      zscorex(5.0, 6.0)(16.0)

      val nadruhou = (x: Int) => x * x
      (1 to 10) map nadruhou

      def zscore3(mean: R, sd: R)(x: R) = (x - mean) / sd

      def nasob(x: R)(y: R)(z: R) = x * y * z

      val nas5 = nasob(5)_

      val nas5a3 = nas5(3)

      nas5a3(2)

      val normer3 = zscore3(7, 0.4) _
      normer3(9)

      val normerx = zscorex(7, 0.4)
      normerx(9)

      val normer = zscore(7, 0.4)
      normer(90)

      def mapmake[T](g: T => T)(seq: List[T]) = seq.map(g)

      val obalec = mapmake[String]("*" + _ + "*") _
      obalec(List("aa", "bb", "c"))

      12.+(4)

      "abcd" charAt 2

      def sum(x: Int*) = x.reduceRight(_ + _)

      sum(3, 4, 5, 1)

      val x, y, z = (1, 2, 3)
      y

      val aa, bb = List(5, 10, 15)
      aa ++ bb

      val ll: List[Int] = (1 to 10).toList

      //ll map { case (x,y) => x*y }

      //val xsys = (xs zip ys) 
      val xsys = List(1, 2, 3, 4, 5, 6) zip List(1, 2, 3, 4, 5, 6)
      xsys map { case (x, y) => x * y }
      xsys

      val xs = List(40, 41, 42)
      val ys = List(50, 51, 52)

      (xs zip ys) map { z => val (x, y) = z; x * y }

      object Blb {
        def apply(x : String *) = println("LL: " + x) 
      }

      object Multi {
        var data = 100 to 109 toArray
        def apply(x : String *) = println("LL: " + x)
        
        override def toString = (data toList) toString
        
        def update(x : Int, value : Int) = { (0 to 2) foreach { y => data(x + y) = value}   }
      }

      
      Blb("aa", "bbbb")
      
      Multi(5) = 3000
      Multi(1) = 5000
      
      Multi
      
      val stm = 1 #:: 20 #:: 50 #:: 100 #:: Stream()
      val x3 = (stm take 50) toList
      
      x3
      stm
      
      def isPrvoc(n : Int) = (2 until n).view forall (n % _ != 0 ) 
      
      def nextPrvoc(n : Int) : Int = if (isPrvoc(n)) n else nextPrvoc(n+1) 
      
      def prvocFrom(n: Int): Stream[Int] = {
        val next = nextPrvoc(n)
        next #:: prvocFrom(next + 1)
      } 
      
      val prvocisla = prvocFrom(2) take(5000) toList
      
      isPrvoc(23)
      nextPrvoc(24)
      prvocisla

    }

    println(kk())

  }
}