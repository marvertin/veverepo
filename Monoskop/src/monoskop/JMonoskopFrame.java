package monoskop;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class JMonoskopFrame extends JFrame {

  private static final long serialVersionUID = -2038543946251515748L;

  public JMonoskopFrame() {
    init();
  }

  void init() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(306, 262));

    JMonoskop mono = new JMonoskop();
    mono.setPreferredSize(new java.awt.Dimension(306, 262));
    add(mono);
    pack();
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        JFrame inst = new JMonoskopFrame();
        inst.setLocationRelativeTo(null);
        inst.setVisible(true);
      }
    });
  }

}
