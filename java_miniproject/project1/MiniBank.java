import java.util.Scanner;

record BankInfo(String name, String branch) {
}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

public class MiniBank {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "Main Branch");

        System.out.println("================================");
        System.out.println("       WELCOME TO MINIBANK      ");
        System.out.println(bank);
        System.out.println("================================");

        boolean running = true;

        while (running) {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number from 1 to 5.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }

            MenuOption option = MenuOption.values()[choice - 1];

            String message = switch (option) {
                case OPEN_ACCOUNT ->
                    "Open Account - to be implemented in a later lab.";

                case DEPOSIT ->
                    "Deposit - to be implemented in a later lab.";

                case WITHDRAW ->
                    "Withdraw - to be implemented in a later lab.";

                case TRANSFER ->
                    "Transfer - to be implemented in a later lab.";

                case EXIT ->
                    "Thank you for using MiniBank. Goodbye!";
            };

            System.out.println(message);

            if (option == MenuOption.EXIT) {
                running = false;
            }
        }

        sc.close();
    }
}