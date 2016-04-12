package personalspaceinvaders.parts;

import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class HitpointsPart extends Part {
    private float hitpoints;
    
    public HitpointsPart(float hitpoints) {
        this.hitpoints = hitpoints;
    }
    
    public float getHitpoints() {
        return hitpoints;
    }
    
    public void setHitpoints(float hitpoints) {
        this.hitpoints = hitpoints;
    }
}
