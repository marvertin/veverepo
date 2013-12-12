package monoskop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class JMonoskop extends JComponent {

  private static final long serialVersionUID = -8380922828670923544L;


  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.RED);
    g.setFont(Font.decode("Arial-36"));
    g.drawString(getWidth() + " * " + getHeight(), 300, 300);
    g.setColor(Color.BLACK);
    g.drawLine(0, 0, getWidth(), getHeight());


    int krok = 1;
    for (int y = 0; y < getHeight(); y += 100, krok *= 2) {
      for (int x = 0; x < getWidth(); x += krok) {
        g.drawLine(x, y, x, y + 100);

      }
    }
  }


}
