import java.util.Map;
import java.util.Set;


public class Dka {

  DkaUzel start;

  Map<Veta, Set<Slovo>> porovnej(String text) {

    DkaUzel uzel = start;

    for (char c : text.toCharArray()) {
      uzel = uzel.prechody[c - 'a'];
      if (uzel == null) return null; // uz to dal nejde
    }
//    System.err.println("proslo: " + text);
    if (uzel.konecny) {
//      System.err.println("konecny: " + uzel);
      return uzel.vety;
    } else
      return null;

  }
}
