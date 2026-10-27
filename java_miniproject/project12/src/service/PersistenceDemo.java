package service;

import java.nio.file.Files;
import java.nio.file.Path;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

public class PersistenceDemo {

    public static void main(String[] args) {
        Path dataFolder = Path.of("data");
        Path accountsFile = dataFolder.resolve("accounts.dat");
        Path logsFolder = dataFolder.resolve("logs");
        Path logFile = logsFolder.resolve("transactions.log");
        Path reportFile = dataFolder.resolve("report.txt");

        try {
            Files.createDirectories(logsFolder);

            Account[] accounts = {
                new SavingsAccount("Rahul", 1000, 0),
                new CurrentAccount("Priya", 2000, 1000),
                new FixedDepositAccount("Riya", 5000)
            };

            StatePersister.save(accounts, accountsFile);
            System.out.println("Accounts saved successfully.");

            Account[] loadedAccounts = StatePersister.load(accountsFile);

            System.out.println("\n===== RELOADED ACCOUNTS =====");

            for (Account account : loadedAccounts) {
                System.out.println(account.getAccountNumber() + " | "
                    + account.getOwnerName() + " | Balance: "
                    + account.getBalance());
            }

            TransactionLog.append(logFile, "DEPOSIT AC0001 500");
            TransactionLog.append(logFile, "WITHDRAW AC0002 200");
            TransactionLog.append(logFile, "DEPOSIT AC0003 1000");

            ReportGenerator.generate(logsFolder, reportFile);

            System.out.println("\n===== REPORT =====");

            for (String line : Files.readAllLines(reportFile)) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}