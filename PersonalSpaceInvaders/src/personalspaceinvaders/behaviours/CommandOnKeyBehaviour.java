package personalspaceinvaders.behaviours;

import java.awt.event.KeyEvent;
import personalspaceinvaders.Behaviour;
import personalspaceinvaders.Command;
import personalspaceinvaders.KeyboardManager;

/**
 *
 * @author SHerbocopter
 */
public class CommandOnKeyBehaviour extends Behaviour {
    private int commandKey = KeyEvent.VK_ESCAPE;
    private Command command = null;
    private boolean executeOnce = true;
    private Object commandObject = null;
    
    public void setCommandKey(int commandKey) {
        this.commandKey = commandKey;
    }
    
    public int getCommandKey() {
        return commandKey;
    }
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public Command getCommand() {
        return command;
    }
    
    public void setExecuteOnce(boolean executeOnce) {
        this.executeOnce = executeOnce;
    }
    
    public boolean getExecuteOnce() {
        return executeOnce;
    }
    
    public void setCommandObject(Object commandObject) {
        this.commandObject = commandObject;
    }
    
    public Object getCommandObject() {
        return commandObject;
    }
    
    public CommandOnKeyBehaviour(int commandKey, Command command, boolean executeOnce) {
        this.commandKey = commandKey;
        this.command = command;
        this.executeOnce = executeOnce;
    }
    
    @Override
    public void update(float delta) {
        KeyboardManager km = KeyboardManager.getInstance();
        
        if ((executeOnce == true && km.keyDownOnce(commandKey)) ||
                (executeOnce == false && km.keyDown(commandKey))) {
            command.execute(commandObject);
        }
    }
}
