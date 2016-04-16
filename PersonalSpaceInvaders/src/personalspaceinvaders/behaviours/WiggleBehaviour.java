package personalspaceinvaders.behaviours;

import personalspaceinvaders.Behaviour;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class WiggleBehaviour extends Behaviour {
    private float speed;
    private float range;
    private TransformPart localTr;
    private WiggleState state;
    
    private enum WiggleState {
        ST1_CR, //center -> right
        ST2_RL, //right -> left
        ST3_LC  //left -> center
    }
    
     /**
     * Example behaviour.
     * Moves left and right.
     */
    
    public WiggleBehaviour(float speed, float range) {
        this.speed = speed;
        this.range = range;
        
        localTr = new TransformPart(0, 0, 0, 1);
        state = WiggleState.ST1_CR;
    }
    
    @Override
    public void update(float delta) {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        
        switch(state) {
            case ST1_CR: {
                if (localTr.getX() > range) {
                    progressState();
                    break;
                }
                
                tr.setX(tr.getX() + delta * speed);
                localTr.setX(localTr.getX() + delta * speed);
            } break;
            case ST2_RL: {
                if (localTr.getX() < -range) {
                    progressState();
                    break;
                }
                
                tr.setX(tr.getX() - delta * speed);
                localTr.setX(localTr.getX() - delta * speed);
            } break;
            case ST3_LC: {
                if (localTr.getX() > 0) {
                    progressState();
                    break;
                }
                
                tr.setX(tr.getX() + delta * speed);
                localTr.setX(localTr.getX() + delta * speed);
            }
            default: { }
        }
    }
    
    private void progressState() {
        finishCurrentState();
        prepareCurrentState();
    }
    
    private void finishCurrentState() {
        switch(state) {
            case ST1_CR: {
                state = WiggleState.ST2_RL;
            } break;
            case ST2_RL: {
                state = WiggleState.ST3_LC;
            } break;
            case ST3_LC: {
                state = WiggleState.ST1_CR;
                //here goes callback for completed cycle
            }
            default: { }
        }
    }
    
    private void prepareCurrentState() {
        TransformPart tr = this.getPart().getEntity().get(TransformPart.class);
        
        switch(state) {
            case ST1_CR: {
                float deltaX = 0 - localTr.getX();
                
                tr.setX(tr.getX() + deltaX);
                localTr.setX(localTr.getX() + deltaX);
            } break;
            case ST2_RL: {
                float deltaX = range - localTr.getX();
                
                tr.setX(tr.getX() + deltaX);
                localTr.setX(localTr.getX() + deltaX);
            } break;
            case ST3_LC: {
                float deltaX = -range - localTr.getX();
                
                tr.setX(tr.getX() + deltaX);
                localTr.setX(localTr.getX() + deltaX);
            }
            default: { }
        }
    }
}
