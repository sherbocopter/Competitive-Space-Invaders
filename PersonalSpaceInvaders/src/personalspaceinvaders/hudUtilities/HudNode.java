package personalspaceinvaders.hudUtilities;

import personalspaceinvaders.parts.HudFocusablePart;

/**
 *
 * @author SHerbocopter
 */
public class HudNode {
    private HudFocusablePart managedFocusable;
    public HudNode upNode = null;
    public HudNode downNode = null;
    public HudNode leftNode = null;
    public HudNode rightNode = null;
    
    public HudNode() {
        
    }
    
    public HudFocusablePart getManagedFocusable() {
        return managedFocusable;
    }
    
    public void setManagedFocusable(HudFocusablePart managedFocusable) {
        this.managedFocusable = managedFocusable;
    }
    
    public void setFocus(boolean isFocused) {
        this.managedFocusable.setFocused(isFocused);
    }
    
    public void performFocusableAction() {
        //sets the shouldPerform in the managedFocusable
    }
}
