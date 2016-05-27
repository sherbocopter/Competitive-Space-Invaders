package personalspaceinvaders.factories;

import java.util.ArrayList;
import personalspaceinvaders.Commons;
import personalspaceinvaders.Entity;
import personalspaceinvaders.factories.EntityFactory.EntityType;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class WavesFactory implements Commons {
    private static final WavesFactory instance = new WavesFactory();
    private WavesFactory() { }
    public static WavesFactory getInstance() {
        return instance;
    }
    
    public static enum WaveType {
        WAVE_BASIC_BLOCK,
        WAVE_MIXED_BLOCK,
        DUMMY
    }
    
    //
    //ideally, waves should be built using some sort
    //of monsters map (type and transform etc)
    //
    
    //<editor-fold defaultstate="collapsed" desc="PRIMITIVES">
    public ArrayList<Entity> createLine(EntityType type, int count, float x1, float y1,
            float x2, float y2) {
        if (count < 2) {
            throw new IllegalArgumentException();
        }
        
        float stepX = (x2 - x1) / (count - 1);
        float stepY = (y2 - y1) / (count - 1);
        
        EntityFactory ef = EntityFactory.getInstance();
        ArrayList<Entity> entities = new ArrayList<>();
        
        for (int i = 0; i < count; ++i) {
            Entity entity = ef.createEntity(type);
            
            TransformPart transform = entity.get(TransformPart.class);
            transform.setX(x1 + i * stepX);
            transform.setY(y1 + i * stepY);
            
            entities.add(entity);
        }
        
        return entities;
    }
    
    public ArrayList<Entity> createCircle(EntityType type, int count, float ray) {
        //not yet supported
        return null;
    }
//</editor-fold>
    
    public ArrayList<Entity> createWave(WaveType type) {
        switch(type) {
            case WAVE_BASIC_BLOCK: {
                return createBasicBlock();
            }
            case WAVE_MIXED_BLOCK: {
                return createMixedBlock();
            }
            default: {
                throw new IllegalArgumentException("waveType not found");
            }
        }
    }
    
    private ArrayList<Entity> createBasicBlock() {
        ArrayList<Entity> entities = new ArrayList<>();
        
        float left = 100;
        float right = 540;
        float top = -520;
        float bottom = -20;
        int horCount = 5;
        int verCount = 4;
        
        float stepX = (right - left) / (horCount - 1);
        float stepY = (bottom - top) / (verCount - 1);
        
        for (int i = 1; i <= verCount; ++i) {
            entities.addAll(createLine(EntityType.ALIEN_BASIC_WHITE, horCount,
                                left, top + (i - 1) * stepY, right, top + (i - 1) * stepY));
        }
        
        return entities;
    }
    
    private ArrayList<Entity> createMixedBlock() {
        ArrayList<Entity> entities = new ArrayList<>();
        
        float left = 100;
        float right = 540;
        float top = -320;
        float bottom = -20;
        int horCount = 5;
        int verCount = 4;
        
        float stepX = (right - left) / (horCount - 1);
        float stepY = (bottom - top) / (verCount - 1);
        
        entities.addAll(createLine(EntityType.ALIEN_BASIC_WHITE, horCount,
                                left, top + 0 * stepY, right, top + 0 * stepY));
        entities.addAll(createLine(EntityType.ALIEN_BASIC_GREEN, horCount,
                                left, top + 1 * stepY, right, top + 2 * stepY));
        entities.addAll(createLine(EntityType.ALIEN_BASIC_RED, horCount,
                                left, top + 3 * stepY, right, top + 3 * stepY));
        
        return entities;
    }
}
