public class MiniBank {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("Rahul", 10000, 2000),
            new CurrentAccount("Priya", 5000, 3000),
            new FixedDepositAccount("Riya", 20000)
        };

        System.out.println("===== MINIBANK ACCOUNT DETAILS =====");

        for (Account account : accounts) {
            System.out.println("\nAccount Number: " + account.getAccountNumber());
            System.out.println("Owner: " + account.getOwnerName());
            System.out.println("Balance: Rs. " + account.getBalance());
            System.out.println("Interest Rate: " + account.interestRate() + "%");
            System.out.println("Monthly Interest: Rs. " + account.monthlyInterest());

            if (account instanceof SavingsAccount savings) {
                System.out.println("Minimum Balance: Rs. 2000");
                System.out.println("Can withdraw Rs. 1000: "
                        + savings.canWithdraw(1000));
            }
        }

        System.out.println("\n===== WITHDRAWAL TEST =====");

        System.out.println("Savings: " + accounts[0].withdraw(1000));
        System.out.println("Current: " + accounts[1].withdraw(6000));
        System.out.println("Fixed Deposit: " + accounts[2].withdraw(1000));
    }
}