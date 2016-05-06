package personalspaceinvaders;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author SHerbocopter
 * http://rivermanmedia.com/object-oriented-game-programming-the-scene-system/
 */
public abstract class Scene implements Commons {
    public ArrayList<Entity> entities = new ArrayList<>();
    protected Entity controlEntity = new Entity();
    
    public void load() {
        
    }
    
    public void unload() {
        
    }
    
    public void update(float delta) {
        KeyboardManager km = KeyboardManager.getInstance();
        km.poll();
        
        controlEntity.update(delta);
        
        for (Entity entity : entities) {
            if (entity.isActive()) {
                entity.update(delta);
            }
        }
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        for (Entity entity : entities) {
            if (entity.isVisible()) {
                entity.draw(g2d);
            }
        }
    }
}
