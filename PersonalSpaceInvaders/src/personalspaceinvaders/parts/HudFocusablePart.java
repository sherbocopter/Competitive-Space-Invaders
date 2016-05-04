package personalspaceinvaders.parts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import personalspaceinvaders.Command;
import personalspaceinvaders.Part;

/**
 *
 * @author SHerbocopter
 */
public class HudFocusablePart extends Part {
    private boolean isFocused = false;
    private boolean shouldExecute = false;
    private Command command = null;
    
    private float xOffset;
    private float yOffset;
    private float rotationOffset;
    private float width;
    private float height;
    
    private Color color;
    private float highlightWidth = 1;
    
    public HudFocusablePart(float xOffset, float yOffset, float rotationOffset, float width, float height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.rotationOffset = rotationOffset;
        this.width = width;
        this.height = height;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters + Setters">
    public Command getCommand() {
        return command;
    }
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public float getHighlightWidth() {
        return highlightWidth;
    }
    
    public void setHighlightWidth(float highlightWidth) {
        this.highlightWidth = highlightWidth;
    }
    
    public boolean getFocused() {
        return isFocused;
    }
    
    public void setFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }
    
    public boolean getShouldExecute() {
        return shouldExecute;
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
    
    public void executeCommand() {
        this.shouldExecute = true;
    }
    
    @Override
    public void update(float delta) {
        if (shouldExecute) {
            if (command != null) {
                command.execute(this);
            }
            shouldExecute = false;
        }
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        if (!isFocused)
            return;
        
        g2d.setColor(color);
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(highlightWidth));
        TransformPart transform = entity.get(TransformPart.class);
        
        g2d.drawRect((int) (transform.getX() + xOffset),
                        (int) (transform.getY() + yOffset),
                        (int) width, (int) height);
        
        g2d.setStroke(oldStroke);
    }
}
