package org.delta.account.services;

import org.delta.account.AccountFactory;
import org.delta.account.AccountType;
import org.delta.account.AccountTypeDoesNotExist;
import org.delta.account.BaseAccount;
import org.delta.person.Person;
import org.delta.person.PersonFactory;
import org.delta.utils.AccountNumberGeneratorService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class AccountService {

    private final List<BaseAccount> accounts = new LinkedList<>();
    @Inject
    private AccountFactory accountFactory;

    @Inject
    private AccountNumberGeneratorService accountNumberGeneratorService;

    @Inject
    private PersonFactory personFactory;

    public void addAccount(BaseAccount account) {
        this.accounts.add(account);
    }

    public BaseAccount findAccount(String accountNumber) {
        for (BaseAccount account : this.accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    public BaseAccount createAccount(AccountType accountType, Person person, float balance) throws AccountTypeDoesNotExist {

        String accountNumber = this.accountNumberGeneratorService.generateAccountNumber();

        BaseAccount account = switch (accountType) {
            case BASE -> this.accountFactory.createBaseAccount(accountNumber, person, balance);
            case STUDENT -> this.accountFactory.createStudentAccount(accountNumber, person, balance);
            case SAVING -> this.accountFactory.createSavingAccount(accountNumber, person, balance);
        };

        if (account == null) {
            throw new AccountTypeDoesNotExist();
        }

        this.addAccount(account);

        return account;
    }

    public BaseAccount[] getAccounts() {
        return this.accounts.toArray(new BaseAccount[0]);
    }
}
