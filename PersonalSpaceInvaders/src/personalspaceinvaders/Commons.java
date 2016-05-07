package personalspaceinvaders;

import java.awt.Font;

/**
 *
 * @author SHerbocopter
 */
public interface Commons {
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    public static final int PLAY_WIDTH = 600;
    public static final int PLAY_HEIGHT = 600;
    public static final int HUD_WIDTH = BOARD_WIDTH - PLAY_WIDTH;
    public static final int FPS_DELAY = 32;
    public static final Font FONT_DEFAULT = new Font("Impact", Font.PLAIN, 20);
    public static final Font FONT_SMALL = new Font("Impact", Font.PLAIN, 15);
    public static final int MAX_WAVES = 5;
}
