package sifra;

public class Sifra {

  
  private static final char[] abeceda = new char[]
   {'A', 'B', 'C', 'È', 'D', 'Ï', 'E', 'F', 'G', 'H', '+', 'I', 'J', 'K', 'L', 'M', 'N',
    'Ò', 'O', 'P', 'Q', 'R', 'Ø', 'S', 'Š', 'T', '', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ž'};
  private final char[] klicovka = new char[abeceda.length];
  
  private final String klíè;

  public Sifra(String klic) {
    boolean[] pouzito = new boolean[abeceda.length]; // pøíznak použitých znakù
    int n = abeceda.length;
    klíè = odøežÈárky(klic);
    for (char c : klíè.toCharArray()) {
      int index = hledejVPoli(abeceda, c);
      if (index >= 0 && ! pouzito[index]) {
        n--;
        klicovka[n] = c;
        pouzito[index] = true;
      }
    }
    for (int i = 0; i < abeceda.length; i++) {
      if (! pouzito[i]) {
        n--;
        klicovka[n] = abeceda[i];
      }
    }
    if (n != 0) throw new RuntimeException("Nevyslo to!");
  }
  
  private int hledejVPoli(char[] pole, char cc) {
    for (int i=0; i < pole.length; i++) {
      if (cc == pole[i]) return i;
    }
    return -1;
  }

  public String sifrujdesifruj(String textik, char[] odkud, char[] kam) {
    StringBuffer sb = new StringBuffer();
    for (char c : odøežÈárky(textik).toCharArray()) {
      int index = hledejVPoli(odkud, c);
      if (index >= 0) {
        sb.append(kam[index]);
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
    
  }
  
  public String sifruj(String decoded) {
    return sifrujdesifruj(decoded, klicovka, abeceda);
  }

  public String desifruj(String coded) {
    return sifrujdesifruj(coded, abeceda, klicovka);
  }
  
  private String odøežÈárky(String s) {
    StringBuffer sb = new StringBuffer();
    for (char c : s.toCharArray()) {
      sb.append(odøežÈárky(c));
    }
    return sb.toString();
  }

  
  private char odøežÈárky(char c) {
    c = Character.toUpperCase(c);
    switch (c) {
      case 'Á': return 'A';
      case 'É': return 'E';
      case 'Ì': return 'E';
      case 'Í': return 'I';
      case 'Ó': return 'O';
      case 'Ú': return 'U';
      case 'Ù': return 'U';
      case 'Ý': return 'Y';
      default: return c;
    }
  }

  public String getKlíè() {
    return klíè;
  }

  private String vmezeø (char[] cc) {
    return new String(cc).replaceAll("(.)", "$1 ").trim();
  }
  
  public String getAbeceda() {
    return vmezeø(abeceda);
  }

  public String getKlicovka() {
    return vmezeø(klicovka);
  }

}
