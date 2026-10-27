public class MiniBank {

    public static void main(String[] args) {

        System.out.println("===== VALIDATOR TESTS =====");

        System.out.println("Valid Mobile: "
                + Validator.isValidMobile("9876543210"));
        System.out.println("Invalid Mobile: "
                + Validator.isValidMobile("12345"));

        System.out.println("Valid Email: "
                + Validator.isValidEmail("abc@xyz.com"));
        System.out.println("Invalid Email: "
                + Validator.isValidEmail("abcxyz.com"));

        System.out.println("Valid PAN: "
                + Validator.isValidPan("ABCDE1234F"));
        System.out.println("Invalid PAN: "
                + Validator.isValidPan("ABC123"));

        System.out.println("Valid IFSC: "
                + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("Invalid IFSC: "
                + Validator.isValidIfsc("SBIN123"));

        System.out.println("Valid Amount: "
                + Validator.isValidAmount("500"));
        System.out.println("Invalid Amount: "
                + Validator.isValidAmount("-500"));

        System.out.println("\n===== COMMAND PARSER =====");

        try {
            Command command =
                    CommandParser.parse("DEPOSIT AC0001 500");

            System.out.println("Type: " + command.type());
            System.out.println("Account Number: "
                    + command.accountNumber());
            System.out.println("Amount: " + command.amount());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n===== INVALID COMMAND TEST =====");

        try {
            CommandParser.parse("DEPOSIT AC0001");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n===== ACCOUNT STATEMENT =====");

        Account account = new Account("Riya", 5000);

        account.deposit(2000);
        account.withdraw(1000);

        System.out.println(
                StatementFormatter.buildStatement(account));
    }
}