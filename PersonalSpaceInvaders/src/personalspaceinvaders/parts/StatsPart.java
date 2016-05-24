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
    public float invulnerableTime = -1;
    private float invulnerablePassed = 0;
    private boolean shouldDie = false;
    private boolean shouldInvulnerable = false;
    
    public void setCurrentHitpoints(float currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }
    
    public float getCurrentHitpoints() {
        return currentHitpoints;
    }
    
    public void inflictDamage(float inflictedDamage) {
        if (isInvulnerable || shouldInvulnerable) {
            return;
        }
        
        this.currentHitpoints -= inflictedDamage;
        if (currentHitpoints <= 0) {
            shouldDie = true;
        }
        
        if (invulnerableTime > 0 && !isInvulnerable) {
            invulnerablePassed = 0;
            shouldInvulnerable = true;
        }
    }
    
    @Override
    public void update(float delta) {
        if (shouldDie) {
            this.entity.die();
            return;
        }
        
        if (shouldInvulnerable) {
            isInvulnerable = true;
            shouldInvulnerable = false;
        }
        
        if (isInvulnerable && invulnerableTime > 0) {
            invulnerablePassed += delta;
            
            if (invulnerablePassed > invulnerableTime) {
                isInvulnerable = false;
            }
        }
    }
}
