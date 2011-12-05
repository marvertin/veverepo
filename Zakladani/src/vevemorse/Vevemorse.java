package vevemorse;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author veverka
 *
 */
public class Vevemorse  {

  private final Map<Character, String> domorse = new HashMap<Character, String>();
  private final Map<String, Character> zmorse = new HashMap<String, Character>();

  public Vevemorse(boolean cesky) {

    mor('A', ".-");
    mor('B', "-...");
    mor('C', "-.-.");
    mor('D', "-..");
    mor('E', ".");
    mor('F', "..-.");
    mor('G', "--.");
    mor('H', "....");
    if (cesky) {
      mor('@', "----");
    }
    mor('I', "..");
    mor('J', ".---");
    mor('K', "-.-");
    mor('L', ".-..");
    mor('M', "--");
    mor('N', "-.");
    mor('O', "---");
    mor('P', ".--.");
    mor('Q', "--.-");
    mor('R', ".-.");
    mor('S', "...");
    mor('T', "-");
    mor('U', "..-");
    mor('V', "...-");
    mor('W', ".--");
    mor('X', "-..-");
    mor('Y', "-.--");
    mor('Z', "--..");

    mor('Ä', ".-.-");
    mor('Ö', "---.");
    mor('Ü', "..--");

    mor('0', "-----");
    mor('1', ".----");
    mor('2', "..---");
    mor('3', "...--");
    mor('4', "....-");
    mor('5', ".....");
    mor('6', "-....");
    mor('7', "--...");
    mor('8', "---..");
    mor('9', "----.");
  }

  protected String sifruj(String cislice1, int klic) {
    String morcislice1 = doMorse(cislice1);
    String mormezi1 = transpoziceTam(morcislice1);
    String morpismena1 = substituceKazdaDruha(mormezi1);
    String pismena = zMorsePismenaDleKlice(morpismena1, klic);

    // Tady je kontrola
    String morpismena2x = doMorse(pismena);
    if (! (morpismena2x.equals(morpismena1)))
      throw new RuntimeException(String.format("Problem s pismeny a morse: %s != %s pro %s", morpismena1, morpismena2x, pismena));

    String mormezi2 = substituceKazdaDruha(morpismena2x);
    if (! (mormezi2.equals(mormezi1)))
      throw new RuntimeException(String.format("Problem s mezi a morse: %s != %s pro %s", mormezi1, mormezi2, pismena));

    String morcislice2 = transpoziceZpet(mormezi2);
    if (! (morcislice2.equals(morcislice1)))
      throw new RuntimeException(String.format("Problem z transpozici: %s != %s", morcislice1, morcislice2));

    String cislice2 = zMorseCisla(morcislice2);
    if (! (cislice2.equals(cislice1)))
      throw new RuntimeException(String.format("Problem z cislicemi a morse: %s != %s", cislice1, cislice2));

    return pismena.replace("@", "ch");
  }


  /**
   * @param aMorcse
   * @return
   */
  private String substituceKazdaDruha(String aMorcse) {
    //if (true) return aMorcse;
    char[] a = aMorcse.toCharArray();
    for (int i=0; i<a.length; i++) {
      if (i / 2 % 2 == 1) {
        a[i] = vymen(a[i]);
      }
    }
    return String.copyValueOf(a);
  }

  char vymen(char x) {
    if (x == '.') return '-';
    return '.';
  }

  private String doMorse(String s) {
    s = s.trim().toUpperCase();
    StringBuffer sb = new StringBuffer();
    for (char c : s.toCharArray()) {
      String morznak = domorse.get(c);
      if (morznak != null) {
        sb.append(morznak);
      }
    }
    return sb.toString();
  }

  private String zMorseCisla(String s) {
    StringBuffer sb = new StringBuffer();

    int i = 0;
    while (i < s.length()) {
      Character znak;
      String mor = s.substring(i, i + 5);
      znak = zmorse.get(mor);
      if (znak == null)
        throw new RuntimeException("Tak z tohoto nelze udelat cislo " + mor + " " + i);
      sb.append(znak);
      i += 5;
    }
    return sb.toString();
  }


  private String zMorsePismenaDleKlice(String s, int klic) {
    StringBuffer sb = new StringBuffer();

    int i = 0;
    int delka = 0;
    while (i < s.length()) {
      int zbyva = s.length() - i;
      if(zbyva <= 4) {  //už se dávyjádøit jediným znakem
        delka = zbyva;
      } else {
        delka = (delka + klic & 3) % 4 + 1;
        klic = klic >> 2;
      }
      char znak = zmorse.get(s.substring(i, i + delka));
      sb.append(znak);
      i += delka;
    }
    return sb.toString();
  }


  private String transpoziceTam(String s) {
    StringBuilder sb = new StringBuilder();
    int i = (s.length() - 1) / 2;
    int krok = 0;
    while (i >= 0 && i < s.length()) {
      sb.append(s.charAt(i));
      if (krok <= 0) {
        krok = - krok + 1;
      } else {
        krok = - krok - 1;
      }
      i += krok;
    }
    return sb.toString();
  }

  private String transpoziceZpet(String s) {
    String result = "";
    for (int i = 0; i < s.length(); i++) {
      if (i % 2 == 0) {
        result = s.charAt(i) + result;
      } else {
        result = result + s.charAt(i);
      }
    }
    return result;
  }

  /**
   * @param aC
   * @param aString
   */
  private void mor(char znak, String mor) {
    domorse.put(znak, mor);
    zmorse.put(mor, znak);
  }

}
