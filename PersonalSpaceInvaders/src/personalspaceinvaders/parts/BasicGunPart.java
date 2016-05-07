package personalspaceinvaders.parts;

import com.sun.glass.events.KeyEvent;
import personalspaceinvaders.Entity;
import personalspaceinvaders.KeyboardManager;
import personalspaceinvaders.Part;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.factories.EntityFactory.EntityType;

/**
 *
 * @author SHerbocopter
 */
public class BasicGunPart extends Part {
    private float xOffset;
    private float yOffset;
    private int shootKey = KeyEvent.VK_SPACE;
    private float shootDelay = 1;
    private float timePassed = 0;
    private boolean canShoot = true;
    
    public BasicGunPart(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
//<editor-fold defaultstate="collapsed" desc="Getters + Setters">
    public float getXOffset() {
        return xOffset;
    }
    
    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }
    
    public float getYOffset() {
        return yOffset;
    }
    
    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
    public int getShootKey() {
        return shootKey;
    }
    
    public void setShootKey(int shootKey) {
        this.shootKey = shootKey;
    }
    
    public float getShootDelay() {
        return shootDelay;
    }
    
    public void setShootDelay(float shootDelay) {
        this.shootDelay = shootDelay;
    }
//</editor-fold>
    
    @Override
    public void update(float delta) {
        timePassed += delta;
        
        if (timePassed >= shootDelay) {
            canShoot = true;
        }
        
        KeyboardManager km = KeyboardManager.getInstance();
        if (km.keyDown(shootKey) && canShoot) {
            shoot();
            timePassed = 0;
            canShoot = false;
        }
    }
    
    public void shoot() {
        EntityFactory ef = EntityFactory.getInstance();
        Entity bullet = ef.createEntity(EntityType.BULLET_BASIC);
        
        TransformPart tpEntity = this.entity.get(TransformPart.class);
        TransformPart tpBullet = bullet.get(TransformPart.class);
        
        tpBullet.setX(tpEntity.getX() + xOffset);
        tpBullet.setY(tpEntity.getY() + yOffset);
        
        this.entity.getScene().addEntity(bullet);
    }
}
