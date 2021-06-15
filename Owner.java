package FinalProject;

import java.util.Scanner;

public class Owner {

    private String input;
    private Scanner scan = new Scanner(System.in);
    private static Owner instance = null;

    public static Owner getInstance() {
        if (instance == null) {
            instance = new Owner();
        }
        return instance;
    }

    private Owner() {
    }

    public void ownerStart() {
        System.out.println("\n[Books]\n[Customers]\n[Logout]");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("[books]")) {
            ownerBooks();
        } else if (input.equalsIgnoreCase("[customers]")) {
            ownerCustomers();
        } else if (input.equalsIgnoreCase("[logout]")) {
            Main.start();
        } else {
            System.out.println("invalid input in ownerStart()\n\n");
            ownerStart();
        }
    }

    public void ownerBooks() {
        System.out.println("\nBook Name\tBook Price");
        System.out.println(Books.getInstance().toString());
        System.out.println("[Add]\n[Delete]\n[Back]");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("[add]")) {
            System.out.print("Book name: ");
            String name = scan.nextLine();
            System.out.print("Book price: ");
            if (scan.hasNextDouble()) {
                double price = scan.nextDouble();
                scan.nextLine(); //buffer
                Books.getInstance().addBook(new Book(name, price));
                ownerBooks();
            } else {
                System.out.println("invalid input in ownerBooks().[add]: expecting a double\n\n");
                ownerBooks();
            }
        } else if (input.equalsIgnoreCase("[delete]")) {
            System.out.print("Please enter the position of the book you would like to delete: ");
            if (scan.hasNextInt()) {
                int pos = scan.nextInt();
                scan.nextLine(); //buffer
                if (pos > 0 && pos <= Books.getInstance().getBooksSize()) {
                    Books.getInstance().deleteBook(pos - 1);
                    ownerBooks();
                } else {
                    System.out.println("invalid input in ownerBooks().[delete]: position must be between 1 and "
                            + Books.getInstance().getBooksSize() + "\n\n");
                    ownerBooks();
                }
            } else {
                System.out.println("invalid input in ownerBooks().[delete]: expecting an int\n\n");
                ownerBooks();
            }
        } else if (input.equalsIgnoreCase("[back]")) {
            ownerStart();
        } else {
            System.out.println("invalid input in ownerBooks()\n\n");
            ownerBooks();
        }
    }

    public void ownerCustomers() {
        System.out.println("\nUsername\tPassword\tPoints");
        System.out.println(Customers.getInstance().toString());
        System.out.println("[Add]\n[Delete]\n[Back]");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("[add]")) {
            System.out.print("Customer username: ");
            String username = scan.nextLine();
            if (Customers.getInstance().validateCustomer(new Customer(username, "")) == null) {
                System.out.print("Customer password: ");
                String password = scan.nextLine();
                Customers.getInstance().addCustomer(new Customer(username, password));
                ownerCustomers();
            } else {
                System.out.println("This username is already taken");
                ownerCustomers();
            }
        } else if (input.equalsIgnoreCase("[delete]")) {
            System.out.println("Please enter the position of the customer you would like to delete");
            if (scan.hasNextInt()) {
                int pos = scan.nextInt();
                scan.nextLine(); //buffer
                if (pos > 0 && pos <= Customers.getInstance().getCustomersSize()) {
                    Customers.getInstance().deleteCustomer(pos - 1);
                    ownerCustomers();
                } else {
                    System.out.println("invalid input in ownerCustomers().[delete]: position must be between 1 and "
                            + Customers.getInstance().getCustomersSize() + "\n\n");
                    ownerCustomers();
                }
            } else {
                System.out.println("invalid input in ownerCustomers().[delete]: expecting an int");
                ownerCustomers();
            }
        } else if (input.equalsIgnoreCase("[back]")) {
            ownerStart();
        } else {
            System.out.println("invalid input in ownerCustomers()");
            ownerCustomers();
        }
    }
}
