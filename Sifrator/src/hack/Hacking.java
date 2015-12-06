package hack;

import java.io.IOException;

public class Hacking {
  
  
  public void delej() throws IOException {

    Ohledavac.otevri();
    
    for (int ports=0; ports < 1; ports += 1) {
      
      new Thread(new Ohledavac(ports, 1)).start();
      
    }
    
  }
  
  
  public static void main(String[] args) throws IOException {
    System.out.println("START");
    new Hacking().delej();
    System.out.println("STOP");
    
  }
}
