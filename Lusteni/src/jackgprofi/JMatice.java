package jackgprofi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class JMatice extends JComponent {

  boolean[] mat = new boolean[256];
  int SIZE = 20;
  int MEZERA = 3;
  int n=20;

  @Override
  protected void paintComponent(Graphics g) {

    for (int i=0; i<mat.length; i++) {
      int x = i % n;
      int y = i / n;
      Color color = mat[i] ? Color.RED : Color.GREEN;
      g.setColor(color);
      g.fillOval(x * SIZE + MEZERA, y * SIZE + MEZERA, SIZE, SIZE);
    }
  }

  void clear(int n) {
    this.n = n;
    mat = new boolean[256];
    repaint();
  }
}
