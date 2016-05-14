package personalspaceinvaders.networking.serializables;

import java.util.ArrayList;
import personalspaceinvaders.factories.WavesFactory.WaveType;

/**
 *
 * @author SHerbocopter
 */
public class WavesSer extends SerData {
    public ArrayList<WaveType> waveTypes;
    
    public WavesSer() {
        super();
    }
    
    public WavesSer(ArrayList<WaveType> waveTypes) {
        super();
        
        this.waveTypes = waveTypes;
    }
}
