package personalspaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import personalspaceinvaders.parts.HitboxPart;
import personalspaceinvaders.parts.HitpointsPart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class GameBoard extends JPanel implements Runnable, Commons {
    private boolean inGame = true;
    
    private ArrayList<Entity> entities = new ArrayList<>();
    
    private Thread animator;
    
    public GameBoard() {
        init();
    }
    
    private void init() {
        setFocusable(true);
        setBackground(Color.BLACK);
        
        gameInit();
        setDoubleBuffered(true);
    }
    
    private void gameInit() {
        temporaryEntitiesInit();
        
        if (animator == null || !inGame) {
            animator = new Thread(this);
            animator.start();
        }
    }
    
    @Override
    public void run() {
        long beforeTime, deltaTime, sleepTime;
        
        beforeTime = System.currentTimeMillis();
        
        while (inGame) {
            repaint();
            
            deltaTime = System.currentTimeMillis() - beforeTime;
            sleepTime = FPS_DELAY - deltaTime;
            
            updateEntities(deltaTime);
            
            if (sleepTime < 0) {
                sleepTime = 2;
            } //still needs some sleep
            
            try {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            beforeTime = System.currentTimeMillis();
        }
        
        gameOver();
    }
    
    private void updateEntities(float deltaTime) {
        for (Entity entity : entities) {
            if (entity.isActive()) {
                entity.update(deltaTime);
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        for (Entity entity : entities) {
            entity.draw(g2d);
        }
    }
    
    private void gameOver() {
        
    }
    
    //
    //  should use a factory class instead
    //
    private Entity createAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(100, 100, 0, 1));
        alien.attach(new HitboxPart(0, 0, 0, 10, 10));
        alien.attach(new HitpointsPart(100));
        
        return alien;
    }
    
    private void temporaryEntitiesInit() {
        entities.add(createAlien());
    }
}
