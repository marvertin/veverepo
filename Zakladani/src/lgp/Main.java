package lgp;

import java.io.File;
import java.io.IOException;

public class Main {

  private static void list(Instrukce[] insts) {
    Adresa lastAddr = Adresa.from(0);
    boolean dira = true;
    for (Instrukce i : insts) {
      if (!dira && (i == null || i.getAdresa().toInt() != lastAddr.toInt() + 1)) {
        System.out.println("........");
        dira = true;
      }
      if (i != null) {
        System.out.println(i);
        lastAddr = i.getAdresa();
        dira = false;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    String jmeno = args[0];
    File file = new File(jmeno);
    System.out.println("-----------------------------------------");
    Prekladac prk = new Prekladac();
    prk.compile(file);

    list(prk.getMemory());

    System.out.println("-----------------------------------------");
    Simulator sim = new Simulator();
    sim.setMem(prk.getMemory());
    System.out.println("START");
    while (! sim.isHalted()) {
      //System.out.println(prk.getSymbols().get("CITAC"));
      sim.runOne();
    }
    System.out.println("STOP, instrukci=" + sim.getPocitadlo());
    System.out.println("------------Print------------------------");
    System.out.println(sim.getVystup());
    System.out.println("------------Nepouzite--------------------");
    vypisNepouzitce(sim.getMem());
    System.out.println("------------Vystupy ---------------------");
    vypisVystupy(sim.getMem());
    System.out.println("-----------------------------------------");

  }
  /**
   * @param aMem
   */
  private static void vypisNepouzitce(Instrukce[] aMem) {
    for (Instrukce inst : aMem) {
      if (inst == null) continue;
      if (! inst.pouzita()) {
        System.out.println(inst);
      }
    }
  }

  /**
   * @param aMem
   */
  private static void vypisVystupy(Instrukce[] aMem) {
    for (Instrukce inst : aMem) {
      if (inst == null) continue;
      if (inst.obsahujeVystup()) {
        System.out.println(inst);
      }
    }
  }



}
