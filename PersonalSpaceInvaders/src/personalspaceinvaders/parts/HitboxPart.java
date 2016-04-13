package personalspaceinvaders.parts;

import java.awt.Color;
import java.awt.Graphics2D;
import personalspaceinvaders.Part;

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
    
    public HitboxPart(float xOffset, float yOffset, float rotationOffset, float width, float height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.rotationOffset = rotationOffset;
        this.width = width;
        this.height = height;
        
        this.setIsVisible(true);
    }
    
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
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.setColor(Color.PINK);
        TransformPart transform = entity.get(TransformPart.class);
        g2d.fillRect((int) (transform.getX() + xOffset),
                        (int) (transform.getY() + yOffset),
                        (int) width, (int) height);
    }
}
