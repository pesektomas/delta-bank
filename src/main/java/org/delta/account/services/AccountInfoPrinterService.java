package org.delta.account.services;

import org.delta.account.BaseAccount;
import org.delta.account.SavingAccount;
import org.delta.account.StudentAccount;
import org.delta.card.BaseCard;
import org.delta.card.CardInfoPrinterService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AccountInfoPrinterService {

    @Inject
    private CardInfoPrinterService cardInfoPrinterService;

    public void printAccountInfo(BaseAccount account) {
        System.out.println(this.getTypePrefix(account) + "Balance of " + account.getOwner().getFullName() + " account (" + account.getAccountNumber() + ") is " + account.getBalance() + ", number of cards: " + account.getCardCount());

        for (BaseCard card : account.getCards()) {
            this.cardInfoPrinterService.printBaseInfo(card);
        }
    }

    private String getTypePrefix(BaseAccount account) {
        if (account instanceof StudentAccount) {
            return "[Student] ";
        }

        if (account instanceof SavingAccount) {
            return "[Saving] ";
        }

        return "[Base] ";
    }

}
