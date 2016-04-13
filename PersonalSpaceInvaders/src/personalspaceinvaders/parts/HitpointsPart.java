package personalspaceinvaders.parts;

import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class HitpointsPart extends Part {
    private float currentHitpoints;
    private float maxHitpoints;
    
    public HitpointsPart(float maxHitpoints) {
        this.maxHitpoints = maxHitpoints;
        this.currentHitpoints = maxHitpoints;
    }
    
    public float getMaxHitpoints() {
        return maxHitpoints;
    }
    
    public void setMaxHitpoints(float maxHitpoints) {
        this.maxHitpoints = maxHitpoints;
    }
    
    public float getCurrentHitpoints() {
        return currentHitpoints;
    }
    
    public void setCurrentHitpoints(float currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }
}
