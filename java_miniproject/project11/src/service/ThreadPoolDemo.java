package service;

import model.Account;
import model.SavingsAccount;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        Account account = new SavingsAccount("Rahul", 1000, 0);

        TransactionProcessor processor = new TransactionProcessor();

        for (int i = 0; i < 10; i++) {
            processor.submit(new TransactionTask(account, 100, true));
        }

        for (int i = 0; i < 5; i++) {
            processor.submit(new TransactionTask(account, 100, false));
        }

        processor.stop();

        System.out.println("Final balance: " + account.getBalance());
    }
}