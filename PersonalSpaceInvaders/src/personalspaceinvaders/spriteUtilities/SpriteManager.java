package personalspaceinvaders.spriteUtilities;

import java.awt.Image;
import java.util.HashMap;

/**
 *
 * @author SHerbocopter
 */
public class SpriteManager {
    private static SpriteManager instance = new SpriteManager();
    private SpriteManager() {
        putSprite("mainMenuBanner", "res/sprites/banner.png");
        
        putSprite("playerShip", "res/sprites/player_ship.png");
        putSprite("alien1",     "res/sprites/alien1.png");
        putSprite("alien2",     "res/sprites/alien2.png");
        putSprite("alien3",     "res/sprites/alien3.png");
        putSprite("bullet1",    "res/sprites/bullet1.png");
    }
    public static SpriteManager getInstance() {
        return instance;
    }
    
    private HashMap<String, SpriteInfo> spriteMap = new HashMap<>();
    
    private void putSprite(String key, String path) {
        SpriteInfo spriteInfo = new SpriteInfo(path);
        spriteInfo.loadImageIcon();
        spriteMap.put(key, spriteInfo);
    }
    
    public Image getImage(String key) {
        if (spriteMap.containsKey(key)) {
            return spriteMap.get(key).imageIcon.getImage();
        }
        return null;
    }
}
