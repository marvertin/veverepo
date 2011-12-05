/**
 * 
 */
package modulak;

/**
 * @author veverka
 *
 */
public class Modulak {

  static long b ;
  static long m = 1;
  
  public static void pocitej(long a, long x) {
    while((b + a) % x != 0 ) {
      b = b + m;
    }
    m = m * x;
  }
  
  public static long vzorec(long a, long x) {
    System.out.println("Modulo: " + ((b + a) % x));
    return (b+a) / x;
  }
  
  
  public static void main(String args[]) {
    
    pocitej( 174, 999);
    pocitej(-299, 998);
    pocitej(-460, 997);
    pocitej(-345, 995);
    pocitej(-114, 991);
    pocitej( 248, 989);
    System.out.println(m);
    System.out.println(b);
    System.out.println(b + m);

    long n = (vzorec( 174, 999) + vzorec(-299, 998) + vzorec(-460, 997) + 293) % 1000;
    long e = (vzorec(-345, 995) + vzorec(-114, 991) + vzorec( 248, 989) - 84) % 1000;
    
    System.out.println(n);
    System.out.println(e);
    System.out.println(Long.toHexString(b));
    
  }
}
