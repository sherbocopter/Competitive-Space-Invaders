package personalspaceinvaders.spriteUtilities;

import javax.swing.ImageIcon;

/**
 *
 * @author SHerbocopter
 */
public class SpriteInfo {
    public String path = "";
    public ImageIcon imageIcon = null;
    
    public SpriteInfo(String path) {
        this.path = path;
    }
    
    public void loadImageIcon() {
        if (imageIcon != null) {
            return;
        }
        
        imageIcon = new ImageIcon(path);
    }
}
