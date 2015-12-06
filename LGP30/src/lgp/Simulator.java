package lgp;

public class Simulator {

  private Instrukce[] mem;

  private int citacx;

  private int a;

  private int pocitadlo;

  private boolean halted;

  private StringBuffer vystup = new StringBuffer();

  private Program program;

  public void run() {
    for (Sekce sekce : program.sekciony) {
      runSekci(sekce);
    }
  }
  
  public void runSekci(Sekce sekce) {
    if (! sekce.isActive()) return;
    pocitadlo = 0;
    reset();
    Adresa adresa = sekce.getPrvniInstrukce().getAdresa();
    citacx = adresa.toInt();
    System.out.println("START " +  sekce.getNumber()+	" on " + adresa);
    while (! isHalted()) {
      runOne();
    }
    System.out.println("STOP " +  sekce.getNumber()+ " on " + Adresa.from(citacx) +  ", instrukci=" + pocitadlo);

  }
  
  public void reset() {
    halted = false;
  }
  
  
  /**
   * Provede jednu instrukci.
   */
  public void runOne() {
    //System.out.println(citac);
    char currOperCodeChar = currOperCodeChar();
    try {
      mem[citacx].simProvedena();  // oznacime, že jsme provedli tuto instrukci
      pocitadlo++;
      switch (currOperCodeChar) {
      case 'Z':
        halted = true;
        return;
      case 'B':
        a = check(loadCurr());
        break;
      case 'Y':
        saveCurrAdressPart(a);
        break;
      case 'R':
        saveCurrAdressPart((citacx + 2) << 2);
        break;
      case 'I':
        throw new RuntimeException("Not supported");
      case 'D':
        long aa = ((long) a) << 31;
        long bb = loadCurr();
        long cc = aa / bb;
        //System.out.printf("%08x / %08x = %08x\n", a, bb, cc);
        a = check (cc);
        break;
      case 'N':
        a = check(((a * loadCurr())) & 0xFFFFFFFE);
        break;
      case 'M':
        a = check((a * loadCurr()) >> 31);
        break;
      case 'P':
        print(mem[citacx].getPrintedChar());
        break;
      case 'E':
        a &= loadCurr();
        break;
      case 'U':
        citacx = currOperand() - 1;
        break;
      case 'T':
        if (a < 0)
          citacx = currOperand() - 1;
        break;
      case 'H':
        saveCurrVar(a);
        break;
      case 'C':
        saveCurrVar(a);
        a = 0;
        break;
      case 'A':
        a = check(a + loadCurr());
        break;
      case 'S':
        a = check(a - loadCurr());
        break;

      default:
        throw new RuntimeException("Spatny kod");
      }
      trace();
      citacx++;
      return;
    } catch (Exception e) {
      throw new RuntimeException(" citac=" + Adresa.from(citacx), e);
    }
  }


  private int check(long x) {
    if (x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
      throw new RuntimeException("Owerflow " + x);
    }
    return (int) x;
  }

  private void print(char c) {
    vystup.append(c);
  }

  private void trace () {
    if (mem[citacx] == null) return;
    String traceCmd = mem[citacx].getTrace();
    if (traceCmd == null) return;
    if (currOperand() == 0)
      System.out.printf("A = %08xh = %f\n", a, Instrukce.convert(
          a, 0));
    else
      System.out.println(mem[currOperand()]);
  }

  private char currOperCodeChar() {
    return mem[citacx].getOperCodeChar();
  }

  private int currOperand() {
    return mem[citacx].getOperand().toInt();

  }

  private long loadCurr() {
    Instrukce instrukce = currOperandAsInstrukce();
    instrukce.simCtena();
    if (! instrukce.isConstantOrVariableSeted()) throw new RuntimeException("Unsetted variable " + instrukce);
    return instrukce.getBinValue();
  }
  /**
   * @return
   */
  private Instrukce currOperandAsInstrukce() {
    return mem[mem[citacx].getOperand().toInt()];
  }

  private void saveCurrVar(int aValue) {
    Instrukce instrukce = currOperandAsInstrukce();
    if (instrukce.isStrict() && instrukce.getType() != Instrukce.Type.VAR) throw new RuntimeException("Cannot modify notvariable: " + instrukce.getType() + " " + instrukce);
    instrukce.setVariable(aValue);
  }
  
  private void saveCurrAdressPart(int aValue) {
    Instrukce instrukce = currOperandAsInstrukce();
    if (instrukce.getType() != Instrukce.Type.INST) throw new RuntimeException("Cannot modify not instruction: " + instrukce);
    Adresa adresa = Adresa.from((aValue & 0x3FFC) >> 2);
    instrukce.setSimulatorOperand(adresa);	
  }
  
  public int getCitac() {
    return citacx;
  }
  public void setCitac(int citac) {
    this.citacx = citac;
  }
  public Instrukce[] getMem() {
    return mem;
  }
  public void setProgram(Program program) {
    this.program = program;
    mem = program.instrukce;
  }
  public boolean isHalted() {
    return halted;
  }
  public int getPocitadlo() {
    return pocitadlo;
  }
  public String getVystup() {
    return vystup.toString();
  }


}
