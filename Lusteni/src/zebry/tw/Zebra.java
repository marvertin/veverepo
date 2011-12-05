package zebry.tw;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="mailto:?????.?????@turboconsult.cz">????? ?????</a>
 * @version $Revision: 1 $
 * @see "TW####??????.vjp"
 * @see "$Header: /Home/MV/Other/Cukrarna/Tabulka.java 1 6.04.01 23:09 Veverka $"
 */
public class Zebra {
  private static final EStav ANO = EStav.ANO;

  private static final EStav NE = EStav.NE;

  //private static final int NEVIM = 0;

  private EStav[][][][] mat;

  private int nd, nh;
  
  private final Map<String, DimenzoHodnota> iMapa = new LinkedHashMap<String, DimenzoHodnota>();

  private int iid, iih; // aktuální dimenze a hodnota pøi pøidávání

  private String[][] iNazvy;
  
  public Zebra() {
  }
  
  public Zebra(int nd, int nh) {
    this.nd = nd;
    this.nh = nh;
    mat = new EStav[nd][nh][nd][nh];
    for (int d = 0; d < nd; d++)
      for (int h = 0; h < nh; h++)
        setAno(d, h, d, h);

  }
  
  /**
   * Pøidá hodnotu
   * @param aName
   */
  public void addHodnota(String aName) {
    DimenzoHodnota dh = new DimenzoHodnota();
    dh.iDimenze = iid;
    dh.iHodnota = iih;
    dh.iNazev = aName;
    DimenzoHodnota olddh = iMapa.put(aName, dh);
    if (olddh != null) throw new RuntimeException("Hodnota " + aName + " už existuje");
    
    iih++;
  }
  
  /*
   * Ukonèí zadávání dimenze
   */
  public void finishDimenze() {
    if (iid == 0) { // první ukonèení dimenze
      nh = iih; // tolik kolik hodnot, tolik ji bude
      if (nh == 0) throw new RuntimeException("Nemuze byt nula hodnot");
    } else { // není to poprvé, musíme kontrolovat
      if (nh != iih) throw new RuntimeException("Nespravny pocet hodnot v dimenzi");
    }
    iih=0;
    iid++;
  }
  
  /**
   * Ukonèí zadávání celé tabulky a urèí její velikost
   */
  public void finishTable() {
    this.nd = iid;
    if (nd == 0)  throw new RuntimeException("Nemuze byt nula dimenzi");
    System.out.println("Dimenze: " + nd);
    System.out.println("Hodnoty: " + nh);
    mat = new EStav[nd][nh][nd][nh];
    for (int d = 0; d < nd; d++)
      for (int h = 0; h < nh; h++)
        setAno(d, h, d, h);
    
    iNazvy = new String[nd][nh];
    for (DimenzoHodnota dh : iMapa.values()) {
      iNazvy[dh.iDimenze][dh.iHodnota] = dh.iNazev;
    }
    
  }
  
  
  
  public void setAno(String aJmeno1, String aJmeno2) {
    DimenzoHodnota dh1 = iMapa.get(aJmeno1);
    DimenzoHodnota dh2 = iMapa.get(aJmeno2);
    if (dh1 == null) throw new RuntimeException("Neexistuje: '" + aJmeno1 + "'");
    if (dh2 == null) throw new RuntimeException("Neexistuje: '" + aJmeno2 + "'");
    if (dh1.iDimenze == dh2.iDimenze) throw new RuntimeException(aJmeno1 + " a " + aJmeno2 + " jsou ze stejné dimenze");
    setAno(dh1.iDimenze, dh1.iHodnota, dh2.iDimenze, dh2.iHodnota);
  }

  public void setNe(String aJmeno1, String aJmeno2) {
    DimenzoHodnota dh1 = iMapa.get(aJmeno1);
    DimenzoHodnota dh2 = iMapa.get(aJmeno2);
    if (dh1 == null) throw new RuntimeException("Neexistuje: '" + aJmeno1 + "'");
    if (dh2 == null) throw new RuntimeException("Neexistuje: '" + aJmeno2 + "'");
    if (dh1.iDimenze == dh2.iDimenze) throw new RuntimeException(aJmeno1 + " a " + aJmeno2 + " jsou ze stejné dimenze");
    setNe(dh1.iDimenze, dh1.iHodnota, dh2.iDimenze, dh2.iHodnota);
  }

  
  public void setAno(int id, int ih, int jd, int jh) {
    EStav hod = mat[id][ih][jd][jh];
    if (hod == ANO)
      return; // je už nastaveno, není co øešit
    if (hod == NE)
      throw new RuntimeException("Nelze nastavit ANO, protoze je jiz NE v " + id + " " + ih + " " + jd + " " + jh + " - " + nazev(id,ih) + " : " + nazev(jd,jh));
    mat[id][ih][jd][jh] = ANO; // vlastní nastavení
    setAno(jd, jh, id, ih); // nastavit zrcadlovì obrácený krám
    // Projít vlastní ètvereèek a ostatní nastavit na NE
    for (int h = 0; h < nh; h++) {
      if (h != ih)
        setNe(id, h, jd, jh);
      if (h != jh)
        setNe(id, ih, jd, h);
    }
    // projít tranzitivnosti a postarat se podle toho
    for (int d = 0; d < nd; d++)
      for (int h = 0; h < nh; h++) { // pro sloupec i radek najednou resime
                                      // tranzitivitu

        if (mat[d][h][jd][jh] == ANO)
          setAno(id, ih, d, h);
        if (mat[d][h][jd][jh] == NE)
          setNe(id, ih, d, h);
        if (mat[id][ih][d][h] == ANO)
          setAno(d, h, jd, jh);
        if (mat[id][ih][d][h] == NE)
          setNe(d, h, jd, jh);

      }
  }

  public void setNe(int id, int ih, int jd, int jh) {
    EStav hod = mat[id][ih][jd][jh];
    if (hod == NE)
      return; // je už nastaveno, není co øešit
    if (hod == ANO)
      throw new RuntimeException("Nelze nastavit NE, protoze je jiz ANO v " + id + " " + ih + " " + jd + " " + jh);
    mat[id][ih][jd][jh] = NE; // vlastní nastavení
    setNe(jd, jh, id, ih); // nastavit zrcadlovì obrácený krám
    // Zkontrolovat, zda nezbývá poslední a pokud ano, nastavit, že ano
    int nni = nh;
    int nnj = nh;
    int xxih = -1;
    int xxjh = -1;
    for (int h = 0; h < nh; h++) {
      if (mat[id][h][jd][jh] != NE)
        xxih = h;
      else
        nni--;
      if (mat[id][ih][jd][h] != NE)
        xxjh = h;
      else
        nnj--;
    }
    if (nni == 1)
      setAno(id, xxih, jd, jh);
    if (nnj == 1)
      setAno(id, ih, jd, xxjh);
    // projít tranzitivnost a postart se podle toho
    // projít tranzitivnosti a postarat se podle toho
    for (int d = 0; d < nd; d++)
      for (int h = 0; h < nh; h++) { // pro sloupec i radek najednou resime
                                      // tranzitivitu

        if (mat[d][h][jd][jh] == ANO)
          setNe(id, ih, d, h);
        if (mat[id][ih][d][h] == ANO)
          setNe(d, h, jd, jh);

      }
  }

  // Tisknutí
  private String proTisk(EStav hod) {
    return (hod == ANO ? "O" : hod == NE ? "x" : ".") + " ";
  }

  public void print() {
    // prvni radek hlavicky
    System.out.print("              ");
    for (int id = 0; id < nd; id++) {
      for (int ih = 0; ih < nh; ih++) {
        System.out.print(id);
        System.out.print(" ");
      }
      System.out.print("  ");
    }
    System.out.println();
    // druhy radek hlavicky
    System.out.print("              ");
    for (int id = 0; id < nd; id++) {
      for (int ih = 0; ih < nh; ih++) {
        System.out.print(ih);
        System.out.print(" ");
      }
      System.out.print("  ");
    }

    System.out.println();
    System.out.println();
    // názvy
    boolean jedem = true;
    for (int ic=0; jedem ;ic++) {
      jedem = false;
      System.out.print("              ");
      for (int id = 0; id < nd; id++) {
        for (int ih = 0; ih < nh; ih++) {
          String nazev = nazev(id, ih);
          if (ic < nazev.length()) {
            System.out.print(nazev.charAt(ic));
            jedem = true;
          } else {
            System.out.print(" ");
          }
          System.out.print(" ");
        }
        System.out.print("  ");
      }
      System.out.println();
    }

    

    // vlastni matice
    for (int id = 0; id < nd; id++) {
      for (int ih = 0; ih < nh; ih++) {
        System.out.printf("%d%d %-10s ", id, ih, nazev(id, ih));
        for (int jd = 0; jd < nd; jd++) {
          for (int jh = 0; jh < nh; jh++)
            System.out.print(proTisk(mat[id][ih][jd][jh]));
          System.out.print("  ");
        }
        System.out.println();
      }
      System.out.println();
    }
  }

  /**
   * @param id
   * @param ih
   * @return
   */
  private String nazev(int id, int ih) {
    if (! checkNazevExistence(id,ih)) return "?";
    
    return iNazvy[id][ih];
  }
  private boolean checkNazevExistence(int id, int ih) {
    if (iNazvy == null) return false;
    if (iNazvy.length <= id) return false;
    if (iNazvy[id] == null) return false;
    if (iNazvy[id].length <= ih) return false;
    if (iNazvy[id][ih] == null) return false;
    return true;
    
  }
  

  private static class DimenzoHodnota {
    public String iNazev;
    private int iDimenze;
    private int iHodnota;

    
  }
}
