/**
 * 
 */
package lgp;


class Instrukce {

  private static char[] codes = new char[]{'Z', 'B', 'Y', 'R', 'I', 'D', 'N', 'M',
    'P', 'E', 'U', 'T', 'H', 'C', 'A', 'S'};

  private static char[] hexas = new char[]{'0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9', 'F', 'G', 'J', 'K', 'Q', 'W'};

  private static char[] conrols = new char[]{' ', '-', '+', ';', '/', '.', ',', 'V',
    'O', 'X', '?', '?', '?', '?', '?',  '?'};

  private Adresa adresa = null;

  private int cislo;

  private String sourceLine;

  private int scale;

  private boolean tracex;

  private String symbol;

  private boolean constantOrVariableSeted;
  
  private Type type;
  
  private boolean ctenaNekdy;
  
  private boolean ctenaPosledni;

  private int provedena;

//0 Z  Zastav (1) 8 P print Tiskni znak (4) 
//1 B bring Zapiš do støádaèe 9 E extract Logický souèin støádaèe a obsahu adresy 
//2 Y Adresovou èást støádaèe ulož na adresu F U uncondit. jump Skoè na adresu 
//3 R return Pøíprava návratu z podprogramu G T test Skoè na adresu, je-li støádaè záporný. (5) 
//4 I input Èti ze vstupního zaøízení (2) J H hold Obsah støádaèe ulož na adresu 
//5 D divide Vydìl støádaè obsahem adresy K C clear dtto, potom støádaè vynuluj 
//6 N Násob støádaè obsahem adresy, ponech nižší bity (3) Q A add Pøièti ke støádaèi obsah adresy 
//7 M multiply dtto, ponech vyšší bity (3) W S subtract Odeèti od støádaèe obsah adresy 




  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    check(Type.INST);
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    String s = type != Type.INST
    ? String.format("%s %08x %s", adresa, cislo, getCisloScale())
        : String.format("%s %s %s %08x %d", adresa, getOperCodeChar(), getOperand(), cislo, cislo);

    s = String.format("%-35s %s", s, getSourceLine());
    return s;
  }


  public void setOperCode(int code) {
    check(Type.INST);
    if (code < 0 || code > 15) throw new RuntimeException("code="+code);
    cislo = cislo & 0xFFF0FFFF | ((code << 16) &  0x000F0000);
  }

  public void setOperCode(char aOperCode) {
    check(Type.INST);
    int x = convertCode(aOperCode);
    if (x < 0) throw new RuntimeException("Tak tento kod nezname: \"" + aOperCode + "\"");
    setOperCode(x);
  }

  public void setOperand(Adresa aOperand) {
    check(Type.INST);
    symbol = null;
    cislo = cislo & 0xFFFFC003 | ((aOperand.toInt() << 2) & 0x3FFC) ;
  }

  public void setConstant(int aCislo) {
    if (constantOrVariableSeted) throw new RuntimeException("Cannot reset constant in instruction: " + aCislo + " in " + this);
    check(Type.CONST);
    int x = aCislo & 0xFFFFFFFE;
    cislo = x;
    constantOrVariableSeted = true;
  }

  public void setVariable(int aCislo) {
    check(Type.VAR);
    cislo = aCislo & 0xFFFFFFFE;
    constantOrVariableSeted = true;
    ctenaPosledni = false;
  }
  
  public void ctena() {
    ctenaNekdy = true;
    ctenaPosledni = true;
  }
  
  public void provedena() {
    check(Type.INST);
    provedena ++;
  }
  
  private int getOperCode() {
    int result = (cislo & 0x000F0000) >> 16;
    return result;
  }

  public char getOperCodeChar() {
    return codes[getOperCode()];
  }

  public Adresa getOperand() {
    Adresa result = Adresa.from((cislo & 0x3FFC) >> 2);
    return result;
  }

  public int getCislo() {
    return cislo;
  }


  public double getDouble() {
    double result = convert(cislo, scale);
    return result;
  }

  public String getCisloScale() {
    return getDouble() + "@" + scale;
  }

  public char getPrintedChar() {
    char result = convertChar((cislo & 0x3F00) >> 8);
    return result;
  }

  private static int convertCode(char code) {
    code = Character.toUpperCase(code);
    for (int i =0; i < codes.length; i++) {
      if (codes[i] == code) {
        return i;
      }
    }
    return -1;
  }

  private char convertChar(int aCislo) {
    char c;
    int z = aCislo >> 2;
    int zona = aCislo & 3;
    switch (zona) {
    case 0: c = '?'; break;
    case 1: c = codes[z]; break;
    case 2: c = hexas[z]; break;
    case 3: c = conrols[z]; break;
    default: throw new RuntimeException();
    }
    return c;
  }

  public Adresa getAdresa() {
    return adresa;
  }

  public void setAdresa(Adresa adresa) {
    this.adresa = adresa;
  }

  public static boolean isCode(char code) {
    int x = convertCode(code);
    return x >= 0;
  }

  public String getSourceLine() {
    return sourceLine;
  }

  public void setSourceLine(String sourceLine) {
    this.sourceLine = sourceLine;
  }

  public int getScale() {
    return scale;
  }

  public void setScale(int scale) {
    this.scale = scale;
  }


  public static double convert(int aValue, int aScale) {
    double d = aValue;
    for (int i=0; i<31-aScale; i++) {
      d = d / 2;
    }
    return d;

  }

  public boolean isTrace() {
    return tracex;
  }

  public void setTrace(boolean trace) {
    this.tracex = trace;
  }

  public static int parseHexa(String s) {
    int cislo = 0;
    if (s.length() != 8) throw new RuntimeException("Hexa cislo \"" + s + "\"nema 8 cifer");
    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      int k = -1;
      for (int j=0; j<hexas.length; j++) {
        if (hexas[j] == c) {
          k = j;
          break;
        }
      }
      if (k == -1) throw new RuntimeException("Spatny znak v hexa cisle \"" + s + "\"");
      cislo = (cislo << 4) | k; 
    }
    return cislo;
  }

  /**
   * @return the variableSeted
   */
  public boolean isConstantOrVariableSeted() {
    return constantOrVariableSeted;
  }


  public static enum Type {INST, VAR, CONST}

  /**
   * @return the type
   */
  public Type getType() {
    return type;
  }

  /**
   * @param aType the type to set
   */
  public void setType(Type aType) {
    if (type != null && type != aType) {
      throw new RuntimeException("Cannot change instrukction type " + type + " => " + aType + " in " + this);
    }
    type = aType;
  };
  
  private void check(Type aType) {
    if (type != aType) {
      throw new RuntimeException("Bad instrukcion type for this operation, required " + aType + ", but " + type + " in " + this);
    }
  }


  /**
   * @return the ctenaNekdy
   */
  public boolean isCtenaNekdy() {
    return ctenaNekdy;
  }

  /**
   * @param aCtenaNekdy the ctenaNekdy to set
   */
  public void setCtenaNekdy(boolean aCtenaNekdy) {
    ctenaNekdy = aCtenaNekdy;
  }

  /**
   * @return the ctenaPosledni
   */
  public boolean isCtenaPosledni() {
    return ctenaPosledni;
  }

  /**
   * @param aCtenaPosledni the ctenaPosledni to set
   */
  public void setCtenaPosledni(boolean aCtenaPosledni) {
    ctenaPosledni = aCtenaPosledni;
  }

  /**
   * @return the provedena
   */
  public int getProvedenaCount() {
    return provedena;
  }
  
  public boolean pouzita() {
    return type == Type.INST && getProvedenaCount() > 0 
        || type == Type.CONST && isCtenaNekdy()
        || type == Type.VAR && isConstantOrVariableSeted();
  }
  
  public boolean obsahujeVystup() {
    return pouzita() && !isCtenaPosledni() && type == Type.VAR;
  }
}