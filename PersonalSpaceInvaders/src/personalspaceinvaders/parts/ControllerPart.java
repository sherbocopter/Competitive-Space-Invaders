package personalspaceinvaders.parts;

import java.util.ArrayList;
import java.util.HashMap;
import personalspaceinvaders.Behaviour;
import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class ControllerPart extends Part {
    private HashMap<Class<? extends Behaviour>, Behaviour> behaviours = new HashMap<>();
    
    /**
     * 
     * ControllerPart is used to control an entity with a collection of
     * behaviours for which it also serves as a collection.
     * 
     * Behavious are stored in a way similar to the entity Entity HashMap.
     * Get, replace, remove methods not implemented as they
     * are not yet needed.
     * 
     */
    
    public <T extends Behaviour> boolean has(Class<T> behaviourClass) {
        return behaviours.containsKey(behaviourClass);
    }
    
    public void attach(Behaviour behaviour) {
        if (this.has(behaviour.getClass())) {
            throw new IllegalArgumentException();
        }
        
        behaviours.put(behaviour.getClass(), behaviour);
        behaviour.setPart(this);
        
        behaviour.initialize();
    }
    
    @Override
    public void update(float delta) {
        for (Behaviour b : behaviours.values()) {
            if (b.isActive()) {
                b.update(delta);
            }
        }
    }
}
