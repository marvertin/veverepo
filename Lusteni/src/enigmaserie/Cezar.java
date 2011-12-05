/**
 * 
 */
package enigmaserie;


/**
 * @author veverka
 *
 */
public class Cezar {

  private final String S = "SNMWXMDLQJ BROAJ TMN SN SNW MEJLNC YNC VXIWXBCR CX SN CJTN CANCR LRBRUTX ";

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new Cezar().execute(args);
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  protected void execute(String[] aArgs) throws Exception {

    String s = S.toUpperCase();
    for (int i=0; i<=27; i++) {
      System.out.println(s);
      StringBuffer sb = new StringBuffer();
      for (char c : s.toCharArray()) {
        char cc = (char)(c + 1);
        if (cc > 'Z') {
          cc = 'A';
        }
        if (c == ' ') {
          cc = c;
        }
        sb.append(cc);
      }
      s = sb.toString();

    }

  }


}
