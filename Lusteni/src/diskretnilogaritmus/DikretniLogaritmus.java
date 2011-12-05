/**
 * 
 */
package diskretnilogaritmus;

import java.math.BigInteger;

/**
 * @author veverka
 *
 */
public class DikretniLogaritmus  {


  private final BigInteger DVE_NA_64 = new BigInteger("18446744073709551616");
  private final BigInteger PET = new BigInteger("5");


  //long xHi, xLo;

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  protected void execute(String[] aArgs) throws Exception {

    System.out.println(new BigInteger("123").modPow(new BigInteger("17"), new BigInteger("3233")));
    System.out.println(new BigInteger("855").modPow(new BigInteger("2753"), new BigInteger("3233")));
    //double d1 = new BigInteger("15860584089531798358308118294328202587").doubleValue();
    //double d2 = Math.sqrt(d1);
    //double d3 = d2 / 1e17;
    //    System.out.println("d1: " + d1);
    //    System.out.println("ZPOMALENI: " +d3);




    //BigInteger p    = new BigInteger("251864028266405356799");
    BigInteger p    = new BigInteger("15860584089531798358308118294328202587");
    //
    //BigInteger p    = new BigInteger("15860584089531798358308118294328202587");
    BigInteger powerZUlohy = new BigInteger( "6361196924231058595008858273263807320");


    //vysledek ve tvaru: xxxxABCDEFGHxx...xx

    BigInteger exponent = new BigInteger("11473769387225613271575199155876761324");
    BigInteger power = PET.modPow(exponent, p);
    System.out.println("MODULUS  = " + p);
    System.out.println("BASE     = " + PET);
    System.out.println("EXPONENT = " + exponent);
    System.out.println("POWER    = " + power);
    System.out.println("RozdÌl   = " + power.subtract(powerZUlohy));

    //    öroùSV˝pisy(p, ocek, 112233445500000000L);
    //    öroùSV˝pisy(p, ocekZUlohy, 1000137438953473L);
    öroùSV˝pisy(p, power, 0);
    //3982534882399876
  }

  /**
   * @param p
   * @param power
   * @param zaËÌn·meOd
   */
  private void öroùSV˝pisy(BigInteger p, BigInteger power, long zaËÌn·meOd) {
    System.out.println("prvocislo   = " + p); // s tim se dela modulu
    System.out.println("power       = " + power);
    System.out.println("zaciname od = " + zaËÌn·meOd);
    System.out.println("Ëas zaË·tku = " + System.currentTimeMillis());
    long start = System.currentTimeMillis();
    long vysledek = öroù(p, power, zaËÌn·meOd);
    System.out.println("!!!! MAME VYSLEDEK: " + vysledek);
    long stop = System.currentTimeMillis();
    long trvani = stop - start;
    System.out.println("!!!! Trvalo to    : " + (trvani / 1000) + " sekund!");
    System.out.println("Ëas konce   = " + System.currentTimeMillis());
  }

  private BigInteger dejBigInteger(long aHi, long aLo) {
    BigInteger hi = new BigInteger(aHi + "");
    if (aHi < 0) {
      hi = hi.add(DVE_NA_64);
    }
    BigInteger lo = new BigInteger(aLo + "");
    if (aLo < 0) {
      lo = lo.add(DVE_NA_64);
    }
    //   System.out.println("EEEEEE HI: " + hi);
    //    System.out.println("EEEEEE xLO: " + xLo);
    BigInteger vysl = hi.multiply(DVE_NA_64).add(lo);

    return vysl;

  }

  private long dejHi(BigInteger aValue) {
    BigInteger[] p2 = aValue.divideAndRemainder(DVE_NA_64);
    long hi = p2[0].longValue();
    return hi;
  }

  private long dejLo(BigInteger aValue) {
    BigInteger[] p2 = aValue.divideAndRemainder(DVE_NA_64);
    long lo = p2[1].longValue();
    return lo;
  }


  private long öroù(BigInteger p, BigInteger aOcek, long aZacniOd) {
    long pHi, pLo;
    long ocekHi, ocekLo;
    pHi = dejHi(p);
    pLo = dejLo(p);
    ocekHi = dejHi(aOcek);
    ocekLo = dejLo(aOcek);
    //    System.out.println("P:    " + pHi + " " + pLo);
    //    System.out.println("OCEK: " + ocekHi + " " + ocekLo);
    // vypoËet poË·tku
    BigInteger zacatecni = PET.modPow(BigInteger.valueOf(aZacniOd), p);
    long xHi = dejHi(zacatecni);
    long xLo = dejLo(zacatecni);

    long startReseni = System.currentTimeMillis();
    long startEtapy = startReseni;
    int pocitadloRovnostiPriPorovnavan = 0;
    int vypisovaciCitac = 0;
    for (long exponent=aZacniOd+1;;exponent++) {
      long prenos = (xLo >> 62) & 3;
      long prvniLo = xLo;
      long puvodHi = xHi;
      xLo <<= 2;
      long druheLo = xLo;
      //System.out.println("POSUN: " + ss + " " + xLo + " prenos = " + prenos + "  urez = " + urez);
      xHi <<= 2;
      xHi += prenos;
      // dokonceno nasoveni ctyrmi
      // prenosy k scitani
      // a ted pricteme
      xLo = prvniLo + druheLo;
      xHi += puvodHi;
      long vysledekLo = xLo;
      // preteceni
      boolean horni1 = prvniLo < 0;
      boolean horni2 = druheLo < 0;
      if (horni1 && horni2) {
        xHi++;
      } else if (horni1 || horni2) {
        long posun = vysledekLo < 0 ? 0 : 1;
        //        System.out.println(posun);
        //    posun = 1;
        //        System.out.println("POCITAM: prvniLo: " + prvniLo+ " druheLo: " + druheLo+ " vysledekLo: " + vysledekLo) ;
        //        System.out.println("SUNU " + posun + " horni1: " + horni1+ " horni2: " + horni2) ;
        xHi += posun;
      }
      // tak a teÔ otestovat, zda nenÌ vÏtöÌ neû prvoËÌslo, a kdyû je odeËÌst ho
      if (xHi >= pHi) { // je moûnÈ, ûe jsme p¯ekroËili
        boolean odecitame;
        do {
          if (xHi >= pHi) { // je moûnÈ, ûe jsme p¯ekroËili
            //System.out.println("PREKROCENI " + i);
            odecitame = xHi > pHi; // takze vime, ze budeme odecitat
            if (!odecitame) { // vime, ze se rovnaly a ze musime porovnat spodni, ale pozor na znamenko
              //              // malo pravdepodobne, takze pouzijeme pomalou, ale bezpecnou cestu
              //              if (xLo < 0) {
              //                if (pLo < 0) {
              //                  odecitame = xLo >= pLo;
              //                } else {
              //                  odecitame = true;
              //                }
              //              } else {
              //                if (pLo < 0) {
              //                  odecitame = false;
              //                } else {
              //                  odecitame = xLo >= pLo;
              //                }
              //              }
              odecitame = dejBigInteger(xHi, xLo).compareTo(dejBigInteger(pHi, pLo)) > 0;
              //              if (odecitame != odecitame2) {
              //                System.out.println("ODECITACKA:  [" + xHi + "," + xLo + "] " +  odecitame + " " + odecitame2 );
              //                System.out.println("ODECITACKA:  [" + pHi + "," + pLo + "] " +  odecitame + " " + odecitame2 );
              //                System.out.println();
              //              }
              pocitadloRovnostiPriPorovnavan ++;
            }
            if (odecitame) { // musÌme odeËÌst, ale s p¯enosem
              boolean prenosdolu;
              long rozdilLo = xLo - pLo;
              if (rozdilLo < 0) {
                prenosdolu = xLo >= 0 || pLo < 0;
              } else { // rozdil je vetsi nebo roven nule
                prenosdolu = xLo >= 0 && pLo < 0;
              }
              xLo = rozdilLo;
              xHi -= pHi;
              if (prenosdolu) {
                xHi --;
              }
            }
          } else {
            odecitame = false;
          }
        } while (odecitame);
      } // odeËÌt·nÌ
      // a teÔ porovnat s oËek·van˝m v˝sledkem
      if (xHi == ocekHi && xLo == ocekLo)
        return exponent;

      if (vypisovaciCitac ++ == 0) {
        long cas = System.currentTimeMillis();
        long trvaniEtapy = cas - startEtapy;
        long trvaniReseni = cas - startReseni;

        System.out.println("EXPONENT " + exponent + " cas etapy = " + (trvaniEtapy /1000) + " S, celkovy cas  = " + (trvaniReseni / 1000) + " S");
        if (pocitadloRovnostiPriPorovnavan > 0) {
          System.out.println("Pocet rovnosti: " + pocitadloRovnostiPriPorovnavan);
          pocitadloRovnostiPriPorovnavan = 0;
        }
        startEtapy = cas;
      }
    }

  }


  public static void main(String[] args) throws Exception {
    new DikretniLogaritmus().execute(args);
  }

}
