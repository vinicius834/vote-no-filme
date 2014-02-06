package br.org.votenofilme.models;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person person = null;
	private Person person1 = null;
	private Person person2 = null;
	
	@Before
	public void setUp() throws Exception {
		person = new Person("Adele", "");
		person1 = new Person("Daniela", "");
		person2 = new Person("Glória", "");
	}
	
	private void persistPersonsToSaveTest() {
		person.save();
		
	}
	
	private void persistPersonsToDeleteTest() {
		person1.save();
	}
	
	private void persistPersonsToUpdateTest() {
		person2.save();
	}

	@Test
	public void saveTest() {
		persistPersonsToSaveTest();
		Person personFind = Person.findById(person.getId());
		Assert.assertTrue(personFind.equals(person));
	}
	
	@Test
	public void listTest() {
		List<Person> persons = (List<Person>) Person.list();
		Assert.assertFalse(persons.isEmpty());
		Assert.assertTrue(persons.size() > 0);
	}
	
	@Test
	public void deleteTest() {
		persistPersonsToDeleteTest();
		long idPerson = person1.getId();
		person1.delete();
		Person personFind = Person.findById(idPerson);
		Assert.assertTrue(personFind == null);
	}
	
	@Test
	public void updateTest() {
		persistPersonsToUpdateTest();
		String oldName = person2.getName();
		String newName = "Messi";
		person2.setName(newName);
		person2.update();
		Person personFind = Person.findById(person2.getId());
		Assert.assertTrue(personFind.getName().equals(newName));
		Assert.assertFalse(personFind.getName().equals(oldName));
	}
}