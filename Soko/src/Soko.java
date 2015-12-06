
public class Soko {

  
  private final int nx;
  private final int ny;

  private int x1 = -1;
  private int y1 = -1;

  private int x2 = -1;
  private int y2 = -1;

  private int x3 = -1;
  private int y3 = -1;
  
  private int x;
  private int y;
  
  private int xPridavany;
  
  private enum Co {NIC, ZED, BEDNA, JA};
  
  private Co[][] plocha;
  
  private boolean[][] cilovka;
  
  
  private boolean projito[];
  
  private int citac;
  
  
  public Soko(int nx, int ny) {
    this.nx = nx;
    this.ny = ny;
    plocha = new Co[nx][ny];
    cilovka = new boolean[nx][ny];
    System.out.println("Pocet stavu: " + pocetStavu());
    projito = new boolean[pocetStavu()];
  }
  
  public void proved() {
    kontrola();
    vypis();
    jdi(-1,0, null, 0);
    jdi(1,0, null, 0);
    jdi(0,-1, null, 0);
    jdi(0,1, null, 0);
    System.out.println("Pocet provedeni: " + citac);
  }
  
  private void jdi(int xd, int yd, Dvoj advoj, int hloubka) {
    if (jeCil()) {
      System.out.println("CILOVKA " + citac + " --- " + hloubka);
      advoj.print();
      System.exit(0);
    }
    Dvoj dvoj = new Dvoj();
    dvoj.xd = xd;
    dvoj.yd = yd;
    dvoj.xx = x;
    dvoj.yy = y;
    dvoj.prev = advoj;
    
    int xx = x + xd;
    int yy = y + yd;
    if (mimo(xx, yy)) return; // šlapeme mimo plochu
    if (plocha[xx][yy] == Co.ZED) return; // šlapeme na zeï
    boolean tlacime = plocha[xx][yy] == Co.BEDNA;
    if (tlacime) { // tlaèíme bednu
      if (mimo(xx + xd, yy + yd)) return; // mimo pole
      if (plocha[xx + xd][yy + yd] != Co.NIC) return; // nelze tlaèit, nìcotam je
      presunBednu(xx, yy, xx + xd, yy + yd);
      dvoj.posun = true;
    }
    plocha[xx][yy] = Co.JA; // tak tam vlezu
    plocha[x][y] = Co.NIC; // a uvolnim misto po sobe
    int xpuv = x; int ypuv = y;
    x = xx; y = yy;
    int stav = stav();
    if (! projito[stav]) {
      citac++;
      if (citac % 10000000 == 0) {
        System.out.println(citac);
      }
      projito[stav] = true;
      //vypis();
      jdi( 0, 1, dvoj, hloubka+1);
      jdi( 0, -1, dvoj, hloubka+1);
      jdi( 1, 0, dvoj, hloubka+1);
      jdi( -1, 0, dvoj, hloubka+1);
      //projito[stav] = false;
    }
    x = xpuv;
    y = ypuv;
    plocha[x][y] = Co.JA; // a jdeme zpìt
    plocha[xx][yy] = Co.NIC; // a místo, kam jsem zkoušel
    if (tlacime) {
      presunBednu(xx + xd, yy + yd, xx, yy);
    }
  }
  
  private void presunBednu(int xz, int yz, int xkam, int ykam) {
    if (x1 == xz && y1 == yz) {
      x1 = xkam;
      y1 = ykam;
    }
    if (x2 == xz && y2 == yz) {
      x2 = xkam;
      y2 = ykam;
    }
    if (x3 == xz && y3 == yz) {
      x3 = xkam;
      y3 = ykam;
    }
    plocha[xkam][ykam] = Co.BEDNA;
    plocha[xz][yz] = Co.NIC;
  }
  
  boolean mimo(int ax, int ay) {
    return ax < 0 || ay < 0 || ax >= nx  || ay >= ny;
  }

  int rozmisteni(int ax1, int ay1, int ax2, int ay2, int ax3, int ay3) {
    int result = (((((0 * nx+ax1) * nx+ax2) * nx+ax3) * ny+ay1) * ny+ay2) * ny+ay3;
    return result;
  }

  int rozmisteni() {
    int result = rozmisteni(x1, y1, x2, y2, x3, y3);
    return result;
  }
  
  int stav() {
    int result = (rozmisteni() * nx+x) * ny+y;
    return result;
  }
  
  int pocetStavu() {
    return nx * nx * nx * nx * ny * ny * ny * ny;
  }
  
  public void data(String s) {
    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      boolean budecil = false;
      Co co;
      switch (c) {
        case '*': co = Co.ZED; break;

        case ',': budecil = true;
        case '.': co = Co.NIC; break;

        
        case '-': budecil = true;
        case '+': co = Co.BEDNA;
                if (x1 < 0) { x1 = xPridavany; y1 = i; }
           else if (x2 < 0) { x2 = xPridavany; y2 = i; }
           else if (x3 < 0) { x3 = xPridavany; y3 = i; }
           break;
        case '!':
           co = Co.JA;
           x = xPridavany;
           y = i;
        break;
        default: throw new RuntimeException("Nesmyslny znak " + c);
      }
      plocha[xPridavany][i] = co;
      cilovka[xPridavany][i] = budecil;
      
    }
    xPridavany ++;
    
  }
  
  private void kontrola() {
    if (plocha[x1][y1] != Co.BEDNA) chyba();
    if (plocha[x2][y2] != Co.BEDNA) chyba();
    if (plocha[x3][y3] != Co.BEDNA) chyba();
    if (plocha[x][y] != Co.JA) chyba();
    int beden = 0;
    int me = 0;
    int cilovek = 0;
    for (int i=0; i<nx; i++ ) {
      for (int j=0; j<ny; j++ ) {
        if (plocha[i][j] == null) chyba();
        if (plocha[i][j] == Co.BEDNA) beden ++;
        if (plocha[i][j] == Co.JA) me ++;
        if (cilovka[i][j]) {
          cilovek ++;
          if (plocha[i][j] == Co.ZED) chyba();
        }
      }
    }
    if (me != 1) chyba();
    if (beden != 3) chyba();
    if (cilovek != 3) chyba();
    
  }

  private void vypis() {
    kontrola();
    for (int i=0; i<nx; i++ ) {
      for (int j=0; j<ny; j++ ) {
        
        switch (plocha[i][j]) {
        case NIC: System.out.print('.'); break;
        case ZED: System.out.print('*');break;
        case BEDNA: System.out.print('+');break;
        case JA: System.out.print('!');break;
        }
        
      }
      System.out.println();
    }
    System.out.println();
    
  }
  
  private void chyba() {
    throw new RuntimeException("CHYBA, citac = " + citac);
    
  }
  
  private boolean jeCil() {
    boolean result = cilovka[x1][y1] && cilovka[x2][y2] && cilovka[x3][y3];
    return result;
  }
  
  
  private class Dvoj {
    int xx, yy, xd, yd;
    boolean posun;
    Dvoj prev;
    
    void print() {
      if (prev != null) prev.print();
      if (posun) 
      System.out.printf("%3d%3d  -- %3d%3d %b\r\n", xx, yy , xd, yd, posun);
      
    }
  }
}
