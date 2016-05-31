/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import personalspaceinvaders.Entity;
import personalspaceinvaders.factories.EntityFactory;
import personalspaceinvaders.parts.HitboxPart;

/**
 *
 * @author Vlad
 */
public class BulletCreationTest
{
    
    public BulletCreationTest()
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
    public void createBasicBulletTest() 
    {
        // createBasicBullet will return an entity that has several parts
        // Testing for some of the parts
        
        EntityFactory fac = EntityFactory.getInstance();
        Entity returned = fac.createEntity(EntityFactory.EntityType.BULLET_BASIC);
        
        assertTrue(returned.get(HitboxPart.class).getColor().equals(Color.GREEN));
        
    }
}
