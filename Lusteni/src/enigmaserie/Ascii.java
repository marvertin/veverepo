/**
 * 
 */
package enigmaserie;


/**
 * @author veverka
 *
 */
public class Ascii {

  //  private final String S =
  //  "\u005a\u0061\u006b\u006c\u0061\u0064\u006e\u0069\u0020\u0041\u0053\u0043\u0049\u0049\u0020\u0073\u006e\u0061\u0064\u0020\u006e\u0065\u0064\u0065\u006c\u0061\u0020\u0070\u0072\u006f\u0062\u006c\u0065\u006d\u0079\u002c\u0020\u0061\u0063\u006b\u006f\u0020\u006a\u0065\u0020\u0064\u0076\u0061\u002c\u0020\u006a\u0065\u0064\u006e\u006f\u0064\u0075\u0063\u0068\u0065";

  //  private final String S = "\u0005\u0047\u0054\u0077\u0065\u0044\u0074\u0044\u0057\u0057\u0064\u0056\u0044\u0045\u0045\u0077\u0047\u0044\u0044" +
  //                           "\u00ec\u009a\u00dc\u00bc\u0099\u00b0\u00a9\u000f\u00cb\u00ea\u0090\u0089\u00ef\u0009\u00b9\u009b\u0008\u00b0\u0056";

  private final String S = "\u000e\u0049\u007a\u005d\u004c\u007b\u007c\u0069\u0059\u004b\u0040\u007a\u0049\u0040\u004f\u007b\u005e\u007a\u0069\u0040\u0058\u0069\u004e\u004f\u0040\u0059\u004b\u0059\u0079\u007b\u0040\u0078\u004b\u0040\u0045\u0046";
  public static void main(String[] args) throws Exception {
    new Ascii().execute(args);
  }

  protected void execute(String[] aArgs) throws Exception {
    //    for (int i : S) {
    //      char c = ctverec[i / 10 - 1][i % 10 - 1];
    //      System.out.print(c);
    //    }
    System.out.println(S);

  }

  @SuppressWarnings("unused")
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


}
