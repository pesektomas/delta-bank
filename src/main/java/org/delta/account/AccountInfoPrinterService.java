package org.delta.account;

import javax.inject.Singleton;

@Singleton
public class AccountInfoPrinterService {

    public void printAccountInfo(BaseAccount account) {
        System.out.println(this.getTypePrefix(account) + "Balance of " + account.getOwner().getFullName() + " account is " + account.getBalance());
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
