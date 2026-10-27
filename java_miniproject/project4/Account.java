public class Account {
    private static int count = 1;
    private String accountNumber;
    private String ownerName;
    private long balance;
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

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void deposit(long amount) {
        if (amount > 0 && active) {
            balance += amount;
        }
    }

    public void withdraw(long amount) {
        if (amount > 0 && amount <= balance && active) {
            balance -= amount;
        }
    }
}