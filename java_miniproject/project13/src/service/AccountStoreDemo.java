package service;

import java.util.ArrayList;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

public class AccountStoreDemo {

    public static void main(String[] args) {

        AccountStore store = new AccountStore();

        Account a1 = new SavingsAccount("Rahul", 5000, 0);
        Account a2 = new CurrentAccount("Priya", 2000, 1000);
        Account a3 = new FixedDepositAccount("Riya", 9000);

        store.add(a1);
        store.add(a2);
        store.add(a3);

        System.out.println("===== ACCOUNT LOOKUP =====");

        Account found = store.get("AC0002");

        if (found != null) {
            System.out.println(
                found.getAccountNumber() + " | "
                + found.getOwnerName() + " | Balance: "
                + found.getBalance()
            );
        } else {
            System.out.println("Account not found.");
        }

        System.out.println("\n===== ACCOUNTS SORTED BY BALANCE =====");

        ArrayList<Account> sortedAccounts = store.sortedByBalance();

        for (Account account : sortedAccounts) {
            System.out.println(
                account.getAccountNumber() + " | "
                + account.getOwnerName() + " | Balance: "
                + account.getBalance()
            );
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        for (String entry : store.getTransactionHistory()) {
            System.out.println(entry);
        }

        System.out.println("\n===== ACCOUNTS ABOVE 4000 =====");

        for (Account account : store.getAccountsAbove(4000)) {
            System.out.println(
                account.getAccountNumber() + " | "
                + account.getOwnerName() + " | Balance: "
                + account.getBalance()
            );
        }
    }
}