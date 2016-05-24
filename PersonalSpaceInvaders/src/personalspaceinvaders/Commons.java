package personalspaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 *
 * @author SHerbocopter
 */
public interface Commons {
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    public static final int PLAY_ORIGX = 0;
    public static final int PLAY_ORIGY = 0; //TODO: proper use of PLAY_ORIGs
    public static final int PLAY_WIDTH = 580;
    public static final int PLAY_HEIGHT = 580;
    public static final int HUD_WIDTH = BOARD_WIDTH - PLAY_WIDTH;
    public static final int FPS_DELAY = 32;
    public static final Font FONT_DEFAULT = new Font("Impact", Font.PLAIN, 20);
    public static final Font FONT_SMALL = new Font("Impact", Font.PLAIN, 15);
    
    public static final Color COLOR_BUTTON = new Color(0xB34FFF);
    public static final Color COLOR_HIGHLIGHT = new Color(0x78D685);
    
    public static final int MAX_WAVES = 5;
    public static final int MAX_ROUNDS = 3;
    
    public static final int MULTIPLAYER_PORT = 9093;
    public static final int POLLING_TIME = 2000;
    public static final int POLLING_TRIES = 10;
    
    public static final int KEY_EXIT_TO_MENU = KeyEvent.VK_ESCAPE;
    public static final int KEY_PAUSE = KeyEvent.VK_P;
}
