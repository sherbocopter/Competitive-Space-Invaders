package personalspaceinvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author SHerbocopter
 * http://www.gamedev.net/page/resources/_/technical/general-programming/java-games-keyboard-and-mouse-r2439
 */
public class KeyboardManager implements KeyListener {
    private static final int KEY_COUNT = 256;
    private boolean[] currentKeys = null;
    private KeyState[] keys = null;
    
    private enum KeyState {
        RELEASED,
        PRESSED,
        ONCE
    }
    
    private static KeyboardManager instance = new KeyboardManager();
    
    private KeyboardManager() {
        currentKeys = new boolean[KEY_COUNT];
        keys = new KeyState[KEY_COUNT];
        for (int i = 0; i < KEY_COUNT; ++i) {
            keys[i] = KeyState.RELEASED;
        }
    }
    
    public static KeyboardManager getInstance() {
        return instance;
    }
    
    public synchronized void poll() {
        for (int i = 0; i < KEY_COUNT; ++i) {
            if (currentKeys[i]) {
                if (keys[i] == KeyState.RELEASED) {
                    keys[i] = KeyState.ONCE;
                }
                else {
                    keys[i] = KeyState.PRESSED;
                }
            }
            else {
                keys[i] = KeyState.RELEASED;
            }
        }
    }
    
    public boolean keyDown(int keyCode) {
        if (keyCode < 0 || keyCode >= KEY_COUNT) {
            throw new IllegalArgumentException();
        }
        
        return keys[keyCode] == KeyState.ONCE || keys[keyCode] == KeyState.PRESSED;
    }
    
    public boolean keyDownOnce(int keyCode) {
        if (keyCode < 0 || keyCode >= KEY_COUNT) {
            throw new IllegalArgumentException();
        }
        
        return keys[keyCode] == KeyState.ONCE;
    }
    
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            currentKeys[keyCode] = true;
        }
    }
    
    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            currentKeys[keyCode] = false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
