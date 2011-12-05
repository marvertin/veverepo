/**
 * 
 */
package sifra;

/**
 * @author veverka
 *
 */
public class Napolen extends SifraSHeslem0 {

  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  
  /**
   * @param aHeslo
   * @param aTin
   * @return
   */
  protected String sifruj(String aHeslo, String aTin) {
    aHeslo = aHeslo.toLowerCase();
    if (aHeslo.length() == 0) aHeslo = "a";
    aTin = aTin.toLowerCase();
    StringBuilder sb = new StringBuilder();
    int j = 0;
    for (int i=0; i <aTin.length(); i++) {
      char c = aTin.charAt(i);
      char h = aHeslo.charAt(j % aHeslo.length());
      if (c >='a' && c <='z') {
        sb.append(sifruj(h, c));
        j++;
      } else {
        sb.append(c);
      }
      
    }
    return sb.toString();
  }

  /**
   * @param aH
   * @param aC
   * @return
   */
  private char sifruj(char h, char c) {
    char result = (char) (sifr(h - 'a', c -'a') + 'a');
    return result;
  }

  private int sifr(int h, int c) {
    int tab = h /2; // tabulka
    int poc2 = 13 + tab; // cim zacina druhy radek
    int vysl;
    if (c < 13) {
      vysl = (poc2 + c) % 13 + 13;
    } else {
      vysl = (c + 26 - poc2) % 13;
    }
    return vysl;
    
  }

  public static void main(String[] args) {
    new Napolen().spust();
  }
}
