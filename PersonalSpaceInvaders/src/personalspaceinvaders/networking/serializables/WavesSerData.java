package personalspaceinvaders.networking.serializables;

import java.util.ArrayList;
import personalspaceinvaders.factories.WavesFactory.WaveType;

/**
 *
 * @author SHerbocopter
 */
public class WavesSerData extends SerData {
    public ArrayList<WaveType> waveTypes;
    
    public WavesSerData() {
        super();
    }
    
    public WavesSerData(ArrayList<WaveType> waveTypes) {
        super();
        
        this.waveTypes = waveTypes;
    }
}
