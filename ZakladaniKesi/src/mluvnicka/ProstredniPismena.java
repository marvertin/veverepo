package mluvnicka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ProstredniPismena {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub


    BufferedReader rr = new BufferedReader(new FileReader( "C:\\gc\\Zakládání\\Mluvnická\\seznamkytek.txt"));
    String line;
    Map<Character, Set<String>> m = new TreeMap<Character, Set<String>>();
    while ((line = rr.readLine()) != null) {
      line = line.trim();
      int poz = line.indexOf(' ');
      if (poz < 0) {
        continue;
      }
      String nazev = line.substring(0, poz).toLowerCase();
      nazev = nazev.replace("ch", "@");
      if (nazev.length() % 2 == 0) {
        continue;
      }
      char stred = nazev.charAt(nazev.length() / 2);

      Set<String> set = m.get(stred);
      if (set == null) {
        set = new TreeSet<String>();
        m.put(stred, set);
      }
      set.add(nazev);
    }

    for (Character ch : m.keySet()) {
      System.out.println();
      System.out.println("******* " + ch + " ******");
      System.out.println();
      for (String s : m.get(ch)) {
        System.out.println("  " + s.replace("@", "ch"));

      }
    }
  }

}
