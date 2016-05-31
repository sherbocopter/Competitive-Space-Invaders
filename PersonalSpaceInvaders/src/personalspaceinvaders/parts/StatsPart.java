package personalspaceinvaders.parts;

import personalspaceinvaders.Part;

/**
 *
 * @author bosSHerbocopter
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
    
    /*spec_public*/private float currentHitpoints = 1;
    public float maxHitpoints = 1;
    public float damage = 0;
    public Faction faction = Faction.FACTION_FRIENDLY;
    public StatsType statsType = StatsType.SHIP;
    public boolean isInvulnerable = false;
    public float invulnerableTime = -1;
    /*spec_public*/ private float invulnerablePassed = 0;
    /*spec_public*/ private boolean shouldDie = false;
    /*spec_public*/ private boolean shouldInvulnerable = false;
    
    
    
    //@public normal_behaviour
    //@assignable this.currentHitpoints;
    //@ensures this.currentHitpoints == currentHitpoints;
    
    public void setCurrentHitpoints(float currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }
    
    //@public normal_behaviour
    //@ensures \result == currentHitpoints;
    
    public float getCurrentHitpoints() {
        return currentHitpoints;
    }
    
    //@public normal_behaviour
    //@requires !isInvulnerable;
    //@requires !shouldInvulnerable;
    //@assignable currentHitpoints;
    //@ensures currentHitpoints -= infictedDamage;
    //@also
    //@requires !isInvulnerable;
    //@requires !shouldInvulnerable;
    //@requires currentHitpoints <= 0;    
    //@assignable shouldDie;
    //@ensures shouldDie == true;
    //@also
    //@requires !isInvulnerable;
    //@requires !shouldInvulnerable;
    //@requires !isInvulnerable;
    //@assignable invulnerablePassed;
    //@assignable shouldInvulnerable;
    //@ensures invulnerablePassed == 0;
    //@ensures shouldInvulnerable == true;
    
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
    
    
    //@public normal_behaviour
    //@requires shouldDie;
    //@ensures this.entity.die();
    //@also
    //@requires !shouldDie ;
    //@requires shouldInvulnerable;
    //@assignable isInvulnerable;
    //@assignable shouldInvulnerable;
    //@ensures isInvulnerable == true;
    //@ensures shouldInvulnerable == false;
    //@also
    //@requires !shouldDie;
    //@requires isInvulnerable;
    //@requires invulnerableTime > 0;
    //@assignable invulnerablePassed;
    //@ensures invulnerablePassed += delta;
    //@requires invulnerablePassed > invulnerableTime;
    //@ensures isInvulnerable == false;
    
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
    
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof StatsPart))return false;
        StatsPart otherMyClass = (StatsPart)other;
        if(this.getDamage() != otherMyClass.getDamage())
        {
            return false;
        }
        if(this.getCurrentHitpoints()!= otherMyClass.getCurrentHitpoints())
        {
            return false;
        }
        if(this.getFaction()!= otherMyClass.getFaction())
        {
            return false;
        }
        if(this.getInvulnerablePassed()!= otherMyClass.getInvulnerablePassed())
        {
            return false;
        }
        if(this.getInvulnerableTime()!= otherMyClass.getInvulnerableTime())
        {
            return false;
        }
        if(this.getMaxHitpoints()!= otherMyClass.getMaxHitpoints())
        {
            return false;
        }
        if(this.getStatsType()!= otherMyClass.getStatsType())
        {
            return false;
        }
        return true;
    }

    public float getMaxHitpoints()
    {
        return maxHitpoints;
    }

    public float getDamage()
    {
        return damage;
    }

    public Faction getFaction()
    {
        return faction;
    }

    public StatsType getStatsType()
    {
        return statsType;
    }

    public boolean isIsInvulnerable()
    {
        return isInvulnerable;
    }

    public float getInvulnerableTime()
    {
        return invulnerableTime;
    }

    public float getInvulnerablePassed()
    {
        return invulnerablePassed;
    }

    public boolean isShouldDie()
    {
        return shouldDie;
    }

    public boolean isShouldInvulnerable()
    {
        return shouldInvulnerable;
    }
    
    
}
