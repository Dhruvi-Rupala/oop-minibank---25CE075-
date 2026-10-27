package service;

import java.util.ArrayList;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import util.Repository;

public class RepositoryDemo {

    public static void main(String[] args) {

        Repository<Account> repository = new Repository<>();

        Account a1 = new SavingsAccount("Rahul", 5000, 0);
        Account a2 = new CurrentAccount("Priya", 2000, 1000);
        Account a3 = new FixedDepositAccount("Riya", 9000);

        repository.saveAll(a1, a2, a3);

        System.out.println("===== FIND ACCOUNT =====");

        Account found = repository.findById("AC0002");

        if (found != null) {
            System.out.println(
                found.getAccountNumber() + " | "
                + found.getOwnerName() + " | Balance: "
                + found.getBalance()
            );
        } else {
            System.out.println("Account not found.");
        }

        System.out.println("\n===== ALL ACCOUNTS =====");

        ArrayList<Account> accounts = repository.findAll();

        for (Account account : accounts) {
            System.out.println(
                account.getAccountNumber() + " | "
                + account.getOwnerName() + " | Balance: "
                + account.getBalance()
            );
        }

        System.out.println("\nTotal accounts: " + repository.count());

        Integer[] numbers = {10, 20, 30, 40};

        System.out.println("Average: " + Repository.average(numbers));

        System.out.println(
            "Delete AC0002: " + repository.delete("AC0002")
        );

        System.out.println(
            "Accounts after deletion: " + repository.count()
        );
    }
}