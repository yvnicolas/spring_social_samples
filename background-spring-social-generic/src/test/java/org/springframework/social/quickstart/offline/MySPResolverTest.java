package org.springframework.social.quickstart.offline;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A few tests to illustrate the MyFBFactory which returns a facebook object for
 * a specified user.
 * 
 * @author yves
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.springframework.social.quickstart.config.MainConfig.class,
        org.springframework.social.quickstart.config.SocialConfig.class,
        org.springframework.social.quickstart.config.TestConfigMySPResolverFactory.class })
public class MySPResolverTest {

    @Autowired
    private MySPResolverFactory factory;
    
    private SPConnectionRetriever spResolver;
    private List<Person> connections;
    

  

    @Autowired
    private User yves;

    @Autowired
    private User pauline;
    
    @Autowired
    private User kouser;
 

    /**
     * Plain test. Make sure the user id you specify exists in the connection
     * database before and that the number of connections in facebook
     * matches the assertEquals first argument
     * Using autowired existing users defined as beans in
     * TestConfigMyFBFactory
     */
    @Test
    public void test2() {
        
        
        // First Facebook Connections
        spResolver = factory.getConnectionRetriever(yves, ServiceProviders.FACEBOOK);
        connections = spResolver.getConnections();
        System.out.println(String.format("les %s connections : ", connections.size()));
        for (Person p : connections) {
            System.out.println(p.getFirstName()+" "+p.getLastName());
        }
        assertEquals(211, connections.size());
    }
    @Test
    public void test3() {
        
        // Then LinkedIn Connections
        spResolver = factory.getConnectionRetriever(yves, ServiceProviders.LINKEDIN);
        connections = spResolver.getConnections();
        System.out.println(String.format("les %s connections : ", connections.size()));
        for (Person p : connections) {
            System.out.println(p.getFirstName()+" "+p.getLastName());
        }
        assertEquals(891, connections.size());
        
    }
    
   //user connected to Facebook and not to linkedin
    
    @Test
    public void test4() {
        
        
        //  Facebook Connections
        spResolver = factory.getConnectionRetriever(pauline, ServiceProviders.FACEBOOK);
        connections = spResolver.getConnections();
        System.out.println(String.format("les %s connections : ", connections.size()));
        for (Person p : connections) {
            System.out.println(p.getFirstName()+" "+p.getLastName());
        }
        assertEquals(48, connections.size());
    }
  
    // LinkedIn should not work
    @Test
    public void testkoli() {
        try {
        spResolver = factory.getConnectionRetriever(pauline, ServiceProviders.LINKEDIN);
        fail("Should raise exception here");
        }
        catch (NotConnectedException e){
            assertTrue(true);
        }
    
    }
    /**
     * Testing a user which has no connection in the database
     */
    @Test
    public void testko() {
        try {
        spResolver = factory.getConnectionRetriever(kouser, ServiceProviders.FACEBOOK);
        fail("Should raise exception here");
        }
        catch (NotConnectedException e){
            assertTrue(true);
        }
    
    }

}
