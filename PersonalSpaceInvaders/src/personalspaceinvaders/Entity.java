package personalspaceinvaders;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author SHerbocopter
 * using
 * http://www.gamedev.net/page/resources/_/technical/game-programming/entities-parts-i-game-objects-r3596
 */
public class Entity {
    private boolean isInitialized = false;
    private boolean isActive = true;
    private boolean isVisible = true;
    private HashMap<Class<? extends Part>, Part> parts = new HashMap<>();
    private ArrayList<Part> partsToAdd = new ArrayList<>();
    private ArrayList<Class<? extends Part>> partsToRemove = new ArrayList<>();
    private Scene scene;
    
    //<editor-fold defaultstate="collapsed" desc="Getters + Setters">
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public void setScene(Scene scene) {
        this.scene = scene;
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
//</editor-fold>
    
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
    
    public void die() {
        this.scene.removeEntity(this);
        //System.out.println("I DAE");
    }
    
    public void update(float delta) {
        for (Part part : parts.values()) {
            if (part.isActive()) {
                part.update(delta);
            }
        }
        
        while (!partsToRemove.isEmpty()) {
            remove(partsToRemove.remove(0));
        }
        
        while (!partsToAdd.isEmpty()) {
            attach(partsToAdd.remove(0));
        }
    }
    
    public void draw(Graphics2D g2d) {
        for (Part part : parts.values()) {
            if (part.isVisible()) {
                part.draw(g2d);
            }
        }
    }
}
