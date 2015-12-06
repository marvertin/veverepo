package sifra;

public class Sifrator {

  private String KLIC ="hurá prázdniny";
  private String TEXT = "Dárek ke dni dìtí hledej tam, kde se udí maso.";
  /**
   * @param args
   */
  public static void main(String[] args) {
    new Sifrator().delej();
  }

  private void zpracuj(String s) {
    Sifra sifra = new Sifra(KLIC);
    String x = sifra.sifruj(s);
    String y = sifra.desifruj(x);
    System.out.println("Klíè:         " + sifra.getKlíè());
    System.out.println("Abeceda:      " + sifra.getAbeceda());
    System.out.println("Klíèovka:     " + sifra.getKlicovka());
    System.out.println("Zašifrováno:  " + x);
    System.out.println("Rozšifrováno: " + y);
    System.out.println("----------------------------------");
  }
  private void delej() {
    zpracuj(TEXT);
    zpracuj("Pøíliš luouèkı kùò úpìl ïábelské ódy.");
    zpracuj("Vytvoøíme pøekákovou dráhu.");
  }

}
