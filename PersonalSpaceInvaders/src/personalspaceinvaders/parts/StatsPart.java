package personalspaceinvaders.parts;

import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class StatsPart extends Part{
    public enum Faction {
        FACTION_FRIENDLY,
        FACTION_ENEMY
    }
    
    public enum StatsType {
        SHIP,
        BULLET
    }
    
    private float currentHitpoints = 1;
    public float maxHitpoints = 1;
    public float damage = 0;
    public Faction faction = Faction.FACTION_FRIENDLY;
    public StatsType statsType = StatsType.SHIP;
    public boolean isInvulnerable = false;
    
    public void setCurrentHitpoints(float currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }
    
    public float getCurrentHitpoints() {
        return currentHitpoints;
    }
    
    public void inflictDamage(float inflictedDamage) {
        this.currentHitpoints -= inflictedDamage;
        if (currentHitpoints <= 0) {
            this.entity.die();
        }
    }
}
