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
public class Point2D
{
    public Point2D()
    {
        m_nX = 0;
        m_nY = 0;
    }
    
    public Point2D(double temp_x, double temp_y)
    {
        m_nX = temp_x;
        m_nY = temp_y;
    }

    public double getM_nX()
    {
        return m_nX;
    }

    public double getM_nY()
    {
        return m_nY;
    }
    
    private final double m_nX;
    private final double m_nY;
}
