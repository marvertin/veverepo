package lgp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

  private static void list(PrintWriter pw, Program program, boolean aCache) {
    Adresa lastAddr = Adresa.from(0);
    if (aCache) {
      pw.println("<pre width=200  style='background-color: #EEEECC; color:darkgreen; padding:15px; width: 300; font-size:20'>");
    }
    boolean dira = true;
    for (Instrukce i : program.instrukce) { 
      if (!dira && (i == null || i.getAdresa().toInt() != lastAddr.toInt() + 1)) {
        pw.println("........");
        dira = true;
      }
      if (i != null) {
        tiskInstrukce(pw, i, aCache);
        lastAddr = i.getAdresa();
        dira = false;
      }
    }
    if (aCache) {
      pw.println("</pre>");
    }
    pw.flush();
  }
  
  private static void tiskInstrukce(PrintWriter pw, Instrukce ii, boolean aCache) {
    pw.println(aCache ? ii.toStringForCache() : ii.toString());
  }
  
  public static void main(String[] args) throws IOException {
    try {
      String jmeno = args[0];
      File file = new File(jmeno);
      File listing = new File(jmeno.replaceAll("\\.lgp", ".lst.html"));
      System.out.println("-----------------------------------------");
      Prekladac prk = new Prekladac();
      prk.compile(file);

      list(new PrintWriter(System.out), prk.getProgram(), false);
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(listing)));
      list(pw, prk.getProgram(), true);
      pw.close();

      System.out.println("-----------------------------------------");
      Simulator sim = new Simulator();
      sim.setProgram(prk.getProgram());
      System.out.println("START");
      sim.run();
      System.out.println("STOP, pocet instrukci=" + sim.getPocitadlo());
      System.out.println("------------Print------------------------");
      System.out.println(sim.getVystup());
      System.out.println("------------Nepouzite--------------------");
      vypisNepouzitce(sim.getMem());
      System.out.println("------------Vystupy ---------------------");
      vypisVystupy(sim.getMem());
      System.out.println("------------Konecne hodnoty z ;* -------");
      vypisKonecneHodnoty(sim.getMem());
      System.out.println("-----------------------------------------");

    } catch (Exception e) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e1) {
      }
      e.printStackTrace();
    }

  }
  /**
   * @param aMem
   */
  private static void vypisNepouzitce(Instrukce[] aMem) {
    for (Instrukce inst : aMem) {
      if (inst == null) continue;
      if (! inst.pouzita() && inst.getSekce().isActive()) {
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


  /**
   * @param aMem
   */
  private static void vypisKonecneHodnoty(Instrukce[] aMem) {
    for (Instrukce inst : aMem) {
      if (inst == null) continue;
      if (inst.getType() == Instrukce.Type.INST) continue;
      if (inst.getTrace() == null) continue;
      // a tak vypíšeme
      String s = inst.toString();
      if ("coord".equalsIgnoreCase(inst.getTrace())) {
        s += " | " + Utils.convertToCoordinates(inst.getDouble());
      }

      System.out.println(s);
    }
  }

}
