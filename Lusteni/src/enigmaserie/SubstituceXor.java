/**
 * 
 */
package enigmaserie;

import java.io.PrintWriter;

/**
 * @author veverka
 *
 */
public class SubstituceXor extends AppBase {

  private final String H = "ASCII";
  //  private final String S =
  //   "\u0019\u001c\u0011\u0069\u0023\u0024\u0073\u0030\u003c\u0039\u0033\u0073\u0025\u003c\u0027\u002a\u0030\u0026\u0065\u0069\u002b\u0036\u0027\u002c\u0027\u0020\u0030\u0037\u0069\u0023\u0024\u0073\u0021\u002c\u002a\u002a\u003c";

  private final String SS = "19 1c 11 69 23 24 73 30 3c 39 33 73 25 3c 27 2a 30 26 65 69 2b 36 27 2c 27 20 30 37 69 23 24 73 21 2c 2a 2a 3c ";
  @SuppressWarnings("unused")
  private final String SSx = "19 1c 11 69 20 32 73 33 2c 3b 27 36 20 3d 69 27 26 2d 2a 3d 28 3c 2d 65 69 2f 26 2e 2b 2c 33 73 01 69 20 32 73 26 25 2c 37 36 2d";
  public static void main(String[] args) {
    new SubstituceXor().runApp(args);
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  @Override
  protected void execute(String[] aArgs) throws Exception {
    int i = 0;
    int[] retez = Dekoder.decodujHexa(SS);
    for (int c : retez) {
      char h = H.charAt(i);
      i = (i + 1) % H.length();
      char cc = (char)( c ^ h);
      System.out.print(cc);
    }
    System.out.println();

  }


  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#printHelp(java.io.PrintWriter)
   */
  @Override
  protected void printHelp(PrintWriter aSysout) throws Exception {
  }

}
