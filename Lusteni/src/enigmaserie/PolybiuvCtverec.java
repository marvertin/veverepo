/**
 * 
 */
package enigmaserie;

import java.io.PrintWriter;

/**
 * @author veverka
 *
 */
public class PolybiuvCtverec extends AppBase {

  //private final String S = "Tak trochu jinou cestou";
  //  private final String S = "KQXAEIYXHPVHFEPXFALE";
  @SuppressWarnings("unused")
  private final String H = "CACHE";
  private final String S = "u0005\u0047\u0054\u0077\u0065\u0044\u0074\u0044\u0057\u0057\u0064\u0056\u0044\u0045\u0045\u0077\u0047\u0044\u0044\u00ec\u009a\u00dc\u00bc\u0099\u00b0\u00a9\u000f\u00cb\u00ea\u0090\u0089\u00ef\u0009\u00b9\u009b\u0008\u00b0\u0056";
  @SuppressWarnings("unused")
  private final char ctverec[][] = {{'A','B','C','D','E'},
    {'F','G','H','I','J'},
    {'K','L','M','N','O'},
    {'P','Q','R','S','T'},
    {'U','V','X','Y','Z'}};

  public static void main(String[] args) {
    new PolybiuvCtverec().runApp(args);
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  @Override
  protected void execute(String[] aArgs) throws Exception {
    System.out.println(S);
    //    for (int i : S) {
    //      char c = ctverec[i / 10 - 1][i % 10 - 1];
    //      System.out.print(c);
    //    }
    System.out.println();

  }



  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#printHelp(java.io.PrintWriter)
   */
  @Override
  protected void printHelp(PrintWriter aSysout) throws Exception {
  }

}
