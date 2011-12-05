package zebry.ulohy;

import zebry.tw.Zebra;

/**
 * This class can take a variable number of parameters on the command line.
 * Program execution begins with the main() method. The class constructor is not
 * invoked unless an object of type 'Class1' created in the main() method.
 */
public class GC2J1X9_MysteryEvent {
  /**
   * The main entry point for the application.
   * 
   * @param args
   *                Array of parameters passed to the application via the
   *                command line.
   */
  public static void main(String[] args) throws Exception {
    Zebra t = new Zebra();

    t.addHodnota("zidle1");
    t.addHodnota("zidle2");
    t.addHodnota("zidle3");
    t.addHodnota("zidle4");
    t.addHodnota("zidle5");
    t.finishDimenze();
    
    
    t.addHodnota("Rotis");
    t.addHodnota("Skinfax");
    t.addHodnota("Wonx");
    t.addHodnota("Tiniska");
    t.addHodnota("Malex");
    t.finishDimenze();

    t.addHodnota("d1");
    t.addHodnota("d2");
    t.addHodnota("d3");
    t.addHodnota("d4");
    t.addHodnota("d5");
    t.finishDimenze();

    t.addHodnota("tradicka");
    t.addHodnota("multina");
    t.addHodnota("mysterka");
    t.addHodnota("earthka");
    t.addHodnota("wherigo");
    t.finishDimenze();

    t.addHodnota("Garmin");
    t.addHodnota("Magellan");
    t.addHodnota("DreimGo");
    t.addHodnota("Prestigo");
    t.addHodnota("Holux");
    t.finishDimenze();

    t.addHodnota("kompas");
    t.addHodnota("pivo");
    t.addHodnota("vykricnik");
    t.addHodnota("parnik");
    t.addHodnota("jester");
    t.finishDimenze();


    t.finishTable();

    t.setAno("Rotis", "d1"); //          01) Rotis lustil obtiznost jedna.
    t.setAno("Skinfax", "tradicka"); //  02) Skinfax lovi nejradsi tradicky.
    t.setAno("Wonx", "Magellan"); //     03) Wonx ma GPS navigaci znacky Magellan.
    t.setAno("Tiniska", "zidle1"); //    04) Tiniska sedel na prvni zidli (uplne vlevo).
    t.setAno("Malex", "kompas"); //      05) Malex ma CWG s logem kompasu.
    t.setAno("pivo", "DreimGo"); //      06) Kacer, ktery ma na CWG logo piva, vlastni GPS navigaci znacky DreimGo.
    t.setNe("Tiniska", "d5"); //         07) Tiniska sedel vedle kacera lusticiho obtiznost 5.
    t.setAno("d5", "zidle2");
    t.setAno("vykricnik", "earthka"); // 08) Ten, kdo ma na CWG logo s vykricnikem, lovi nejradsi earthky.
    t.setAno("zidle3", "multina"); //    09) Kacer, jez sedel uprostred, lovi nejradsi multiny.
    t.setNe("Prestigo", "parnik"); //    10) Kacer s GPS navigaci znacky Prestigo sedel vedle kacera s parnikem na CWG.
    t.setAno("Prestigo", "zidle2");
    //t.setNe("d2", "d4"); //            11) Kacer, ktery lustil obtiznost dva, sedel bezprostredne nalevo od toho, ktery lustil obtiznost ctyri.
    t.setAno("d2", "zidle4"); 
    t.setAno("d4", "zidle5"); 
    
    t.setAno("d2", "wherigo"); //        12) Ten, kdo lustil obtiznost dva, lovi nejradsi wherigovky.
    t.setAno("d3", "parnik"); //         13) Ten, kdo lustil obtiznost tri, ma na CWG logo s parnikem.
    t.setNe("jester", "Holux"); //       14) Kacer, ktery ma na CWG logo s jesterem, sedel vedle vlastnika GPS navigace znacky HOLUX.
    t.setAno("Holux", "zidle1");
    t.setNe("jester", "mysterka"); //    15) Kacer s jesterem na CWG sedel vedle toho, ktery nejradsi lovi mysterky.
    t.setAno("jester", "zidle2");

    t.print();
  }
}
