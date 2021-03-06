package jackgprofi;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * 
 */

/**
 * @author veverka
 *
 */
public class ProfiMain extends JFrame {




  /**
   * 
   */
  private static final long serialVersionUID = -1527032129709847190L;
  private Box box;
  private final JButton northButton = new JButton("North");
  private final JButton southButton =  new JButton("South");
  private final JButton westButton =  new JButton("West");
  private final JButton eastButton  =  new JButton("East");

  private JMenuItem menuF1;
  private JMenuItem menuF2;
  private JMenuItem menuF3;
  private JMenuItem menuF4;
  private JMenuItem menuF5;

  private final JMatice jMatice = new JMatice();

  /**
   * 
   */
  public ProfiMain() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
    registerEvents();
    pack();
    setSize(400, 300);

  }


  /**
   * 
   */
  private void registerEvents() {
    northButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        new Updatovac(jMatice).execute();

      }
    });


  }


  /**
   * 
   */
  private void initComponents() {
    box = Box.createVerticalBox();
    add(box);
    add(northButton, BorderLayout.NORTH);
    add(southButton, BorderLayout.SOUTH);
    add(westButton, BorderLayout.WEST);
    add(eastButton, BorderLayout.EAST);

    JMenuBar menuBar = new JMenuBar();
    JMenu jMenu = new JMenu("Fn");
    menuBar.add(jMenu);
    menuF1 = createMenuItem("F1");
    jMenu.add(menuF1);
    menuF2 = createMenuItem("F2");
    jMenu.add(menuF2);
    menuF3 = createMenuItem("F3");
    jMenu.add(menuF3);
    menuF4 = createMenuItem("F4");
    jMenu.add(menuF4);
    menuF5 = createMenuItem("F5");
    jMenu.add(menuF5);

    setJMenuBar(menuBar);


    box.add(jMatice);

  }



  private JMenuItem createMenuItem(final String fx) {
    JMenuItem jMenuItem = new JMenuItem(fx);
    jMenuItem.setAccelerator(KeyStroke.getKeyStroke(fx));
    jMenuItem.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent aE) {
        System.out.println("Vybran menu item: " + fx);

      }
    });

    return jMenuItem;
  }


  public void spust() {

    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

      @Override
      public void uncaughtException(Thread aT, Throwable ee) {
        System.err.println("Osetrena nechycena vyjimka: ");
        for (Throwable e = ee; e != null; e = e.getCause()) {
          System.err.println("::" + e.toString() + "  " + e.getMessage());
        }
        ee.printStackTrace();

      }
    });


    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        setVisible(true);
      }
    });
  }


  public static void main(String[] args) {
    new ProfiMain().spust();
  }


  //////////////////////////////////////////////////////////////////////////////////////////


  protected String sifruj(String ncisla, int klic, boolean cesky) {
    return "xxx";
  }


}
