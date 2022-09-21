package org.delta.account;

import org.delta.person.Person;

public class SavingAccount extends BaseAccount implements AccountWithInterest {

    public SavingAccount(Person owner, float balance) {
        super(owner, balance);
    }

    @Override
    public float getInterest() {
        return 5;
    }
}
