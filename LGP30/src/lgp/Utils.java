package lgp;

/**
 * Utilityx jako p�evody k�d� a podobn�
 * @author Martin
 */
public class Utils {

  private static char[] codes = new char[]{'Z', 'B', 'Y', 'R', 'I', 'D', 'N', 'M',
    'P', 'E', 'U', 'T', 'H', 'C', 'A', 'S'};

  private static char[] hexas = new char[]{'0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9', 'F', 'G', 'J', 'K', 'Q', 'W'};

  private static char[] conrols = new char[]{' ', '-', '+', ';', '/', '.', ',', 'V',
    'O', 'X', '?', '?', '?', '?', '?',  '?'};

  /**
   * Konvertuje p�smenn� k�d zadan� jako parameter.
   * @param code
   * @return -1, pokud znak neznamen� k�d instrukce, jinak
   * k�d instrukce 0 - 15 
   */
  public static int convertOperCodeToInt(char code) {
    code = Character.toUpperCase(code);
    for (int i =0; i < codes.length; i++) {
      
      if (codes[i] == code) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Konvertuje p�smenn� k�d zadan� jako parameter.
   * @param code
   * @return -1, pokud znak neznamen� k�d instrukce, jinak
   * k�d instrukce 0 - 15 
   */
  public static char convertOperCodeToChar(int code) {
    return codes[code];
  }
  
  /**
   * Konvertuje hodnotu (obvykle ze st�ada�e) na znak, kter� bude 
   * vyti�t�n.
   * @param aCislo
   * @return
   */
  public static char convertToCharForPrint(int aCislo) {
    char c;
    int z = aCislo >> 2;
    int zona = aCislo & 3;
    switch (zona) {
    case 0: c = '?'; break;
    case 1: c = codes[z]; break;
    case 2: c = hexas[z]; break;
    case 3: c = conrols[z]; break;
    default: throw new RuntimeException();
    }
    return c;
  }
  
  /**
   * Parsruje hexa ��slo.
   * Vstupn� �et�zec mus� m�t pr�v� 8 znak� a jen hexa ��slice.
   * @param s
   * @return
   */
  public static int parseHexa(String s) {
    int cislo = 0;
    if (s.length() != 8) throw new RuntimeException("Hexa cislo \"" + s + "\"nema 8 cifer");
    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      int k = -1;
      for (int j=0; j<hexas.length; j++) {
        if (hexas[j] == c) {
          k = j;
          break;
        }
      }
      if (k == -1) throw new RuntimeException("Spatny znak v hexa cisle \"" + s + "\"");
      cislo = (cislo << 4) | k; 
    }
    return cislo;
  }
 
  public static String formatHexa(int aPar) {
    String x = String.format("%08x", aPar);
    x = x.replace('a', 'F');
    x = x.replace('b', 'G');
    x = x.replace('c', 'J');
    x = x.replace('d', 'K');
    x = x.replace('e', 'Q');
    x = x.replace('f', 'W');
    return x;
  }

  public static String convertToCoordinates(double radiany) {
    double stupne = Math.toDegrees(radiany);
    double celeStupne = Math.floor(stupne);
    double fractStupnu = stupne - celeStupne;
    double minuty = fractStupnu * 60;
    minuty = (double) Math.round(minuty * 1000) / 1000;
    return (int)celeStupne + "�" + minuty;
  }
  

  
}
