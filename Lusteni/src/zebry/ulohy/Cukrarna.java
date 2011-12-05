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

    
    
    t.addHodnota("Maru�");
    t.addHodnota("Iva");
    t.addHodnota("Eva");
    t.addHodnota("Sl�vina");
    t.addHodnota("Jaru�");
    t.addHodnota("Olina");
    t.addHodnota("Pavl�na");
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
    t.addHodnota("p�te�");
    t.addHodnota("�lu�n�k");
    t.addHodnota("cukrovka");
    t.addHodnota("vystlak");
    t.addHodnota("hlava");
    t.addHodnota("hluchota");
    t.addHodnota("n�ztlak");
    t.finishDimenze();

    t.addHodnota("nic");
    t.addHodnota("preso");
    t.addHodnota("v�de�");
    t.addHodnota("turek");
    t.addHodnota("irsk�");
    t.addHodnota("al��r");
    t.addHodnota("s ml�kem");
    t.addHodnota("kapu�ino");
    t.finishDimenze();

    t.addHodnota("soda");
    t.addHodnota("tonik");
    t.addHodnota("miner�lka");
    t.addHodnota("d�us");
    t.addHodnota("fanta");
    t.addHodnota("lift");
    t.addHodnota("kiwi");
    t.addHodnota("kola");
    t.finishDimenze();

    
    t.addHodnota("sachr");
    t.addHodnota("v�trn�k");
    t.addHodnota("malakov");
    t.addHodnota("kokoska");
    t.addHodnota("rol�da");
    t.addHodnota("ovocn�");
    t.addHodnota("poh�r");
    t.addHodnota("diav�ne�ek");
    t.finishDimenze();

    t.addHodnota("bro�");
    t.addHodnota("n�ramek");
    t.addHodnota("prsten");
    t.addHodnota("snub�k");
    t.addHodnota("3 prsteny");
    t.addHodnota("�et�zek");
    t.addHodnota("kor�lky");
    t.addHodnota("briiant");
    t.finishDimenze();

    t.addHodnota("fialov�");
    t.addHodnota("hn�d�");
    t.addHodnota("�ed�");
    t.addHodnota("zelen�");
    t.addHodnota("pepito");
    t.addHodnota("�ern�");
    t.addHodnota("b�ov�");
    t.addHodnota("b�l�");
    t.finishDimenze();
    t.finishTable();
    
    t.setAno("preso", "bro�"); // k�vu preso si poru�ila d�ma s bro��,
    t.setAno("bro�", "v4"); // kter� m�la 4 vnuky
    t.setAno("Maru�", "v�de�"); // Maru� si dala v�de�skou k�vu
    t.setAno("Maru�", "sachr"); // a dort sachr
    t.setAno("revma", "v�trn�k"); // d�mu, kterou zlobil revmatismus si pochutnala na
                          // v�trn�ku
    t.setAno("Iva", "fialov�"); // Iva p�i�la ve fialov�ch �atech
    t.setAno("Iva", "soda"); // a pila sodu
    t.setAno("malakov", "hn�d�"); // Dort Malakov m�la d�ma v hn�d�ch �atech
    t.setAno("hn�d�", "p�te�"); // kter� m�la pot�e s kr�n� p�te��
    t.setAno("tonik", "�ed�"); // Tonik pila d�ma v �ed�ch �atech
    t.setNe("tonik", "bro�"); // ta v�ak nem�la bro�
    t.setNe("tonik", "�lu�n�k"); // nest�ovala si na �lu�n�k
    t.setAno("zelen�", "miner�lka"); // D�ma v zelen�ch �atech pila neslazenou miner�lku
    t.setAno("zelen�", "cukrovka"); // proto�e m�la cukrovku
    t.setAno(3, 3, 4, 3); // Tureckou k�vu a d�us
    t.setAno(3, 3, 1, 5); // si objednala babi�ka p�ti vnou�at
    t.setAno(0, 2, 3, 4); // Eva pila irskou k�vu
    t.setAno(0, 3, 6, 1); // Sl�vina m�la kr�sn� n�ramek
    t.setAno(5, 3, 7, 4); // Na kokosce si pochutn�vala d�ma v pepito �atech
    t.setAno(7, 4, 1, 7); // kter� se pi�nila sedmi vnuky
    t.setAno(5, 4, 3, 5); // Jedna z dam konzumovala rol�du a al��rskou k�vu.
    t.setAno(6, 2, 4, 4); // Prsten s rub�nem m�la d�ma, kter� pila fantu
    t.setAno(6, 2, 1, 3); // a kter� se chlubila fotografiemi sv�ch t�� vnou�at
    t.setAno(3, 6, 2, 2); // K�vu s ml�kem si objednala d�ma s nemocn�m
                          // �lu�n�kem
    t.setAno(5, 5, 6, 3); // Ovocn� �ez si dala d�ma, kter� m�la pouze snubn�
                          // prsten
    t.setNe(5, 5, 7, 5); // a nem�la �ern� �aty
    t.setAno(0, 4, 1, 2); // Jaru� m�la dv� vnou�ata
    t.setAno(0, 4, 6, 4); // a t�i prsteny
    t.setNe(3, 0, 2, 4); // K�vu nepila d�ma s vysok�m krevn�m tlakem
    t.setAno(2, 4, 5, 6); // ale vynahradila si to �leha�kov�m poh�rem
    t.setAno(2, 5, 1, 1); // Bolestmi hlavy trp�la d�ma s jedn�m vnou�etem
    t.setAno(0, 5, 4, 5); // Olina pop�jela limon�du lift.
    t.setAno(7, 6, 6, 5); // B�ov� �aty a �et�zek se srd��kem m�la d�ma
    t.setAno(7, 6, 4, 6); // kter� si vybrala limon�du kiwi
    t.setAno(7, 7, 1, 0); // D�ma v b�l�ch �atech nem�la dosud ��dn� vnou�e
    t.setNe(7, 7, 3, 5); // a nevybrala si al��rskou k�vu
    t.setAno(3, 7, 6, 6); // Kapu�ino pila d�ma s kor�lky
    t.setAno(5, 7, 1, 6); // Na diav�ne�ku si pochutn�vala babi�ka �esti vnou�at
    t.setAno(5, 7, 2, 3); // (a m�la tedy cukrovku)
    t.setAno(0, 6, 7, 5); // Pavl�na p�i�la v �ern�ch �atech
    t.setAno(0, 6, 2, 6); //   a st�ovala si na nedosl�ch�n�
    t.setAno(4, 7, 2, 7); //Kolu pila d�ma s n�zk�m krevn�m tlakem,
    t.setAno(4, 7, 6, 7); //   kter� m�la briliantov� prsten
    
    t.setNe("Maru�", "v2");

    t.print();
  }
}
