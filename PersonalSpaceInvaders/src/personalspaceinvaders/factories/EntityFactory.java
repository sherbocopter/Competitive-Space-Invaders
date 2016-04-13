package personalspaceinvaders.factories;

import java.awt.Color;
import personalspaceinvaders.Entity;
import personalspaceinvaders.parts.HitboxPart;
import personalspaceinvaders.parts.HitpointsPart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class EntityFactory {
    private static final EntityFactory instance = new EntityFactory();
    private EntityFactory() { }
    public static EntityFactory getInstance() {
        return instance;
    }
    
    public static enum EntityType {
        ALIEN_BASIC_WHITE,
        ALIEN_BASIC_RED,
        ALIEN_BASIC_GREEN
    }
    
    public Entity createEntity(EntityType type) {
        Entity entity = null;
        
        switch (type) {
            case ALIEN_BASIC_WHITE: {
                entity = createBasicWhiteAlien();
            } break;
            case ALIEN_BASIC_RED: {
                entity = createBasicRedAlien();
            } break;
            case ALIEN_BASIC_GREEN: {
                entity = createBasicGreenAlien();
            } break;
            default: {
                throw new IllegalArgumentException("entityType not found");
            }
        }
        
        return entity;
    }
    
    private Entity createBasicWhiteAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(100, 100, 0, 1));
        alien.attach(new HitpointsPart(100));
        
        HitboxPart hitbox = new HitboxPart(-25, -15, 0, 50, 30);
        hitbox.setColor(Color.WHITE);
        hitbox.setVisible(true);
        
        alien.attach(hitbox);
        
        return alien;
    }
    
    private Entity createBasicRedAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(100, 100, 0, 1));
        alien.attach(new HitpointsPart(120));
        
        HitboxPart hitbox = new HitboxPart(-25, -20, 0, 50, 40);
        hitbox.setColor(Color.RED);
        hitbox.setVisible(true);
        
        alien.attach(hitbox);
        
        return alien;
    }
    
    private Entity createBasicGreenAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(100, 100, 0, 1));
        alien.attach(new HitpointsPart(80));
        
        HitboxPart hitbox = new HitboxPart(-20, -10, 0, 40, 20);
        hitbox.setColor(Color.GREEN);
        hitbox.setVisible(true);
        
        alien.attach(hitbox);
        
        return alien;
    }
}
