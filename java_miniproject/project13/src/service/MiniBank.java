package service;

import model.Account;
import model.SavingsAccount;
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class MiniBank {

    public static void main(String[] args) {

        Account account = new SavingsAccount("Rahul", 1000, 0);

        System.out.println("Account Holder: " + account.getOwnerName());
        System.out.println("Initial Balance: " + account.getBalance());

        try {
            account.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: short by " + e.getShortfall());
        } catch (InvalidAmountException e) {
            System.out.println("Invalid withdrawal: " + e.getMessage());
        } finally {
            System.out.println("Withdrawal operation completed.");
        }

        System.out.println("Balance after withdrawal: " + account.getBalance());

        try {
            account.deposit(-100);
        } catch (InvalidAmountException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        } finally {
            System.out.println("Deposit operation completed.");
        }

        try {
            account.transfer(null, 100);
        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        } finally {
            System.out.println("Main transfer handling completed.");
        }

        try (BankResource resource = new BankResource()) {
            System.out.println("Using bank resource.");
        } catch (Exception e) {
            System.out.println("Resource error: " + e.getMessage());
        }

    }
}

class BankResource implements AutoCloseable {

    public BankResource() {
        System.out.println("Bank resource opened.");
    }

    @Override
    public void close() {
        System.out.println("Bank resource closed.");
    }
}