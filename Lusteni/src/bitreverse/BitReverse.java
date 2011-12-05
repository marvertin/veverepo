/**
 * 
 */
package bitreverse;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author veverka
 *
 */
public class BitReverse  {

  /* (non-Javadoc)
   * @see cz.tconsult.tw.app.AppBase#execute(java.lang.String[])
   */
  protected void execute(String[] aArgs) throws Exception {
    @SuppressWarnings("unused")
    File in = new File(aArgs[0]);
    File out = new File(aArgs[0] + ".psid");

    FileOutputStream fos = new FileOutputStream(out);
    // TODO vyresit cteni souboru
    //byte[] bb = FileManager.getInstance(1000000).readWholeFileAsBytes(in);
    byte[] bb = new byte[]{0};
    for (int i= bb.length -1; i >=0; i--) {
      int b = bb[i];
      b = b & 0xFF;
      System.out.printf("%h\n", b);
      int x = 0;
      for (int j =0; j <8; j++) {
        int bit = b % 2;
        x = x << 1;
        b = b >> 1;
        x =  (x | bit);
        System.out.printf("    %d - %h - %h\n", bit, b, x);
      }
      fos.write(x);
    }
    fos.close();
  }


  public static void main(String[] args) throws Exception {
    new BitReverse().execute(args);
  }
}
