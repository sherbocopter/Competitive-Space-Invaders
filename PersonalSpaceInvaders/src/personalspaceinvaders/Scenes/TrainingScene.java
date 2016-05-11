package personalspaceinvaders.Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import personalspaceinvaders.Command;
import personalspaceinvaders.Entity;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.HudFactory;
import personalspaceinvaders.factories.HudFactory.HudType;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.TextLabelPart;
import personalspaceinvaders.parts.TransformPart;
import personalspaceinvaders.waveUtilities.WaveManagerPart;

/**
 *
 * @author SHerbocopter
 */
public class TrainingScene extends Scene {
    private class PushWaveToStackCommand implements Command {
        private WaveType waveType = WaveType.DUMMY;
        private Scene scene = null;
        
        public PushWaveToStackCommand(WaveType waveType, Scene scene) {
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
        private Scene scene = null;
        
        public PopWaveFromQueueCommand(Scene scene) {
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
        private Scene scene = null;
        
        public SendWavesCommand(Scene scene) {
            super();
            
            this.scene = scene;
        }
        
        @Override
        public void execute(Object data) {
            WaveManagerPart wmp = scene.controlEntity.get(WaveManagerPart.class);
            wmp.start();
            //HERE GOES THE SEND WAVES CODE
        }
    }
    
    @Override
    public void load() {
        temporaryEntitiesInit();
        initWaveSelector();
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
    
    private void temporaryEntitiesInit() {
        //WavesFactory wf = WavesFactory.getInstance();
        EntityFactory ef = EntityFactory.getInstance();
        
        //entities.addAll(wf.createWave(WavesFactory.WaveType.WAVE_BASIC_BLOCK));
        this.addEntity(ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC));
        
        /*
        WaveManagerPart waveManager = new WaveManagerPart(this);
        ArrayList<WaveType> waves = new ArrayList<>();
        waves.add(WaveType.WAVE_BASIC_BLOCK);
        waves.add(WaveType.WAVE_MIXED_BLOCK);
        waveManager.setWaves(waves);
        this.controlEntity.attach(waveManager);
        waveManager.setActive(true);
        waveManager.start();
        */
    }
    
    private void initWaveSelector() {
        if (this.controlEntity.has(HudManagerPart.class)) {
            throw new IllegalArgumentException();
        }
        
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
        
        Entity buttonWave2 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave2 = buttonWave2.get(TransformPart.class);
        tpWave2.setY(tpWave1.getY());
        tpWave2.setX(tpWave1.getX() + 100);
        buttonWave2.get(HudFocusablePart.class).setCommand(new PushWaveToStackCommand(WaveType.WAVE_MIXED_BLOCK, this));
        buttonWave2.get(TextLabelPart.class).setText("Mixed");
        this.addEntity(buttonWave2);
        buttons.add(buttonWave2.get(HudFocusablePart.class));
        
        Entity buttonWave3 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave3 = buttonWave3.get(TransformPart.class);
        tpWave3.setY(tpWave1.getY() + 50);
        tpWave3.setX(tpWave1.getX());
        //command
        buttonWave3.get(TextLabelPart.class).setText("Basic");
        this.addEntity(buttonWave3);
        buttons.add(buttonWave3.get(HudFocusablePart.class));
        
        Entity buttonWave4 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave4 = buttonWave4.get(TransformPart.class);
        tpWave4.setY(tpWave3.getY());
        tpWave4.setX(tpWave2.getX());
        //command
        buttonWave4.get(TextLabelPart.class).setText("Mixed");
        this.addEntity(buttonWave4);
        buttons.add(buttonWave4.get(HudFocusablePart.class));
        
        Entity buttonWave5 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave5 = buttonWave5.get(TransformPart.class);
        tpWave5.setY(tpWave3.getY() + 50);
        tpWave5.setX(tpWave1.getX());
        //command
        buttonWave5.get(TextLabelPart.class).setText("Basic");
        this.addEntity(buttonWave5);
        buttons.add(buttonWave5.get(HudFocusablePart.class));
        
        Entity buttonWave6 = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpWave6 = buttonWave6.get(TransformPart.class);
        tpWave6.setY(tpWave5.getY());
        tpWave6.setX(tpWave2.getX());
        //command
        buttonWave6.get(TextLabelPart.class).setText("Mixed");
        this.addEntity(buttonWave6);
        buttons.add(buttonWave6.get(HudFocusablePart.class));
        
        Entity buttonClear = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpClear = buttonClear.get(TransformPart.class);
        tpClear.setY(BOARD_HEIGHT - 100);
        tpClear.setX(tpWave1.getX());
        buttonClear.get(HudFocusablePart.class).setCommand(new PopWaveFromQueueCommand(this));
        buttonClear.get(TextLabelPart.class).setText("Delete");
        this.addEntity(buttonClear);
        buttons.add(buttonClear.get(HudFocusablePart.class));
        
        Entity buttonSelect = ef.createEntity(EntityFactory.EntityType.BUTTON_BASIC);
        TransformPart tpSelect = buttonSelect.get(TransformPart.class);
        tpSelect.setY(tpClear.getY());
        tpSelect.setX(tpWave2.getX());
        buttonSelect.get(HudFocusablePart.class).setCommand(new SendWavesCommand(this));
        buttonSelect.get(TextLabelPart.class).setText("Select");
        this.addEntity(buttonSelect);
        buttons.add(buttonSelect.get(HudFocusablePart.class));
        
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
        
        Entity wavesLabel = ef.createEntity(EntityFactory.EntityType.LABEL_BASIC);
        TransformPart tpWavesLabel = wavesLabel.get(TransformPart.class);
        tpWavesLabel.setX(BOARD_WIDTH - 130);
        tpWavesLabel.setY(tpWave6.getY() + 100);
        TextLabelPart tlpWavesLabel = wavesLabel.get(TextLabelPart.class);
        tlpWavesLabel.setFont(FONT_SMALL);
        tlpWavesLabel.setText("Normal Block Wave\nMixed Block Wave\nNormal Block Wave\nMixed Block Wave");
        tlpWavesLabel.setColor(Color.WHITE);
        this.addEntity(wavesLabel);
        
        //WaveManager
        WaveManagerPart waveManager = new WaveManagerPart(this);
        waveManager.setOutputLabel(tlpWavesLabel);
        this.controlEntity.attach(waveManager);
        waveManager.setActive(true);
    }
}
