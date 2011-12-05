import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class DkaUzel {

  static int pocet;

  final Set<NkaUzel> nuzly;

  DkaUzel[] prechody = new DkaUzel['z' - 'a' + 1];

  boolean konecny;

  Map<Veta, Set<Slovo>> vety = new HashMap<Veta, Set<Slovo>>();

  int cislo;

  public DkaUzel(Set<NkaUzel> nuzly) {
    pocet++;
    cislo = pocet;
    this.nuzly = nuzly;
    for (NkaUzel nuzel : nuzly) {
      if (nuzel.konecny) {
        Set<Slovo> set = vety.get(nuzel.slovo.veta);
        if (set == null) {
          set = new HashSet<Slovo>();
          vety.put(nuzel.slovo.veta, set);
        }
        set.add(nuzel.slovo);
        konecny = true;
      }
    }
    if (!konecny) {
      vety = null; // u prubeznych uzlu nejsou potreba
    }
  }


  @Override
  public String toString() {
    return String.format("[%sDSTAV-%d / %s]",
        konecny ? "$" : ""
        , cislo, vety);
  }


  /**
   * 
   */
  public void print() {
    System.out.printf("%s%n", this);
    for (NkaUzel nuzel : nuzly) {
      System.out.println("    " + nuzel);
    }
    for (int i=0; i < prechody.length; i++) {
      if (prechody[i] != null) {
        System.out.printf("   \"%s\" => %s%n", (char)(i + 'a'), prechody[i]);
      }
    }

    // TODO Auto-generated method stub
    
  }
}
