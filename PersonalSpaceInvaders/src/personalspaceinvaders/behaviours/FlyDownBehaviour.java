package personalspaceinvaders.behaviours;

import personalspaceinvaders.Behaviour;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class FlyDownBehaviour extends Behaviour {
    private float speed;
    
    /**
     * Example behaviour.
     * Entity flies down like a dead pidgeon.
     */
    
    public FlyDownBehaviour(float speed) {
        this.speed = speed;
    }

    @Override
    public void update(float delta) {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        
        tr.setY(tr.getY() + speed * delta);
    }
}