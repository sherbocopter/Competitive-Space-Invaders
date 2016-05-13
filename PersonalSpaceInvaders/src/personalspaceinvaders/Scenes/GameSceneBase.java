package personalspaceinvaders.Scenes;

import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.waveUtilities.WaveDictionary;

/**
 *
 * @author SHerbocopter
 */
public class GameSceneBase extends Scene {
    public void spawnWave(WaveType type) {
        WavesFactory wf = WavesFactory.getInstance();
        this.addEntities(wf.createWave(type));
    }
}
