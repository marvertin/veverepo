import java.util.ArrayList;
import java.util.List;


public class NkaUzel {

  static int pocet;

  final Slovo slovo;
  final List<NkaPrechod> prechody = new ArrayList<NkaPrechod>();
  boolean konecny;
  boolean startovaci;

  int cislo;

  private final String doplnujiciPopis;

  NkaUzel(Slovo slovo, String doplnujiciPopis) {
    this.slovo = slovo;
    this.doplnujiciPopis = doplnujiciPopis;
    pocet ++;
    cislo = pocet;
  }

  public void addPrechod(char c, NkaUzel uzel) {
    NkaPrechod prechod = new NkaPrechod();
    prechod.znak = c;
    prechod.next = uzel;
    prechody.add(prechod);
  }


  @Override
  public String toString() {
    return String.format("[%s%sNSTAV-%d %s %s]",
        startovaci ? "^" : "",
        konecny ? "$" : ""
        , cislo, (slovo == null ? "<null>"  : slovo.text), doplnujiciPopis);
    
  }
}
