package personalspaceinvaders;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import personalspaceinvaders.parts.HitboxPart;
import personalspaceinvaders.parts.StatsPart;
import personalspaceinvaders.parts.StatsPart.Faction;

/**
 *
 * @author SHerbocopter
 * http://rivermanmedia.com/object-oriented-game-programming-the-scene-system/
 */
public abstract class Scene implements Commons {
    protected ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToAdd = new ArrayList<>();
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    public Entity controlEntity = new Entity();
    
    public void load() {
        
    }
    
    public void unload() {
        
    }
    
    public void update(float delta) {
        //System.out.println("UPDATE");
        KeyboardManager km = KeyboardManager.getInstance();
        km.poll();
        
        controlEntity.update(delta);
        
        for (Entity entity : entities) {
            if (entity.isActive()) {
                entity.update(delta);
            }
        }
        
        while (!entitiesToRemove.isEmpty()) {
            entities.remove(entitiesToRemove.remove(0));
        }
        
        while (!entitiesToAdd.isEmpty()) {
            entities.add(entitiesToAdd.remove(0));
        }
    }
    
    public void draw(Graphics g) {
        //System.out.println("DRAW");
        Graphics2D g2d = (Graphics2D) g;
        
        for (Entity entity : entities) {
            if (entity.isVisible()) {
                entity.draw(g2d);
            }
        }
    }
    
    public ArrayList<Entity> getCollissions(float up, float down, float left, float right) {
        ArrayList<Entity> collided = new ArrayList<>();
        
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            
            if (entity.has(HitboxPart.class)) {
                if (entity.get(HitboxPart.class).collides(up, down, left, right)) {
                    collided.add(entity);
                }
            }
        }
        
        return collided;
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
    
    public void removeEntity(Entity newEntity) {
        entitiesToRemove.add(newEntity);
    }
    
    public void removeEntities(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            this.removeEntity(entity);
        }
    }
}
