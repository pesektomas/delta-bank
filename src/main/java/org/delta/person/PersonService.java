package org.delta.person;

import org.delta.serialization.person.PersonJsonSerializationObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class PersonService {

    private final Map<String, Person> persons = new HashMap<>();

    @Inject
    private PersonFactory personFactory;

    public Person createPerson(String id, String name, String lastName) {
        Person person = this.personFactory.createPerson(id, name, lastName);

        this.persons.put(person.getId(), person);

        return person;
    }

    public Person createPerson(PersonJsonSerializationObject personJsonSerializationObject) {
        Person person = this.personFactory.createPerson(personJsonSerializationObject);

        this.persons.put(person.getId(), person);

        return person;
    }

    public Person[] getPersons() {
        return this.persons.values().toArray(new Person[0]);
    }

    public Person findPersonById(String id) {
        return this.persons.get(id);
    }
}
