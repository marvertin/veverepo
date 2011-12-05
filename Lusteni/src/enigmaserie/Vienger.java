/**
 * 
 */
package enigmaserie;

import java.io.PrintWriter;

/**
 * @author veverka
 *
 */
public class Vienger extends AppBase {

  //private final String S = "Tak trochu jinou cestou";
  //  private final String S = "KQXAEIYXHPVHFEPXFALE";
  private final String H = "CACHE";
  private final String S = "VAVV QGTQKE PA LLHPOFBGJE UPJTOXHRK URSRG SVHGK CKT HGLUP ONIE AMO HWY NG PTVPQMKAINNC TEIIERCO CKZPGM LL TQCGA WNOX ASJOVV XGXVB";

  public static void main(String[] args) {
    new Vienger().runApp(args);
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  @Override
  protected void execute(String[] aArgs) throws Exception {
    int i = 0;
    for (char c : S.toUpperCase().toCharArray()) {
      if (Character.isLetter(c)) {
        char h = H.charAt(i);
        i = (i + 1) % H.length();
        int hPosun = h - 'A';
        char cc = rotujCelek(c, -hPosun);
        System.out.print(cc);
      } else {
        System.out.print(c);
      }
    }
    System.out.println();

  }

  private char rotujCelek(char c, int n) {
    int x = c + n;
    while (x > 'Z') {
      x -=26;
    }
    while (x < 'A') {
      x +=26;
    }
    return (char)x;
  }

  @SuppressWarnings("unused")
  private char rotujDole(char c, int n) {
    int x = c + n;
    while (x > 'M') {
      x -=13;
    }
    while (x < 'A') {
      x +=13;
    }
    return (char)x;
  }


  @SuppressWarnings("unused")
  private char rotujNahore(char c, int n) {
    int x = c + n;
    while (x > 'Z') {
      x -=13;
    }
    while (x < 'N') {
      x +=13;
    }
    return (char)x;
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#printHelp(java.io.PrintWriter)
   */
  @Override
  protected void printHelp(PrintWriter aSysout) throws Exception {
  }

}
