/**
 * 
 */
package enigmaserie;


/**
 * @author veverka
 *
 */
public class Napoleon  {

  //private final String S = "Tak trochu jinou cestou";
  //  private final String S = "KQXAEIYXHPVHFEPXFALE";
  private final String H = "BONAPARTE";
  private final String S = "WXX B KBEJXEYHV IEZZRUHGV LVNIJ XXF FY CGLKVBT IPPZ JPQ LOSKBAWRVWN NVRXNS USXQUADV RVLRRT WZ ZTYRT XSVXN EBOIGH BYZXYA";

  public static void main(String[] args) throws Exception {
    new Napoleon().execute(args);
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  protected void execute(String[] aArgs) throws Exception {
    int i = 0;
    for (char c : S.toUpperCase().toCharArray()) {
      if (Character.isLetter(c)) {
        char h = H.charAt(i);
        i = (i + 1) % H.length();
        int hPosun = (h - 'A') / 2; // 0 až 12
        char cc = rotujCelek(c, 13);
        if (c >= 'A' && c <='M') {
          cc = rotujNahore(cc, hPosun);
        }
        if (c >= 'N' && c <='Z') {
          cc = rotujDole(cc, -hPosun);

        }
        if (cc > 'Z') {
          cc = 'A';
        }
        if (c == ' ') {
          cc = c;
        }
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
