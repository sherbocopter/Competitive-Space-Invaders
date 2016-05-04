package personalspaceinvaders.Scenes;

import java.awt.Graphics;
import java.util.ArrayList;
import personalspaceinvaders.Command;
import personalspaceinvaders.Entity;
import personalspaceinvaders.Scene;
import personalspaceinvaders.SceneManager;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.HudFactory;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class MainMenuScene extends Scene {
    private class NewGameCommand implements Command {
        @Override
        public void execute(Object data) {
            SceneManager.getInstance().changeScene(new GameScene());
        }
    }
    
    @Override
    public void load() {
        loadMainMenu();
    }
    
    @Override
    public void unload() {
        
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    
    private void loadMainMenu() {
        if (this.controlEntity.has(HudManagerPart.class)) {
            throw new IllegalArgumentException();
        }
        
        EntityFactory ef = EntityFactory.getInstance();
        HudFactory hf = HudFactory.getInstance();
        
        Entity button1 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tp1 = button1.get(TransformPart.class);
        tp1.setY(60);
        button1.get(HudFocusablePart.class).setCommand(new NewGameCommand());
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
