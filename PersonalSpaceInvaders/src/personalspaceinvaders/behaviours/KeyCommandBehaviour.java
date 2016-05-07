package personalspaceinvaders.behaviours;

import java.awt.event.KeyEvent;
import personalspaceinvaders.Behaviour;
import personalspaceinvaders.Command;
import personalspaceinvaders.KeyboardManager;

/**
 *
 * @author SHerbocopter
 */
public class KeyCommandBehaviour extends Behaviour {
    private int commandKey = KeyEvent.VK_SPACE;
    private Command command = null;
    
    public KeyCommandBehaviour(int commandKey, Command command) {
        this.commandKey = commandKey;
        this.command = command;
    }
    
    public void setCommandKey(int commandKey) {
        this.commandKey = commandKey;
    }
    
    public int setCommandKey() {
        return commandKey;
    }
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public Command getCommand() {
        return command;
    }
    
    @Override
    public void update(float delta) {
        KeyboardManager km = KeyboardManager.getInstance();
        
        if (km.keyDown(commandKey)) {
            command.execute(this.part.getEntity());
        }
    }
}
