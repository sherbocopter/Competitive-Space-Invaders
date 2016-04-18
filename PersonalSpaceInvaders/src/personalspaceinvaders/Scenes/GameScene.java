package personalspaceinvaders.Scenes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import personalspaceinvaders.Entity;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.WavesFactory;

/**
 *
 * @author SHerbocopter
 */
public class GameScene extends Scene {
    private ArrayList<Entity> entities = new ArrayList<>();
    
    @Override
    public void load() {
        temporaryEntitiesInit();
    }
    
    @Override
    public void unload() {
        
    }
    
    @Override
    public void update(float delta) {
        KeyboardManager km = KeyboardManager.getInstance();
        km.poll();
        
        for (Entity entity : entities) {
            if (entity.isActive()) {
                entity.update(delta);
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        for (Entity entity : entities) {
            if (entity.isVisible()) {
                entity.draw(g2d);
            }
        }
    }
    
    private void temporaryEntitiesInit() {
        WavesFactory wf = WavesFactory.getInstance();
        EntityFactory ef = EntityFactory.getInstance();
        
        entities.addAll(wf.createWave(WavesFactory.WaveType.WAVE_MIXED_BLOCK));
        entities.add(ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC));
    }
}
