
public class VsechnyZNakyUnicode {


  private static final int NARADKU = 64;

  public static void main(final String[] args) {

    System.out.printf("<table>");
    for (int i = 0; i < 256 * 256 + 1024 * 1024; i+=NARADKU) {
      System.out.printf("<tr><td style='color : green; padding-right: 20px'>%h</td>", i);
      for (int j = i; j < i + NARADKU; j++) {
        System.out.printf("<td>&#x%h;<td>", j);
      }
      System.out.println("<tr>");
    }
    System.out.printf("</table>");
  }
}
