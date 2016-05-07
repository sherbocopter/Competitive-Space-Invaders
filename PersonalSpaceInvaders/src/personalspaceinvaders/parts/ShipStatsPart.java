package personalspaceinvaders.parts;

/**
 *
 * @author SHerbocopter
 */
public class ShipStatsPart {
    public enum Faction {
        FACTION_FRIENDLY,
        FACTION_ENEMY
    }
    
    public float currentHitpoints = 0;
    public float maxHitpoints = 0;
    public Faction faction = Faction.FACTION_FRIENDLY;
}
