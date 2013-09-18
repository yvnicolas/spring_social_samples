package org.springframework.social.quickstart.offline;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.springframework.social.quickstart.config.MainConfig.class,
        org.springframework.social.quickstart.config.SocialConfig.class })
public class FacebookOfflineTest {

    @Test
    public void test1() {
        FacebookOffline essai = new FacebookOffline(new User("yves"));
		List<String> contacts = essai.contacts();
		System.out.println(String.format("Liste des %s amis de yves",
				contacts.size()));
		System.out.println("-----------------");

		for (String ami : contacts) {
			System.out.println(ami);
		}
		assertTrue(true);
	}

	@Test
	public void test2() {
		FacebookOffline essai = new FacebookOffline(new User("2"));
		List<String> contacts = essai.contacts();
		System.out.println(String.format("Liste des %s amis de user 2",
				contacts.size()));
		System.out.println("-----------------");
		for (String ami : contacts) {
			System.out.println(ami);
		}
		assertTrue(true);
	}

}
