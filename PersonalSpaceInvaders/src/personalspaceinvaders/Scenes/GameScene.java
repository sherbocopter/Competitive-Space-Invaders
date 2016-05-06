package personalspaceinvaders.Scenes;

import java.awt.Graphics;
import java.util.ArrayList;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.waveUtilities.WaveManagerPart;

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
        //WavesFactory wf = WavesFactory.getInstance();
        EntityFactory ef = EntityFactory.getInstance();
        
        //entities.addAll(wf.createWave(WavesFactory.WaveType.WAVE_BASIC_BLOCK));
        entities.add(ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC));
        
        WaveManagerPart waveManager = new WaveManagerPart(this);
        ArrayList<WaveType> waves = new ArrayList<>();
        waves.add(WaveType.WAVE_BASIC_BLOCK);
        waves.add(WaveType.WAVE_MIXED_BLOCK);
        waveManager.setWaves(waves);
        this.controlEntity.attach(waveManager);
        waveManager.setActive(true);
        waveManager.start();
    }
}
