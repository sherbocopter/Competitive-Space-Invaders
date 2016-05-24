package personalspaceinvaders.parts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import personalspaceinvaders.Entity;
import personalspaceinvaders.Part;
import personalspaceinvaders.parts.StatsPart.StatsType;

/**
 *
 * @author SHerbocopter
 */
public class HitboxPart extends Part {
    private float xOffset;
    private float yOffset;
    private float rotationOffset;
    private float width;
    private float height;
    
    private Color color = Color.WHITE;
    
    public HitboxPart(float xOffset, float yOffset, float rotationOffset, float width, float height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.rotationOffset = rotationOffset;
        this.width = width;
        this.height = height;
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
    
    public float getRotationOffset() {
        return rotationOffset;
    }
    
    public void setRotationOffset(float rotationOffset) {
        this.rotationOffset = rotationOffset;
    }
    
    public float getWidth() {
        return width;
    }
    
    public void setWidth(float width) {
        this.width = width;
    }
    
    public float getHeight() {
        return height;
    }
    
    public void setHeight(float height) {
        this.height = height;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
//</editor-fold>
    
    public boolean collides(float up2, float down2, float left2, float right2) {
        TransformPart tp = this.entity.get(TransformPart.class);
        
        float up1 = tp.getY() + yOffset;
        float down1 = up1 + height;
        float left1 = tp.getX() + xOffset;
        float right1 = left1 + width;
        
        return (up1 < down2 && down1 > up2 &&
                left1 < right2 && right1 > left2); //de morgan boss
    }
    
    @Override
    public void update(float delta) {
        if (!this.entity.has(StatsPart.class))
            return;
        
        TransformPart tp = this.entity.get(TransformPart.class);
        StatsPart stats = this.entity.get(StatsPart.class);
        
        float up = tp.getY() + yOffset;
        float down = up + height;
        float left = tp.getX() + xOffset;
        float right = left + width;
        
        ArrayList<Entity> collided = this.entity.getScene().getCollissions(up, down, left, right);
        collided.remove(this.entity);
        
        switch (stats.statsType) {
            case BULLET: {
                for (int i = 0; i < collided.size(); ++i) {
                    Entity ent = collided.get(i);
                    if (ent == this.entity) {
                        continue;
                    }
                    if (ent.has(StatsPart.class)) {
                        StatsPart entStats = ent.get(StatsPart.class);
                        if (entStats.faction == stats.faction) {
                            continue;
                        }

                        if (entStats.isInvulnerable == false) {
                            entStats.inflictDamage(stats.damage);
                        }
                    }
                }

                if (stats.statsType == StatsType.BULLET && collided.size() > 0) {
                    this.entity.die();
                }
            } break;
            case SHIP: {
                if (stats.isInvulnerable) {
                    break;
                }
                for (int i = 0; i < collided.size(); ++i) {
                    Entity ent = collided.get(i);
                    if (ent == this.entity) {
                        continue;
                    }
                    if (ent.has(StatsPart.class)) {
                        StatsPart entStats = ent.get(StatsPart.class);
                        if (entStats.faction == stats.faction) {
                            continue;
                        }

                        if (entStats.isInvulnerable == false) {
                            entStats.inflictDamage(stats.damage);
                        }
                    }
                }
            } break;
            default: {
                
            } break;
        }
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        TransformPart transform = entity.get(TransformPart.class);
        g2d.fillRect((int) (transform.getX() + xOffset),
                        (int) (transform.getY() + yOffset),
                        (int) width, (int) height);
    }
}
