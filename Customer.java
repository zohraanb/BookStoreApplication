package FinalProject;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;

public class Customer {

    private String username, password;
    private double points;
    private State state;    
    private String input;
    private Scanner scan = new Scanner(System.in);

    public Customer(String username, String password, double points) {
        this.username = username;
        this.password = password;
        this.points = points;
        if (this.points < 1000) {
            state = new Silver();
        } else {
            state = new Gold();
        }
    }
    
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
    }
    
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof Customer)) {
            return false;
        }
        Customer c = (Customer) ob;
        return (this.username.equals(c.username));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public String toString() {
        return (this.username + "\t" + this.password + "\t" + this.points + "\n");
    }
    
    public void makeGold() {
        state.makeGold(this);
    }
    
    public void makeSilver() {
        state.makeSilver(this);
    }
    
    public void setState(State s) {
        state = s;
    }
    
    public State getStatus() {
        if (points < 1000) {
            makeSilver();
        }
        else {
            makeGold();
        }
        return state;
    }
    
    public void customerStart() {
        System.out.println("\nWlecome " + username + ". You have " + points + " points. Your status is " + state.toString());
        System.out.println("Book Name\tBook Price\tSelect");
        System.out.println(Books.getInstance().toString());
        System.out.println("[Buy]\n[Redeem Points and Buy]\n[Logout]");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("[buy]")) {
            System.out.println("please select the positions of all the books you would like to buy");
            input = scan.nextLine();
            Scanner s = new Scanner(input);
            ArrayList<Integer> arr = new ArrayList<>();
            while (s.hasNext()) {
                if (s.hasNextInt()) {
                    int temp = s.nextInt();
                    if (arr.contains(temp)) {
                        System.out.println("invalid input in customerStart().[Buy]: you can't have duplicate positions\n\n");
                        customerStart();
                        return;
                    } else {
                        if (temp > 0 && temp <= Books.getInstance().getBooksSize()) {
                            arr.add(temp);
                        } else {
                            System.out.println("invalid input in customerStart().[Buy]: positions must be between 1 and " + Books.getInstance().getBooksSize() + "\n\n");
                        }
                    }
                } else {
                    System.out.println("invalid input: expecting integers\n\n");
                    customerStart();
                    return;
                }
            }
            int counter = 1;
            double price = 0;
            for (int i = 0; i < arr.size(); i++) {
                price += Books.getInstance().buyBook(arr.get(i) - counter++).getPrice();
            }
            points += price * 10;
            customerCost(price);
        } else if (input.equalsIgnoreCase("[redeem points and buy]")) {
            System.out.println("please select the positions of all the books you would like to buy");
            input = scan.nextLine();
            Scanner s = new Scanner(input);
            ArrayList<Integer> arr = new ArrayList<>();
            while (s.hasNext()) {
                if (s.hasNextInt()) {
                    int temp = s.nextInt();
                    if (arr.contains(temp)) {
                        System.out.println("invalid input in customerStart().[Redeem Points and Buy]: you can't have duplicate positions\n\n");
                        customerStart();
                        return;
                    } else {
                        if (temp > 0 && temp <= Books.getInstance().getBooksSize()) {
                            arr.add(temp);
                        } else {
                            System.out.println("invalid input in customerStart().[Redeem Points and Buy]: positions must be between 1 and " + Books.getInstance().getBooksSize() + "\n\n");
                        }
                    }
                } else {
                    System.out.println("invalid input: expecting integers\n\n");
                    customerStart();
                    return;
                }
            }
            int counter = 1;
            double price = 0;
            for (int i = 0; i < arr.size(); i++) {
                price += Books.getInstance().buyBook(arr.get(i) - counter++).getPrice();
            }
            if (price >= points * 0.01) {
                price -= points * 0.01;
                points = price * 10;
            } else {
                points -= price * 100;
                price = 0;
            }
            customerCost(price);
        } else if (input.equalsIgnoreCase("[logout]")){
            Main.start();
        } else {
            System.out.println("invalid input in customerStart()\n\n");
        }
    }
    
    public void customerCost(double price) {
        System.out.println("\nTotal Cost: " + price);
        System.out.println("Points: " + this.points);
        System.out.println("Status: " + getStatus().toString());
        System.out.println("[Logout]");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("[logout]")) {
            Main.start();
        } else {
            System.out.println("invalid input in customerBuy()");
            customerCost(price);
        }
    }
}
