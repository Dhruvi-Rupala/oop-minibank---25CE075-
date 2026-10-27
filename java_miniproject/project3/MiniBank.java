public class MiniBank {
    public static void main(String[] args) {

        Account a1 = new Account("AC0001", "Riya", 4000);
        Account a2 = new Account("AC0001", "Riya", 4000);
        Account a3 = new Account("Amit", 3000);

        System.out.println("Account Details:");
        System.out.println(a1);
        System.out.println(a3);

        System.out.println("\nAccount Comparison:");
        System.out.println("a1 equals a2: " + a1.equals(a2));
        System.out.println("a1 equals a3: " + a1.equals(a3));

        System.out.println("\nHash Codes:");
        System.out.println("a1: " + a1.hashCode());
        System.out.println("a2: " + a2.hashCode());

        System.out.println("\nInstanceof Check:");
        Object obj = a1;
        System.out.println(obj instanceof Account);

        Customer.Address address =
                new Customer.Address("Main Road", "Rajkot", "360001");

        Customer c1 = new Customer(
                "Riya",
                "riya@gmail.com",
                "9876543210",
                address
        );

        System.out.println("\nCustomer Details:");
        System.out.println("Customer ID: " + c1.getCustomerId());
        System.out.println("Name: " + c1.getName());
        System.out.println("City: " + c1.getAddress().getCity());
        System.out.println("Pincode: " + c1.getAddress().getPincode());

        Customer c2 = c1.clone();

        System.out.println("\nCloning:");
        System.out.println("Original Name: " + c1.getName());
        System.out.println("Cloned Name: " + c2.getName());
        System.out.println("Same object: " + (c1 == c2));
        System.out.println("Same customer ID: "
                + c1.getCustomerId().equals(c2.getCustomerId()));
    }
}