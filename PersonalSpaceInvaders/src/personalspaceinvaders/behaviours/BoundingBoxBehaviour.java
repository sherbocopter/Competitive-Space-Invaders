package personalspaceinvaders.behaviours;

import personalspaceinvaders.Behaviour;
import personalspaceinvaders.Commons;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class BoundingBoxBehaviour extends Behaviour implements Commons {
    private float boundsInset = 0;
    
    public BoundingBoxBehaviour() {
        super();
    }
    
    public float getBoundsInset() {
        return boundsInset;
    }
    
    public void setBoundsInset(float boundsInset) {
        this.boundsInset = boundsInset;
    }
    
    @Override
    public void update(float delta) {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        
        if (tr.getX() <= PLAY_ORIGX + boundsInset) {
            tr.setX(PLAY_ORIGX + boundsInset);
        }
        
        if (tr.getX() >= PLAY_ORIGX + PLAY_WIDTH - boundsInset) {
            tr.setX(PLAY_ORIGX + PLAY_WIDTH - boundsInset);
        }
        
        if (tr.getY() <= PLAY_ORIGY + boundsInset) {
            tr.setY(PLAY_ORIGY + boundsInset);
        }
        
        if (tr.getY() >= PLAY_ORIGX + PLAY_HEIGHT - boundsInset) {
            tr.setY(PLAY_ORIGX + PLAY_HEIGHT - boundsInset);
        }
    }
}
