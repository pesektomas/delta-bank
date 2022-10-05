package org.delta;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.delta.account.AccountTypeDoesNotExist;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new BankInjector());

        Bank bank = injector.getInstance(Bank.class);
        try {
            bank.example();
        } catch (AccountTypeDoesNotExist e) {
            throw new RuntimeException(e);
        }

        //Bank bank = new Bank();
        //bank.startTerminal();
        //bank.example();
    }
}