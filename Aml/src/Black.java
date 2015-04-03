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

  public void addVeta(final String ... slova) {
    curCol = 0;
    final Veta veta = new Veta();
    for (final String s : slova) {
      final Slovo slovo = new Slovo();
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
    final Nka nka = sestavNka();
    System.out.println("Stavu NKA: " + NkaUzel.pocet);
    dka = nka.zdeterministièni();
    System.out.println("Stavu DKA: " + DkaUzel.pocet);

  }

  private Nka sestavNka() {
    final Nka nka = new Nka();
    for (final Veta veta : vety) {
      for (final Slovo slovo : veta.slova) {
        nka.pridej(slovo);
      }
    }
    for (final NkaUzel nuzel : nka.uzly) {
      if (nka.starty.contains(nuzel)) {
        System.out.println("-------------------");
      }
      System.out.println("< " + nuzel);
      for (final NkaPrechod prechod : nuzel.prechody) {
        System.out.println("   \"" + prechod.znak + "\" ==> " + prechod.next);
      }
    }
    return nka;
  }

  public void porovnej(final String... texty) {

    final Map<Veta, Citac> map = new HashMap<Veta, Citac>();
    for (final String text : texty) {
      final Map<Veta, Set<Slovo>> vysl = dka.porovnej(text);
      if (vysl != null) {
        for (final Map.Entry<Veta, Set<Slovo>> entry : vysl.entrySet()) {
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
    for (final Map.Entry<Veta, Citac> entry : map.entrySet()) {
      System.out.println("    " + entry.getValue().n + ": " + entry.getKey().slova);
    }
  }


  class Citac {
    int n;
  }

}
