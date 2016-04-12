package personalspaceinvaders;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SHerbocopter
 * using
 * http://www.gamedev.net/page/resources/_/technical/game-programming/entities-parts-i-game-objects-r3596
 */
public class Entity {
    private boolean isInitialized = false;
    private boolean isActive = false;
    private HashMap<Class<? extends Part>, Part> parts = new HashMap<>();
    private ArrayList<Part> partsToAdd = new ArrayList<>();
    private ArrayList<Class<? extends Part>> partsToRemove = new ArrayList<>();
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public <T extends Part> boolean has(Class<T> partClass) {
        return parts.containsKey(partClass);
    }
    
    public <T extends Part> T get(Class<T> partClass) {
        if (!this.has(partClass)) {
            throw new IllegalArgumentException();
        }
        return (T)parts.get(partClass);
    }
    
    public void attach(Part part) {
        if (this.has(part.getClass())) {
            throw new IllegalArgumentException();
        }
        
        parts.put(part.getClass(), part);
        part.setEntity(this);
        
        if (this.isInitialized) {
            part.initialize();
        }
    }
    
    public <T extends Part> void detach(Class<T> partClass) {
        if (this.has(partClass) && !partsToRemove.contains(partClass)) {
            partsToRemove.add(partClass);
        }
    }
    
    public void replace(Part part) {
        if (this.has(part.getClass())) {
            detach(part.getClass());
        }
        
        if (this.isInitialized) {
            partsToAdd.add(part);
        }
        else {
            attach(part);
        }
    }
    
    public void initialize() {
        isInitialized = true;
        isActive = true;
        for (Part part : parts.values()) {
            part.initialize();
        }
    }
    
    public void cleanup() {
        isActive = false;
        for (Part part : parts.values()) {
            part.cleanup();
        }
    }
    
    private <T extends Part> void remove(Class<T> partClass) {
        if (!this.has(partClass)) {
            throw new IllegalArgumentException();
        }
        parts.get(partClass).cleanup();
        parts.remove(partClass);
    }
    
    public void update(float delta) {
        for (Part part : parts.values()) {
            part.update(delta);
        }
        
        while (!partsToRemove.isEmpty()) {
            remove(partsToRemove.remove(0));
        }
        
        while (!partsToAdd.isEmpty()) {
            attach(partsToAdd.remove(0));
        }
    }
}
