package org.people.service.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetFamily() throws Exception {
		Family family = peopleServiceClient.getFamily(2L);
		assertEquals(family.getFid().longValue(), 2L);
		assertEquals(family.getName(), "Smith");
	}

	@Test
	public final void testFindFamily() throws Exception {
		List<Family> familyList = peopleServiceClient.findFamily("Smith");
		assertEquals(1, familyList.size());
	}

	@Test
	public final void testAddFamily() throws Exception {
		Family family = new Family();
		family.setName("Unique");
		Family added = peopleServiceClient.addFamily(family);

		assertNotNull(added.getFid());
		assertEquals(added.getName(), family.getName());
	}

	@Test
	public final void testUpdateFamily() throws Exception {
		Family family = peopleServiceClient.getFamily(1L);
		family.setName(family.getName() + " (Modified) ");
		Family updated = peopleServiceClient.updateFamily(family);

		assertEquals(updated.getName(), family.getName());
	}

	@Test
	public final void testGetPerson() throws Exception {
		Person person = peopleServiceClient.getPerson(2L);
		assertEquals(person.getPid().longValue(), 2L);
		assertEquals(person.getName(), "Jane Doe");
	}

	@Test
	public final void testFindPerson() throws Exception {
		List<Person> personList = peopleServiceClient.findPerson("Jane Doe");
		assertEquals(1, personList.size());
	}

	@Test
	public final void testAddPerson() throws Exception {
		Person person = new Person();
		person.setName("Definitly Unique");
		Person added = peopleServiceClient.addPerson(person);

		assertNotNull(added.getPid());
		assertEquals(added.getName(), person.getName());
	}

	@Test
	public final void testUpdatePerson() throws Exception {
		Person person = peopleServiceClient.getPerson(1L);
		String origName = person.getName();

		person.setName(origName + " (Modified) ");
		Person updated = peopleServiceClient.updatePerson(person);

		assertEquals(updated.getName(), person.getName());

		person.setName(origName);
		Person reverted = peopleServiceClient.updatePerson(person);

		assertEquals(reverted.getName(), person.getName());
	}

	@Test
	public final void testAddPersonToFamily() throws Exception {
		Family family = new Family();
		family.setName("test");
		Family addedFamily = peopleServiceClient.addFamily(family);

		Person person = new Person();
		person.setName("Person A");
		Person addedPerson = peopleServiceClient.addPerson(person);

		peopleServiceClient.addPersonToFamily(addedPerson.getPid(),
				addedFamily.getFid());

		Person personWithFamily = peopleServiceClient.getPerson(addedPerson
				.getPid());

		assertNotNull(personWithFamily.getFamily());
		assertEquals(personWithFamily.getFamily().getName(),
				addedFamily.getName());
	}

	@Test
	public final void testRemovePersonFromFamily() throws Exception {
		Family family = new Family();
		family.setName("Sample");
		Family addedFamily = peopleServiceClient.addFamily(family);

		Person person = new Person();
		person.setName("Sample B");
		Person addedPerson = peopleServiceClient.addPerson(person);

		peopleServiceClient.addPersonToFamily(addedPerson.getPid(),
				addedFamily.getFid());

		Person personWithFamily = peopleServiceClient.getPerson(addedPerson
				.getPid());

		assertNotNull(personWithFamily.getFamily());
		assertEquals(personWithFamily.getFamily().getName(),
				addedFamily.getName());

		peopleServiceClient.removePersonFromFamily(addedPerson.getPid(),
				addedFamily.getFid());

		personWithFamily = peopleServiceClient.getPerson(addedPerson.getPid());

		assertNull(personWithFamily.getFamily());
	}

	@Test
	public final void testGetFamilyPeople() throws Exception {
		Person person = peopleServiceClient.getPerson(1L);
		String origName = person.getName();

		person.setName(origName + " (Modified) ");
		Person updated = peopleServiceClient.updatePerson(person);

		assertEquals(updated.getName(), person.getName());

		person.setName(origName);
		Person reverted = peopleServiceClient.updatePerson(person);

		assertEquals(reverted.getName(), person.getName());
	}

}
