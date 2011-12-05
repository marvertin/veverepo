package zebry.ulohy;

import zebry.tw.Zebra;

/**
 * This class can take a variable number of parameters on the command line.
 * Program execution begins with the main() method. The class constructor is not
 * invoked unless an object of type 'Class1' created in the main() method.
 */
public class Cukrarna {
  /**
   * The main entry point for the application.
   * 
   * @param args
   *                Array of parameters passed to the application via the
   *                command line.
   */
  public static void main(String[] args) throws Exception {
    Zebra t = new Zebra();

    
    
    t.addHodnota("Maruš");
    t.addHodnota("Iva");
    t.addHodnota("Eva");
    t.addHodnota("Slávina");
    t.addHodnota("Jaruš");
    t.addHodnota("Olina");
    t.addHodnota("Pavlína");
    t.addHodnota("Anna");
    t.finishDimenze();

    t.addHodnota("v0");
    t.addHodnota("v1");
    t.addHodnota("v2");
    t.addHodnota("v3");
    t.addHodnota("v4");
    t.addHodnota("v5");
    t.addHodnota("v6");
    t.addHodnota("v7");
    t.finishDimenze();

    t.addHodnota("revma");
    t.addHodnota("páteø");
    t.addHodnota("žluèník");
    t.addHodnota("cukrovka");
    t.addHodnota("vystlak");
    t.addHodnota("hlava");
    t.addHodnota("hluchota");
    t.addHodnota("níztlak");
    t.finishDimenze();

    t.addHodnota("nic");
    t.addHodnota("preso");
    t.addHodnota("vídeò");
    t.addHodnota("turek");
    t.addHodnota("irská");
    t.addHodnota("alžír");
    t.addHodnota("s mlékem");
    t.addHodnota("kapuèino");
    t.finishDimenze();

    t.addHodnota("soda");
    t.addHodnota("tonik");
    t.addHodnota("minerálka");
    t.addHodnota("džus");
    t.addHodnota("fanta");
    t.addHodnota("lift");
    t.addHodnota("kiwi");
    t.addHodnota("kola");
    t.finishDimenze();

    
    t.addHodnota("sachr");
    t.addHodnota("vìtrník");
    t.addHodnota("malakov");
    t.addHodnota("kokoska");
    t.addHodnota("roláda");
    t.addHodnota("ovocný");
    t.addHodnota("pohár");
    t.addHodnota("diavìneèek");
    t.finishDimenze();

    t.addHodnota("brož");
    t.addHodnota("náramek");
    t.addHodnota("prsten");
    t.addHodnota("snubák");
    t.addHodnota("3 prsteny");
    t.addHodnota("øetízek");
    t.addHodnota("korálky");
    t.addHodnota("briiant");
    t.finishDimenze();

    t.addHodnota("fialové");
    t.addHodnota("hnìdé");
    t.addHodnota("šedé");
    t.addHodnota("zelené");
    t.addHodnota("pepito");
    t.addHodnota("èerné");
    t.addHodnota("béžové");
    t.addHodnota("bílé");
    t.finishDimenze();
    t.finishTable();
    
    t.setAno("preso", "brož"); // kávu preso si poruèila dáma s broží,
    t.setAno("brož", "v4"); // která mìla 4 vnuky
    t.setAno("Maruš", "vídeò"); // Maruš si dala vídeòskou kávu
    t.setAno("Maruš", "sachr"); // a dort sachr
    t.setAno("revma", "vìtrník"); // dámu, kterou zlobil revmatismus si pochutnala na
                          // vìtrníku
    t.setAno("Iva", "fialové"); // Iva pøišla ve fialových šatech
    t.setAno("Iva", "soda"); // a pila sodu
    t.setAno("malakov", "hnìdé"); // Dort Malakov mìla dáma v hnìdých šatech
    t.setAno("hnìdé", "páteø"); // která mìla potíže s krèní páteøí
    t.setAno("tonik", "šedé"); // Tonik pila dáma v šedých šatech
    t.setNe("tonik", "brož"); // ta však nemìla brož
    t.setNe("tonik", "žluèník"); // nestìžovala si na žluèník
    t.setAno("zelené", "minerálka"); // Dáma v zelených šatech pila neslazenou minerálku
    t.setAno("zelené", "cukrovka"); // protože mìla cukrovku
    t.setAno(3, 3, 4, 3); // Tureckou kávu a džus
    t.setAno(3, 3, 1, 5); // si objednala babièka pìti vnouèat
    t.setAno(0, 2, 3, 4); // Eva pila irskou kávu
    t.setAno(0, 3, 6, 1); // Slávina mìla krásný náramek
    t.setAno(5, 3, 7, 4); // Na kokosce si pochutnávala dáma v pepito šatech
    t.setAno(7, 4, 1, 7); // která se pišnila sedmi vnuky
    t.setAno(5, 4, 3, 5); // Jedna z dam konzumovala roládu a alžírskou kávu.
    t.setAno(6, 2, 4, 4); // Prsten s rubínem mìla dáma, která pila fantu
    t.setAno(6, 2, 1, 3); // a která se chlubila fotografiemi svých tøí vnouèat
    t.setAno(3, 6, 2, 2); // Kávu s mlékem si objednala dáma s nemocným
                          // žluèníkem
    t.setAno(5, 5, 6, 3); // Ovocný øez si dala dáma, která mìla pouze snubní
                          // prsten
    t.setNe(5, 5, 7, 5); // a nemìla èerné šaty
    t.setAno(0, 4, 1, 2); // Jaruš mìla dvì vnouèata
    t.setAno(0, 4, 6, 4); // a tøi prsteny
    t.setNe(3, 0, 2, 4); // Kávu nepila dáma s vysokým krevním tlakem
    t.setAno(2, 4, 5, 6); // ale vynahradila si to šlehaèkovým pohárem
    t.setAno(2, 5, 1, 1); // Bolestmi hlavy trpìla dáma s jedním vnouèetem
    t.setAno(0, 5, 4, 5); // Olina popíjela limonádu lift.
    t.setAno(7, 6, 6, 5); // Béžové šaty a øetízek se srdíèkem mìla dáma
    t.setAno(7, 6, 4, 6); // která si vybrala limonádu kiwi
    t.setAno(7, 7, 1, 0); // Dáma v bílých šatech nemìla dosud žádné vnouèe
    t.setNe(7, 7, 3, 5); // a nevybrala si alžírskou kávu
    t.setAno(3, 7, 6, 6); // Kapuèino pila dáma s korálky
    t.setAno(5, 7, 1, 6); // Na diavìneèku si pochutnávala babièka šesti vnouèat
    t.setAno(5, 7, 2, 3); // (a mìla tedy cukrovku)
    t.setAno(0, 6, 7, 5); // Pavlína pøišla v èerných šatech
    t.setAno(0, 6, 2, 6); //   a stìžovala si na nedoslýchání
    t.setAno(4, 7, 2, 7); //Kolu pila dáma s nízkým krevním tlakem,
    t.setAno(4, 7, 6, 7); //   která mìla briliantový prsten
    
    t.setNe("Maruš", "v2");

    t.print();
  }
}
