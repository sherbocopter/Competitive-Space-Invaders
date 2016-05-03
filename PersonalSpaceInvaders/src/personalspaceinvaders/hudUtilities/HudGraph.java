package personalspaceinvaders.hudUtilities;

import java.util.HashMap;

/**
 *
 * @author SHerbocopter
 */
public class HudGraph {
    private boolean isActive = true;
    private boolean isVisible = true;
    
    private HashMap<String, HudNode> graph = new HashMap<>();
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public boolean has(String nodeKey) {
        return graph.containsKey(nodeKey);
    }
    
    public HudNode get(String nodeKey) {
        if (!this.has(nodeKey)) {
            throw new IllegalArgumentException();
        }
        
        return graph.get(nodeKey);
    }
    
    public void attach(String nodeKey, HudNode node) {
        if (this.has(nodeKey)) {
            throw new IllegalArgumentException();
        }
        
        graph.put(nodeKey, node);
    }
    
    
}
