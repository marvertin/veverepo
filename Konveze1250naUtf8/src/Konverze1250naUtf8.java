import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Set;
import java.util.TreeSet;


public class Konverze1250naUtf8 {

  private static Set<String> pripony = new TreeSet<String>();

  private static int pocet;

  public static void main(String[] args) throws IOException {

    System.out.println("Konverze1250naUtf8");

    File srcDir = new File("c:/gc/geokuk/vyvoj/Geokuk/src");
    File destDir = new File("c:/git/geokuk/Geokuk/src");

    konvertuj(srcDir, destDir);
    System.out.println("Konverotvano: " + pocet);
    System.out.println("Nekonvertovane pripony: " + pripony);
  }

  private static void konvertuj(File srcFord, File destFord) throws IOException {
    if (srcFord.isDirectory()) {

      for (String name : srcFord.list()) {
        File sford = new File(srcFord, name);
        File dford = new File(destFord, name);
        konvertuj(sford, dford);
      }
    } else {
      konvertujSoubor(srcFord, destFord);
    }
  }

  private static void konvertujSoubor(File srcFord, File destFord) throws IOException {
    String name = srcFord.getName();
    int poz = name.lastIndexOf(".");
    if (poz < 0) {
      System.err.println("Bezteèkový subor: " + srcFord);
      return;
    }

    String ext = name.substring(poz);

    if (dovolenaPripona(ext)) {
      destFord.getParentFile().mkdirs();
      Writer wrt = new OutputStreamWriter(new FileOutputStream(destFord),"UTF8");
      Reader rdr = new InputStreamReader(new FileInputStream(srcFord), "CP1250");
      char[] buf = new char[10000000];
      int len = rdr.read(buf);
      wrt.write(buf, 0, len);
      rdr.close();
      wrt.close();
      pocet++;

    } else {
      pripony.add(ext);

    }

  }

  private static boolean dovolenaPripona(String ext) {
    return ext.equals(".java");
  }


}
