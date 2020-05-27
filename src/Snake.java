import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by clombardo on 5/10/16.
 */
public class Snake implements MoveableShape{
    private int direction;
    private int direction_at_frame_start;

    private int score;
    private ArrayList<SnakePart> parts;

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static final int BLOCKSIZE = 20;

    public static final int STARTINGX = 100;
    public static final int STARTINGY = 400;

    public static final int STARTINGLENGTH = 6;

    Random r = new Random();

    /**
     * constructs a new snake
     * @param inSize the staring length of the snake
     */
    public Snake(int inSize){
        parts = new ArrayList<>();
        direction = RIGHT;
        direction_at_frame_start = RIGHT;
        score = 0;
        for (int i = 0; i < inSize; i++){
            Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            if (i == 0)
                parts.add(new SnakePart(STARTINGX, STARTINGY, true, Color.RED));
            else
                parts.add(new SnakePart(STARTINGX - (i * BLOCKSIZE), STARTINGY, false, c));
        }

    }

    /**
     * returns the snake as an ArrayList
     * @return
     */
    public ArrayList<SnakePart> getParts() {
        return parts;
    }

    /**
     * draws the snake
     * @param g2 the graphics used to draw it
     */
    public void draw(Graphics2D g2){
        for (int i = 0; i < parts.size(); i++){
            parts.get(i).draw(g2);
        }

        g2.setColor(Color.GRAY);
        Rectangle r1 = new Rectangle(800, 0, 105, 800);
        g2.draw(r1);
        g2.fill(r1);

        g2.setColor(Color.WHITE);
        Font f = new Font("Comic Sans", Font.BOLD, 28);
        g2.setFont(f);
        g2.drawString("SCORE", 805, 25);

        Font f2 = new Font("Comic Sans", Font.BOLD, 65);
        g2.setFont(f2);
        g2.drawString("" + score, 805, 80);

        g2.setFont(f);
        g2.drawString("SNAKE", 805, 135);

        Font f3 = new Font("Comic Sans", Font.BOLD, 23);
        g2.setFont(f3);
        g2.drawString("LENGTH", 805, 155);

        g2.setFont(f2);
        g2.drawString("" + parts.size(), 805, 210);

    }

    /**
     * moves the snake by one piece
     */
    public void move(){
        direction_at_frame_start = direction;
        for (int i = parts.size() - 1; i >= 0; i--){
            if (!parts.get(i).isHead()) {
                parts.get(i).setxLoc(parts.get(i - 1).getxLoc());
                parts.get(i).setyLoc(parts.get(i - 1).getyLoc());
            }
            else {
                if (direction == UP)
                    parts.get(i).setyLoc(parts.get(i).getyLoc() - BLOCKSIZE);
                else if (direction == DOWN)
                    parts.get(i).setyLoc(parts.get(i).getyLoc() + BLOCKSIZE);
                else if (direction == LEFT)
                    parts.get(i).setxLoc(parts.get(i).getxLoc() - BLOCKSIZE);
                else if (direction == RIGHT)
                    parts.get(i).setxLoc(parts.get(i).getxLoc() + BLOCKSIZE);
            }
        }
    }


    /**
     * returns the score (number of food pieces collected
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * adds 3 randomly colored pieces to the snake off the screen, they will be added at the next time move() is called
     */
    public void grow(){
        score++;
        for (int i = 0; i < 3; i++) {
            Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            parts.add(new SnakePart(2000, 2000, false, color));
        }
    }

    /**
     * changes the direction the snake is moving
     * @param inDirection the new direction
     */
    public void changeDirection(int inDirection){
        boolean change = true;
        if (inDirection == UP && direction_at_frame_start == DOWN){
            change = false;
        }
        else if (inDirection == DOWN && direction_at_frame_start == UP){
            change = false;
        }
        else if (inDirection == RIGHT && direction_at_frame_start == LEFT){
            change = false;
        }
        else if (inDirection == LEFT && direction_at_frame_start == RIGHT){
            change = false;
        }
        if (change)
            direction = inDirection;
    }

    /**
     * checks if the block in front of the snake is a food piece
     * @param food the food piece it is checking for
     * @return true if it is a food piece, false otherwise
     */
    public boolean checkForFood(Food food){
        if (direction == UP && food.getxLoc() == parts.get(0).getxLoc() &&
                food.getyLoc() == parts.get(0).getyLoc() - BLOCKSIZE)
            return true;
        else if (direction == DOWN && food.getxLoc() == parts.get(0).getxLoc() &&
                food.getyLoc() == parts.get(0).getyLoc() + BLOCKSIZE)
            return true;
        else if (direction == RIGHT && food.getxLoc() == parts.get(0).getxLoc() + BLOCKSIZE &&
                food.getyLoc() == parts.get(0).getyLoc())
            return true;
        else if (direction == LEFT && food.getxLoc() == parts.get(0).getxLoc() - BLOCKSIZE &&
                food.getyLoc() == parts.get(0).getyLoc())
            return true;
        return false;
    }

    /**
     * checks if the block in front of the head is a snake piece
     * @return true if there is a snake piece, false otherwise
     */
    public boolean checkForSelf(){
        for (int i = 1; i < parts.size(); i++){
            if (direction == UP && parts.get(i).getxLoc() == parts.get(0).getxLoc() && parts.get(i).getyLoc() == parts.get(0).getyLoc() - BLOCKSIZE){
                return true;
            }
            else if (direction == DOWN && parts.get(i).getxLoc() == parts.get(0).getxLoc() && parts.get(i).getyLoc() == parts.get(0).getyLoc() + BLOCKSIZE){
                return true;
            }
            else if (direction == RIGHT && parts.get(i).getxLoc() == parts.get(0).getxLoc() + BLOCKSIZE && parts.get(i).getyLoc() == parts.get(0).getyLoc()){
                return true;
            }
            else if (direction == LEFT && parts.get(i).getxLoc() == parts.get(0).getxLoc() - BLOCKSIZE && parts.get(i).getyLoc() == parts.get(0).getyLoc()){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the block in front of the head is a wall
     * @param xSize the width of the grid the snake is allowed to explore
     * @param ySize the height of the grid the snake is allowed to explore
     * @return true if there is a wall in front of the snake, false otherwise2
     */
    public boolean checkForWall(int xSize, int ySize){
        if (parts.get(0).getxLoc() > xSize - BLOCKSIZE || parts.get(0).getxLoc() < 0 || parts.get(0).getyLoc() > ySize - BLOCKSIZE - BLOCKSIZE || parts.get(0).getyLoc() < 0){
            return true;
        }
        return false;
    }
}
