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
import personalspaceinvaders.parts.TextLabelPart;
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
    
    private class ExitGameCommand implements Command {
        @Override
        public void execute(Object data) {
            System.exit(0);
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
        tp1.setY(120);
        tp1.setX(BOARD_WIDTH / 2);
        button1.get(HudFocusablePart.class).setCommand(new NewGameCommand());
        button1.get(TextLabelPart.class).setText("Training");
        
        Entity button2 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tp2 = button2.get(TransformPart.class);
        tp2.setY(tp1.getY() + 60);
        tp2.setX(tp1.getX());
        button2.get(TextLabelPart.class).setText("Options");
        
        Entity button3 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tp3 = button3.get(TransformPart.class);
        tp3.setY(tp2.getY() + 60);
        tp3.setX(tp2.getX());
        button3.get(HudFocusablePart.class).setCommand(new ExitGameCommand());
        button3.get(TextLabelPart.class).setText("Exit");
        
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
