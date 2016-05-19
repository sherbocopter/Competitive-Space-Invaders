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
public class Line2D
{
    Line2D()
    {
        m_end = new Point2D();
        m_start = new Point2D();
    }

    public Line2D(Point2D m_start, Point2D m_end)
    {
        this.m_start = m_start;
        this.m_end = m_end;
    }

    public Point2D getM_start()
    {
        return m_start;
    }

    public Point2D getM_end()
    {
        return m_end;
    }

    public void setM_start(Point2D m_start)
    {
        this.m_start = m_start;
    }

    public void setM_end(Point2D m_end)
    {
        this.m_end = m_end;
    }
    
    private Point2D m_start;
    private Point2D m_end;
}
