package personalspaceinvaders.factories;

import java.util.ArrayList;
import personalspaceinvaders.hudUtilities.HudGraph;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.hudUtilities.HudNode;
import personalspaceinvaders.parts.HudFocusablePart;

/**
 *
 * @author SHerbocopter
 */
public class HudFactory {
    private static final HudFactory instance = new HudFactory();
    private HudFactory() { }
    public static HudFactory getInstance() {
        return instance;
    }
    
    public static enum HudType {
        TEST_3R1C //3 rows, 1 column
    }
    
    public HudManagerPart createHud(HudType type, ArrayList<HudFocusablePart> focusables) {
        switch(type) {
            case TEST_3R1C: {
                return createHud3R1C(focusables);
            }
            default: {
                throw new IllegalArgumentException("hudType not found");
            }
        }
    }
    
    private HudManagerPart createHud3R1C(ArrayList<HudFocusablePart> focusables) {
        if (focusables.size() != 3) {
            throw new IllegalArgumentException("Invalid focusables count");
        }
        
        HudManagerPart hudManagerPart = new HudManagerPart();
        HudGraph graph = new HudGraph();
        
        HudNode node1 = new HudNode();
        HudNode node2 = new HudNode();
        HudNode node3 = new HudNode();
        
        node1.setManagedFocusable(focusables.get(0));
        node2.setManagedFocusable(focusables.get(1));
        node3.setManagedFocusable(focusables.get(2));
        
        node1.upNode = node3;
        node1.downNode = node2;
        
        node2.upNode = node1;
        node2.downNode = node3;
        
        node3.upNode = node2;
        node3.downNode = node1;
        
        graph.attach("button1", node1);
        graph.attach("button2", node2);
        graph.attach("button3", node3);
        
        hudManagerPart.setHudGraph(graph);
        hudManagerPart.setCurrentNode(node1);
        return hudManagerPart;
    }
}
