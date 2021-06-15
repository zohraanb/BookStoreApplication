package FinalProject;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
//import java.io.FileReader;
import java.util.Scanner;

public class Customers {

    //Name of the associated file
    private String filename;
    private File f;
    private ArrayList<Customer> customers = new ArrayList<>();

    private static Customers instance = null;

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers("customers.txt");
        }
        return instance;
    }

    private Customers(String n) {
        filename = n;
        f = new File(filename);
    }

    public int getCustomersSize() {
        return customers.size();
    }

    // Effects: Reads and prints the contents of the associated
    // file to the standard output.
    public void read() {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Effects: Appends the specified message, msg, to the
    // associated file.
    public void write(String msg) {
        try { // Write the code here
            FileWriter a = new FileWriter(filename);
            a.write(msg);
            a.close();
            //System.out.println("wrote to file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer c) {
        customers.add(c);
        this.save();
    }

    public void deleteCustomer(Customer c) {
        customers.remove(c);
        this.save();
    }

    public void deleteCustomer(int i) {
        customers.remove(i);
        this.save();
    }

    public void save() {
        this.write(this.toString());
    }

    public void recall() {
        try {
            customers.clear();
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] arr = s.nextLine().split("\t");
                customers.add(new Customer(arr[0], arr[1], Double.parseDouble(arr[2])));
            }
            s.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Customer validateCustomer(Customer c) {
        for (Customer cust : customers) {
            if (cust.equals(c) && cust.getPassword().equals(c.getPassword())) {
                return cust;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (Customer c : customers) {
            s += c.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        Customers.getInstance().recall();
        //      Customers.getInstance().addCustomer(new Customer ("The 40 year old virgin", "11.99"));
        //      Books.getInstance().read();
        //    Books.getInstance().deleteBook(0);
        //    Books.getInstance().read();
        //  Books.getInstance().addBook(new Book ("The Giver", 19.99));
        //Books.getInstance().addBook(new Book ("iBoy", 13.99));
        Customers.getInstance().read();
    }
}
