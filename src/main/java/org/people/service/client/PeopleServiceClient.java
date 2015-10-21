package org.people.service.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.people.model.Family;
import org.people.model.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class PeopleServiceClient extends RestClient {

	public PeopleServiceClient() {
		super();
	}

	public Family addFamily(Family family) {
		HttpEntity<Family> httpEntity = createHttpEntity(family);
		String postUrl = getServiceUrl() + "/family";
		return getRestTemplate().postForObject(postUrl, httpEntity,
				Family.class);
	}

	public Family updateFamily(Family family) {
		HttpEntity<Family> httpEntity = createHttpEntity(family);
		String postUrl = getServiceUrl() + "/family/update";
		return getRestTemplate().postForObject(postUrl, httpEntity,
				Family.class);
	}

	public List<Family> findFamily(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		String getUrl = getServiceUrl() + "/family/find/{name}";
		ResponseEntity<Family[]> responseEntity = getRestTemplate().exchange(
				getUrl, HttpMethod.GET, createHttpEntity(null), Family[].class,
				parameters);
		Family[] families = responseEntity.getBody();

		return Arrays.asList(families);
	}

	public Family getFamily(Long fid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fid", fid);
		String getUrl = getServiceUrl() + "/family/{fid}";
		ResponseEntity<Family> responseEntity = getRestTemplate().exchange(
				getUrl, HttpMethod.GET, createHttpEntity(null), Family.class,
				parameters);
		Family family = responseEntity.getBody();

		return family;
	}

	public Person addPerson(Person person) {
		HttpEntity<Person> httpEntity = createHttpEntity(person);
		String postUrl = getServiceUrl() + "/person";
		return getRestTemplate().postForObject(postUrl, httpEntity,
				Person.class);
	}

	public Person updatePerson(Person person) {
		HttpEntity<Person> httpEntity = createHttpEntity(person);
		String postUrl = getServiceUrl() + "/person/update";
		return getRestTemplate().postForObject(postUrl, httpEntity,
				Person.class);
	}

	public List<Person> findPerson(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		String getUrl = getServiceUrl() + "/person/find/{name}";
		ResponseEntity<Person[]> responseEntity = getRestTemplate().exchange(
				getUrl, HttpMethod.GET, createHttpEntity(null), Person[].class,
				parameters);
		Person[] persons = responseEntity.getBody();

		return Arrays.asList(persons);
	}

	public Person getPerson(Long pid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pid", pid);
		String getUrl = getServiceUrl() + "/person/{pid}";
		ResponseEntity<Person> responseEntity = getRestTemplate().exchange(
				getUrl, HttpMethod.GET, createHttpEntity(null), Person.class,
				parameters);
		Person person = responseEntity.getBody();

		return person;
	}

	public void addPersonToFamily(Long pid, Long fid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pid", pid);
		parameters.put("fid", fid);
		String getUrl = getServiceUrl() + "/add/person/{pid}/family/{fid}";
		getRestTemplate().exchange(
				getUrl, HttpMethod.GET, createHttpEntity(null), String.class,
				parameters);
	}

	public void removePersonFromFamily(Long pid, Long fid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pid", pid);
		parameters.put("fid", fid);
		String getUrl = getServiceUrl() + "/del/person/{pid}/family/{fid}";
		getRestTemplate().exchange(getUrl, HttpMethod.GET,
				createHttpEntity(null), String.class, parameters);
	}

	public List<Person> getFamilyPeople(Long fid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fid", fid);
		String getUrl = getServiceUrl() + "/familyPeople/{fid}";
		ResponseEntity<Person[]> responseEntity = getRestTemplate().exchange(
				getUrl, HttpMethod.GET, createHttpEntity(null), Person[].class,
				parameters);
		Person[] persons = responseEntity.getBody();

		return Arrays.asList(persons);

	}

}
