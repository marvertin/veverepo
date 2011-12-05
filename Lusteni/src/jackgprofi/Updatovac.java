package jackgprofi;

import java.util.List;

import javax.swing.SwingWorker;

public class Updatovac extends SwingWorker<Void, String> {


  static int[] prvoci = new int[500];
  static int[] xx = {17,241,233,61,227,73,31,53,71,11,43,167,67,47,151,101,13,173,241,43,67,167,17,
    53,47,73,227,13,61,151,71,11,3,107,137,7,23,2,157,43,53,41,157,43,173,2,227,73,
    53,107,3,31,13,7,37,103,241,163,131,5,167,233,17,131,241,37,103,163,17,167,5,
    227,13,101,2,41,137,157,2,73,23,7,37,103,107,241,211,131,127,113,157,211,23,17,
    53,163,113,107,71,61,101,31,41,241,37,103,163,53,31,127,23,131,101,41,137,151,
    71,17,131,23,47,67,5,73,2,241,3,137,61,223,151,7,103,67,73,47,2,241,3,151,7,37,
    11,107,41,227,73,167,41,107,223,11,13,101,61,151,17,2,241,3,71,31,53,241,131,37,
    103,3,53,227,101,7,2,17,5,73,13,61,71,23,167,31,41,163,151,37,11,47,131,67,17,
    31,7,103,5,23,241,71,53,167,5,23,47,157,127,167,67,17,53,163,41,151,11,71,31,113,
    211,107,23,157,211,17,53,31,71,163,107,113,61,101,41,127,131,73,41,163,103,7,31,
    53,61,241,37,71,2,17,101,157,131,5,167,157,2,61,37,71,41,241,7,227,101,103,227,
    73,101,41,61,23,5,127,167,211,113,71,107,157,107,211,131,61,37,113,127,151,241,
    157,137,103,137,151,101,13,2,211,43,167,67,5,47,23,131,37,103,71,131,211,37,211,
    131,37,173,41,233,137,61,37,103,71,31,3,107,47,131,211,157,5,167,67,53,13,23,43,
    3,233,41,2,137,31,53,173,107,101,137,137,7,157};
  private final JMatice jMatice;

  static {
    int k = 0;
    for (int p =2; k < prvoci.length; p++) {
      boolean jeprcox = true;
      for (int i = 2; i<p; i++) {
        if (p % i == 0) {
          jeprcox = false;
        }
      }
      if (jeprcox) {
        prvoci[k++] = p;
      }

    }
    for (int prv : prvoci) {
      System.out.print(prv + " ");
    }
    System.out.println();
    System.out.println("----------");

  }

  public Updatovac(JMatice jMatice) {
    this.jMatice = jMatice;

  }

  @Override
  protected Void doInBackground() throws Exception {
    try {
      for (int nnn=3; nnn<64; nnn++) {
        System.out.println(nnn + " " + xx.length);
        jMatice.clear(nnn);
        for (int element : xx) {
          //for (int j = 0; j<256; j++) {
          int j = 0;
          while (true) {
            if (element == prvoci[j]) {
              break;
            }
            j++;
          }
          jMatice.mat[j] = ! jMatice.mat[j];
          jMatice.mat[j] = true;
          //Thread.sleep(50);
          publish("x");
        }
        Thread.sleep(3000);
        System.out.println(nnn);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }


  @Override
  protected void process(List<String> arg0) {
    // TODO Auto-generated method stub
    super.process(arg0);
    jMatice.repaint();
  }

  //  public static void main(String[] args) {
  //    for (int x : xx) {
  //      for (int i = 2; i<x; i++) {
  //        if (x % i == 0) {
  //          System.out.println("Nen prvocislo: " + x);
  //        }
  //      }
  //    }
  //  }
}
