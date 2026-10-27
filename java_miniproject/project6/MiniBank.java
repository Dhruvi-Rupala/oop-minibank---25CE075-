package service;

import java.util.Scanner;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.WithdrawRule;
import model.Premium;
import static util.Validator.isValidMobile;

public class MiniBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account[] accounts = {
            new SavingsAccount("Rahul", 10000, 2000),
            new CurrentAccount("Priya", 5000, 3000),
            new FixedDepositAccount("Riya", 20000)
        };

        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        WithdrawRule rule2 =
                (account, amount) -> account.canWithdraw(amount);

        System.out.println("===== WITHDRAW RULE TEST =====");
        System.out.println("Anonymous Class: "
                + rule1.allow(accounts[0], 1000));
        System.out.println("Lambda Expression: "
                + rule2.allow(accounts[0], 1000));

        System.out.println("\n===== INTEREST RATES =====");

        for (Account account : accounts) {
            System.out.println("Owner: " + account.getOwnerName());
            System.out.println("Balance: Rs. " + account.getBalance());
            System.out.println("Interest Rate: "
                    + account.interestRate() + "%");
            System.out.println("Yearly Interest: Rs. "
                    + account.yearlyInterest());

            if (account instanceof Premium) {
                System.out.println("Premium Account: Yes");
            }

            System.out.println();
        }

        System.out.println("Valid Mobile: "
                + isValidMobile("9876543210"));

        int choice;

        do {
            System.out.println("\n===== MINIBANK MENU =====");
            System.out.println("1. Show Accounts");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Account account : accounts) {
                        System.out.println(
                                account.getAccountNumber() + " | "
                                + account.getOwnerName() + " | Rs. "
                                + account.getBalance());
                    }
                    break;

                case 2:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 2);

        sc.close();
    }
}