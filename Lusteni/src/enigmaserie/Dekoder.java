/**
 * 
 */
package enigmaserie;

/**
 * @author veverka
 *
 */
public class Dekoder {
  
  public static int[] decodujHexa (String s) {
    
    String[] rozdelenec = s.split(" +");
    int[] pole = new int[rozdelenec.length];
    for (int i=0; i<rozdelenec.length; i++) {
      pole[i] = Integer.parseInt(rozdelenec[i], 16);
    }
    return pole;
  }

}
