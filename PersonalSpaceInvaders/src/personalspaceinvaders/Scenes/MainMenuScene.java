package personalspaceinvaders.Scenes;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private class NewTrainingCommand implements Command {
        @Override
        public void execute(Object data) {
            SceneManager.getInstance().changeScene(new TrainingScene());
        }
    }
    
    private class NewHostCommand implements Command {
        @Override
        public void execute(Object data) {
            SceneManager.getInstance().changeScene(new MultiplayerScene());
        }
    }
    
    private class NewGuestCommand implements Command {
        @Override
        public void execute(Object data) {
            String hostAddress = JOptionPane.showInputDialog("Enter host IP (running on port " + MULTIPLAYER_PORT + ")");
            SceneManager.getInstance().changeScene(new MultiplayerScene(hostAddress, MULTIPLAYER_PORT));
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
        ArrayList<HudFocusablePart> focusables = new ArrayList<>();
        
        Entity buttonTraining = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpTraining = buttonTraining.get(TransformPart.class);
        tpTraining.setY(170);
        tpTraining.setX(BOARD_WIDTH / 4);
        buttonTraining.get(HudFocusablePart.class).setCommand(new NewTrainingCommand());
        buttonTraining.get(TextLabelPart.class).setText("Training");
        focusables.add(buttonTraining.get(HudFocusablePart.class));
        this.addEntity(buttonTraining);
        
        Entity buttonHost = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpHost = buttonHost.get(TransformPart.class);
        tpHost.setY(tpTraining.getY() + 60);
        tpHost.setX(tpTraining.getX());
        buttonHost.get(HudFocusablePart.class).setCommand(new NewHostCommand());
        buttonHost.get(TextLabelPart.class).setText("Host game");
        focusables.add(buttonHost.get(HudFocusablePart.class));
        this.addEntity(buttonHost);
        
        Entity buttonJoin = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpJoin = buttonJoin.get(TransformPart.class);
        tpJoin.setY(tpHost.getY() + 60);
        tpJoin.setX(tpHost.getX());
        buttonJoin.get(HudFocusablePart.class).setCommand(new NewGuestCommand());
        buttonJoin.get(TextLabelPart.class).setText("Join game");
        focusables.add(buttonJoin.get(HudFocusablePart.class));
        this.addEntity(buttonJoin);
        
        Entity buttonSettings = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpSettings = buttonSettings.get(TransformPart.class);
        tpSettings.setY(tpJoin.getY() + 60);
        tpSettings.setX(tpJoin.getX());
        buttonSettings.get(TextLabelPart.class).setText("Settings");
        focusables.add(buttonSettings.get(HudFocusablePart.class));
        this.addEntity(buttonSettings);
        
        Entity buttonExit = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpExit = buttonExit.get(TransformPart.class);
        tpExit.setY(tpSettings.getY() + 60);
        tpExit.setX(tpSettings.getX());
        buttonExit.get(HudFocusablePart.class).setCommand(new ExitGameCommand());
        buttonExit.get(TextLabelPart.class).setText("Exit");
        focusables.add(buttonExit.get(HudFocusablePart.class));
        this.addEntity(buttonExit);
        
        this.controlEntity.attach(hf.createHud(HudFactory.HudType.MAIN_MENU, focusables));
        //0090.addEntity(alfieSuperCuts); what
        
        Entity banner = ef.createEntity(EntityFactory.EntityType.MAINMENU_BANNER);
        TransformPart tpBanner = banner.get(TransformPart.class);
        tpBanner.setX(BOARD_WIDTH * 3 / 4);
        tpBanner.setY(BOARD_HEIGHT / 2);
        this.addEntity(banner);
    }
}
