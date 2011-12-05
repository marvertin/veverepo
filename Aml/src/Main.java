

public class Main {

  private static Black black;

  private static void porovnej(String... slova) {
    black.porovnej(slova);
    //System.out.printf("%d: %s%n", pocet, Arrays.asList(slova));
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    black = new Black();
    black.addVeta("abcd", "efgh", "ijkl", "mnop", "qrst", "uvwx");

//    black.addVeta("martin", "veverka", "marsov", "blbovmars", "marvertin");
//    black.addVeta("helena", "veverkova", "senitce");
//    black.addVeta("nejaky", "blbec");
//    black.addVeta("jedna", "dva", "tri", "ctyri");
//    black.addVeta("toto", "mame", "pitomce");

    black.compute();


    porovnej("aaa", "bbv", "ccccccc");
    porovnej("tomaretinka", "jaed", "vev");
    porovnej("marso");
    porovnej("ver");
    porovnej("veverka");
    porovnej("xmartinec");
    porovnej("blbovmarsov", "rti");
  }

}
