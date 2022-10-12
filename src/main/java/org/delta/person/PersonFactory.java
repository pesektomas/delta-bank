package org.delta.person;

import org.delta.person.serialization.PersonJsonSerializationObject;

import javax.inject.Singleton;

@Singleton
public class PersonFactory {

    public Person createPerson(String name, String lastName) {
        return new Person(name, lastName);
    }

    public Person createFromPersonJsonSerializationObject(PersonJsonSerializationObject personJsonSerializationObject) {
        return this.createPerson(
                personJsonSerializationObject.firstName,
                personJsonSerializationObject.lastName
        );
    }

}
