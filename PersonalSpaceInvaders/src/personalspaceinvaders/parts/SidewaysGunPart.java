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
public class SidewaysGunPart extends Part {
    private float xOffset1;
    private float yOffset1;
    private float xOffset2;
    private float yOffset2;
    private int shootKey = KeyEvent.VK_SPACE;
    private float shootDelay = 1;
    private float timePassed = 0;
    private boolean canShoot = true;
    
    public SidewaysGunPart(float xOffset1, float yOffset1, float xOffset2, float yOffset2) {
        this.xOffset1 = xOffset1;
        this.yOffset1 = yOffset1;
        this.xOffset2 = xOffset2;
        this.yOffset2 = yOffset2;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters + Setters">
    public float getXOffset1() {
        return xOffset1;
    }
    
    public void setXOffset1(float xOffset1) {
        this.xOffset1 = xOffset1;
    }
    
    public float getYOffset1() {
        return yOffset1;
    }
    
    public void setYOffset1(float yOffset1) {
        this.yOffset1 = yOffset1;
    }
    
    public float getXOffset2() {
        return xOffset2;
    }
    
    public void setXOffset2(float xOffset2) {
        this.xOffset2 = xOffset2;
    }
    
    public float getYOffset2() {
        return yOffset2;
    }
    
    public void setYOffset2(float yOffset2) {
        this.yOffset2 = yOffset2;
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
        TransformPart tpEntity = this.entity.get(TransformPart.class);
        StatsPart statsEntity;
        if (this.entity.has(StatsPart.class)) {
            statsEntity = this.entity.get(StatsPart.class);
        }
        else { return; }
        
        //leftBullet
        Entity bullet1 = ef.createEntity(EntityFactory.EntityType.BULLET_LEFT);
        
        TransformPart tpBullet1 = bullet1.get(TransformPart.class);
        tpBullet1.setX(tpEntity.getX() + xOffset1);
        tpBullet1.setY(tpEntity.getY() + yOffset1);
        
        StatsPart statsBullet1 = bullet1.get(StatsPart.class);
        statsBullet1.faction = statsEntity.faction;
        
        this.entity.getScene().addEntity(bullet1);
        
        //rightBullet
        Entity bullet2 = ef.createEntity(EntityFactory.EntityType.BULLET_RIGHT);
        
        TransformPart tpBullet2 = bullet2.get(TransformPart.class);
        tpBullet2.setX(tpEntity.getX() + xOffset2);
        tpBullet2.setY(tpEntity.getY() + yOffset2);
        
        StatsPart statsBullet2 = bullet2.get(StatsPart.class);
        statsBullet2.faction = statsEntity.faction;
        
        this.entity.getScene().addEntity(bullet2);
    }
}
