package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import model.annotation.Positive;

public abstract class Account implements Transactable, InterestBearing {
    private static int count = 1;

    @Id
    private String accountNumber;

    private String ownerName;

    @Positive
    protected long balance;

    private boolean active;

    public Account(String ownerName, long balance) {
        this.accountNumber = String.format("AC%04d", count++);
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }

        if (!active) {
            throw new InvalidAmountException("Account is inactive.");
        }

        balance += amount;
    }

    @Override
    public boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }

        if (!active) {
            throw new InvalidAmountException("Account is inactive.");
        }

        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }

        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException(amount - balance);
        }

        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws BankException {
        try {
            if (to == null) {
                throw new BankException("Destination account not found.");
            }

            withdraw(amount);
            to.deposit(amount);

            System.out.println("Transfer successful.");
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer operation completed.");
        }
    }

    public abstract boolean canWithdraw(long amount);
}