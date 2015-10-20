package org.people.service.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.people.model.Family;
import org.people.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
 "classpath:/rest-client-config.xml" })
public class PeopleServiceRestClientIntegrationTest {

	@Autowired
	private PeopleServiceClient peopleServiceClient;

	private Family family;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddFamily() throws Exception {
		family = createSampleFamily();

		Family added = peopleServiceClient.addFamily(family);

		assertNotNull(added.getFid());
		assertEquals(added.getName(), family.getName());
	}

	@Test
	public final void testUpdateFamily() throws Exception {
		family.setName(family.getName() + " (Modified) ");
		Family updated = peopleServiceClient.updateFamily(family);

		assertEquals(updated.getName(), family.getName() + " (Modified) ");
	}

	private Person createSamplePerson() {
		Person person = new Person();
		person.setName("Definitly Unique");
		return person;
	}

	private Family createSampleFamily() {
		Family family = new Family();
		family.setName("Unique");
		return family;
	}
}
