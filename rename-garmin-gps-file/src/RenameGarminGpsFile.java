import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenameGarminGpsFile {

  private static String[] mesiceCz = {
      "LED",
      "ÚNR",
      "BØE",
      "DUB",
      "KVÌ",
      "ÈRN",
      "ÈRC",
      "SRP",
      "ZÁØ",
      "ØÍJ",
      "LIS",
      "PRO",
  };

  private static String[] mesiceEn= {
      "JAN",
      "FEB",
      "MAR",
      "APR",
      "MAY",
      "JUN",
      "JUL",
      "AUG",
      "SEP",
      "OCT",
      "NOV",
      "DEC",
  };


  public void rename(final Path path) {
    renamuj(path, mesiceCz);
    renamuj(path, mesiceEn);
  }

  private void renamuj(final Path path, final String[] mes) {
    try {
      final String ff = path.getFileName().toString();
      //System.out.println(ff);

      final Pattern reg = Pattern.compile("(.*)(\\d\\d)-(...)-(\\d\\d)(.*)");
      final Matcher m = reg.matcher(ff);
      if (m.matches()) {
        if (Arrays.asList(mes).contains(m.group(3))) {
          final String s = String.format("%s20%s-%02d-%s%s",
              m.group(1),
              m.group(4),
              Arrays.asList(mes).indexOf(m.group(3)) + 1,
              m.group(2),
              m.group(5));
          System.out.println(ff + " --> " + s);
          Files.move(path, path.getParent().resolve(s));
        }

        //System.out.println("\"" + m.group(1) + "\",");
      }
    } catch (final Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void delej(final Path  rootDir) throws IOException {
    Files.walk(rootDir)
    .filter(Files::isRegularFile)
    .forEach(this::rename);
  }



  public static void main(final String[] args) throws IOException {
    new RenameGarminGpsFile().delej(Paths.get(args[0]));


  }
}


