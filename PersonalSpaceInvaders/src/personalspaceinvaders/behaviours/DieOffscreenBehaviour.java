package personalspaceinvaders.behaviours;

import personalspaceinvaders.Behaviour;
import personalspaceinvaders.Commons;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class DieOffscreenBehaviour extends Behaviour implements Commons {
    public float upOffset = 0;
    public float downOffset = 0;
    public float leftOffset = 0;
    public float rightOffset = 0;
    
    public DieOffscreenBehaviour() {
        super();
    }
    
    public void setAllBounds(float boundsOffset) {
        upOffset = downOffset = leftOffset = rightOffset = boundsOffset;
    }
    
    @Override
    public void update(float delta) {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        float x = tr.getX();
        float y = tr.getY();
        
        if (x < PLAY_ORIGX - leftOffset ||
                x > PLAY_ORIGX + PLAY_WIDTH + rightOffset ||
                y < PLAY_ORIGY - upOffset ||
                y > PLAY_ORIGY + PLAY_HEIGHT + downOffset) {
            this.getPart().getEntity().die();
        }
    }
}
