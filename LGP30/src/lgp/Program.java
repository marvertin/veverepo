package lgp;

import java.util.ArrayList;
import java.util.List;

public class Program {

  public Instrukce[] instrukce  = new Instrukce[64*64];

  public List<Sekce> sekciony = new ArrayList<Sekce>();

  public int[] getIntMemory() {
    int[] mem = new int[4096];
    for (Instrukce inst : instrukce) {
      mem[inst.getAdresa().toInt()] = inst.getBinValue();
    }
    return mem;
  }
  
  
}
