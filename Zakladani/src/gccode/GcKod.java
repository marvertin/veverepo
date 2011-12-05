/**
 * 
 */
package gccode;


/**
 * @author veverka
 *
 */
public class GcKod {

  final String BASE31 = "0123456789ABCDEFGHJKMNPQRTVWXYZ";

  /**
   * Zaklad soustavy cisel kesy do GCFFFF vcetne
   */
  private static final int ZAKLAD_STARY = 16;

  /**
   * Zaklad soustavy cisel kesy od GCG0000 vcetne
   */
  private static final int ZAKLAD_NOVY = 31;

  /**
   * 
   */
  private static final int ZAKLAD_NOVY_na_3 = ZAKLAD_NOVY * ZAKLAD_NOVY * ZAKLAD_NOVY;

  /**
   * 
   */
  private static final int ZALAD_NOVY_na_4 = ZAKLAD_NOVY_na_3 * ZAKLAD_NOVY;

  /**
   * Mezni kes
   */
  private static final int GCG000 = ZAKLAD_STARY * ZAKLAD_STARY * ZAKLAD_STARY * ZAKLAD_STARY;
  /**
   * Mezni kes
   */
  private static final int GC10000 = 512401;


  public String toGcCode (int id) {
    if (id < GCG000)
      return "GC" + toBaseN(ZAKLAD_STARY, id);
    else if (id < GC10000)
      // base 31 na 4 znaky
      return "GC" + toBaseN(ZAKLAD_NOVY, id - GCG000 + ZAKLAD_STARY * ZAKLAD_NOVY_na_3 );
    else
      // base 31 na 5 znakù
      return "GC" +  toBaseN(ZAKLAD_NOVY, id - GC10000 + ZALAD_NOVY_na_4);
  }

  public int toId(String gcCode) {
    String s = gcCode.substring(2); // odriznout GC
    if (s.length() <= 4) {
      if (s.charAt(0) < 'G')
        return fromBaseN(ZAKLAD_STARY, s);
      else
        return fromBaseN(ZAKLAD_NOVY, s) + GCG000 - ZAKLAD_STARY * ZAKLAD_NOVY_na_3;
    } else
      return fromBaseN(ZAKLAD_NOVY, s) + GC10000 - ZALAD_NOVY_na_4;

  }

  /**
   * @param aBase
   * @param aS
   * @return
   */
  private int fromBaseN(int aBase, String aNumber) {
    if (aNumber.length() == 0) return 0;
    return fromBaseN (aBase, aNumber.substring(0, aNumber.length() - 1))
    * aBase + BASE31.indexOf(aNumber.charAt(aNumber.length() - 1));
  }

  String toBaseN(int base, int x) {
    if (x == 0) return "";
    else return toBaseN(base, x / base) + BASE31.charAt(x % base);
  }

  void test(int id) {
    System.out.printf("%9d: %s\n", id, toGcCode(id));
  }

  void testAll() {
    for (int id1 = 1; id1 < 10000000; id1++) {
      int id2 = toId(toGcCode(id1));
      if (id1 != id2) {
        //        System.out.printf("%10d %-8s  !=   %10d %-8s\n", id1, toGcCode(id1), id2, toGcCode(id2));
        System.out.printf("%10d %-8s  !=   %10d %-8s\n", id1, toGcCode(id1), id2, "xx");
      }

    }
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  protected void execute(String[] aArgs) throws Exception {

    test(4);
    test(100);
    test(1000);
    test(65535);
    test(44444);
    test(65537);
    test(512401);
    test(888888);
    test(1035665);
    test(1497490);
    testAll();

  }


  public static void main(String[] args) throws Exception {
    new GcKod().execute(args);
  }


}
