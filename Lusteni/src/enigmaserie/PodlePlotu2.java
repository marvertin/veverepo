/**
 * 
 */
package enigmaserie;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author veverka
 *
 */
public class PodlePlotu2 extends AppBase {

  //  char h = H.charAt(i);
  //  i = (i + 1) % H.length();
  //  char cc = (char)( c ^ h);

  private final String SS = "\u0005\u0047\u0054\u0077\u0065\u0044\u0074\u0044\u0057\u0057\u0064\u0056\u0044\u0045\u0045\u0077\u0047\u0044\u0044" +
  "\u00ec\u009a\u00dc\u00bc\u0099\u00b0\u00a9\u000f\u00cb\u00ea\u0090\u0089\u00ef\u0009\u00b9\u009b\u0008\u00b0\u0056";



  public static void main(String[] args) {
    new PodlePlotu2().runApp(args);
  }

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  @Override
  protected void execute(String[] aArgs) throws Exception {
    //prevod na nybly
    List<Integer> list  = new ArrayList<Integer>();
    for (char c : SS.toCharArray()) {
      list.add(c >> 4);
      list.add(c & 0x0F);
    }
    list.remove(0);

    int delka = list.size();
    int pulka = delka / 2;
    System.out.println(delka);
    for (int i= 0; i < pulka; i ++) {
      int n1 = list.get(i);
      int n2 = list.get(i + pulka);

      //      int c = n1 * 16 + (n2 ^ 0x88);
      int c = n1 * 16 + (n2 ^ 0x8);
      System.out.print((char)c + " ");
      //    System.out.print(n1 + " " + n2 + " ");
      //      System.out.print(Integer.toHexString(n1) + " ");
      //      System.out.print(Integer.toHexString(n2) + " ");
      //System.out.print(c2);
    }
    System.out.println();
    System.out.println("----------");
    System.out.println();
    for (char c : SS.toUpperCase().toCharArray()) {
      System.out.print((int)c + " ");
    }
    System.out.println();
    for (char c : SS.toUpperCase().toCharArray()) {
      System.out.print(Integer.toHexString(c) + " ");
    }
    System.out.println();
    //    System.out.println();
    //    System.out.println();
    //    System.out.println();
    //
    //    System.out.println(FString.toDump(S));
    //    System.out.println();
    //    System.out.println();

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

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#printHelp(java.io.PrintWriter)
   */
  @Override
  protected void printHelp(PrintWriter aSysout) throws Exception {
  }

}
