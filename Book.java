package FinalProject;

import java.util.Objects;

public class Book {

    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof Book)) {
            return false;
        }
        Book b = (Book) ob;
        return this.name.equals(b.name);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        return (this.name + "\t" + this.price + "\n");
    }
}
