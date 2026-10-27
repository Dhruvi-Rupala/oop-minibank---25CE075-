package model;

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
    public void deposit(long amount) {
        if (amount > 0 && active) {
            balance += amount;
        }
    }

    @Override
    public boolean withdraw(long amount) {
        if (amount > 0 && active && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public abstract boolean canWithdraw(long amount);
}