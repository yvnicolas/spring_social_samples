package org.springframework.social.quickstart.offline;

import static org.junit.Assert.*;

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
        org.springframework.social.quickstart.config.TestConfigMyFBFactory.class })
public class MyFBFactoryTest {

    @Autowired
    private MyFBFactory underTest;

    /**
     * Plain test. Make sure the user id you specify exists in the connection
     * database before and that the first name of the profile in facebook
     * matches the assertEquals first argument
     */
    @Test
    public void test1() {
        Facebook fb = underTest.getFacebook(new User("yves@dynamease"));
        FacebookProfile mypro = fb.userOperations().getUserProfile();
        System.out
                .println(String.format("Profil : %s %s %s", mypro.getFirstName(), mypro.getLastName(), mypro.getId()));
        assertEquals("Yves", mypro.getFirstName());
    }

    @Autowired
    private User yves;

    @Autowired
    private User pauline;

    /**
     * Same test but using autowired existing users defined as beans in
     * TestConfigMyFBFactory
     */
    @Test
    public void test2() {
        Facebook fb = underTest.getFacebook(yves);
        FacebookProfile mypro = fb.userOperations().getUserProfile();
        System.out
                .println(String.format("Profil : %s %s %s", mypro.getFirstName(), mypro.getLastName(), mypro.getId()));
        assertEquals("Yves", mypro.getFirstName());
    }
    
    /**
     * Same test but using autowired existing users defined as beans in
     * TestConfigMyFBFactory
     */
    @Test
    public void test3() {
        Facebook fb = underTest.getFacebook(pauline);
        FacebookProfile mypro = fb.userOperations().getUserProfile();
        System.out
                .println(String.format("Profil : %s %s %s", mypro.getFirstName(), mypro.getLastName(), mypro.getId()));
        assertEquals("Pauline", mypro.getFirstName());
    }

    
    @Autowired
    private User kouser;
    /**
     * Testing a user which has no connection in the database
     */
    @Test
    public void testko() {
        try {
        underTest.getFacebook(kouser);
        fail("Should raise exception here");
        }
        catch (NotConnectedException e){
            assertTrue(true);
        }
    
    }

}
