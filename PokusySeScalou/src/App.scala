case class Kls(a: Int) {
  def nasob(x: Int) = x * a
}

object App {

  def fu(x: Int, y: Int) = x * x + y * y
  def fuc(x: Int)(y: Int) = x * x + y * y

  def dvakrat(x: Int) = 2 * x

  def fibo(list: List[Long]): List[Long] = {
    if (list.length < 20)
      fibo(list ::: list.init.last + list.last :: Nil) else list
  }

  def reverse[T](list: List[T]): List[T] = {
    if (list == Nil) Nil else reverse(list.tail) ::: (list.head :: Nil)
  }

  def konkat[T](l1: List[T], l2: List[T]): List[T] = {
    if (l1 == Nil) l2 else l1.head :: konkat(l1 tail, l2)
  }

  def posledni[T](list: List[T]): T = {
    val ocas = list tail;
    if (ocas == Nil) list head else posledni(list tail)
  }

  def zkus: Any = {

    val sez1 = List(55, 13, 8, 14, 7, 19)
    val sez2 = List(300, 500, 800)

    // println(sez1)

    //println (sez1 tail)

    7 :: 3 :: sez1 ::: sez2
    "ahoj" :: "vole" :: List("guj")
    fu _ :: "ouki" :: 8 :: (14, 7, 899) :: List("guj")
    fuc(5)(8)
    val po = fuc(5) _
    po(6)

    val words = List("the", "quick", "brown", "fox")
    words map (_.toList)
    words flatMap (_.toList)

    // fibo(List(1,1,2,3))
    reverse(List(5, 8, 9, 12, 15, 18))
    reverse(List("aaa", 8, 9, 8, 15, 18))
    Set("aaa", 8, 9, 8, 15, 18)
    val ran = 2 to 12
    reverse((2 to 13).toList);

    val x = (2).+(3);

    dvakrat(6);
    val k = Kls(6);
    k nasob 7
    konkat(List(1, 2, 3, 4), List("a", "b", "c"))
    posledni(konkat(List(1, 2, 3, 4), List("a", "b", "c")))

    val a_#+-*= = 7;
    val _abcd__xyz_$ = 87
    a_#+-*= + 5
    _abcd__xyz_$ + 3 + 2
    val abcd_+ = 15
    val -- = abcd_+ * 2
    --;
    val `b(.lb` = "hus";
    `b(.lb`;

    """ahoj,
      | tak,to,
     | jena více řádků""".stripMargin
    val xx = scala.Symbol("x")
    val yy = 'yyyc
    xx
    val empty_? = a_#+-*=
    val ## = x
    val <:* = 56
    val y: Int = 8;
    val x8: Double = (7.0);

    case class C(a: Int)

    val c1 = new C(2)
    val c2 = new C(2)
    c1 == c2

    def mm(a: Int, b: Int): Int = {
      println("SUMOV: " + a + "+" + b + "=" + (a * a + b * b))
      a * a + b * b
    }

    def popo(x: => Int, y: Int) {
      println("popo1");
      println("1. x: " + x);
      println("1. y: " + y);
      println("popo2");
      println("2. x: " + x);
      println("2. y: " + y);
      println("popo3");

    }

    def seznamuj[T](list: List[T], f: T => T): Unit = {
      println(list + " ---> " + aplikuj(list, f))
    }

    //popo(mm(18, 30), mm(5,6))
    def pricti2(x: Int) = x + 2

    def aplikuj[T](list: List[T], f: T => T): List[T] = {
      if (list == Nil) Nil else f(list.head) :: aplikuj(list.tail, f);
      //List(8,9)
    }

    seznamuj(List(3, 5, 8, 9, 16), (x: Int) => x * x)
    seznamuj(List(3, 5, 8, 9, 16), pricti2)
    seznamuj(List(3, 5, 8, 9, 16), (x: Int) => pricti2(x) * x)
    seznamuj(List("aa", "bb", "cc"), (x: String) => "*" + x + "*")

    {
      def exists2[T](list: List[T], p: T => Boolean): Boolean = {
        if (list == Nil) false
        else if (p(list.head)) true else exists2(list.tail, p)
      }

      exists2(List(3, 5, 20, 8, 9, 4), (x: Int) => (x > 10))

      def forall[T](list: List[T], p: T => Boolean): Boolean = {
        //def neexistuje (x : T) = 
        val neexistuje = (x: T) => !p(x)
        !exists2(list, neexistuje)
      }
      forall(List(3, 5, 20, 8, 9, 4), (x: Int) => (x < 18))

      val nula: Int = 0
      val sez: List[Int] = List(3, 5, 20, 8, 9, 4)
      //exists(sez, 0 == )

    }

    def exists[T](xs: Array[T], p: T => Boolean) = {
      var i: Int = 0
      while (i < xs.length && !p(xs(i))) i = i + 1
      i < xs.length
    }
    def forall[T](xs: Array[T], p: T => Boolean) = {
      def not_p(x: T) = !p.apply(x)
      !exists(xs, not_p)
    }
    //    def hasZeroRow(matrix: Array[Array[Int]]) =
    //      exists(matrix, (row: Array[Int]) => forall(row, 0 ==))
    val dvadva = dvakrat _;
    dvadva.apply(19)
    (dvakrat _).apply(19)
    dvadva(12)

    class Foo {
      var ii = 0;
      var ele: String = "x";

      def update(index: Int, elem: String): Unit = {
        ii = index
        ele = elem
      }

      def tisk = {
        println("ELE: " + ii + " / " + ele)
      }

      def apply(index: Int, ss: String) = (index * 3, "/" + ss + "/")
    }

    val foo = new Foo
    foo.tisk
    foo(7) = "blb"
    foo.tisk

    val x5s = foo(88, "ll")
    x5s

    def hasZeroRow(matrix: Array[Array[Int]]) =
      matrix exists (row => row forall (0 ==))

    // seznamuj(List(3, 5, 8, 9, 16), 3 * )

    def obal(s: String): String = {
      "+" + s + "-"
    }

    def aplika(s: String, f: String => String) = "/*" + f(s.toUpperCase()) + "*/"
    aplika("ahoj", (x) => x + x)
    aplika("ahoj", "blbe " +)
    aplika("ahoj", _.codePointAt(1).toString())

    def mapuj[T](list: List[T], f: T => T): List[T] = {
      if (list == Nil) Nil else f(list head) :: mapuj(list tail, f)
    }

    val kk = for (u <- List(5, 6, 9))
      yield 3 * u

    kk

    def sqrts(xs: List[Double]): List[Double] =
      for (x <- xs if x > 0) yield Math.sqrt(x)

    val ckk = for (u <- List(5, 6, 9) if u > 0) yield { (3 * u, u + 2) }
    ckk

    sqrts(List(15, 25, 49, -8, 100))
    
    
    def kombinace(a : Int, b : Int) = 
    for (x <- a to b; y <- x+1 to b; z <- y+1 to b) yield (x,y,z)
    
    kombinace(2, 6) foreach { case (xj, yj, zj) => println (xj + "--" + yj + "--" + zj) }  

    case class Cislo(val c : Double) {
      def mocnina2() = c * c
      def pulka = c / 2
      def krat(x : Int) = c * x
    }
    
    
      val c11 : Cislo = Cislo(8);
      
      c11.mocnina2()
      c11 krat 6
      c11 pulka
    
      foo ##
      
      val ll = List(8, 48, 29, 16, 3,50, 300, 18, 46, 98, 35, 1)
      ll match {
        case 5 :: zbytek => println("A: " + zbytek)
        case 9 :: zbytek => println("B: " + zbytek)
        case _ :: druhy :: 29 :: _ => println("C: " + druhy)
      }
      
      ll.zipWithIndex
      ll.sum
      
      val ll2 = List("a","bb","ccc")
      ll.splitAt(3)
      
      def xsplitAtx[T](list : List[T], kde : Int) : (List[T], List[T]) = {
        (kde, list) match {
          case (_, Nil) => (Nil, Nil)
          case (0, _) => (Nil, list)
          case (_, x :: rest) => {
            val (l1, l2) = xsplitAtx(rest, kde - 1)
            (x :: l1, l2)
          }
        }
      }
      
      xsplitAtx(ll, 3)
      
      (ll2 :\ List("xx"))(_ :: _)
      
      def cyklic(prv : Int, sum : Int) = {
        println("--- " + sum + " * 7 + " + prv + " = " + (sum * 7 + prv))
        sum * 7 + prv
      }
        
      (ll :\ 0)(cyklic(_,_))
      
  }

  def main(args: Array[String]): Unit = {
    //println("jedu");
    println(zkus)

  }
}