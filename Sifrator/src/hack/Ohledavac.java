package hack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Ohledavac implements Runnable {

  private final int od;
  private final int krok;
  
  //private static int citac;
  static PrintWriter  wrt;

  Ohledavac(int aOd, int aKrok) {
    od = aOd;
    krok = aKrok;
  }

  static void otevri() throws IOException {
    wrt = new PrintWriter(new BufferedWriter(new FileWriter("vysl.txt")));
    
  }
  
  @Override
  public void run() {
    
  
    
    try {
      
      InetAddress iadr = InetAddress.getByName("212.96.160.158");
      for (int port = od; port <= 80000; port += krok) {
        try {
          new Socket(iadr, port);
          synchronized (wrt) {
            wrt.println("********** Nalezen port: " + port);
          }
        } catch (ConnectException e) {
          synchronized (wrt) {
            wrt.printf("%s - %6d\n", e, port);
//          if (citac++ % 1000 == 0) {
//            System.out.println("jsem u " + citac);
//          }
          }
        }
        synchronized (wrt) {
          wrt.flush();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
