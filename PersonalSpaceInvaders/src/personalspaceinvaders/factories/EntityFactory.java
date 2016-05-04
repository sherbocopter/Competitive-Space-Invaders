package personalspaceinvaders.factories;

import java.awt.Color;
import java.awt.Font;
import personalspaceinvaders.Commons;
import personalspaceinvaders.Entity;
import personalspaceinvaders.behaviours.FlyDownBehaviour;
import personalspaceinvaders.behaviours.PlayerMoveBehaviour;
import personalspaceinvaders.behaviours.WiggleBehaviour;
import personalspaceinvaders.parts.ControllerPart;
import personalspaceinvaders.parts.HitboxPart;
import personalspaceinvaders.parts.HitpointsPart;
import personalspaceinvaders.parts.HudFocusablePart;
import personalspaceinvaders.parts.TextLabelPart;
import personalspaceinvaders.parts.TransformPart;

/**
 *
 * @author SHerbocopter
 */
public class EntityFactory implements Commons {
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
        BUTTON_BASIC
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
            default: {
                throw new IllegalArgumentException("entityType not found");
            }
        }
        
        return entity;
    }
    
    private Entity createBasicWhiteAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(0, 0, 0, 1));
        alien.attach(new HitpointsPart(100));
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-25, -15, 0, 50, 30);
        hitbox.setColor(Color.WHITE);
        hitbox.setVisible(true);
        alien.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
        
        FlyDownBehaviour flyDownBehavior = new FlyDownBehaviour(15);
        controller.attach(flyDownBehavior);
        WiggleBehaviour wiggleBehaviour = new WiggleBehaviour(20, 5);
        controller.attach(wiggleBehaviour);
        controller.setActive(true);
        alien.attach(controller);
        
        alien.setActive(true);
        
        return alien;
    }
    
    private Entity createBasicRedAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(0, 0, 0, 1));
        alien.attach(new HitpointsPart(120));
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-25, -20, 0, 50, 40);
        hitbox.setColor(Color.RED);
        hitbox.setVisible(true);
        alien.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
        FlyDownBehaviour behaviour = new FlyDownBehaviour(15);
        controller.attach(behaviour);
        controller.setActive(true);
        alien.attach(controller);
        
        return alien;
    }
    
    private Entity createBasicGreenAlien() {
        Entity alien = new Entity();
        
        alien.attach(new TransformPart(0, 0, 0, 1));
        alien.attach(new HitpointsPart(80));
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-20, -10, 0, 40, 20);
        hitbox.setColor(Color.GREEN);
        hitbox.setVisible(true);
        alien.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
        FlyDownBehaviour behaviour = new FlyDownBehaviour(15);
        controller.attach(behaviour);
        controller.setActive(true);
        alien.attach(controller);
        
        return alien;
    }
    
    private Entity createBasicPlayer() {
        Entity player = new Entity();
        
        player.attach(new TransformPart(320, 400, 0, 1));
        player.attach(new HitpointsPart(200));
        
        //hitbox
        HitboxPart hitbox = new HitboxPart(-30, -20, 0, 60, 40);
        hitbox.setColor(Color.BLUE);
        hitbox.setVisible(true);
        player.attach(hitbox);
        
        //controller
        ControllerPart controller = new ControllerPart();
        controller.setActive(true);
        player.attach(controller);
        controller.attach(new PlayerMoveBehaviour(100, 70));
        
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
}
