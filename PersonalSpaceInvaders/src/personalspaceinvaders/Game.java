package personalspaceinvaders;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author SHerbocopter
 */
public class Game extends JFrame implements Commons {
    public Game() {
        initUI();
    }
    
    private void initUI() {
        GameBoard gb = new GameBoard();
        add(gb);

        setTitle("Personal Space Invaders - alpha af");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        
        setVisible(true);
        setResizable(false);
    }
}
