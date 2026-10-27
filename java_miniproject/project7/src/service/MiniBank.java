package service;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.WithdrawRule;
import util.AnnotationValidator;
import static util.Validator.isValidMobile;

public class MiniBank {
    public static void main(String[] args) {
        Account invalidAccount =
                new SavingsAccount("Rahul", -100, 2000);

        System.out.println("===== INVALID ACCOUNT =====");

        String[] errors = AnnotationValidator.validate(invalidAccount);

        for (String error : errors) {
            System.out.println(error);
        }

        Account validAccount =
                new SavingsAccount("Priya", 10000, 2000);

        System.out.println("\n===== VALID ACCOUNT =====");

        String[] validErrors =
                AnnotationValidator.validate(validAccount);

        if (validErrors.length == 0) {
            System.out.println("No validation errors");
        } else {
            for (String error : validErrors) {
                System.out.println(error);
            }
        }

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

        System.out.println("\nAnonymous Class: "
                + rule1.allow(accounts[0], 1000));

        System.out.println("Lambda Expression: "
                + rule2.allow(accounts[0], 1000));

        System.out.println("Valid Mobile: "
                + isValidMobile("9876543210"));

        System.out.println("\n===== INTEREST RATES =====");

        for (Account account : accounts) {
            System.out.println(account.getOwnerName() + ": "
                    + account.interestRate() + "%");
        }
    }
}