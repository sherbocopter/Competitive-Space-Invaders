package personalspaceinvaders.waveUtilities;

import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;

/**
 *
 * @author SHerbocopter
 */
public class WaveInfo {
    public WaveType type = WaveType.DUMMY;
    public String name = "";
    public float duration = 0;
    
    public boolean equals(Object other)
    {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof WaveInfo))return false;
    WaveInfo otherMyClass = (WaveInfo)other;
        if(this.type != otherMyClass.getType())
        {
            return false;
        }
        if(this.name != otherMyClass.getName())
        {
            return false;
        }
        if(this.getDuration() != otherMyClass.getDuration())
        {
            return false;
        }
        return true;
    }

    public WaveType getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public float getDuration()
    {
        return duration;
    }
    
    
}
