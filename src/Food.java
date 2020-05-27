import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by clombardo on 5/17/16.
 */
public class Food implements MoveableShape{
    int xLoc;
    int yLoc;
    boolean isEaten;
    Snake s;

    Random r = new Random();

    /**
     * contstructs a new piece of food
     * @param inx x location
     * @param iny y location
     * @param inSnake snake associated with the food
     */
    public Food(int inx, int iny, Snake inSnake) {
        xLoc = inx;
        yLoc = iny;
        isEaten = false;
        s = inSnake;
    }

    /**
     * food becomes eaten
     */
    public void getEaten(){
        isEaten = true;
    }

    /**
     * returns y location
     * @return y location
     */
    public int getyLoc() {
        return yLoc;
    }

    /**
     * returns x location
     * @return x location
     */
    public int getxLoc() {
        return xLoc;
    }

    /**
     * moves the food piece to a random location on the screen, considering the size of the snake and won't generate on top of the snake
     */
    public void move() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 800;
        int height = 800;

        ArrayList<Integer> availableX = new ArrayList<>();
        for (int i = 0; i < width - Snake.BLOCKSIZE; i = i + Snake.BLOCKSIZE){
            availableX.add(i);
        }

        ArrayList<Integer> availableY = new ArrayList<>();
        for (int i = 0; i < height - Snake.BLOCKSIZE; i = i + Snake.BLOCKSIZE){
            availableY.add(i);
        }

        if (isEaten){
            boolean done = false;
            int newX = -10;
            int newY = -10;
            while (!done){
                newX = availableX.get(r.nextInt(availableX.size()));
                newY = availableY.get(r.nextInt(availableY.size()));
                int count = 0;
                for (int i = 0; i < s.getParts().size(); i++){
                    if (s.getParts().get(i).getxLoc() == newX && s.getParts().get(i).getyLoc() == newY){
                        count++;
                    }
                }
                if (count == 0){
                    done = true;
                }
            }
            xLoc = newX;
            yLoc = newY;
            isEaten = false;
        }
    }

    /**
     * draws the snake
     * @param g graphics for drawing snake
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        Rectangle r = new Rectangle(xLoc, yLoc, Snake.BLOCKSIZE, Snake.BLOCKSIZE);
        g.draw(r);
        g.fill(r);
    }
}
