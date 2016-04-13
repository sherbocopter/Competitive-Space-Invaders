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
        WAVE_BASIC_BLOCK
    }
    
    //
    //ideally, waves should be built using some sort
    //of monsters map (type and transform etc)
    //
    
    //PRIMITIVES begin
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
            
            entity.setActive(true);
            entity.setActive(false);
            
            entities.add(entity);
        }
        
        return entities;
    }
    
    public ArrayList<Entity> createCircle(EntityType type, int count, float ray) {
        //not yet supported
        return null;
    }
    //PRIMITIVES end
    
    public ArrayList<Entity> createWave(WaveType type) {
        switch(type) {
            case WAVE_BASIC_BLOCK: {
                return createBasicBlock();
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
        float top = 100;
        float bottom = 250;
        int horCount = 5;
        int verCount = 3;
        
        float stepX = (right - left) / (horCount - 1);
        float stepY = (bottom - top) / (verCount - 1);
        
        for (int i = 1; i <= verCount; ++i) {
            entities.addAll(createLine(EntityType.ALIEN_BASIC_WHITE, horCount,
                                left, i * stepY, right, i * stepY));
        }
        
        return entities;
    }
}
