package service;

import model.Account;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class TransactionTask implements Runnable {

    private Account account;
    private long amount;
    private boolean deposit;

    public TransactionTask(Account account, long amount, boolean deposit) {
        this.account = account;
        this.amount = amount;
        this.deposit = deposit;
    }

    @Override
    public void run() {
        try {
            if (deposit) {
                account.deposit(amount);
                System.out.println(Thread.currentThread().getName()
                        + " deposited " + amount);
            } else {
                account.withdraw(amount);
                System.out.println(Thread.currentThread().getName()
                        + " withdrew " + amount);
            }
        } catch (InsufficientFundsException | InvalidAmountException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}