package personalspaceinvaders.commands;

import javax.swing.JOptionPane;
import personalspaceinvaders.Command;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.SceneManager;
import personalspaceinvaders.Scenes.MainMenuScene;

/**
 *
 * @author SHerbocopter
 */
public class ExitToMainMenuCommand implements Command {
    public ExitToMainMenuCommand() {
        
    }
    
    @Override
    public void execute(Object data) {
        int reply = JOptionPane.showConfirmDialog(null, "Exit to main menu?", "", JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.OK_OPTION) {
            SceneManager sm = SceneManager.getInstance();

            sm.changeScene(new MainMenuScene());
        }
        
        KeyboardManager km = KeyboardManager.getInstance();
        km.resetKeyboardManager();
    }
}
