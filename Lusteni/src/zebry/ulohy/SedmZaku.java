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

    t.setAno(3, 0, 0, 0); // zelenou ta�ku m� Pavel
    t.setAno(3, 0, 5, 0); // p�semnou pr�ci mu hodnotul pan u�itel Bez�k
    t.setAno(0, 0, 5, 0);
    t.setAno(1, 0, 2, 0); // chlapec z Mal��e chod� do 8.B
    t.setAno(0, 6, 3, 1); // Ota nos� modrou ta�ku
    t.setAno(0, 6, 4, 0); // dostal dvojku
    t.setAno(1, 1, 0, 1); // v Norlech bydl� Tom�
    t.setAno(2, 1, 4, 1); // �ty�ku dostal ��k ze 4.A
    t.setAno(6, 0, 3, 2); // na kole p�ijel majitel hn�d� ta�ky
    t.setAno(4, 2, 2, 2); // jedna minus chlapec ze 6.C
    t.setAno(4, 2, 1, 2); // ze �t�tic
    t.setAno(2, 3, 5, 1); // v 8.A opravovala Jahelkov�
    t.setAno(5, 2, 2, 4); // �ebelkov� p�semku pro 7.A
    t.setAno(5, 2, 0, 2); // psal ji i Ond�ej
    t.setAno(6, 1, 5, 3); // s Bratrem na motocyklu se p�ivezl ��k, kter�mu dal
                          // C�sa�
    t.setAno(6, 1, 4, 3); // jedni�ku
    t.setAno(3, 3, 1, 3); // oran�ovou ta�ku m� chlapec z Kukl�ku
    t.setAno(0, 3, 6, 2); // Mirek jezd� Vlakem
    t.setAno(1, 4, 6, 3); // chlapce z Bu�an p�ivezl Otec osobn� amutem
    t.setAno(4, 4, 0, 4); // na dv� minus byla ohodnocena Va�kova p�semn� pr�ce
    t.setAno(1, 5, 5, 4); // ��k z HAvl��kova m�l straxh z p�semky od Vachov�
    t.setAno(3, 4, 2, 5); // jedin� �ern� ta�ka se vyskytuje v 7.B
    t.setAno(4, 5, 1, 6); // p�tku dostal chlapecz Tan��na
    t.setAno(0, 5, 3, 5); // Jirku s jeh fialovou ta�kou
    t.setAno(0, 5, 6, 4); // p�ivezl soused n�kla��kem
    t.setAno(2, 6, 6, 5); // ��k 6.B chod� do �koly p�ky
    t.setAno(4, 6, 5, 5); // jeden z chlapc� dostal trojku od Doubkov�
    t.setAno(5, 6, 3, 6); // pan u�itel zv�dav na v�sledke se �lutou ta�kou

    t.setNe(3, 3, 0, 3); // zkou��m varianty

    t.print();
  }
}
