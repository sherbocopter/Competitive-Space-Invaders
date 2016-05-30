/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalspaceinvaders.geometry;

/**
 *
 * @author Vlad
 */
public class Vector2D
{
    public Vector2D(double x, double y)
    {
        m_dX = x;
        m_dY = y;
    }
    
    public Vector2D add(Vector2D rhs)
    {
        this.m_dX += rhs.m_dX;
        this.m_dY += rhs.m_dY;
        return this;
    }
    
    public Vector2D multiplyScalar(double s)
    {
        this.m_dX *= s;
        this.m_dY *= s;
        return this;
    }
    
    public double crossProduct(Vector2D rhs)
    {
        return (this.m_dX * rhs.m_dY - this.m_dY * rhs.m_dX); 
    }
    
    public Boolean isCollinear(Vector2D rhs)
    {
        return this.crossProduct(rhs) == 0;
    }
    
    // The vector has its origin in (0, 0) and the x and y reprezent the
    // Direction of the vector
    private double m_dX;
    private double m_dY;
}
