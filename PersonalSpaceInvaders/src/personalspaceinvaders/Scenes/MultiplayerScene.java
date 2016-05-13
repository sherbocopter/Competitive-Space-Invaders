package personalspaceinvaders.Scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import personalspaceinvaders.Command;
import personalspaceinvaders.Entity;
import personalspaceinvaders.Scene;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.HudFactory;
import personalspaceinvaders.factories.HudFactory.HudType;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.factories.WavesFactory.WaveType;
import personalspaceinvaders.hudUtilities.HudManagerPart;
import personalspaceinvaders.networking.MultiplayerBase;
import personalspaceinvaders.networking.MultiplayerGuest;
import personalspaceinvaders.networking.MultiplayerHost;
import personalspaceinvaders.networking.serializables.Message;
import personalspaceinvaders.networking.serializables.Message.MsgType;
import personalspaceinvaders.networking.serializables.WavesSerData;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.TextLabelPart;
import personalspaceinvaders.parts.TransformPart;
import personalspaceinvaders.waveUtilities.MultiplayerWaveManagerPart;

/**
 *
 * @author SHerbocopter
 */
public class MultiplayerScene extends GameSceneBase {
    private class PushWaveToStackCommand implements Command {
        private WaveType waveType = WaveType.DUMMY;
        private MultiplayerScene scene = null;
        
        public PushWaveToStackCommand(WaveType waveType, MultiplayerScene scene) {
            super();
            
            this.scene = scene;
            this.waveType = waveType;
        }
        
        @Override
        public void execute(Object data) {
            WavesFactory wf = WavesFactory.getInstance();
            
            //scene.addEntities(wf.createWave(waveType)); //to be changed
            MultiplayerWaveManagerPart mwmp = scene.controlEntity.get(MultiplayerWaveManagerPart.class);
            mwmp.remoteEncounter.pushWave(waveType);
            
            Message message = new Message();
            message.text = "U SUCK";
            scene.multiplayerBase.sendMessage(message);
        }
    }
    
    private class PopWaveFromQueueCommand implements Command {
        private MultiplayerScene scene = null;
        
        public PopWaveFromQueueCommand(MultiplayerScene scene) {
            super();
            
            this.scene = scene;
        }
        
        @Override
        public void execute(Object data) {
            MultiplayerWaveManagerPart mwmp = scene.controlEntity.get(MultiplayerWaveManagerPart.class);
            mwmp.remoteEncounter.popWave();
        }
    }
    
    private class SendWavesCommand implements Command {
        private MultiplayerScene scene = null;
        
        public SendWavesCommand(MultiplayerScene scene) {
            super();
            
            this.scene = scene;
        }
        
        @Override
        public void execute(Object data) {
            MultiplayerWaveManagerPart mwmp = scene.controlEntity.get(MultiplayerWaveManagerPart.class);
            scene.multiplayerBase.sendWaves(mwmp.remoteEncounter.getWaves());
            
            scene.setState(MultiplayerState.WAIT_WAVES);
        }
    }
    
    private enum MultiplayerState {
        WAIT_CONNECTION,
        SELECT_WAVES,
        WAIT_WAVES,
        PLAY,
        WAIT_PEER,
        DUMMY
    }
    
    private boolean isHost = false;
    private String hostIp = "";     //used only on guest
    private int hostPort = MULTIPLAYER_PORT;    //hardcoded for the moment
    public MultiplayerBase multiplayerBase = null;
    private MultiplayerState gameState = MultiplayerState.DUMMY;
    private HashMap<MultiplayerState, ArrayList<Entity>> stateEntities = new HashMap<>();
    public boolean havePeerStatus = false;
    
    public MultiplayerScene() {
        super();
        
        this.isHost = true;
        multiplayerBase = new MultiplayerHost(this);
        multiplayerBase.start();
        
        initialize();
        
        setState(MultiplayerState.WAIT_CONNECTION);
    }
    
    public MultiplayerScene(String ipAddress, int port) {
        super();
        
        this.hostIp = ipAddress;
        this.hostPort = port;
        multiplayerBase = new MultiplayerGuest(this, ipAddress, port);
        multiplayerBase.start();
        
        initialize();
        
        setState(MultiplayerState.WAIT_CONNECTION);
    }
    
    private void initialize() {
        
        for (MultiplayerState state : MultiplayerState.values()) {
            stateEntities.put(state, new ArrayList<>());
        }
        
        temporaryEntitiesInit();
        initWaveSelector();
        
        for (MultiplayerState state : MultiplayerState.values()) {
            exitState(state);
        }
    }
    
    //TODO: proper StateManager class
    //<editor-fold defaultstate="collapsed" desc="MultiplayerState utils">
    private void updateState() {
        boolean shouldSwitch = checkStateEndCondition(gameState);
        if (shouldSwitch) {
            switch (gameState) {
                case WAIT_CONNECTION: {
                    setState(MultiplayerState.SELECT_WAVES);
                } break;
                case SELECT_WAVES: {
                    setState(MultiplayerState.WAIT_WAVES);
                } break;
                case WAIT_WAVES: {
                    setState(MultiplayerState.PLAY);
                } break;
                case PLAY: {
                    setState(MultiplayerState.WAIT_PEER);
                } break;
                case WAIT_PEER: {
                    setState(MultiplayerState.SELECT_WAVES);
                } break;
                default: {

                } break;
            }
        }
    }
    
    private boolean checkStateEndCondition(MultiplayerState state) {
        try {
            switch (state) {
                case WAIT_CONNECTION: {
                    return (multiplayerBase.streamToPeer != null &&
                            multiplayerBase.streamFromPeer != null);
                }
                case SELECT_WAVES: {
                    
                } break;
                case WAIT_WAVES: {
                    MultiplayerWaveManagerPart mwmp = this.controlEntity.get(MultiplayerWaveManagerPart.class);
                    return mwmp.haveNewWaves;
                }
                case PLAY: {
                    MultiplayerWaveManagerPart mwmp = this.controlEntity.get(MultiplayerWaveManagerPart.class);
                    ArrayList<Entity> aliens = this.stateEntities.get(MultiplayerState.PLAY);
                    return (mwmp.localEncounter.isFinished == true &&
                            aliens.isEmpty());
                }
                case WAIT_PEER: {
                    return havePeerStatus;
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
    
    private void setState(MultiplayerState newState) {
        exitState(gameState);
        gameState = newState;
        enterState(gameState);
    }
    
    private void enterState(MultiplayerState state) {
        for (Entity entity : stateEntities.get(state)) {
            entity.setActive(true);
            entity.setVisible(true);
        }
        
        switch (state) {
            case WAIT_CONNECTION: {
                
            } break;
            case SELECT_WAVES: {
                HudManagerPart hudManager = this.controlEntity.get(HudManagerPart.class);
                hudManager.setActive(true);
            } break;
            case WAIT_WAVES: {
                
            } break;
            case PLAY: {
                MultiplayerWaveManagerPart mwmp = this.controlEntity.get(MultiplayerWaveManagerPart.class);
                mwmp.haveNewWaves = false;
                mwmp.localEncounter.start();
            } break;
            case WAIT_PEER: {
                multiplayerBase.sendStatus();
                System.out.println("FINISHED WAVES, WAITING");
            } break;
            default: {
                
            } break;
        }
    }
    
    private void exitState(MultiplayerState state) {
        for (Entity entity : stateEntities.get(state)) {
            entity.setActive(false);
            entity.setVisible(false);
        }
        
        switch (state) {
            case WAIT_CONNECTION: {
                
            } break;
            case SELECT_WAVES: {
                HudManagerPart hudManager = this.controlEntity.get(HudManagerPart.class);
                hudManager.setActive(false);
            } break;
            case WAIT_WAVES: {
                
            } break;
            case PLAY: {
                
            } break;
            case WAIT_PEER: {
                havePeerStatus = false;
            } break;
            default: {
                
            } break;
        }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Network messaging">
    public void setLocalWaves(ArrayList<WaveType> waves) {
        MultiplayerWaveManagerPart mwmp = this.controlEntity.get(MultiplayerWaveManagerPart.class);
        mwmp.localEncounter.setWaves(waves);
        mwmp.haveNewWaves = true;
    }
    
    public void setPeerStatus() {
        
        havePeerStatus = true;
    }
//</editor-fold>
    
    @Override
    public void load() {
        
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
        this.stateEntities.get(MultiplayerState.PLAY).addAll(aliens);
    }
    
    @Override
    public void removeEntity(Entity entity) {
        super.removeEntity(entity);
        
        ArrayList<Entity> aliens = this.stateEntities.get(MultiplayerState.PLAY);
        if (aliens.contains(entity)) {
            aliens.remove(entity);
        }
    }
    
    private void temporaryEntitiesInit() {
        EntityFactory ef = EntityFactory.getInstance();
        
        this.addEntity(ef.createEntity(EntityFactory.EntityType.PLAYER_BASIC));
    }
    
    private void initWaveSelector() {
        if (this.controlEntity.has(HudManagerPart.class)) {
            throw new IllegalArgumentException();
        }
        
        ArrayList<Entity> selectWaveEntities = stateEntities.get(MultiplayerState.SELECT_WAVES);
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
        
        //HUD
        for (int i = 0; i < buttons.size(); ++i) {
            HudFocusablePart focusable = buttons.get(i);
            focusable.setWidth(100);
            focusable.setXOffset(-50);
        }
        
        HudManagerPart hudManager = hf.createHud(HudType.WAVE_SELECTOR, buttons);
        this.controlEntity.attach(hudManager);
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
        MultiplayerWaveManagerPart multplayerWaveManager = new MultiplayerWaveManagerPart(this);
        multplayerWaveManager.remoteEncounter.setOutputLabel(tlpWavesLabel);
        this.controlEntity.attach(multplayerWaveManager);
        multplayerWaveManager.setActive(true);
    }
}
