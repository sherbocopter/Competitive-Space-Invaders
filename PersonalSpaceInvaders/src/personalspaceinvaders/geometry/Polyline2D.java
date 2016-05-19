/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalspaceinvaders.geometry;

import java.util.ArrayList;

/**
 *
 * @author Vlad
 */
public class Polyline2D
{
    Polyline2D()
    {
        m_Vertices.clear();
        m_NumberOfVertices = 0;
        m_bIsClosed = false;
    }
    
    Polyline2D(ArrayList<Point2D> array)
    {
        m_Vertices = array;
        m_NumberOfVertices = array.size();
        m_bIsClosed = false;
    } 
    
    public Point2D getVertexAt(int i) throws IndexOutOfBoundsException
    {
        return m_Vertices.get(i);
    }

    public void setM_Vertices(ArrayList<Point2D> m_Vertices)
    {
        this.m_Vertices = m_Vertices;
    }

    public void setM_NumberOfVertices(int m_NumberOfVertices)
    {
        this.m_NumberOfVertices = m_NumberOfVertices;
    }

    public void setM_bIsClosed(Boolean m_bIsClosed)
    {
        this.m_bIsClosed = m_bIsClosed;
    }
    
    public ArrayList<Point2D> getM_Vertices()
    {
        return m_Vertices;
    }

    public int getM_NumberOfVertices()
    {
        return m_NumberOfVertices;
    }

    public Boolean getM_bIsClosed()
    {
        return m_bIsClosed;
    }
     
    private ArrayList<Point2D> m_Vertices;
    private int m_NumberOfVertices;
    private Boolean m_bIsClosed;
}
