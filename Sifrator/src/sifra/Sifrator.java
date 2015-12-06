package sifra;

public class Sifrator {

  private String KLIC ="hur� pr�zdniny";
  private String TEXT = "D�rek ke dni d�t� hledej tam, kde se ud� maso.";
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
    System.out.println("Kl��:         " + sifra.getKl��());
    System.out.println("Abeceda:      " + sifra.getAbeceda());
    System.out.println("Kl��ovka:     " + sifra.getKlicovka());
    System.out.println("Za�ifrov�no:  " + x);
    System.out.println("Roz�ifrov�no: " + y);
    System.out.println("----------------------------------");
  }
  private void delej() {
    zpracuj(TEXT);
    zpracuj("P��li� �lu�ou�k� k�� �p�l ��belsk� �dy.");
    zpracuj("Vytvo��me p�ek�kovou dr�hu.");
  }

}
