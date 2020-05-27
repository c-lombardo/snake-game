import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by clombardo on 5/12/16.
 */
public class KeyManager implements KeyListener{
    Snake snek;
    Food food;

    public KeyManager(Snake s, Food f){
        snek = s;
        food = f;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            snek.changeDirection(Snake.UP);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            snek.changeDirection(Snake.LEFT);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            snek.changeDirection(Snake.DOWN);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            snek.changeDirection(Snake.RIGHT);
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            food.getEaten();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
