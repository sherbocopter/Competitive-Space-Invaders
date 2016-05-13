package personalspaceinvaders.waveUtilities;

import personalspaceinvaders.Commons;
import personalspaceinvaders.Part;
import personalspaceinvaders.Scene;

/**
 *
 * @author SHerbocopter
 */
public class MultiplayerWaveManagerPart  extends Part implements Commons {
    public WaveManagerPart localEncounter;  //waves from peer
    public WaveManagerPart remoteEncounter; //wavet to peer
    private Scene managedScene;
    public boolean haveNewWaves = false;
    
    public MultiplayerWaveManagerPart(Scene managedScene) {
        this.managedScene = managedScene;
        localEncounter = new WaveManagerPart(managedScene);
        remoteEncounter = new WaveManagerPart(managedScene);
    }
    
    public void update(float delta) {
        localEncounter.update(delta);
        remoteEncounter.update(delta);
    }
}
