package personalspaceinvaders;

/**
 *
 * @author SHerbocopter
 * using
 * http://www.gamedev.net/page/resources/_/technical/game-programming/entities-parts-i-game-objects-r3596
 */
public abstract class Part {
    private Entity entity;
    private boolean isActive = true;
    
    public final boolean isActive() {
        return isActive;
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
}
