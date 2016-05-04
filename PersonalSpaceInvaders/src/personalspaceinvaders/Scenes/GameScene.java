package personalspaceinvaders.Scenes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import personalspaceinvaders.Entity;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.HudFactory;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class GameScene extends Scene {
    @Override
    public void load() {
        temporaryEntitiesInit();
    }
    
    @Override
    public void unload() {
        
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    
    private void temporaryEntitiesInit() {
        WavesFactory wf = WavesFactory.getInstance();
        EntityFactory ef = EntityFactory.getInstance();
        
        entities.addAll(wf.createWave(WavesFactory.WaveType.WAVE_MIXED_BLOCK));
        entities.add(ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC));
    }
}
