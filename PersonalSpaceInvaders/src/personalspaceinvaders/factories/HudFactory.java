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
        TEST_3R1C, //3 rows, 1 column
        MAIN_MENU,
        WAVE_SELECTOR
    }
    
    public HudManagerPart createHud(HudType type, ArrayList<HudFocusablePart> focusables) {
        switch(type) {
            case TEST_3R1C: {
                return createHud3R1C(focusables);
            }
            case MAIN_MENU: {
                return createMainMenu(focusables);
            }
            case WAVE_SELECTOR: {
                return createWaveSelector(focusables);
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
    
    private HudManagerPart createMainMenu(ArrayList<HudFocusablePart> focusables) {
        int cnt = 5;
        
        if (focusables.size() != cnt) {
            throw new IllegalArgumentException("Invalid focusables count");
        }
        
        HudManagerPart hudManagerPart = new HudManagerPart();
        HudGraph graph = new HudGraph();
        
        HudNode[] nodes = new HudNode[cnt];
        for (int i = 0; i < cnt; ++i) {
            nodes[i] = new HudNode();
        }
        
        for (int i = 0; i < cnt; ++i) {
            nodes[i].setManagedFocusable(focusables.get(i));
            int prev = (cnt + i - 1) % cnt;
            int next = (i + 1) % cnt;
            
            nodes[i].upNode = nodes[i].leftNode = nodes[prev];
            nodes[i].downNode = nodes[i].rightNode = nodes[next];
            
            graph.attach("button" + i, nodes[i]);
        }
        
        hudManagerPart.setHudGraph(graph);
        hudManagerPart.setCurrentNode(nodes[0]);
        return hudManagerPart;
    }
    
    private HudManagerPart createWaveSelector(ArrayList<HudFocusablePart> focusables) {
        int cnt = 8;
        if (focusables.size() != cnt) {
            throw new IllegalArgumentException("Invalid focusables count");
        }
        
        HudManagerPart hudManagerPart = new HudManagerPart();
        HudGraph graph = new HudGraph();
        
        ArrayList<HudNode> nodes = new ArrayList<>();
        for (int i = 0; i < cnt; ++i) {
            nodes.add(new HudNode());
        }
        
        for (int i = 0; i < cnt; ++i) {
            HudNode node = nodes.get(i);
            node.setManagedFocusable(focusables.get(i));
            
            node.upNode = nodes.get((cnt + i - 2) % cnt);
            node.downNode = nodes.get((i + 2) % cnt);
            node.leftNode = nodes.get((cnt + i - 1) % cnt);
            node.rightNode = nodes.get((i + 1) % cnt);
            
            graph.attach("button" + i, node);
        }
        
        hudManagerPart.setHudGraph(graph);
        hudManagerPart.setCurrentNode(nodes.get(0));
        return hudManagerPart;
    }
}
