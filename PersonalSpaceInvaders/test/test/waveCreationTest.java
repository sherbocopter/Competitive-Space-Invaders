/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import personalspaceinvaders.Entity;
import personalspaceinvaders.factories.WavesFactory;

/**
 *
 * @author Vlad
 */
public class waveCreationTest
{
    
    public waveCreationTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void waveCreationTest() 
    {
        ArrayList<Entity> array = new ArrayList<Entity>();
        array = WavesFactory.getInstance().createWave(WavesFactory.WaveType.WAVE_BASIC_BLOCK);
        assertTrue(array.size() == 20);
    }
}