package org.delta.account;

import org.delta.person.Person;

import javax.inject.Singleton;

@Singleton
public class AccountFactory {

    public BaseAccount createBaseAccount(String accountNumber, Person person, float balance) {
        return new BaseAccount(accountNumber, person, balance);
    }

    public StudentAccount createStudentAccount(String accountNumber, Person person, float balance) {
        return new StudentAccount(accountNumber, person, balance);
    }

    public SavingAccount createSavingAccount(String accountNumber, Person person, float balance) {
        return new SavingAccount(accountNumber, person, balance);
    }
}
