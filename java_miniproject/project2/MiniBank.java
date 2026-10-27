public class MiniBank {
    public static boolean transfer(Account from, Account to, long amount) {
        if (from.withdraw(amount)) {
            to.deposit(amount);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Customer c1 = new Customer("Riya", "riya@gmail.com", "9876543210");

        System.out.println("Customer ID: " + c1.getCustomerId());
        System.out.println("Customer Name: " + c1.getName());

        Account[] accounts = new Account[3];

        accounts[0] = new Account("Riya", 5000);
        accounts[1] = new Account("Amit", 3000);
        accounts[2] = new Account("Priya");

        System.out.println("\nInitial Account Details:");

        for (int i = 0; i < accounts.length; i++) {
            System.out.println(
                accounts[i].getAccountNumber() + " | " +
                accounts[i].getOwnerName() + " | Balance: " +
                accounts[i].getBalance()
            );
        }

        Account a = accounts[0];

        a.deposit(2000);
        System.out.println("\nAfter deposit: " + a.getBalance());

        boolean result1 = a.withdraw(3000);
        System.out.println("Withdraw 3000 successful: " + result1);
        System.out.println("Balance: " + a.getBalance());

        boolean result2 = a.withdraw(10000);
        System.out.println("Withdraw 10000 successful: " + result2);
        System.out.println("Balance: " + a.getBalance());

        boolean result3 = transfer(accounts[1], accounts[2], 1000);
        System.out.println("\nTransfer successful: " + result3);

        System.out.println("\nFinal Account Balances:");

        for (int i = 0; i < accounts.length; i++) {
            System.out.println(
                accounts[i].getAccountNumber() + " | " +
                accounts[i].getOwnerName() + " | Balance: " +
                accounts[i].getBalance()
            );
        }
    }
}