package personalspaceinvaders.waveUtilities;

import java.util.HashMap;
import personalspaceinvaders.factories.WavesFactory.WaveType;

/**
 *
 * @author SHerbocopter
 */
public class WaveDictionary {
    private static final WaveDictionary instance = new WaveDictionary();
    private WaveDictionary() {
        initDictionary();
    }
    public static WaveDictionary getInstance() {
        return instance;
    }
    
    private HashMap<WaveType, WaveInfo> dictionary = new HashMap<>();
    
    private void initDictionary() {
        //WAVE_BASIC_BLOCK
        WaveInfo wiBasicBlock = new WaveInfo();
        wiBasicBlock.type = WaveType.WAVE_BASIC_BLOCK;
        wiBasicBlock.name = "Basic Block";
        wiBasicBlock.duration = 10;
        dictionary.put(WaveType.WAVE_BASIC_BLOCK, wiBasicBlock);
        
        //WAVE_MIXED_BLOCK
        WaveInfo wiMixedBlock = new WaveInfo();
        wiMixedBlock.type = WaveType.WAVE_MIXED_BLOCK;
        wiMixedBlock.name = "Mixed Block";
        wiMixedBlock.duration = 15;
        dictionary.put(WaveType.WAVE_MIXED_BLOCK, wiMixedBlock);
    }
    
    public WaveInfo getWaveInfo(WaveType type) {
        if (!dictionary.containsKey(type)) {
            throw new IllegalArgumentException();
        }
        
        return dictionary.get(type);
    }
}
