package lgp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Prekladac {

  private Instrukce[] instrukce  = new Instrukce[64*64];
  private Map<String, Adresa> symbols = new HashMap<String, Adresa>();

  private Adresa citac;

  private int cisloRadku;

  public void compile(File aFile) throws IOException {
    try {
      symbols.clear();
      preCompile(aFile);
      resolvujSymboly();
    } catch (Exception e) {
      throw new RuntimeException("Error on line " + cisloRadku + " counter=" + citac, e);
    }
  }

  private void preCompile(File aFile) throws IOException {
    instrukce= new Instrukce[64*64];
//  citac = 1000;
    citac = Adresa.ZERO;
    BufferedReader rdr = new BufferedReader(new FileReader(aFile));

    String line;
    cisloRadku = 0;
    while ((line = rdr.readLine()) != null) {
      cisloRadku ++;
      Instrukce instrukce = parsuj(line);
      if (instrukce != null) this.instrukce[instrukce.getAdresa().toInt()] = instrukce;
    }

  }

  private Instrukce parsuj(String line) {
    // zpracování komentáøe
    line = line.trim();
    boolean trace = false;
    int komenpoz = line.indexOf(";");
    if (komenpoz >= 0) { // je tam komentáø
      String comment = line.substring(komenpoz + 1);
      line = line.substring(0, komenpoz);
      if (comment.startsWith("*")) {
        trace = true;
      }
    }
    if (line.length() == 0) return null;
    // komentáø je pryè
    //String rr = Pattern.compile("[A-Za-z][A-Za-z0-9][A-Za-z][A-Za-z0-9]");
    line = line.replaceAll("\\s", " ").trim();
    String sourceLine = line;
    int poz = line.indexOf(":");
    if (poz >= 0) { // je tam label
      String label = line.substring(0, poz);
      line = line.substring(poz + 1).trim();
      boolean byltam = symbols.put(label, citac) != null;
      //System.out.println("LABEL: \"" + label + "\"" + byltam);
      if (byltam) error("Duplicitni symbol \"" + label + "\"");
    }
    if (line.length() == 0) {
      return null;
    }
    if (line.startsWith("/")) {
      citac = Adresa.from(line.substring(1));
      return null;
    }
    char code = line.charAt(0);
    Instrukce r; 
    if (Instrukce.isCode(code)) {
      r = new Instrukce();
      r.setType(Instrukce.Type.INST);
      String symbol = line.substring(2);
      Adresa jeTamCislo;
      if (Adresa.canFrom(symbol)) {
        jeTamCislo = Adresa.from(symbol);
        r.setOperCode(code);
        r.setOperand(jeTamCislo);
      } else {
        r.setSymbol(symbol); // uložit zatím symbol do instrukce
        r.setOperCode(code);
      }
      // je to instrukce
    } else { // je to èíslo
      r = parsujCislo(line);
    }
    r.setTrace(trace);
    r.setAdresa(citac);
    r.setSourceLine(sourceLine);
    citac = citac.inc();
    return r;
  }

  private void resolvujSymboly() {
    for (Instrukce i : instrukce) {
      if (i == null) continue;
      String symbol = i.getSymbol();
      if (symbol == null) continue;
      Adresa adresa = symbols.get(symbol);
      if (adresa == null) error("Nenalezen symbol \"" + symbol + "\"");
      i.setOperand(adresa);
    }
  }

  private Instrukce parsujCislo(String s) {
    // èíslo mùže být dekadické nebo hexa, dle výskytu zavináèe
    Instrukce inst = new Instrukce();
    String[] dvojice = s.split("@", 2);
    if (dvojice.length == 1) {
      int result = Instrukce.parseHexa(dvojice[0]);
      inst.setType(Instrukce.Type.CONST);
      inst.setConstant(result);
    } else if (dvojice.length == 2) { // je to dekadicke
      double aaa;
      int scale = Integer.parseInt(dvojice[1]);
      if (dvojice[0].length() > 0) {
        inst.setType(Instrukce.Type.CONST);
        aaa = Double.parseDouble(dvojice[0]);
        for (; scale < 31; scale++) {
          aaa *= 2;
        }
        int result = (int)aaa;
        inst.setConstant(result);
      } else {
        inst.setType(Instrukce.Type.VAR);
      }
      inst.setScale(scale);
    }
    return inst;
  }

  private void error(String string) {
    throw new RuntimeException("RADEK=" + cisloRadku + ": " + string);
  }

  public int[] getIntMemory() {
    int[] mem = new int[4096];
    for (Instrukce inst : instrukce) {
      mem[inst.getAdresa().toInt()] = inst.getCislo();
    }
    return mem;
  }


  public Instrukce[] getMemory() {
    return instrukce;
  }

  public Map<String, Adresa> getSymbols() {
    return symbols;
  }
}
