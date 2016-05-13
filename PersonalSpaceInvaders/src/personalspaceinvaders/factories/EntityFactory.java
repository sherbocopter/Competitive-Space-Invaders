package personalspaceinvaders.factories;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import personalspaceinvaders.Commons;
import personalspaceinvaders.Entity;
import personalspaceinvaders.behaviours.BoundingBoxBehaviour;
import personalspaceinvaders.behaviours.DieOffscreenBehaviour;
import personalspaceinvaders.behaviours.FlyDownBehaviour;
import personalspaceinvaders.behaviours.FlyUpBehaviour;
import personalspaceinvaders.behaviours.PlayerMoveBehaviour;
import personalspaceinvaders.behaviours.WiggleBehaviour;
import personalspaceinvaders.parts.BasicGunPart;
import personalspaceinvaders.parts.ControllerPart;
import personalspaceinvaders.parts.HitboxPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.StatsPart;
import personalspaceinvaders.parts.StatsPart.Faction;
import personalspaceinvaders.parts.StatsPart.StatsType;
import personalspaceinvaders.parts.TextLabelPart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class EntityFactory implements Commons {
    public float enemyUpOffset = 0;
    public float enemyDownOffset = 0;
    public float enemyLeftOffset = 0;
    public float enemyRightOffset = 0;
    
    private static final EntityFactory instance = new EntityFactory();
    private EntityFactory() { }
    public static EntityFactory getInstance() {
        return instance;
    }
    
    public static enum EntityType {
        ALIEN_BASIC_WHITE,
        ALIEN_BASIC_RED,
        ALIEN_BASIC_GREEN,
        PLAYER_BASIC,
        BUTTON_BASIC,
        LABEL_BASIC,
        BULLET_BASIC
    }
    
    public Entity createEntity(EntityType type) {
        Entity entity = null;
        
        switch (type) {
            case ALIEN_BASIC_WHITE: {
                entity = createBasicWhiteAlien();
            } break;
            case ALIEN_BASIC_RED: {
                entity = createBasicRedAlien();
            } break;
            case ALIEN_BASIC_GREEN: {
                entity = createBasicGreenAlien();
            } break;
            case PLAYER_BASIC: {
                entity = createBasicPlayer();
            } break;
            case BUTTON_BASIC: {
                entity = createBasicButton();
            } break;
            case LABEL_BASIC: {
                entity = createBasicLabel();
            } break;
            case BULLET_BASIC: {
                entity = createBasicBullet();
            } break;
            default: {
                throw new IllegalArgumentException("entityType not found");
            }
        }
        
        return entity;
    }
    
    private Entity createBasicWhiteAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(0, 0, 0, 1));
        
        //shipStats
        StatsPart shipStats = new StatsPart();
        shipStats.maxHitpoints = 120;
        shipStats.setCurrentHitpoints(80);
        shipStats.faction = Faction.FACTION_ENEMY;
        shipStats.statsType = StatsType.SHIP;
        shipStats.damage = 50;
        shipStats.setActive(true);
        alien.attach(shipStats);
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-25, -15, 0, 50, 30);
        hitbox.setColor(Color.WHITE);
        hitbox.setVisible(true);
        hitbox.setActive(true);
        alien.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
            FlyDownBehaviour flyDown = new FlyDownBehaviour(45);
            controller.attach(flyDown);
            
            WiggleBehaviour wiggle = new WiggleBehaviour(20, 5);
            controller.attach(wiggle);
            
            DieOffscreenBehaviour dieOffscreen = new DieOffscreenBehaviour();
            dieOffscreen.upOffset = enemyUpOffset; //a lot
            dieOffscreen.downOffset = enemyDownOffset;
            dieOffscreen.leftOffset = enemyLeftOffset;
            dieOffscreen.rightOffset = enemyRightOffset;
            controller.attach(dieOffscreen);
        controller.setActive(true);
        alien.attach(controller);
        
        alien.setActive(true);
        
        return alien;
    }
    
    private Entity createBasicRedAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(0, 0, 0, 1));
        
        //shipStats
        StatsPart shipStats = new StatsPart();
        shipStats.maxHitpoints = 80;
        shipStats.setCurrentHitpoints(80);
        shipStats.faction = Faction.FACTION_ENEMY;
        shipStats.statsType = StatsType.SHIP;
        shipStats.damage = 50;
        shipStats.setActive(true);
        alien.attach(shipStats);
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-25, -20, 0, 50, 40);
        hitbox.setColor(Color.RED);
        hitbox.setVisible(true);
        hitbox.setActive(true);
        alien.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
            FlyDownBehaviour flyDown = new FlyDownBehaviour(40);
            controller.attach(flyDown);
            
            DieOffscreenBehaviour dieOffscreen = new DieOffscreenBehaviour();
            dieOffscreen.upOffset = enemyUpOffset; //a lot
            dieOffscreen.downOffset = enemyDownOffset;
            dieOffscreen.leftOffset = enemyLeftOffset;
            dieOffscreen.rightOffset = enemyRightOffset;
            controller.attach(dieOffscreen);
        controller.setActive(true);
        alien.attach(controller);
        
        return alien;
    }
    
    private Entity createBasicGreenAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(0, 0, 0, 1));

        //shipStats
        StatsPart shipStats = new StatsPart();
        shipStats.maxHitpoints = 40;
        shipStats.setCurrentHitpoints(120);
        shipStats.faction = Faction.FACTION_ENEMY;
        shipStats.statsType = StatsType.SHIP;
        shipStats.damage = 50;
        shipStats.setActive(true);
        alien.attach(shipStats);
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-20, -10, 0, 40, 20);
        hitbox.setColor(Color.GREEN);
        hitbox.setVisible(true);
        hitbox.setActive(true);
        alien.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
            FlyDownBehaviour flyDown = new FlyDownBehaviour(40);
            controller.attach(flyDown);
            
            DieOffscreenBehaviour dieOffscreen = new DieOffscreenBehaviour();
            dieOffscreen.upOffset = enemyUpOffset; //a lot
            dieOffscreen.downOffset = enemyDownOffset;
            dieOffscreen.leftOffset = enemyLeftOffset;
            dieOffscreen.rightOffset = enemyRightOffset;
            controller.attach(dieOffscreen);
        controller.setActive(true);
        alien.attach(controller);
        
        return alien;
    }
    
    private Entity createBasicPlayer() {
        Entity player = new Entity();
        
        player.attach(new TransformPart(PLAY_WIDTH / 2, PLAY_HEIGHT - 100, 0, 1));
        
        //shipStats
        StatsPart shipStats = new StatsPart();
        shipStats.maxHitpoints = 100;
        shipStats.setCurrentHitpoints(100);
        shipStats.faction = Faction.FACTION_FRIENDLY;
        shipStats.statsType = StatsType.SHIP;
        shipStats.damage = 50;
        shipStats.setActive(true);
        player.attach(shipStats);
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-30, -20, 0, 60, 40);
        hitbox.setColor(Color.BLUE);
        hitbox.setVisible(true);
        hitbox.setActive(true);
        player.attach(hitbox);
        
        //basicGun
        BasicGunPart gunPart = new BasicGunPart(0, -30);
        gunPart.setShootKey(KeyEvent.VK_SPACE);
        gunPart.setShootDelay((float) 0.3);
        gunPart.setActive(true);
        player.attach(gunPart);
        
        //controller
        ControllerPart controller = new ControllerPart();
            controller.attach(new PlayerMoveBehaviour(180, 180));
            
            BoundingBoxBehaviour boundingBox = new BoundingBoxBehaviour();
            boundingBox.setBoundsInset(50);
            controller.attach(boundingBox);
        controller.setActive(true);
        player.attach(controller);
        
        return player;
    }
    
    private Entity createBasicButton() {
        Entity button = new Entity();
        
        button.attach(new TransformPart(50, 50, 0, 1));
        
        //textLabel
        Font font = FONT_DEFAULT;
        TextLabelPart textLabel = new TextLabelPart(font, -100, -20, 0, 200, 40);
        textLabel.setVisible(true);
        textLabel.setColor(Color.YELLOW);
        button.attach(textLabel);
        
        //focusable
        HudFocusablePart focusable = new HudFocusablePart(-100, -20, 0, 200, 40);
        focusable.setHighlightWidth(4);
        focusable.setColor(Color.WHITE);
        focusable.setVisible(true);
        button.attach(focusable);
        
        return button;
    }
    
    private Entity createBasicLabel() {
        Entity label = new Entity();
        
        label.attach(new TransformPart(0, 0, 0, 1));
        
        //textLabel
        Font font = FONT_DEFAULT;
        TextLabelPart textLabel = new TextLabelPart(font, -100, -20, 0, 200, 40);
        textLabel.setVisible(true);
        textLabel.setColor(Color.YELLOW);
        label.attach(textLabel);
        
        return label;
    }
    
    private Entity createBasicBullet() {
        Entity bullet = new Entity();
        
        bullet.attach(new TransformPart(0, 0, 0, 1));
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-3, -3, 0, 6, 6);
        hitbox.setColor(Color.GREEN);
        hitbox.setVisible(true);
        hitbox.setActive(true);
        bullet.attach(hitbox);
        
        //bulletStats
        StatsPart stats = new StatsPart();
        stats.maxHitpoints = 1;
        stats.setCurrentHitpoints(1);
        stats.damage = 80;
        stats.statsType = StatsType.BULLET;
        stats.setActive(true);
        bullet.attach(stats);
        
        //controller
        ControllerPart controller = new ControllerPart();
            FlyUpBehaviour flyUp = new FlyUpBehaviour(300);
            controller.attach(flyUp);
            
            DieOffscreenBehaviour dieOffscreen = new DieOffscreenBehaviour();
            dieOffscreen.setAllBounds(10);
            controller.attach(dieOffscreen);
        controller.setActive(true);
        bullet.attach(controller);
        
        return bullet;
    }
}
