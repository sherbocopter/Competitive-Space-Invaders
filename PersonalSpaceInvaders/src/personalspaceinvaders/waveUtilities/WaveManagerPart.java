package personalspaceinvaders.waveUtilities;

import java.util.ArrayList;
import personalspaceinvaders.Part;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;

/**
 *
 * @author SHerbocopter
 */
public class WaveManagerPart extends Part {
    private ArrayList<WaveType> waves = null;
    private int waveCounter;
    private float timePassed;
    private WaveInfo currentWave;
    private Scene managedScene;
    
    public WaveManagerPart(Scene managedScene) {
        this.managedScene = managedScene;
        waveCounter = -1;
        timePassed = 0;
        currentWave = new WaveInfo();
    }
    
    public void setWaves(ArrayList<WaveType> waves) {
        this.waves = new ArrayList<>();
        this.waves.addAll(waves);
    }
    
    public void start() {
        if (waves == null) {
            return;
        }
        
        waveCounter = -1;
        prepareNextWave();
    }
    
    private void prepareNextWave() {
        waveCounter++;
        if (waveCounter >= waves.size()) {
            waveCounter = -1;
        }
        else {
            prepareWave(waveCounter);
        }
    }
    
    private void prepareWave(int waveIndex) {
        if (waveCounter == -1) {
            return;
        }
        
        WaveDictionary wd = WaveDictionary.getInstance();
        WavesFactory wf = WavesFactory.getInstance();
        
        currentWave = wd.getWaveInfo(waves.get(waveIndex));
        
        managedScene.addEntities(wf.createWave(currentWave.type));
    }
    
    @Override
    public void update(float delta) {
        if (waveCounter == -1) {
            return;
        }
        
        timePassed += delta;
        if (timePassed > currentWave.duration) {
            timePassed = timePassed - currentWave.duration;
            prepareNextWave();
        }
    }
}
