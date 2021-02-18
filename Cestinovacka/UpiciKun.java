

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UpiciKun {

  final String CSS ="<style> td,th { border: 1px solid gray; padding: 3px; border-collapse: collapsed; text-align: center; vertical-align: top;} "
	  + "th { background-color: lightgray; } "
      + "table { border-collapse: collapse; } "
      + ".hexa { color: blue; font-size: 10; }"
      + ".spravny { background: #ffc; }"
      + "</style>";



  final String H = "Příliš žluťoučký kůň úpěl ďábelské ódy.";


  private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
  private static final Charset ISO_8859_2 = Charset.forName("ISO-8859-2");
  private static final Charset WINDOWS_1252 = Charset.forName("windows-1252");
  private static final Charset WINDOWS_1250 = Charset.forName("windows-1250");
  private static final Charset IBM_850 = Charset.forName("IBM850");
  private static final Charset IBM_852 = Charset.forName("IBM852");
  private static final Charset IBM_437 = Charset.forName("IBM437");
  private static final Charset UTF8 = Charset.forName("utf-8");
  static final Charset UTF16 = Charset.forName("utf-16");
  static final Charset UTF16le = Charset.forName("utf-16le");
  static final Charset UTF16be = Charset.forName("utf-16be");


  private static final Charset[] CHARSETY = new  Charset[] {WINDOWS_1250, ISO_8859_2, WINDOWS_1252, ISO_8859_1, IBM_852, IBM_850, IBM_437};
  private PrintWriter o;

  //private static final String PISMENA = "áäéěíóôöőúůüűžščřďťňĺľÁÄÉĚÍÓÔÖŐÚŮÜŰŽŠČŔŘĎŤŇĹĽ";
  private static final String PISMENA = "áäéěíóôúůýĺŕžščřďťňľÁÄÉĚÍÓÔÚŮÝĹŔŽŠČŘĎŤŇĽ";

  void wr(final byte [] b) throws IOException {
    o.write(new String(b, UTF8));
  }

  void wr(final Object s) throws IOException {
    o.println(s.toString());
  }


  void br() throws IOException {
    wr("<br>");
    nl();
  }

  void nl() throws IOException {
    o.println();
  }




  private void zahlaviRadku2(final Object slp1, final Object slp2, boolean jakoZahlavi) throws IOException {
    wr("<tr>");
    String znacka =  jakoZahlavi ? "th>" : "td>";
    wr("<"+ znacka + doStringu(slp1) +"</" + znacka);
    wr("<"+ znacka + doStringu(slp2) + "</" + znacka);
  }



  private void patickaRadku() throws IOException {
    wr("</tr>");
    nl();
  }


  void radekPismenJinakChapat(final Charset zadano, final Charset chapano) throws IOException {

    boolean zahlavni = zadano.equals(chapano);
	if (zahlavni) {
      zahlaviRadku2(zadano, null, true);
    } else {
      zahlaviRadku2(null, chapano, false);
    }
    for (final char c : PISMENA.toCharArray()) {
      wr(zahlavni ? "<th>" : "<td>");
      //final byte[] bb = (c + "").getBytes(zadano);
      //final String ss = new String(bb, chapano);
      final Osmibit bbb = new Unikod(c).encode(zadano);
      final Unikod ss = bbb.decode(chapano);
      //final Text txt = new Text(c + "", zadano).recode(chapano, UTF8);
      wr(ss);br();
      if (zahlavni) {
        wr("<span class='hexa'>");
        wr(bbb);
        wr("</span>");
      }
      wr(zahlavni ? "</th>" : "</td>");
    }
    patickaRadku();
  }


  void radekPismenUtf8DvakratChapat(final Charset chapano1, final Charset chapano2) throws IOException {

    zahlaviRadku2(chapano1, chapano2, true);
    for (final char c : PISMENA.toCharArray()) {
      wr("<td>");
      final Osmibit bbb = new Unikod(c).encode(UTF8);
      final Osmibit ccc = bbb.decode(chapano1).encode(UTF8);
      final Unikod ss = ccc.decode(chapano2);
      //final Text txt = new Text(c + "", zadano).recode(chapano, UTF8);
      wr(ss);br();
      wr("<span class='hexa'>");
      wr(ccc);
      wr("</span>");
      wr("</td>");
    }
    patickaRadku();
  }

  void zahlavniUtf8Radek(int colspanPrvnihoSLoupce) throws IOException {

	    wr("<tr>");
	    wr("<th colspan=" + colspanPrvnihoSLoupce + ">"+ doStringu(UTF8) +"</th>");
	    for (final char c : PISMENA.toCharArray()) {
	      wr("<th>");
	      final Osmibit utf8 = new Unikod(c).encode(UTF8);
	      wr(c);br();
	      wr("<span class='hexa'>");
	      wr(utf8);
	      wr("</span>");
	      wr("</th>");
	    }
	    patickaRadku();
	  }

  void radekPismenProhnatUtf8(final Charset prohnat) throws IOException {

    wr("<tr>");
    wr("<td>"+ doStringu(prohnat) +"</td>");
    for (final char c : PISMENA.toCharArray()) {
      wr("<td>");
      final Osmibit utf8 = new Unikod(c).encode(UTF8);
      final Osmibit bbb = utf8.decode(prohnat).encode(prohnat);
      final Unikod ss = bbb.decode(UTF8);
      //final Text txt = new Text(c + "", zadano).recode(chapano, UTF8);
      wr(ss);br();
      wr("<span class='hexa'>");
      wr(bbb);
      wr("</span>");
      wr("</td>");
    }
    patickaRadku();
  }

  public void testBijektivity(final Charset ch) {

    final byte[] bb = new byte[256];
    for (int i=0; i<256; i++) {
      bb[i] = (byte) i;
    }


    //for (final Charset ch : CHARSETY) {
    final StringBuilder sb = new StringBuilder();
    try {
      final byte[] po = new String(bb, ch).getBytes(ch);
      int pocetChyb = 0;
      for (int i=0; i<256; i++) {
        if (po[i] != bb[i]) {
          pocetChyb++;
          if (pocetChyb < 20) {
            sb.append(String.format("%h ",i));
          }
          if (pocetChyb == 20) {
            sb.append(" ... ");
          }
        }
      }
      //if (pocetChyb > 0) {
      System.out.println("   " + pocetChyb + " - " + ch);
      zahlaviRadku2(ch, pocetChyb, false);
      wr("<td>" + sb + "</td>");
      patickaRadku();
      //}
    } catch (final Exception e) {
      System.err.println(ch);
    }

  }
  public void vypis(final Path soubor) throws Exception {
    o = new PrintWriter( Files.newBufferedWriter(soubor, UTF8));

    //testBijektivity();


    wr("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
    nl();

    wr(CSS);
    wr("<h1>Jedno kódování chápáno jako jiné kódování</h1>");
    wr("<p>Máme nějaký text v nějakém kódování, my ale nevíme v jakém kódování je, přečteme ho tedy v nějakém nesprávném kódování a podle toho vypadá výsledek.</p>");nl();
    wr("<p>Bílý otazník v černém čtverci postaveném na rohu znamená, že v daném kódování daný byte nemá význam.</p>");nl();
    wr("<p>Hexa kódy jsou jen u záhlavních řádků se skutečným kódováním, v dalších řádcích by byly kódy stejné, jen ten ókd má jiný význam a je to jiný znak.</p>");nl();
    wr("<table border=0>");nl();
    wr("<tr><td>skutečné</td><td>chápáno jako</td></tr>");nl();
    radekPismenJinakChapat(UTF8, UTF8);
    for (final Charset ch2 : CHARSETY) {
        radekPismenJinakChapat(UTF8, ch2);
    }
    for (final Charset ch1 : CHARSETY) {
      radekPismenJinakChapat(ch1, ch1);
      for (final Charset ch2 : CHARSETY) {
        if (! ch1.equals(ch2)) {
          radekPismenJinakChapat(ch1, ch2);
        }
      }
    }
    wr("</table>");nl();

    wr("<h1>UTF-8 chápáno jako jiné kóodování a prohnáno přes UNICODE v Javě</h1>");
    wr("<p>Myslí se tím situace, kdy v Javě čtu nějaký souborv UTF-8 textově, zpracovávám ho a pak zase zapisuji do souboru s tím, že mně nezajímá skutečné kódování,"
    		+ "protože mě nezajímají znaky s diakritikou. Například budu mít nástroj, který odstraní zbytečné mezery na koních řádků.</p>");
    wr("<p>Je vidět, že při použití windowsových kódování dojde k poškození některých znaků, je to dáno tím, že v těchto kódováních nejsou všechny kód pointy využity.</p>");
    wr("<p>Hexa je zde nikoli znak v daném kódování, ale sekvence, na kterou je prohnáním znak pokažen. To pak nejsou validní sekvence UTF8. "
    		+ "Má se to tak, že první znaky UTF-8 sekvence pkaženy nejsou nikdy, ale další jsou převedeny na znak 3F '?', což není validní druhý znak UTF-8 sekvence, ten by měl být 10xxxxxx binárně.</p>");
    wr("<table border=0>");nl();
    zahlavniUtf8Radek(1);nl();
    for (final Charset ch : CHARSETY) {
      radekPismenProhnatUtf8(ch);
    }
    wr("</table>");nl();


    wr("<h1>Bijektivnost konverze 8bit => UTF-16 => 8bit</h1>");
    wr("<p>V tabulce jsou nejsou znaky, ale jen jejich kódy, protože nebijektivní kódy nereprezentují v daném kódování žádný znak.</p>");nl();
    wr("<table border=0><tr><th>Kódování</th><th>Počet</th><th>Nebijektivní kódy</th></tr>");nl();
    for (final Charset ch1 : CHARSETY) {
      testBijektivity(ch1);
    }
    wr("</table>");nl();
    
    
    wr("<h1>UTF-8 dvojnásobně špatně chápáno a překódováno stejným kódováním</h1>");
    wr("<table border=0>");nl();
    wr("<p>Mějme text v UTF8, ale mysleme si, že je v nějakém 8. bitovém kódování,"
    		+ " pochopme ho v tomto kódování a vyjádřeme v UTF8 a pak ještě jednou ho chápejme v tom stejném kódování.</p>");nl();
    zahlavniUtf8Radek(2);nl();
    for (final Charset ch : CHARSETY) {
      		radekPismenUtf8DvakratChapat(ch, ch);
    }
    wr("</table>");nl();

    wr("<h1>UTF-8 dvojnásobně špatně chápáno a překódováno různými kódováními</h1>");
    wr("<table border=0>");nl();
    wr("<p>Mějme text v UTF8, ale mysleme si, že je v nějakém 8. bitovém kódování,"
    		+ " pochopme ho v tomto kódování a vyjádřeme v UTF8 a pak ještě jednou ho chápejme v nějakém jiném 8. bitovém kódování.</p>");nl();
    zahlavniUtf8Radek(2);nl();
    for (final Charset ch1 : CHARSETY) {
        for (final Charset ch2 : CHARSETY) {
        	if (! ch1.equals(ch2)) {
        		radekPismenUtf8DvakratChapat(ch1, ch2);
        	}
        }
    }
    wr("</table>");nl();
    
    o.close();

  }




  private class Unikod {
    private final String s;

    Unikod(final String s) {
      this.s = s;
    }

    Unikod(final char c) {
      this (c + "");
    }

    Osmibit encode(final Charset charset) {
      return new Osmibit(s.getBytes(charset));
    }

    @Override
    public String toString() {
      return s;
    }
  }

  private class Osmibit {
    private final byte[] bb;

    Osmibit(final byte[] bb) {
      this.bb = bb;
    }

    Unikod decode(final Charset charset) {
      return new Unikod(new String(bb, charset));
    }


    @Override
    public String toString() {
      return toHex2digsPerByteSpaceTerminatorLowerCase(bb);
    }

  }

  private static char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  /**
   *
   * @return Například b50fe18490f8a09de5425225616e5945dab3c399
   */
  public static String toHex2digsPerByteNoDelimiterLowerCase(final byte[] bb) {
    if (bb == null) {
      return null;
    }
    final StringBuilder sb = new StringBuilder(bb.length * 2);
    for (final byte b : bb) {
      sb.append(hexDigits[(b & 0xF0) >> 4]);
      sb.append(hexDigits[b & 0x0F]);
    }
    return sb.toString();
  }

  public static String toHex2digsPerByteSpaceTerminatorLowerCase(final byte[] bb) {
    if (bb == null) {
      return null;
    }
    final StringBuilder sb = new StringBuilder(bb.length * 2);
    for (final byte b : bb) {
      sb.append(hexDigits[(b & 0xF0) >> 4]);
      sb.append(hexDigits[b & 0x0F]);
      sb.append(" ");
    }
    return sb.toString();
  }


  private static String doStringu(final Object charset) {
    return  charset == null ? "" : charset.toString().replace('_',  '-');
  }

  public static void main(final String[] args) throws Exception {
    new UpiciKun().vypis(Paths.get("Úpící kůň.html"));
  }

}
