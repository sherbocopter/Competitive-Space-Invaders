package personalspaceinvaders.parts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class TextLabelPart extends Part {
    private float xOffset;
    private float yOffset;
    private float rotationOffset;
    private float width;
    private float height;
    
    private Color color = Color.WHITE;
    private Font font;
    private String text = "...";
    private int maxLength = 30;
    private TextAllign textAllign = TextAllign.ALLIGN_CENTER;
    
    public enum TextAllign {
        ALLIGN_LEFT,
        ALLIGN_CENTER,
        ALLIGN_RIGHT
    }
    
    public TextLabelPart(Font font, float xOffset, float yOffset, float rotationOffset, float width, float height) {
        this.font = font;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.rotationOffset = rotationOffset;
        this.width = width;
        this.height = height;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters + Setters">
    public TextAllign getTextAllign() {
        return textAllign;
    }
    
    public void setTextAllign(TextAllign textAllign) {
        this.textAllign = textAllign;
    }
    
    public Font getFont() {
        return font;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public int getMaxLength() {
        return maxLength;
    }
    
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
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
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
//</editor-fold>
    
    @Override
    public void draw(Graphics2D g2d) {
        TransformPart transform = entity.get(TransformPart.class);
        FontMetrics metrics = g2d.getFontMetrics(font);

        float x = transform.getX() + xOffset;
        float y = transform.getY() + yOffset;
        
        switch(textAllign) {
            case ALLIGN_LEFT: {
                
            } break;
            case ALLIGN_CENTER: {
                x += (width - metrics.stringWidth(text)) / 2;
                y += (height - metrics.getHeight()) / 2 + metrics.getAscent();
            } break;
            case ALLIGN_RIGHT: {
                
            } break;
            default: {
                return;
            }
        }
        
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(text, x, y);
    }
}
