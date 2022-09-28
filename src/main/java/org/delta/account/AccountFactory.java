package org.delta.account;

import org.delta.person.Person;

import javax.inject.Singleton;

@Singleton
public class AccountFactory {

    public BaseAccount createBaseAccount(Person person, float balance) {
        return new BaseAccount(person, balance);
    }

    public StudentAccount createStudentAccount(Person person, float balance) {
        return new StudentAccount(person, balance);
    }

    public StudentAccount createSavingAccount(Person person, float balance) {
        return new StudentAccount(person, balance);
    }
}
