package personalspaceinvaders.hudUtilities;

import java.util.HashMap;

/**
 *
 * @author bosSHerbocopter
 */
public class HudGraph {
    /*spec_public*/private boolean isActive = true;
    /*spec_public*/private boolean isVisible = true;
    
    /*spec_public*/private HashMap<String, HudNode> graph = new HashMap<>();
    
    //@public normal_behaviour
    //@ensures \result == isActive;
    
    public boolean isActive() {
        return isActive;
    }
    
    //@public normal_behaviour
    //@assignable this.isActive;
    //@ensures this.isActive == isActive;
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    //@public normal_behaviour
    //@ensures \result == isVisible;
    
    public boolean isVisible() {
        return isVisible;
    }
    
    //@public normal_behaviour
    //@assignable this.isVisible;
    //@ensures this.isVisible == isVisible;
    
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    //@public normal_behaviour
    //@requires graph.containsKey(nodeKey);
    //@ensures \result == true;
    //@also
    //@requires !graph.containsKey(nodeKey);
    //@ensures \result == false;
    
    
    public boolean has(String nodeKey) {
        return graph.containsKey(nodeKey);
    }
    
    //@requires this.has(nodeKey);
    //@ensures \result == graph.get(nodeKey);
    //@also
    //@requires !this.has(nodeKey);
    //@signals_only IllegalArgumentException;
    
    public HudNode get(String nodeKey) {
        if (!this.has(nodeKey)) {
            throw new IllegalArgumentException();
        }
        
        return graph.get(nodeKey);
    }
    
    //@requires !this.has(nodeKey);
    //@ensures graph.put(nodeKey, node);
    //@also
    //@requires this.has(nodeKey);
    //@signals_only IllegalArgumentException;
    
    public void attach(String nodeKey, HudNode node) {
        if (this.has(nodeKey)) {
            throw new IllegalArgumentException();
        }
        
        graph.put(nodeKey, node);
    }
    
}
