package personalspaceinvaders.hudUtilities;

import com.sun.glass.events.KeyEvent;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class HudManagerPart extends Part {
    //might add a list of hudgraphs, so that one can easily switch
    private HudGraph hudGraph = null;
    private HudNode currentNode = null;
    private int upKey = KeyEvent.VK_UP;
    private int downKey = KeyEvent.VK_DOWN;
    private int leftKey = KeyEvent.VK_LEFT;
    private int rightKey = KeyEvent.VK_RIGHT;
    private int selectKey = KeyEvent.VK_ENTER;
    
    public HudGraph getHudGraph() {
        return hudGraph;
    }
    
    public void setHudGraph(HudGraph hudGraph) {
        this.hudGraph = hudGraph;
    }
    
    public void setCurrentNode(HudNode currentNode) {
        this.currentNode = currentNode;
        this.currentNode.setFocus(true);
    }
    
    @Override
    public void update(float delta) {
        if (hudGraph == null || currentNode == null)
            return;
        
        KeyboardManager km = KeyboardManager.getInstance();
        
        if (km.keyDownOnce(upKey) && currentNode.upNode != null) {
            focus(currentNode.upNode);
        }
        else if (km.keyDownOnce(downKey) && currentNode.downNode != null) {
            focus(currentNode.downNode);
        }
        else if (km.keyDownOnce(leftKey) && currentNode.leftNode != null) {
            focus(currentNode.leftNode);
        }
        else if (km.keyDownOnce(rightKey) && currentNode.rightNode != null) {
            focus(currentNode.rightNode);
        }
        else if (km.keyDownOnce(selectKey)) {
            currentNode.executeCommand();
        }
    }
    
    private void focus(HudNode newNode) {
        currentNode.setFocus(false);
        currentNode = newNode;
        currentNode.setFocus(true);
    }
}
