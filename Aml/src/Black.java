import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Black {

  int curRow;
  int curCol;

  List<Veta> vety = new ArrayList<Veta>();
  private Dka dka;

  public void addVeta(String ... slova) {
    curCol = 0;
    Veta veta = new Veta();
    for (String s : slova) {
      Slovo slovo = new Slovo();
      slovo.text = s;
      slovo.col = curCol;
      slovo.veta = veta;
      veta.slova.add(slovo);
      curCol ++;
    }
    vety.add(veta);
    curRow ++;
  }

  public void compute() {
    Nka nka = sestavNka();
    System.out.println("Stavu NKA: " + NkaUzel.pocet);
    dka = nka.zdeterministièni();
    System.out.println("Stavu DKA: " + DkaUzel.pocet);

  }

  private Nka sestavNka() {
    Nka nka = new Nka();
    for (Veta veta : vety) {
      for (Slovo slovo : veta.slova) {
        nka.pridej(slovo);
      }
    }
    for (NkaUzel nuzel : nka.uzly) {
      if (nka.starty.contains(nuzel)) {
        System.out.println("-------------------");
      }
      System.out.println("< " + nuzel);
      for (NkaPrechod prechod : nuzel.prechody) {
        System.out.println("   \"" + prechod.znak + "\" ==> " + prechod.next);
      }
    }
    return nka;
  }

  public void porovnej(String... texty) {

    Map<Veta, Citac> map = new HashMap<Veta, Citac>();
    for (String text : texty) {
      Map<Veta, Set<Slovo>> vysl = dka.porovnej(text);
      if (vysl != null) {
        for (Map.Entry<Veta, Set<Slovo>> entry : vysl.entrySet()) {
          Citac citac = map.get(entry.getKey());
          if (citac == null) {
            citac = new Citac();
            map.put(entry.getKey(), citac);
          }
          citac.n += entry.getValue().size();
        }
      }
    }

    System.out.println(Arrays.asList(texty));
    for (Map.Entry<Veta, Citac> entry : map.entrySet()) {
      System.out.println("    " + entry.getValue().n + ": " + entry.getKey().slova);
    }
  }


  class Citac {
    int n;
  }

}
