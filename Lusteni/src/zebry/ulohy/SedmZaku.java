package zebry.ulohy;

import zebry.tw.Zebra;

/**
 * This class can take a variable number of parameters on the command line.
 * Program execution begins with the main() method. The class constructor is not
 * invoked unless an object of type 'Class1' created in the main() method.
 */
public class SedmZaku {
  /**
   * The main entry point for the application.
   * 
   * @param args
   *                Array of parameters passed to the application via the
   *                command line.
   */
  public static void main(String[] args) throws Exception {

    Zebra t = new Zebra(7, 7);

    t.setAno(3, 0, 0, 0); // zelenou tašku má Pavel
    t.setAno(3, 0, 5, 0); // písemnou práci mu hodnotul pan uèitel Bezák
    t.setAno(0, 0, 5, 0);
    t.setAno(1, 0, 2, 0); // chlapec z Malíèe chodí do 8.B
    t.setAno(0, 6, 3, 1); // Ota nosí modrou tašku
    t.setAno(0, 6, 4, 0); // dostal dvojku
    t.setAno(1, 1, 0, 1); // v Norlech bydlí Tomáš
    t.setAno(2, 1, 4, 1); // ètyøku dostal žák ze 4.A
    t.setAno(6, 0, 3, 2); // na kole pøijel majitel hnìdé tašky
    t.setAno(4, 2, 2, 2); // jedna minus chlapec ze 6.C
    t.setAno(4, 2, 1, 2); // ze štìtic
    t.setAno(2, 3, 5, 1); // v 8.A opravovala Jahelková
    t.setAno(5, 2, 2, 4); // Šebelková písemku pro 7.A
    t.setAno(5, 2, 0, 2); // psal ji i Ondøej
    t.setAno(6, 1, 5, 3); // s Bratrem na motocyklu se pøivezl žák, kterému dal
                          // Císaø
    t.setAno(6, 1, 4, 3); // jednièku
    t.setAno(3, 3, 1, 3); // oranžovou tašku má chlapec z Kuklíku
    t.setAno(0, 3, 6, 2); // Mirek jezdí Vlakem
    t.setAno(1, 4, 6, 3); // chlapce z Buèan pøivezl Otec osobní amutem
    t.setAno(4, 4, 0, 4); // na dvì minus byla ohodnocena Vaškova písemná práce
    t.setAno(1, 5, 5, 4); // žák z HAvlíèkova mìl straxh z písemky od Vachové
    t.setAno(3, 4, 2, 5); // jediná èerná taška se vyskytuje v 7.B
    t.setAno(4, 5, 1, 6); // pìtku dostal chlapecz Tanèína
    t.setAno(0, 5, 3, 5); // Jirku s jeh fialovou taškou
    t.setAno(0, 5, 6, 4); // pøivezl soused náklaïákem
    t.setAno(2, 6, 6, 5); // žák 6.B chodí do školy pìšky
    t.setAno(4, 6, 5, 5); // jeden z chlapcù dostal trojku od Doubkové
    t.setAno(5, 6, 3, 6); // pan uèitel zvìdav na výsledke se žlutou taškou

    t.setNe(3, 3, 0, 3); // zkouším varianty

    t.print();
  }
}
