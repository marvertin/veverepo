package sifra;

public class Sifra {

  
  private static final char[] abeceda = new char[]
   {'A', 'B', 'C', '�', 'D', '�', 'E', 'F', 'G', 'H', '+', 'I', 'J', 'K', 'L', 'M', 'N',
    '�', 'O', 'P', 'Q', 'R', '�', 'S', '�', 'T', '�', 'U', 'V', 'W', 'X', 'Y', 'Z', '�'};
  private final char[] klicovka = new char[abeceda.length];
  
  private final String kl��;

  public Sifra(String klic) {
    boolean[] pouzito = new boolean[abeceda.length]; // p��znak pou�it�ch znak�
    int n = abeceda.length;
    kl�� = od�e���rky(klic);
    for (char c : kl��.toCharArray()) {
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
    for (char c : od�e���rky(textik).toCharArray()) {
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
  
  private String od�e���rky(String s) {
    StringBuffer sb = new StringBuffer();
    for (char c : s.toCharArray()) {
      sb.append(od�e���rky(c));
    }
    return sb.toString();
  }

  
  private char od�e���rky(char c) {
    c = Character.toUpperCase(c);
    switch (c) {
      case '�': return 'A';
      case '�': return 'E';
      case '�': return 'E';
      case '�': return 'I';
      case '�': return 'O';
      case '�': return 'U';
      case '�': return 'U';
      case '�': return 'Y';
      default: return c;
    }
  }

  public String getKl��() {
    return kl��;
  }

  private String vmeze� (char[] cc) {
    return new String(cc).replaceAll("(.)", "$1 ").trim();
  }
  
  public String getAbeceda() {
    return vmeze�(abeceda);
  }

  public String getKlicovka() {
    return vmeze�(klicovka);
  }

}
