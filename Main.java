package FinalProject;

import java.util.Scanner;

public class Main {

    static String input = "";
    static Scanner scan = new Scanner(System.in);

    public static void start() {
        System.out.println("\nWelcome to the BookStore App\n[Login] or [Exit]?");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("[login]")) {
            System.out.print("Username: ");
            String username = scan.nextLine();
            System.out.print("Password: ");
            String password = scan.nextLine();
            if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                Owner.getInstance().ownerStart();
            } else {
                Customer c = Customers.getInstance().validateCustomer(new Customer(username, password));
                if (c == null) {
                    System.out.println("error: username or password is incorrect");
                    start();
                } else {
                    c.customerStart();
                }
            }
        } else if (input.equalsIgnoreCase("[exit]")) {
            //do nothing, ie exit
        } else {
            System.out.println("invalid input in start()\n\n");
            start();
        }
    }

    public static void main(String[] args) {
        Books.getInstance().recall();
        Customers.getInstance().recall();
        start();
    }
}
