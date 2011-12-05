package pokus;
import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class NewJFrame extends javax.swing.JFrame {
  /**
   * 
   */
  private static final long serialVersionUID = 1037378771354553561L;
  private JPanel jPanel1;
  private JButton jButton1;
  private JButton jButton3;
  private JButton jButton2;
  private JPanel jPanel2;
  private JTextField jTextField1;
  private JLabel jLabel1;
  private JTextArea jTextArea1;

  /**
   * Auto-generated main method to display this JFrame
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        NewJFrame inst = new NewJFrame();
        inst.setLocationRelativeTo(null);
        inst.setVisible(true);
      }
    });
  }

  public NewJFrame() {
    super();
    initGUI();
  }

  private void initGUI() {
    try {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      {
        jPanel1 = new JPanel();
        getContentPane().add(jPanel1, BorderLayout.CENTER);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1.setPreferredSize(new java.awt.Dimension(306, 262));
        jPanel1.setEnabled(false);
        {
          jButton1 = new JButton();
          jButton1.setText("jButton1");
        }
        {
          jTextArea1 = new JTextArea();
          jTextArea1.setText("jTextArea1");
        }
        {
          jLabel1 = new JLabel();
          jLabel1.setText("jLabel1");
        }
        {
          jTextField1 = new JTextField();
          jTextField1.setText("jTextField1");
        }
        {
          jPanel2 = new JPanel();
          {
            jButton3 = new JButton();
            jPanel2.add(jButton3);
            jButton3.setText("jButton3");
          }
          {
            jButton2 = new JButton();
            jPanel2.add(jButton2);
            jButton2.setText("jButton2");
          }
        }
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup()
                        .addComponent(jButton1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
                        .addGap(27)
                        .addComponent(jTextArea1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel2, GroupLayout.Alignment.LEADING, 0, 214, Short.MAX_VALUE))
                            .addContainerGap(186, 186));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextArea1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, 0, 39, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(61, 61));
      }
      pack();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
