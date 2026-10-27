package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import model.Account;

public class AccountStore {

    private HashMap<String, Account> accounts = new HashMap<>();
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
        transactionHistory.add("Account added: " + account.getAccountNumber());
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = new ArrayList<>(accounts.values());

        list.sort(new Comparator<Account>() {
            @Override
            public int compare(Account a, Account b) {
                return Long.compare(b.getBalance(), a.getBalance());
            }
        });

        return list;
    }

    public ArrayList<Account> getAccountsAbove(long amount) {
        ArrayList<Account> result = new ArrayList<>();

        for (Account account : accounts.values()) {
            if (account.getBalance() > amount) {
                result.add(account);
            }
        }

        return result;
    }

    public Map<String, Account> getAccountsMap() {
        return new HashMap<>(accounts);
    }
}