/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalspaceinvaders.geometry;

/**
 * http://gamedev.stackexchange.com/questions/39931/fast-accurate-2d-collision
 * Trying to implement this 
 * This is all i can do for now,
 * "Am sa trec si peste asta/ Ce va fi va fi/ Am zic iar, Asta-i viata/ E si maine o zi"
 * 
 * @author Vlad
 */
public class Square2D
{

    public double getM_dWidth()
    {
        return m_dWidth;
    }

    public double getM_dHeight()
    {
        return m_dHeight;
    }

    public Point2D getM_Origin()
    {
        return m_Origin;
    }

    public void setM_dWidth(double m_dWidth)
    {
        this.m_dWidth = m_dWidth;
    }

    public void setM_dHeight(double m_dHeight)
    {
        this.m_dHeight = m_dHeight;
    }

    public void setM_Origin(Point2D m_Origin)
    {
        this.m_Origin = m_Origin;
    }
    
    
    private double m_dWidth;
    private double m_dHeight;
    // m-Origin is left top point
    private Point2D m_Origin;
}
