package personalspaceinvaders.behaviours;

import personalspaceinvaders.Behaviour;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class FlyUpBehaviour extends Behaviour {
    private float speed;
    
    /**
     * In a serious need of physics!
     */
    
    public FlyUpBehaviour(float speed) {
        this.speed = speed;
    }

    @Override
    public void update(float delta) {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        
        tr.setY(tr.getY() - speed * delta);
    }
}
