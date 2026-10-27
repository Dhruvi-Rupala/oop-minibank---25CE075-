package service;

import java.util.ArrayList;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import util.Repository;
import util.Validator;
import exception.BankException;
import exception.AccountNotFoundException;
import exception.InvalidAmountException;
import exception.InsufficientFundsException;

public class BankService {

    private Repository<Account> repository;

    public BankService(Repository<Account> repository) {
        this.repository = repository;
    }

    public Account openAccount(
            String type,
            String ownerName,
            String mobile,
            long balance,
            long limit) throws BankException {

        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new BankException("Owner name cannot be empty.");
        }

        if (!Validator.isValidMobile(mobile)) {
            throw new BankException("Invalid mobile number.");
        }

        if (balance < 0) {
            throw new InvalidAmountException(
                "Opening balance cannot be negative."
            );
        }

        Account account;

        if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(ownerName, balance, 0);
        } else if (type.equalsIgnoreCase("current")) {
            account = new CurrentAccount(ownerName, balance, limit);
        } else if (type.equalsIgnoreCase("fixed")) {
            account = new FixedDepositAccount(ownerName, balance);
        } else {
            throw new BankException("Invalid account type.");
        }

        repository.save(account);

        System.out.println(
            "Account opened successfully: "
            + account.getAccountNumber()
        );

        return account;
    }

    public void deposit(String accountNumber, long amount)
            throws BankException {

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                "Account not found: " + accountNumber
            );
        }

        account.deposit(amount);

        repository.save(account);

        System.out.println("Deposit successful.");
    }

    public void withdraw(String accountNumber, long amount)
            throws BankException {

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                "Account not found: " + accountNumber
            );
        }

        account.withdraw(amount);

        repository.save(account);

        System.out.println("Withdrawal successful.");
    }

    public void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            long amount) throws BankException {

        Account from = repository.findById(fromAccountNumber);
        Account to = repository.findById(toAccountNumber);

        if (from == null) {
            throw new AccountNotFoundException(
                "Source account not found: " + fromAccountNumber
            );
        }

        if (to == null) {
            throw new AccountNotFoundException(
                "Destination account not found: " + toAccountNumber
            );
        }

        if (from == to) {
            throw new BankException(
                "Source and destination accounts must be different."
            );
        }

        from.withdraw(amount);

        try {
            to.deposit(amount);
        } catch (BankException e) {
            from.deposit(amount);
            throw e;
        }

        repository.save(from);
        repository.save(to);

        System.out.println("Transfer successful.");
    }

    public String statement(String accountNumber)
            throws BankException {

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                "Account not found: " + accountNumber
            );
        }

        return "Account Number: " + account.getAccountNumber()
            + "\nOwner Name: " + account.getOwnerName()
            + "\nBalance: " + account.getBalance()
            + "\nActive: " + account.isActive();
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    public int getAccountCount() {
        return repository.count();
    }
}