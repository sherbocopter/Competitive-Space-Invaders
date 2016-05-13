package personalspaceinvaders.waveUtilities;

import java.util.ArrayList;
import personalspaceinvaders.Commons;
import personalspaceinvaders.Part;
import personalspaceinvaders.Scene;
import personalspaceinvaders.Scenes.GameSceneBase;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.parts.TextLabelPart;

/**
 *
 * @author SHerbocopter
 */
public class WaveManagerPart extends Part implements Commons {
    private ArrayList<WaveType> waves = new ArrayList<>();
    private int waveCounter;
    private float timePassed;
    private WaveInfo currentWave;
    private GameSceneBase managedScene;
    private int maxCapacity = MAX_WAVES;
    private TextLabelPart outputLabel = null;
    public boolean isFinished = true;
    
    public WaveManagerPart(GameSceneBase managedScene) {
        this.managedScene = managedScene;
        waveCounter = -1;
        timePassed = 0;
        currentWave = new WaveInfo();
    }
    
    public void setOutputLabel(TextLabelPart outputLabel) {
        this.outputLabel = outputLabel;
    }
    
    public void setWaves(ArrayList<WaveType> waves) {
        this.waves.clear();
        this.waves.addAll(waves);
    }
    
    public ArrayList<WaveType> getWaves() {
        return this.waves;
    }
    
    public void pushWave(WaveType wave) {
        if (waves.size() >= maxCapacity)
            return;
        
        this.waves.add(wave);
    }
    
    public void popWave() {
        if (this.waves.isEmpty())
            return;
        
        this.waves.remove(waves.size() - 1);
    }
    
    public void start() {
        if (waves == null) {
            return;
        }
        
        isFinished = false;
        waveCounter = -1;
        prepareNextWave();
    }
    
    private void prepareNextWave() {
        waveCounter++;
        if (waveCounter >= waves.size()) {
            waveCounter = -1;
            isFinished = true;
        }
        else {
            prepareWave(waveCounter);
        }
    }
    
    private void prepareWave(int waveIndex) {
        if (waveCounter < 0) {
            return;
        }
        
        WaveDictionary wd = WaveDictionary.getInstance();
        currentWave = wd.getWaveInfo(waves.get(waveIndex));
        
        managedScene.spawnWave(currentWave.type);
    }
    
    @Override
    public void update(float delta) {
        if (outputLabel != null) {
            outputLabel.setText(formatWaveList());
        }
        
        if (waveCounter < 0) {
            return;
        }
        
        timePassed += delta;
        if (timePassed > currentWave.duration) {
            timePassed = timePassed - currentWave.duration;
            prepareNextWave();
        }
    }
    
    private String formatWaveList() {
        if (waves.isEmpty())
            return "";
        
        String wl = "";
        
        WaveDictionary wd = WaveDictionary.getInstance();
        WaveInfo wi;
        
        for (int i = 0; i < waves.size() - 1; ++i) {
            wi = wd.getWaveInfo(waves.get(i));
            wl += wi.name + "\n";
        }
        
        wi = wd.getWaveInfo(waves.get(waves.size() - 1));
        wl += wi.name;
        
        return wl;
    }
}
