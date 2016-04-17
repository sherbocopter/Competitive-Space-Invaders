package personalspaceinvaders.behaviours;

import java.awt.event.KeyEvent;
import personalspaceinvaders.Behaviour;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class PlayerMoveBehaviour extends Behaviour {
    private float horSpeed;
    private float verSpeed;
    private int upKey = KeyEvent.VK_W;
    private int downKey = KeyEvent.VK_S;
    private int leftKey = KeyEvent.VK_A;
    private int rightKey = KeyEvent.VK_D;
    
    /**
     * This behaviour gives the entity the possibility to move both horizontally
     * and vertically using the keyboard.
     */
    
    public PlayerMoveBehaviour(float horSpeed, float verSpeed) {
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
    }
    
    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }
    
    public int getUpKey() {
        return upKey;
    }
    
    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }
    
    public int getDownKey() {
        return downKey;
    }
    
    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }
    
    public int getLeftKey() {
        return leftKey;
    }
    
    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }
    
    public int getRightKey() {
        return rightKey;
    }
    
    @Override
    public void update(float delta) {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        KeyboardManager km = KeyboardManager.getInstance();
        
        if (km.keyDown(upKey)) {
            tr.setY(tr.getY() - verSpeed * delta);
        }
        if (km.keyDown(downKey)) {
            tr.setY(tr.getY() + verSpeed * delta);
        }
        if (km.keyDown(leftKey)) {
            tr.setX(tr.getX() - horSpeed * delta);
        }
        if (km.keyDown(rightKey)) {
            tr.setX(tr.getX() + horSpeed * delta);
        }
    }
}
