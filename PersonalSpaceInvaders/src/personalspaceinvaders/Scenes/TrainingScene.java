package personalspaceinvaders.Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import personalspaceinvaders.Command;
import personalspaceinvaders.Entity;
import personalspaceinvaders.Scene;
import personalspaceinvaders.behaviours.CommandOnKeyBehaviour;
import personalspaceinvaders.commands.ExitToMainMenuCommand;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.HudFactory;
import personalspaceinvaders.factories.HudFactory.HudType;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.parts.ControllerPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.StatsPart;
import personalspaceinvaders.parts.TextLabelPart;
import personalspaceinvaders.parts.TransformPart;
import personalspaceinvaders.waveUtilities.WaveManagerPart;

/**
 *
 * @author SHerbocopter
 */
public class TrainingScene extends GameSceneBase {
    private class PushWaveToStackCommand implements Command {
        private WaveType waveType = WaveType.DUMMY;
        private TrainingScene scene = null;
        
        public PushWaveToStackCommand(WaveType waveType, TrainingScene scene) {
            super();
            
            this.scene = scene;
            this.waveType = waveType;
        }
        
        @Override
        public void execute(Object data) {
            WavesFactory wf = WavesFactory.getInstance();
            
            //scene.addEntities(wf.createWave(waveType)); //to be changed
            WaveManagerPart wmp = scene.controlEntity.get(WaveManagerPart.class);
            wmp.pushWave(waveType);
        }
    }
    
    private class PopWaveFromQueueCommand implements Command {
        private TrainingScene scene = null;
        
        public PopWaveFromQueueCommand(TrainingScene scene) {
            super();
            
            this.scene = scene;
        }
        
        @Override
        public void execute(Object data) {
            WaveManagerPart wmp = scene.controlEntity.get(WaveManagerPart.class);
            wmp.popWave();
        }
    }
    
    private class SendWavesCommand implements Command {
        private TrainingScene scene = null;
        
        public SendWavesCommand(TrainingScene scene) {
            super();
            
            this.scene = scene;
        }
        
        @Override
        public void execute(Object data) {
            WaveManagerPart wmp = scene.controlEntity.get(WaveManagerPart.class);
            wmp.start();
            
            scene.setState(TrainingState.PLAY);
        }
    }
    
    private enum TrainingState {
        SELECT_WAVES,
        PLAY,
        DUMMY
    }
    
    public TrainingScene() {
        super();
    }
    
    private TrainingState gameState = TrainingState.DUMMY;
    private HashMap<TrainingState, ArrayList<Entity>> stateEntities = new HashMap<>();
    
//special entities (also added in entities array)
    private Entity playerShip;
    private Entity statusLabel;
    
    
    //<editor-fold defaultstate="collapsed" desc="TrainingState utils">
    private void updateState() {
        boolean shouldSwitch = checkStateEndCondition(gameState);
        if (shouldSwitch) {
            switch (gameState) {
                case SELECT_WAVES: {
                    setState(TrainingState.PLAY);
                } break;
                case PLAY: {
                    setState(TrainingState.SELECT_WAVES);
                } break;
                default: {

                } break;
            }
        }
    }
    
    private boolean checkStateEndCondition(TrainingState state) {
        try {
            switch (state) {
                case SELECT_WAVES: {
                    
                } break;
                case PLAY: {
                    WaveManagerPart wmp = this.controlEntity.get(WaveManagerPart.class);
                    ArrayList<Entity> aliens = this.stateEntities.get(TrainingState.PLAY);
                    return (wmp.isFinished == true &&
                            aliens.isEmpty());
                }
                default: {

                } break;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    private void setState(TrainingState newState) {
        exitState(gameState);
        gameState = newState;
        enterState(gameState);
    }
    
    private void enterState(TrainingState state) {
        for (Entity entity : stateEntities.get(state)) {
            entity.setActive(true);
            entity.setVisible(true);
        }
        
        switch (state) {
            case SELECT_WAVES: {
                HudManagerPart hudManager = this.controlEntity.get(HudManagerPart.class);
                hudManager.setActive(true);
            } break;
            case PLAY: {
                WaveManagerPart wmp = this.controlEntity.get(WaveManagerPart.class);
                
                wmp.start();
                
                TextLabelPart textLabelPart = this.statusLabel.get(TextLabelPart.class);
                StatsPart shipStats = this.playerShip.get(StatsPart.class);
                textLabelPart.setText("HP: " + (int)shipStats.getCurrentHitpoints() +
                                        " / " + (int)shipStats.maxHitpoints);
            } break;
            default: {
                TextLabelPart textLabelPart = this.statusLabel.get(TextLabelPart.class);
                textLabelPart.setText("Something went wrong...");
            } break;
        }
    }
    
    private void exitState(TrainingState state) {
        for (Entity entity : stateEntities.get(state)) {
            entity.setActive(false);
            entity.setVisible(false);
        }
        
        resetStatusLabel();
        
        switch (state) {
            case SELECT_WAVES: {
                HudManagerPart hudManager = this.controlEntity.get(HudManagerPart.class);
                hudManager.setActive(false);
            } break;
            case PLAY: {
                
            } break;
            default: {
                
            } break;
        }
    }
//</editor-fold>

    
    @Override
    public void load() {
        for (TrainingState state : TrainingState.values()) {
            stateEntities.put(state, new ArrayList<>());
        }
        
        initSceneController();
        otherEntitiesInit();
        initWaveSelector();
        
        for (TrainingState state : TrainingState.values()) {
            exitState(state);
        }
        
        setState(TrainingState.SELECT_WAVES);
    }
    
    @Override
    public void unload() {
        
    }
    
    @Override
    public void update(float delta) {
        updateState();
        
        super.update(delta);
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    
    @Override
    public void spawnWave(WaveType type) {
        WavesFactory wf = WavesFactory.getInstance();
        
        ArrayList<Entity> aliens = wf.createWave(type);
        
        this.addEntities(aliens);
        this.stateEntities.get(TrainingState.PLAY).addAll(aliens);
    }
    
    @Override
    public void removeEntity(Entity entity) {
        super.removeEntity(entity);
        
        ArrayList<Entity> aliens = this.stateEntities.get(TrainingState.PLAY);
        if (aliens.contains(entity)) {
            aliens.remove(entity);
        }
    }
    
    private void otherEntitiesInit() {
        EntityFactory ef = EntityFactory.getInstance();
        
        //player
        Entity player = ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC);
        this.addEntity(player);
        this.playerShip = player;
        
        //status label
        Entity status = ef.createEntity(EntityFactory.EntityType.LABEL_BASIC);
        TransformPart tpStatus = status.get(TransformPart.class);
        tpStatus.setX(BOARD_WIDTH - 130);
        tpStatus.setY(BOARD_HEIGHT / 2);
        TextLabelPart tlpStatus = status.get(TextLabelPart.class);
            tlpStatus.setText("");
            tlpStatus.setColor(Color.WHITE);
        this.addEntity(status);
        this.statusLabel = status;
    }
    
    private void initWaveSelector() {
        if (this.controlEntity.has(HudManagerPart.class)) {
            throw new IllegalArgumentException();
        }
        
        ArrayList<Entity> selectWaveEntities = stateEntities.get(TrainingState.SELECT_WAVES);
        EntityFactory ef = EntityFactory.getInstance();
        
        //<editor-fold defaultstate="collapsed" desc="Buttons">
        HudFactory hf = HudFactory.getInstance();
        ArrayList<HudFocusablePart> buttons = new ArrayList<>();
        
        Entity buttonWave1 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave1 = buttonWave1.get(TransformPart.class);
        tpWave1.setY(120);
        tpWave1.setX(BOARD_WIDTH - 180);
        buttonWave1.get(HudFocusablePart.class).setCommand(new PushWaveToStackCommand(WaveType.WAVE_BASIC_BLOCK, this));
        buttonWave1.get(TextLabelPart.class).setText("Basic");
        this.addEntity(buttonWave1);
        buttons.add(buttonWave1.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonWave1);
        
        Entity buttonWave2 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave2 = buttonWave2.get(TransformPart.class);
        tpWave2.setY(tpWave1.getY());
        tpWave2.setX(tpWave1.getX() + 100);
        buttonWave2.get(HudFocusablePart.class).setCommand(new PushWaveToStackCommand(WaveType.WAVE_MIXED_BLOCK, this));
        buttonWave2.get(TextLabelPart.class).setText("Mixed");
        this.addEntity(buttonWave2);
        buttons.add(buttonWave2.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonWave2);
        
        Entity buttonWave3 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave3 = buttonWave3.get(TransformPart.class);
        tpWave3.setY(tpWave1.getY() + 50);
        tpWave3.setX(tpWave1.getX());
        //command
        buttonWave3.get(TextLabelPart.class).setText("Basic");
        this.addEntity(buttonWave3);
        buttons.add(buttonWave3.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonWave3);
        
        Entity buttonWave4 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave4 = buttonWave4.get(TransformPart.class);
        tpWave4.setY(tpWave3.getY());
        tpWave4.setX(tpWave2.getX());
        //command
        buttonWave4.get(TextLabelPart.class).setText("Mixed");
        this.addEntity(buttonWave4);
        buttons.add(buttonWave4.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonWave4);
        
        Entity buttonWave5 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave5 = buttonWave5.get(TransformPart.class);
        tpWave5.setY(tpWave3.getY() + 50);
        tpWave5.setX(tpWave1.getX());
        //command
        buttonWave5.get(TextLabelPart.class).setText("Basic");
        this.addEntity(buttonWave5);
        buttons.add(buttonWave5.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonWave5);
        
        Entity buttonWave6 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave6 = buttonWave6.get(TransformPart.class);
        tpWave6.setY(tpWave5.getY());
        tpWave6.setX(tpWave2.getX());
        //command
        buttonWave6.get(TextLabelPart.class).setText("Mixed");
        this.addEntity(buttonWave6);
        buttons.add(buttonWave6.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonWave6);
        
        Entity buttonClear = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpClear = buttonClear.get(TransformPart.class);
        tpClear.setY(BOARD_HEIGHT - 100);
        tpClear.setX(tpWave1.getX());
        buttonClear.get(HudFocusablePart.class).setCommand(new PopWaveFromQueueCommand(this));
        buttonClear.get(TextLabelPart.class).setText("Delete");
        this.addEntity(buttonClear);
        buttons.add(buttonClear.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonClear);
        
        Entity buttonSelect = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpSelect = buttonSelect.get(TransformPart.class);
        tpSelect.setY(tpClear.getY());
        tpSelect.setX(tpWave2.getX());
        buttonSelect.get(HudFocusablePart.class).setCommand(new SendWavesCommand(this));
        buttonSelect.get(TextLabelPart.class).setText("Select");
        this.addEntity(buttonSelect);
        buttons.add(buttonSelect.get(HudFocusablePart.class));
        selectWaveEntities.add(buttonSelect);
        
        for (int i = 0; i < buttons.size(); ++i) {
            HudFocusablePart focusable = buttons.get(i);
            focusable.setWidth(100);
            focusable.setXOffset(-50);
        }
        
        this.controlEntity.attach(hf.createHud(HudType.WAVE_SELECTOR, buttons));
        //</editor-fold>
        
        //Labels
        Entity titleLabel = ef.createEntity(EntityFactory.EntityType.LABEL_BASIC);
        TransformPart tpTitleLabel = titleLabel.get(TransformPart.class);
        tpTitleLabel.setX(BOARD_WIDTH - 130);
        tpTitleLabel.setY(60);
        TextLabelPart tlpTitleLabel = titleLabel.get(TextLabelPart.class);
        tlpTitleLabel.setText("Select waves");
        tlpTitleLabel.setColor(Color.WHITE);
        this.addEntity(titleLabel);
        selectWaveEntities.add(titleLabel);
        
        Entity wavesLabel = ef.createEntity(EntityFactory.EntityType.LABEL_BASIC);
        TransformPart tpWavesLabel = wavesLabel.get(TransformPart.class);
        tpWavesLabel.setX(BOARD_WIDTH - 130);
        tpWavesLabel.setY(tpWave6.getY() + 100);
        TextLabelPart tlpWavesLabel = wavesLabel.get(TextLabelPart.class);
        tlpWavesLabel.setFont(FONT_SMALL);
        tlpWavesLabel.setText("Normal Block Wave\nMixed Block Wave\nNormal Block Wave\nMixed Block Wave");
        tlpWavesLabel.setColor(Color.WHITE);
        this.addEntity(wavesLabel);
        selectWaveEntities.add(wavesLabel);
        
        //WaveManager
        WaveManagerPart waveManager = new WaveManagerPart(this);
        waveManager.setOutputLabel(tlpWavesLabel);
        this.controlEntity.attach(waveManager);
        waveManager.setActive(true);
    }
    
    private void resetStatusLabel() {
        if (this.statusLabel != null) {
            TextLabelPart textLabelPart = this.statusLabel.get(TextLabelPart.class);
            textLabelPart.setText("");
        }
    }
    
    private void initSceneController() {
        ControllerPart sceneController = new ControllerPart();
            CommandOnKeyBehaviour cokb =
                    new CommandOnKeyBehaviour(KeyEvent.VK_ESCAPE,
                            new ExitToMainMenuCommand(), true);
            sceneController.attach(cokb);
        sceneController.setActive(true);
        this.controlEntity.attach(sceneController);
    }
}
