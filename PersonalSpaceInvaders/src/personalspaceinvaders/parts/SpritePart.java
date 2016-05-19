package personalspaceinvaders.parts;

import java.awt.Graphics2D;
import java.awt.Image;
import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class SpritePart extends Part {
    private float xOffset;
    private float yOffset;
    private float rotationOffset;
    private float width;
    private float height;
    private Image image;
    
    //private Color tint = ceva;
    
    public SpritePart(Image image, float xOffset, float yOffset, float rotationOffset, float width, float height) {
        this.image = image;
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
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
//</editor-fold>
    
    @Override
    public void draw(Graphics2D g2d) {
        TransformPart transform = entity.get(TransformPart.class);
        g2d.drawImage(image, (int) (transform.getX() + xOffset),
                        (int) (transform.getY() + yOffset),
                        (int) width, (int) height, null);
    }
}
