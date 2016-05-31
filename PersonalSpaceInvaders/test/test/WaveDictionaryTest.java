/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import personalspaceinvaders.factories.WavesFactory;
import personalspaceinvaders.waveUtilities.WaveDictionary;
import personalspaceinvaders.waveUtilities.WaveInfo;

/**
 *
 * @author Vlad
 */
public class WaveDictionaryTest
{
    
    public WaveDictionaryTest()
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
    public void waveDictionaryTest() 
    {
        WaveInfo expected = new WaveInfo();   
        expected.type = WavesFactory.WaveType.WAVE_MIXED1_BLOCK;
        expected.name = "Mixed Block";
        expected.duration = 10;
        
        WaveInfo returned = new WaveInfo();
        returned = WaveDictionary.getInstance().getWaveInfo(WavesFactory.WaveType.WAVE_MIXED1_BLOCK);
        
        assertTrue(returned.equals(expected));
        
        
        expected = new WaveInfo();
        expected.type = WavesFactory.WaveType.WAVE_BASIC_BLOCK;
        expected.name = "Basic Block";
        expected.duration = 15;
        
        returned = new WaveInfo();
        returned = WaveDictionary.getInstance().getWaveInfo(WavesFactory.WaveType.WAVE_BASIC_BLOCK);
        
        assertTrue(returned.equals(expected));
        
    }
}
