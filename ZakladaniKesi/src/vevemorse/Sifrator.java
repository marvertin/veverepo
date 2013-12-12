/**
 * 
 */
package vevemorse;

/**
 * @author veverka
 *
 */
public class Sifrator {

  private final int[] iklic;
  private final Devitka[] dklic;

  public Sifrator(String klic) {
    iklic = toNumbers(klic);
    dklic = new Devitka[iklic.length];
    for (int i =0; i< dklic.length; i++) {
      dklic[i] = prevedZnakKlice(iklic[i]);
      System.out.println(dklic[i]);
    }
  }

  public String encode(String str) {
    int n = str.length();
    str = str + "________";
    int[] s = toNumbers(str);
    //int[] r = new int[n + 8];
    for (int i=n-1; i>=0; i--) { // pro každý dekódovaný znak odzadu
      Devitka d = najdiProPozici(i);
      for (int j=0; j<9; j++) {
        if (! d.bb[j]) { // jen ty netransponoane
          int cim = d.pp[j / 3];
          s[i+j] = (s[i+j] + 27 - cim) % 27;
        }
      }
      int pom = s[i+d.pp[2]];
      s[i+d.pp[2]] = s[i+d.pp[1]];
      s[i+d.pp[1]] = s[i+d.pp[0]];
      s[i+d.pp[0]] = pom;
      //r[i] = s[i];
    }
    return toChars(s);

  }

  public String decode(String str) {
    int n = str.length() - 8;
    int[] s = toNumbers(str);
    int[] r = new int[n];
    for (int i=0; i<n; i++) { // pro každý dekódovaný znak
      Devitka d = najdiProPozici(i);
      int pom = s[i+d.pp[0]];
      s[i+d.pp[0]] = s[i+d.pp[1]];
      s[i+d.pp[1]] = s[i+d.pp[2]];
      s[i+d.pp[2]] = pom;
      for (int j=0; j<9; j++) {
        if (! d.bb[j]) { // jen ty netransponoane
          int cim = d.pp[j / 3];
          s[i+j] = (s[i+j] + cim) % 27;
        }
      }
      r[i] = s[i];
    }
    return toChars(r);

  }

  Devitka najdiProPozici(int i) {
    Devitka result = dklic[i % dklic.length];
    return result;
  }

  Devitka prevedZnakKlice(int z) {

    Devitka d = new Devitka();
    for (int i=0; i<3; i++) {
      int k = z % 3;
      z = z / 3;
      d.bb[i * 3 + k] = true;
      d.pp[i] = i * 3 + k;
    }
    return d;


  }

  int toNumber(char c) {
    c = Character.toLowerCase(c);
    if (c >= 'a' && c <= 'z')
      return c - 'a' + 1;
    else
      return 0;
  }

  char toChar(int i) {
    if (i == 0)
      return '_';
    else if (i >=1 && i <= 26)
      return (char) (i - 1 + 'a');
    else
      throw new RuntimeException("Bad number " + i);
  }

  int[] toNumbers(String s) {
    int[] ii = new int[s.length()];
    for (int i=0; i < ii.length; i++) {
      ii[i] = toNumber(s.charAt(i));
    }
    return ii;
  }

  String toChars(int[] ii) {
    StringBuilder sb = new StringBuilder();
    for (int i : ii) {
      sb.append(toChar(i));
    }
    return sb.toString();
  }


  public void test(String s) {
    String encoded = encode(s);
    String decoded = decode(encoded);
    System.out.println("-----------------------");
    System.out.println(s);
    System.out.println(encoded);
    System.out.println(decoded);
  }



  private class Devitka {
    private final boolean bb[] = new boolean[9];
    private final int[] pp = new int[3]; // pozice 3 truu

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return pp[0] + "-" + pp[1] + "-" + pp[2];

    }
  }

  public static void main(String[] args) {

    Sifrator s = new Sifrator("drob");
    //    Sifrator s = new Sifrator("_abcdefghijklmnopqrstuvwxyz");
    //
    s.test("helena veverkova");
    s.test("martin veverka");
    s.test("hura mame to vylusteno sever zvetsi o dva osm sedm jih zmensi o pet sest sedm gratuluje rodinka veverek");
    s.test("abc");

  }


}
