package org.delta;

import org.delta.account.*;
import org.delta.action.ActionListener;
import org.delta.action.HelpAction;
import org.delta.menu.Menu;
import org.delta.menu.MenuChoices;
import org.delta.person.Person;

import javax.inject.Inject;

public class Bank {

    final private ActionListener actionListener;

    @Inject
    private AccountInfoPrinterService accountInfoPrinterService;

    @Inject
    private MoneyTransferService moneyTransferService;

    @Inject
    private InterestRunnerService interestRunnerService;

    public Bank() {
        this.actionListener = new ActionListener();
        this.registerActions();
    }

    public void registerActions() {
        this.actionListener.registerAction(MenuChoices.HELP, new HelpAction());
        this.actionListener.registerAction(MenuChoices.DETAIL, new HelpAction());
        this.actionListener.registerAction(MenuChoices.ACCOUNTS, new HelpAction());
        this.actionListener.registerAction(MenuChoices.CREDIT, new HelpAction());
        this.actionListener.registerAction(MenuChoices.SAVING, new HelpAction());
        this.actionListener.registerAction(MenuChoices.INVALID_CHOICE, new HelpAction());
    }

    public void startTerminal() {
        System.out.println("Hello from bank application!");

        Menu menu = new Menu();
        menu.printMenu();

        while (true) {
            MenuChoices choice = menu.read();

            if (choice == MenuChoices.EXIT) {
                break;
            }

            this.actionListener.processAction(choice);
        }
    }

    public void example() {

        Person owner = new Person("Tomas", "Pesek");

        BaseAccount accountOne = new StudentAccount(owner, 1000);
        BaseAccount accountTwo = new BaseAccount(owner, 5000);
        BaseAccount accountThree = new SavingAccount(owner, 10000);

        this.accountInfoPrinterService.printAccountInfo(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountTwo);
        this.accountInfoPrinterService.printAccountInfo(accountThree);
        System.out.println();

        this.moneyTransferService.transferMoney(accountOne, accountTwo, 500);

        this.accountInfoPrinterService.printAccountInfo(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountTwo);
        System.out.println();

        BaseAccount[] accounts = new BaseAccount[3];
        accounts[0] = accountOne;
        accounts[1] = accountTwo;
        accounts[2] = accountThree;

        interestRunnerService.run(accounts);

        this.accountInfoPrinterService.printAccountInfo(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountTwo);
        this.accountInfoPrinterService.printAccountInfo(accountThree);
        System.out.println();
    }

}
