package personalspaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import personalspaceinvaders.Scenes.GameScene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.EntityFactory.EntityType;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.parts.HitboxPart;
import personalspaceinvaders.parts.HitpointsPart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class SceneManager extends JPanel implements Runnable, Commons {
    private static SceneManager instance = null;
    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
    
    private boolean inGame = true;
    private ArrayList<Entity> entities = new ArrayList<>();
    private Thread animator;
    private Scene currentScene;
    
    private SceneManager() {
        init();
    }
    
    private void init() {
        setFocusable(true);
        setBackground(Color.BLACK);
        
        addKeyListener(KeyboardManager.getInstance());
        
        changeScene(new GameScene());
        
        gameInit();
        setDoubleBuffered(true);
    }
    
    private void gameInit() {
        if (animator == null || !inGame) {
            animator = new Thread(this);
            animator.start();
        }
    }
    
    @Override
    public void run() {
        long beforeTime, deltaTime = 0, sleepTime;
        
        beforeTime = System.currentTimeMillis();
        //int DEBUG_frame = 0;
        //float DEBUG_time = 0;
        while (inGame) {
            repaint();
            updateScene((float) deltaTime / 1000);
            
            deltaTime = System.currentTimeMillis() - beforeTime;
            sleepTime = FPS_DELAY - deltaTime;
            beforeTime = System.currentTimeMillis();
            
            if (sleepTime < 0) {
                sleepTime = 2;
            } //still needs some sleep
            //DEBUG_time += (float) deltaTime / 1000;
            //System.out.println(DEBUG_frame++ + ": " + DEBUG_time);
            try {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
        gameOver();
    }
    
    private void updateScene(float deltaTime) {
        currentScene.update(deltaTime);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        currentScene.draw(g);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    private void gameOver() {
        
    }
    
    public void changeScene(Scene newScene) {
        if (currentScene != null) {
            currentScene.unload();
        }
        
        newScene.load();
        currentScene = newScene;
    }
}
