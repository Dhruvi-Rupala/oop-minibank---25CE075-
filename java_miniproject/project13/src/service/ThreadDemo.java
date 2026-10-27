package service;

import model.Account;
import model.SavingsAccount;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        Account account = new SavingsAccount("Rahul", 0, 0);

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                new AccountWorker(account, 1000, 1),
                "Worker-" + (i + 1)
            );

            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Final Balance: " + account.getBalance());
        System.out.println("Expected Balance: 10000");
    }
}