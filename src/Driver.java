import javax.swing.*;
import java.awt.*;

/**
 * Created by clombardo on 5/10/16.
 */
public class Driver{

    public static Snake snek;
    public static Food food;


    public static void main(String[] args){
        JFrame frame = new JFrame();

        frame.setResizable(false);

        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        snek = new Snake(Snake.STARTINGLENGTH);
        food = new Food(Snake.BLOCKSIZE * 10, Snake.BLOCKSIZE * 4, snek);

        KeyManager key = new KeyManager(snek, food);

        int width = 905;
        int height = 800;

        frame.setSize(width, height);
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimationPanel panel = new AnimationPanel(snek, food);

        frame.add(panel);
        frame.addKeyListener(key);

        frame.setVisible(true);


    }

}
