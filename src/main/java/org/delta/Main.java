package org.delta;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.delta.notification.account.NotifyCustomerEventListener;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new BankInjector());
        Bank bank = injector.getInstance(Bank.class);

        injector.getInstance(NotifyCustomerEventListener.class);

        bank.startTerminal();

    }
}