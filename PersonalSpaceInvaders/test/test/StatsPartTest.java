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
import personalspaceinvaders.Entity;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.parts.StatsPart;

/**
 *
 * @author Vlad
 */
public class StatsPartTest
{
    
    public StatsPartTest()
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
    
    // Creating a basic white alien to test the StatsPart
    @Test
    public void StatsPartTest() 
    {
        EntityFactory fac = EntityFactory.getInstance();
        Entity returned = fac.createEntity(EntityFactory.EntityType.ALIEN_BASIC_WHITE);
        StatsPart returnedStatsPart = new StatsPart();
        returnedStatsPart = returned.get(StatsPart.class);
        
        StatsPart expected = new StatsPart();     
        expected.maxHitpoints = 120;
        expected.setCurrentHitpoints(80);
        expected.faction = StatsPart.Faction.FACTION_ENEMY;
        expected.statsType = StatsPart.StatsType.SHIP;
        expected.damage = 50;
        expected.invulnerableTime = (float) 0.1;
        expected.setActive(true);
        
        assertTrue(returnedStatsPart.equals(expected));
        
        // Testing inflicDamage method
        expected.setCurrentHitpoints(70);
        returnedStatsPart.inflictDamage(10);
        assertTrue(returnedStatsPart.equals(expected));
    }
}
