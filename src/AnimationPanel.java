/**
 * Created by clombardo on 5/10/16.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

/**
 This component animates MoveableShale objects.
 */
public class AnimationPanel extends JPanel{

    Snake s;
    Food f;

    public AnimationPanel(Snake snake, Food food) {
        s = snake;
        f = food;
        setBackground(Color.DARK_GRAY);

        class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (s.checkForFood(f)){
                    f.getEaten();
                    s.grow();
                }
                if (s.checkForSelf() || s.checkForWall(800,800)){
                    JOptionPane.showMessageDialog(null, "Game Over! Score: " + s.getScore());
                    System.exit(0);
                }

                s.move();
                f.move();
                repaint();
            }

        }

        ActionListener listener = new TimerListener();

        final int DELAY = 80; // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, listener);
        t.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        s.draw(g2);
        f.draw(g2);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(800, 0, 800, 900));

    }

}
