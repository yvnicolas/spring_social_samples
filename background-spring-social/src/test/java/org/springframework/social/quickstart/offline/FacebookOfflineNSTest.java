package org.springframework.social.quickstart.offline;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = org.springframework.social.quickstart.config.TestConfig.class)
public class FacebookOfflineNSTest {

	@Test
	public void test1() {
		FacebookOfflineNS essai = new FacebookOfflineNS(new User("yves@dynamease"));
		List<String> contacts = essai.unemethode();
		System.out.println(String.format("Liste des %s amis de user 1",
				contacts.size()));
		System.out.println("-----------------");

		for (String ami : contacts) {
			System.out.println(ami);
		}
		assertTrue(true);
	}

	@Test
	public void test2() {
		FacebookOfflineNS essai = new FacebookOfflineNS(new User("pauline"));
		List<String> contacts = essai.unemethode();
		System.out.println(String.format("Liste des %s amis de user 2",
				contacts.size()));
		System.out.println("-----------------");
		for (String ami : contacts) {
			System.out.println(ami);
		}
		assertTrue(true);
	}

}
