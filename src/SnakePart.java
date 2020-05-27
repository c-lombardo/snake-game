import java.awt.*;

/**
 * Created by clombardo on 5/10/16.
 */
public class SnakePart{
    private int xLoc;
    private int yLoc;
    private boolean isHead;
    private Color color;

    /**
     * constructs a new
     * @param inX x location
     * @param inY y location
     * @param inHead if the part is the head
     * @param inColor color of the snake part
     */
    public SnakePart (int inX, int inY, boolean inHead, Color inColor){
        xLoc = inX;
        yLoc = inY;
        isHead = inHead;
        color = inColor;
    }

    /**
     * sets x location
     * @param xLoc new x location
     */
    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    /**
     * sets y location
     * @param yLoc new y location
     */
    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    /**
     * returns if the piece is the head
     * @return if the piece is the head
     */
    public boolean isHead(){
        return isHead;
    }

    /**
     * returns the x location
     * @return the x location
     */
    public int getxLoc() {
        return xLoc;
    }

    /**
     * returns the x location
     * @return the x location
     */
    public int getyLoc() {
        return yLoc;
    }

    /**
     * draws the snake piece
     * @param g2 the graphics used to draw it
     */
    public void draw(Graphics2D g2) {
        Rectangle r = new Rectangle(xLoc, yLoc, Snake.BLOCKSIZE, Snake.BLOCKSIZE);
        g2.setColor(color);
        g2.draw(r);
        g2.fill(r);
    }

}
