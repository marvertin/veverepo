package sifra;
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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 */

/**
 * @author veverka
 *
 */
public abstract class SifraSHeslem0 extends JFrame {

  /**
   * @author veverka
   *
   */
  private final class ZmenovyListener implements DocumentListener {
    @Override
    public void removeUpdate(DocumentEvent aE) {
      sifruj();
    }

    @Override
    public void insertUpdate(DocumentEvent aE) {
      sifruj();
    }

    @Override
    public void changedUpdate(DocumentEvent aE) {
      sifruj();
    }
  }


  /**
   * 
   */
  private static final long serialVersionUID = -1527032129709847190L;
  private Box box;
  private JButton northButton = new JButton("North");
  private JButton southButton =  new JButton("South");
  private JButton westButton =  new JButton("West");
  private JButton eastButton  =  new JButton("East");
  
  private JMenuItem menuF1;
  private JMenuItem menuF2;
  private JMenuItem menuF3;
  private JMenuItem menuF4;
  private JMenuItem menuF5;
  
  private JTextField heslo;
  private JTextField textin;
  private JTextField textout;
  /**
   * 
   */
  public SifraSHeslem0() {
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
    // TODO Auto-generated method stub
    
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
    
    heslo = new JTextField();
    textin = new JTextField();
    textout = new JTextField();
    textout.setEditable(false);
    
    box.add(heslo);
    box.add(textin);
    box.add(textout);
    box.add(Box.createVerticalGlue());
    
    heslo.getDocument().addDocumentListener(new ZmenovyListener());
    textin.getDocument().addDocumentListener(new ZmenovyListener());
    
    textin.setText("RLBHRQRGQWDOKDLKFEGGEVISZKRZVBYVQRISVOJPLEILNFRFBDXYEKJKGMHGENUOKAZJZNZLPUAWIKDZJFWRRFNBKOK");
  }
  

  private void sifruj() {
    String h = heslo.getText();
    String tin = textin.getText();
    
    try {
      textout.setText(sifruj(h, tin));
    } catch (Exception e) {
      textout.setText(e.toString());
    }
  }
  
  /**
   * @param aHeslo
   * @param aTin
   * @return
   */
  protected abstract String sifruj(String aHeslo, String aTin);


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
}
