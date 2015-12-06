/**
 * 
 */
package lgp;


class Instrukce {
  public static enum Type {INST, VAR, CONST}
  
 
  /** Adresa, na níž je instrukce umístìna */
  private Adresa iAdresa = null;

  /** Binární hodnta instrukce, spolu s adresou øíkají o instrukci vše */
  private int iBinValue;

  /** Sekce, ce které se instruke nacházela */
  private final Sekce iSekce;
  
  /** Typ instrukce, mùže to být skuteèná instrukce, konstanta nebo promìnná */
  private Type iType;
  
  

  private String iTrace;


  private boolean iConstantOrVariableSeted;
  
  
  private boolean iCtenaNekdy;
  
  private boolean ctenaPosledni;

  /** Pokud je instrukce proeditelná, tak poèet provedení instrukce */
  private int provedena;

  private CompilerSetter  iCompilerSetter = new CompilerSetter();
  

//0 Z  Zastav (1) 8 P print Tiskni znak (4) 
//1 B bring Zapiš do støádaèe 9 E extract Logický souèin støádaèe a obsahu adresy 
//2 Y Adresovou èást støádaèe ulož na adresu F U uncondit. jump Skoè na adresu 
//3 R return Pøíprava návratu z podprogramu G T test Skoè na adresu, je-li støádaè záporný. (5) 
//4 I input Èti ze vstupního zaøízení (2) J H hold Obsah støádaèe ulož na adresu 
//5 D divide Vydìl støádaè obsahem adresy K C clear dtto, potom støádaè vynuluj 
//6 N Násob støádaè obsahem adresy, ponech nižší bity (3) Q A add Pøièti ke støádaèi obsah adresy 
//7 M multiply dtto, ponech vyšší bity (3) W S subtract Odeèti od støádaèe obsah adresy 


  Instrukce(Sekce sekce) {
    this.iSekce = sekce;
    sekce.addx(this);
  }


  @Override
  public String toString() {
    String s = iType != Type.INST
    ? String.format("%s %s %s", iAdresa, toHex(), getCisloScale())
        : String.format("%s %s %s %s %d", iAdresa, getOperCodeChar(), getOperand(), toHex(), iBinValue);

    s = String.format("%-35s %s", s, comp().getSourceLine());
    return s;
  }

  public String toStringForCache() {
    String toHex = toHex();
    String s1 = String.format("<b>%s: %s</b>", iAdresa, toHex);
    String s = iType != Type.INST
    ? "" : String.format("<span style='color:gray'> (%s %s)</span>", getOperCodeChar(), getOperand());

    //s = String.format("%-35s %s", s, comp().getSourceLine());
    return s1 + s;
  }


  public String toHex() {
    return Utils.formatHexa(iBinValue);
  }


  public void setSimulatorOperand(Adresa aOperand) {
    check(Type.INST);
    iBinValue = iBinValue & 0xFFFFC003 | ((aOperand.toInt() << 2) & 0x3FFC) ;
  }
  
  public void setVariable(int aCislo) {
    check(Type.VAR);
    iBinValue = aCislo & 0xFFFFFFFE;
    iConstantOrVariableSeted = true;
    ctenaPosledni = false;
  }
  
  public void simCtena() {
    iCtenaNekdy = true;
    ctenaPosledni = true;
  }
  
  public void simProvedena() {
    check(Type.INST);
    provedena ++;
  }
  
  private int getOperCode() {
    int result = (iBinValue & 0x000F0000) >> 16;
    return result;
  }

  public char getOperCodeChar() {
    return Utils.convertOperCodeToChar(getOperCode());
  }

  public Adresa getOperand() {
    Adresa result = Adresa.from((iBinValue & 0x3FFC) >> 2);
    return result;
  }

  public int getBinValue() {
    return iBinValue;
  }


  public double getDouble() {
    double result = convert(iBinValue, comp().getScale());
    return result;
  }

  public String getCisloScale() {
    return getDouble() + "@" + comp().getScale();
  }

  public char getPrintedChar() {
    char result = Utils.convertToCharForPrint((iBinValue & 0x3F00) >> 8);
    return result;
  }



  public Adresa getAdresa() {
    return iAdresa;
  }


  public static boolean isCode(char code) {
    int x = Utils.convertOperCodeToInt(code);
    return x >= 0;
  }


  public static double convert(int aValue, int aScale) {
    double d = aValue;
    for (int i=0; i<31-aScale; i++) {
      d = d / 2;
    }
    return d;

  }

   /**
   * @return the variableSeted
   */
  public boolean isConstantOrVariableSeted() {
    return iConstantOrVariableSeted;
  }



  /**
   * @return the type
   */
  public Type getType() {
    return iType;
  }

  
  private void check(Type aType) {
    if (isStrict() && iType != aType) {
      throw new RuntimeException("Bad instrukcion type for this operation, required " + aType + ", but " + iType + " in " + this);
    }
  }
 
  /**
   * @return the ctenaNekdy
   */
  public boolean isCtenaNekdy() {
    return iCtenaNekdy;
  }

  /**
   * @return the ctenaPosledni
   */
  public boolean isCtenaPosledni() {
    return ctenaPosledni;
  }

  /**
   * @return the provedena
   */
  public int getProvedenaCount() {
    return provedena;
  }
  
  public boolean pouzita() {
    return iType == Type.INST && getProvedenaCount() > 0 
        || iType == Type.CONST && isCtenaNekdy()
        || iType == Type.VAR && isConstantOrVariableSeted();
  }
  
  public boolean obsahujeVystup() {
    return pouzita() && !isCtenaPosledni() && iType == Type.VAR;
  }

  public Sekce getSekce() {
    return iSekce;
  }

 
  public boolean isStrict() {
    return iSekce.isStrict();
  }
  
  public CompilerSetter comp() {
    return iCompilerSetter;
  }
  
  public class CompilerSetter {
    
    /** Symbol ve zdrojáku u instrukce */
    private String iSymbol;

    /** Mìøítko, pokud se jedná o èíslo */
    private int iScale;

    /** Zdrojový øádek, na nìmž byla instrukce umístìna */
    private String iSourceLine;

    
    /**
     * @param aType the type to set
     */
    public void setType(Type aType) {
      if (iType != null && iType != aType) {
        throw new RuntimeException("Cannot change instrukction type " + iType + " => " + aType + " in " + this);
      }
      iType = aType;
    };
    
    public void setAdresa(Adresa aAdresa) {
      iAdresa = aAdresa;
    }
    
    public void setConstant(int aCislo) {
      if (iConstantOrVariableSeted) throw new RuntimeException("Cannot reset constant in instruction: " + aCislo + " in " + this);
      check(Type.CONST);
      int x = aCislo & 0xFFFFFFFE;
      iBinValue = x;
      iConstantOrVariableSeted = true;
    }

    public void setScale(int scale) {
      iScale = scale;
    }

    public void setOperand(Adresa aOperand) {
      check(Type.INST);
      iSymbol = null;
      iBinValue = iBinValue & 0xFFFFC003 | ((aOperand.toInt() << 2) & 0x3FFC) ;
    }

    public void setSymbol(String symbol) {
      check(Type.INST);
      iSymbol = symbol;
    }

    public void setOperCode(int code) {
      check(Type.INST);
      if (code < 0 || code > 15) throw new RuntimeException("code="+code);
      iBinValue = iBinValue & 0xFFF0FFFF | ((code << 16) &  0x000F0000);
    }

    public void setOperCode(char aOperCode) {
      check(Type.INST);
      int x = Utils.convertOperCodeToInt(aOperCode);
      if (x < 0) throw new RuntimeException("Tak tento kod nezname: \"" + aOperCode + "\"");
      setOperCode(x);
    }

    public void setSourceLine(String sourceLine) {
      iSourceLine = sourceLine;
    }
    
    

    public String getSymbol() {
      return iSymbol;
    }

    public String getSourceLine() {
      return iSourceLine;
    }

    public int getScale() {
      return iScale;
    }

    
    
    
  }

  public String getTrace() {
    return iTrace;
  }


  public void setTrace(String trace) {
    iTrace = trace;
  }
}