package lgp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Prekladac {


  private Adresa citac;

  private int cisloRadku;
  
  private Sekce currSekce;
  
  private Program program;

  private boolean vsechnySekceJsouAktivni; 

  public void compile(File aFile) throws IOException {
    program = new Program();
    try {
      preCompile(aFile);
      resolvujSymboly();
    } catch (Exception e) {
      throw new RuntimeException("Error on line " + cisloRadku + " counter=" + citac, e);
    }
  }

  private void preCompile(File aFile) throws IOException {
    citac = Adresa.ZERO;
    BufferedReader rdr = new BufferedReader(new FileReader(aFile));

    String line;
    cisloRadku = 0;
    while ((line = rdr.readLine()) != null) {
      cisloRadku ++;
      Instrukce instrukce = parsuj(line);
      if (instrukce != null) program.instrukce[instrukce.getAdresa().toInt()] = instrukce;
    }

  }

  private Instrukce parsuj(String line) {
    // zpracování komentáøe
    line = line.trim();
    String trace = null;
    int komenpoz = line.indexOf(";");
    if (komenpoz >= 0) { // je tam komentáø
      String comment = line.substring(komenpoz + 1);
      line = line.substring(0, komenpoz);
      if (comment.startsWith("*")) {
        trace = comment.substring(1); // tak to co je za tím
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
      currSekce().addSymbol(label, citac);
    }
    if (line.length() == 0) {
      return null;
    }
    if (line.startsWith("/")) {
      parsujControl(line.substring(1));  
      return null;
    }
    char code = line.charAt(0);
    Instrukce r; 
    if (Instrukce.isCode(code)) {
      r = new Instrukce(currSekce());
      r.comp().setType(Instrukce.Type.INST);
      String symbol = line.substring(2);
      Adresa jeTamCislo;
      if (Adresa.canFrom(symbol)) {
        jeTamCislo = Adresa.from(symbol);
        r.comp().setOperCode(code);
        r.comp().setOperand(jeTamCislo);
      } else {
        r.comp().setSymbol(symbol); // uložit zatím symbol do instrukce
        r.comp().setOperCode(code);
      }
      // je to instrukce
    } else { // je to èíslo
      r = parsujCislo(line);
    }
    r.setTrace(trace);
    r.comp().setAdresa(citac);
    r.comp().setSourceLine(sourceLine);
    citac = citac.inc();
    return r;
  }

  private void parsujControl(String s) {
    if (s.length() == 0) error ("Chybi povel v ridicim prikazu");
    char povel = s.charAt(0);
    String data = s.substring(1);
    switch (povel) {
    case '.':
      citac = Adresa.from(data);
      break;  // adresa    
    case '*':
      vsechnySekceJsouAktivni = true;
      break;
    case '=':  // nova sekce
      Sekce sekce = new Sekce(data.charAt(0) == '=' || vsechnySekceJsouAktivni);
      setCurrSekce(sekce);
      program.sekciony.add(currSekce());
      sekce.setNumber(program.sekciony.size());
      break;
    case '!':  // sekce nebude striktní
      currSekce.setStrict(false);
      break;
    default:
      error("Spatny povel v ridicim prikazu: " + s +	"");
    }
    
    
  }

  private void resolvujSymboly() {
    for (Instrukce i : program.instrukce) {
      if (i == null) continue;
      String symbol = i.comp().getSymbol();
      if (symbol == null) continue;
      Adresa adresa = i.getSekce().getSymbolAdress(symbol);
      i.comp().setOperand(adresa);
    }
  }

  private Instrukce parsujCislo(String s) {
    // èíslo mùže být dekadické nebo hexa, dle výskytu zavináèe
    Instrukce inst = new Instrukce(currSekce());
    String[] dvojice = s.split("@", 2);
    if (dvojice.length == 1) {
      int result = Utils.parseHexa(dvojice[0]);
      inst.comp().setType(Instrukce.Type.CONST);
      inst.comp().setConstant(result);
    } else if (dvojice.length == 2) { // je to dekadicke
      double aaa;
      int xscale = Integer.parseInt(dvojice[1]);
      if (dvojice[0].length() > 0) {
        inst.comp().setType(Instrukce.Type.CONST);
        aaa = Double.parseDouble(dvojice[0]);
        int iscale = xscale;
        for (; iscale < 31; iscale++) {
          aaa *= 2;
        }
        int result = (int)aaa;
        inst.comp().setConstant(result);
      } else {
        inst.comp().setType(Instrukce.Type.VAR);
      }
      inst.comp().setScale(xscale);
    }
    return inst;
  }

  private void error(String string) {
    throw new RuntimeException("RADEK=" + cisloRadku + ": " + string);
  }


  public Program getProgram() {
    return program;
  }

  private void setCurrSekce(Sekce currSekce) {
    this.currSekce = currSekce;
  }

  private Sekce currSekce() {
    if (currSekce == null) throw new RuntimeException("Nabyla definovana sekce!");
    return currSekce;
  }

}
