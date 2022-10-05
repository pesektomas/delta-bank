package org.delta;

import com.google.gson.Gson;
import org.delta.account.*;
import org.delta.action.ActionListener;
import org.delta.action.HelpAction;
import org.delta.card.CardCreatorService;
import org.delta.menu.Menu;
import org.delta.menu.MenuChoices;
import org.delta.person.Person;
import org.delta.person.PersonFactory;

import javax.inject.Inject;

public class Bank {

    @Inject
    private ActionListener actionListener;

    @Inject
    private AccountService accountService;

    @Inject
    private AccountInfoPrinterService accountInfoPrinterService;

    @Inject
    private MoneyTransferService moneyTransferService;

    @Inject
    private InterestRunnerService interestRunnerService;

    @Inject
    private PersonFactory personFactory;

    @Inject
    private CardCreatorService cardCreatorService;

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
        this.registerActions();

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

    public void example() throws AccountTypeDoesNotExist {

        Person owner = this.personFactory.createPerson("Tomas", "Pesek");

        BaseAccount accountOne = this.accountService.createAccount(AccountType.BASE, owner, 1000);
        BaseAccount accountTwo = this.accountService.createAccount(AccountType.STUDENT, owner, 5000);
        BaseAccount accountThree = this.accountService.createAccount(AccountType.SAVING, owner, 10000);

        this.accountInfoPrinterService.printAccountInfo(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountTwo);
        this.accountInfoPrinterService.printAccountInfo(accountThree);
        System.out.println();

        this.moneyTransferService.transferMoney(accountOne, accountTwo, 500);

        this.accountInfoPrinterService.printAccountInfo(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountTwo);
        System.out.println();

        interestRunnerService.run();

        this.accountInfoPrinterService.printAccountInfo(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountTwo);
        this.accountInfoPrinterService.printAccountInfo(accountThree);
        System.out.println();

        this.cardCreatorService.createCardAndSetIntoAccount(accountOne);
        this.accountInfoPrinterService.printAccountInfo(accountOne);

        Gson gson = new Gson();
        String json = gson.toJson(owner);

        System.out.println(json);

        /*try {
            IO.writeFile("accounts.json", json);

            String jsonFile = IO.readFile("accounts.json");
            System.out.println(jsonFile);

            BaseAccount readAccount = gson.fromJson(jsonFile, BaseAccount.class);
            //readAccount.printBalance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


    }
}
