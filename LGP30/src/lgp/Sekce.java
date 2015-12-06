package lgp;

import java.util.HashMap;
import java.util.Map;

public class Sekce {

  private Instrukce prvniInstrukce;
  
  private Map<String, Adresa> symbols = new HashMap<String, Adresa>();
  
  private int number;
  
  private boolean active;
  
  private boolean strict = true;
  
  public Sekce(boolean active) {
    this.active = active;
  }

  void addx(Instrukce instrukce) {
    if (prvniInstrukce == null) {
      prvniInstrukce = instrukce;
    }
  }
  
  public void addSymbol(String aLabel, Adresa aAdresa) {
    boolean byltam = symbols.put(aLabel, aAdresa) != null;
    //System.out.println("LABEL: \"" + label + "\"" + byltam);
    if (byltam) throw new RuntimeException("Duplicitni symbol \"" + aLabel + "\"");
  }
  
  public Adresa getSymbolAdress(String symbol) {
    Adresa adresa = symbols.get(symbol);
    if (adresa == null) throw new RuntimeException("Nenalezen symbol \"" + symbol + "\"");
    return adresa;
  }

  public boolean isActive() {
    return active;
  }

  public Instrukce getPrvniInstrukce() {
    return prvniInstrukce;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public boolean isStrict() {
    return strict;
  }

  public void setStrict(boolean strict) {
    this.strict = strict;
  }
  
}
