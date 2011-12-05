import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Nka {



  Set<NkaUzel> starty = new LinkedHashSet<NkaUzel>();

  List<NkaUzel> uzly = new ArrayList<NkaUzel>();

  NkaUzel spolecnyNekonecny;
  
  /**
   * 
   */
  public Nka() {
    spolecnyNekonecny = novyUzel(null, "|SPOL|");
    spolecnyNekonecny.addPrechod(C.ALL, spolecnyNekonecny);
    spolecnyNekonecny.startovaci = true;
    starty.add(spolecnyNekonecny);
  }
  
  
  void pridej(Slovo slovo) {

    char[] cc = slovo.text.toCharArray();

    { // blacklisove v zadanych
//      NkaUzel lastUzel = novyUzel(slovo, "X/0");
//      lastUzel.addPrechod(C.ALL, lastUzel);
//      starty.add(lastUzel);
//      lastUzel.startovaci = true;
      NkaUzel lastUzel = spolecnyNekonecny;
      int i = 0;
      for (char c : cc) {
        NkaUzel uzel = novyUzel(slovo, "X/" + ++i);
        lastUzel.addPrechod(c, uzel);
        lastUzel = uzel;
      }
      lastUzel.addPrechod(C.ALL, lastUzel);
      lastUzel.konecny = true;
    }

//    for (int i=0; i <= cc.length - C.MIN; i++) {
//      NkaUzel lastUzel = novyUzel(slovo, i + "/0");
//      starty.add(lastUzel);
//      lastUzel.startovaci = true;
//      for (int j=i; j < cc.length; j++) {
//        NkaUzel uzel = novyUzel(slovo, i + "/" + (j-i));
//        uzel.konecny = j - i >= C.MIN - 1;
//        lastUzel.addPrechod(cc[j], uzel);
//        lastUzel = uzel;
//      }
//    }
  }

  NkaUzel novyUzel(Slovo slovo, String doplnujiciPopis) {
    NkaUzel nuzel = new NkaUzel(slovo, doplnujiciPopis);
    uzly.add(nuzel);
    return nuzel;

  }

  public Dka zdeterministièni() {
    return new Zdeterministicnovac().determinuj();
  }

  class Zdeterministicnovac {
    Map<Set<NkaUzel>, DkaUzel> mapa = new LinkedHashMap<Set<NkaUzel>, DkaUzel>();
    Queue<DkaUzel> nazpracovani = new LinkedList<DkaUzel>();
    int citacZasahu;
    int citacNezasahu;

    void zauzluj(final DkaUzel duzelZ) {
      for (char c = 'a'; c <= 'z'; c++) { // nevykonne, ale jasne, pro vsechan pismena
        Set<NkaUzel> nuzlyDo = new LinkedHashSet<NkaUzel>();
        for (NkaUzel nuzelZ : duzelZ.nuzly) { // prochazie všechny NKA uzly tothoto uzlu
          for (NkaPrechod prechod : nuzelZ.prechody) {
            if (prechod.znak == c || prechod.znak == C.ALL) {
              nuzlyDo.add(prechod.next);
            }
          }
        }
        DkaUzel duzelDo = mapa.get(nuzlyDo);
        if (duzelDo == null) {
          citacNezasahu ++;
          duzelDo = new DkaUzel(nuzlyDo);
          
          putDoMapy(duzelDo);
          nazpracovani.add(duzelDo);
          //zauzluj(duzelDo);
        } else {
          citacZasahu ++;
        }
        duzelZ.prechody[c - 'a'] = duzelDo;
      }
      
    }

    Dka determinuj() {
      Dka dka = new Dka();
      dka.start = new DkaUzel(starty);
      putDoMapy(dka.start);
      nazpracovani.add(dka.start);
      while(! nazpracovani.isEmpty()) {
        DkaUzel duzel = nazpracovani.remove();
        zauzluj(duzel);
        duzel.print();
      }
      zauzluj(dka.start);
      return dka;
    }

    void putDoMapy(DkaUzel duzel) {
      mapa.put(duzel.nuzly, duzel);
    }

  }
}
