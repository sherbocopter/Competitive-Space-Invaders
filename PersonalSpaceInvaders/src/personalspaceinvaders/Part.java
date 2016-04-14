package personalspaceinvaders;

import java.awt.Graphics2D;

/**
 *
 * @author SHerbocopter
 * using
 * http://www.gamedev.net/page/resources/_/technical/game-programming/entities-parts-i-game-objects-r3596
 */
public abstract class Part {
    protected Entity entity;
    private boolean isActive = true;    //should update
    private boolean isVisible = false;  //should draw
    
    public final boolean isActive() {
        return isActive;
    }
    
    public final void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public final boolean isVisible() {
        return isVisible;
    }
    
    public final void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public final Entity getEntity() {
        return entity;
    }
    
    public final void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    public void initialize() {
        
    }
    
    public void cleanup() {
        
    }
    
    public void update(float delta) {
        
    }
    
    public void draw(Graphics2D g2d) {
        
    }
}
