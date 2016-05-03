package personalspaceinvaders.Scenes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import personalspaceinvaders.Entity;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.HudFactory;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class GameScene extends Scene {
    private ArrayList<Entity> entities = new ArrayList<>();
    private Entity controlEntity = new Entity();
    
    @Override
    public void load() {
        temporaryEntitiesInit();
    }
    
    @Override
    public void unload() {
        
    }
    
    @Override
    public void update(float delta) {
        KeyboardManager km = KeyboardManager.getInstance();
        km.poll();
        
        controlEntity.update(delta);
        
        for (Entity entity : entities) {
            if (entity.isActive()) {
                entity.update(delta);
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        for (Entity entity : entities) {
            if (entity.isVisible()) {
                entity.draw(g2d);
            }
        }
    }
    
    private void temporaryEntitiesInit() {
        WavesFactory wf = WavesFactory.getInstance();
        EntityFactory ef = EntityFactory.getInstance();
        
        entities.addAll(wf.createWave(WavesFactory.WaveType.WAVE_MIXED_BLOCK));
        entities.add(ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC));
        
        temporaryMenuInit();
    }
    
    private void temporaryMenuInit() {
        if (this.controlEntity.has(HudManagerPart.class)) {
            throw new IllegalArgumentException();
        }
        
        EntityFactory ef = EntityFactory.getInstance();
        HudFactory hf = HudFactory.getInstance();
        
        Entity button1 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tp1 = button1.get(TransformPart.class);
        tp1.setY(60);
        Entity button2 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tp2 = button2.get(TransformPart.class);
        tp2.setY(tp1.getY() + 60);
        Entity button3 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tp3 = button3.get(TransformPart.class);
        tp3.setY(tp2.getY() + 60);
        
        entities.add(button1);
        entities.add(button2);
        entities.add(button3);
        
        ArrayList<HudFocusablePart> buttons = new ArrayList<>();
        buttons.add(button1.get(HudFocusablePart.class));
        buttons.add(button2.get(HudFocusablePart.class));
        buttons.add(button3.get(HudFocusablePart.class));
        
        this.controlEntity.attach(hf.createHud(HudFactory.HudType.TEST_3R1C, buttons));
    }
}
