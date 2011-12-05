/**
 * 
 */
package enigmaserie;

import java.io.PrintWriter;

/**
 * @author veverka
 *
 */
public class PodlePlotu extends AppBase {


  //  char h = H.charAt(i);
  //  i = (i + 1) % H.length();
  //  char cc = (char)( c ^ h);

  private static final String SS = "05 47 54 77 65 44 74 44 57 57 64 56 44 45 45 77 47 44 44 ec 9a dc bc 99 b0 a9 0f cb ea 90 89 ef 09 b9 9b 08 b0 56";
  @SuppressWarnings("unused")
  private static final String SSx = "05 47 54 75 64 67 74 44 44 45 64 46 45 46 64 74 65 45 41 46 54 44 ec 9a dc bd 9e 9b a0 9e 09 08 9e 0a be ba 20 af b8 bc 09 09 d0 56";

  private static final int[] III = Dekoder.decodujHexa(SS);
  static final int POCET_CISEL = III.length;
  static final int POCET_NYBLU = POCET_CISEL * 2;
  private int[] nybly;

  @SuppressWarnings("unused")
  private final int[] obracecka = new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};


  public static void main(String[] args) {
    new PodlePlotu().runApp(args);
  }


  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  @Override
  protected void execute(String[] aArgs) throws Exception {
    System.out.println(POCET_CISEL);
    //vypisdekadicky();
    prevedNaNybly();
    poBitech();
    orezIIIna7();
    vypisOrezaneNa7();
    zkusPoparovatNormalne();
    zkusPoparovatOpacne();
    zkusPoparovatOrez();

    //    for (int i=0; i<18; i++) {
    //      zkusPoparovatSPosunem(i);
    //    }
    //    zkusPoparovat2();
    //    for (int i= 0; i < 19; i ++) {
    //      char c1 =  (char)uprav1(S.charAt(i));
    //      char c2 =  (char)uprav2(S.charAt(i+19));
    //      //System.out.print((char)( c1 ^ c2));
    //      int x1 = c1 & 0xF0 | (c2 >> 4);
    //      int x2 = ((c1 & 0x0F) << 4) | (c2 & 0x0F);
    //      System.out.print((char)x1);
    //      System.out.print((char)x2);
    ////      System.out.print(Integer.toHexString(x1) + " ");
    ////      System.out.print(Integer.toHexString(x2) + " ");
    ////    System.out.print(Integer.toHexString(c1) + " ");
    ////    System.out.print(Integer.toHexString(c2) + " ");
    //      //System.out.print(c2);
    //    }
    //    System.out.println("----------");
    //    System.out.println();
    //    for (char c : S.toUpperCase().toCharArray()) {
    //      System.out.print((int)c + " ");
    //    }
    //    System.out.println();
    //    for (char c : S.toUpperCase().toCharArray()) {
    //      System.out.print(Integer.toHexString(c) + " ");
    //    }
    //    System.out.println();
    //    System.out.println();
    //    System.out.println();
    //    System.out.println();
    //
    //    System.out.println(FString.toDump(S));
    //    System.out.println();
    //    System.out.println();

  }

  /**
   * 
   */
  private void poBitech() {

    int pocBitu = POCET_CISEL*8;
    int ic = 0;
    int citac = 0;
    for (int i=0; i<pocBitu/2; i++) {
      ic <<= 1;
      ic |= bit(i);
      ic <<= 1;
      ic |= bit(i + pocBitu/2);
      citac++;
      if (citac == 4) {
        char c = (char) ic;
        System.out.print(c);
        citac = 0;
        ic = 0;
      }
    }
    System.out.println();

  }

  /**
   * 
   */
  //  private void vypisdekadicky() {
  //
  //    for (char c : S.toCharArray()) {
  //      System.out.print((int)c + " ");
  //    }
  //    System.out.println();
  //
  //  }
  private void orezIIIna7() {
    for (int i = 0; i < III.length; i++) {
      III[i] = III[i] & 127;
    }
    System.out.println();
  }

  private void vypisOrezaneNa7() {
    for (int i : III) {
      char c = (char) (i & 127);
      System.out.print(" " + c);
    }
    System.out.println();
  }
  @SuppressWarnings("unused")
  private void obrat() {
    int izac = POCET_NYBLU/2;
    int ikon = POCET_NYBLU-1;
    while (izac <  ikon) {
      int x = nybly[izac];
      nybly[izac] = nybly[ikon];
      nybly[ikon] = x;
      izac ++;
      ikon --;
    }
  }

  @SuppressWarnings("unused")
  private void rotate() {
    int x = nybly[POCET_NYBLU/2];
    for (int i = POCET_NYBLU/2; i < POCET_NYBLU-1; i++) {
      nybly[i] = nybly[i+1];
    }
    nybly[POCET_NYBLU-1] = x;
  }
  /**
   * 
   */
  private void zkusPoparovatNormalne() {
    for (int i= 0; i < POCET_NYBLU/2; i++) {
      char c = (char) ((nybly[i] << 4) | (nybly[i+POCET_NYBLU/2]));
      System.out.print(" " + c);
    }
    System.out.println();

  }

  private void zkusPoparovatOpacne() {
    for (int i= 0; i < POCET_NYBLU/2; i++) {
      char c = (char) ( ((nybly[i+POCET_NYBLU/2] << 4) | (nybly[i])) & 127);
      System.out.print(" " + c);
    }
    System.out.println();

  }

  private void zkusPoparovatOrez() {
    for (int i= 0; i < POCET_NYBLU/2; i++) {
      char c = (char) ((nybly[i] << 4) | (nybly[i+POCET_NYBLU/2] & 7));
      System.out.print(" " + c);
    }
    System.out.println();

  }

  @SuppressWarnings("unused")
  private void zkusPoparovatSPosunem(int posun) {
    System.out.print("POSUN " + posun + ": ");
    for (int i= 0; i < POCET_NYBLU/2; i++) {
      char c = (char) ((nybly[i] << 4) | ( (nybly[i+POCET_NYBLU/2]) + posun) & 15);
      System.out.print(" " + c);
    }
    System.out.println();

  }

  /**
   * 
   */
  private void prevedNaNybly() {
    nybly = new int[POCET_NYBLU];
    for (int i=0; i < POCET_CISEL/2; i++) {
      nybly[2*i+0] = III[i] >> 4;
    nybly[2*i+1] = III[i] &  15;
    }
    for (int i=POCET_CISEL/2; i<POCET_CISEL; i++) {
      nybly[2*i+0] = III[i] >> 4;
    nybly[2*i+1] = III[i] &  15;
    }
    int j = 0;
    //obrat();
    for (int nybl : nybly) {
      System.out.printf("%2x", nybl);
      if ((++j) == nybly.length / 2) {
        System.out.println();
      }

    }
    System.out.println();
  }

  int uprav(int c) {
    int result = c;
    //result = c / 16 + c % 16 * 16;
    //result = c;
    result = result & 0x7F | 0x40;

    return result;
  }

  int uprav1(int c) {
    int result = c;
    //result = c / 16 + c % 16 * 16;
    //result = c;

    return result;
  }

  int uprav2(int c) {
    int result = c;
    //result = c / 16 + c % 16 * 16;
    //result = c;
    //result = ' ';

    //result = (15 - hi) * 16 + (15 - lo);

    result = result ^ 0x88;

    return result;
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#printHelp(java.io.PrintWriter)
   */
  @Override
  protected void printHelp(PrintWriter aSysout) throws Exception {
  }

  // bity poèítáme od nuly
  private int bit(int nbit) {
    int xx = III[nbit / 8];
    int yy = xx >> (7 - (nbit % 8));
    int bb = yy & 1;
    return bb;
  }
}
