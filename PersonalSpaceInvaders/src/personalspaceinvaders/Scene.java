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
    protected ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToAdd = new ArrayList<>();
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    protected Entity controlEntity = new Entity();
    
    public void load() {
        
    }
    
    public void unload() {
        
    }
    
    public void update(float delta) {
        KeyboardManager km = KeyboardManager.getInstance();
        km.poll();
        
        if (entitiesToAdd.size() > 0) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }
        
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
    
    public void addEntity(Entity newEntity) {
        entitiesToAdd.add(newEntity);
        newEntity.setScene(this);
    }
    
    public void addEntities(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            this.addEntity(entity);
        }
    }
}
