package FinalProject;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
//import java.io.FileReader;
import java.util.Scanner;

public class Books {
    // Name of the associated file
    private String filename;
    private File f;
    private ArrayList<Book> books = new ArrayList<>();
    
    private static Books instance = null;
    
    public static Books getInstance() {
        if (instance == null) {
            instance = new Books("books.txt");
        }
        return instance;
    } 
    
    private Books(String n) {
        filename = n;
        f = new File(filename);
    }
    
    public int getBooksSize() {
        return books.size();
    }
    
    // Effects: Reads and prints the contents of the associated
    // file to the standard output.
    public void read() {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
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
    
    public void addBook(Book b) {
        books.add(b);
        this.save();
    }
    
    public void deleteBook(int i) {
        books.remove(i);
        this.save();
    }
    
    public Book buyBook(int i) {
        Book temp = books.remove(i);
        this.save();
        return temp;
    }
    
    public void save() {
        this.write(this.toString());
    }
    
    public void recall() {
        try {
            books.clear();
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
                String[] arr = s.nextLine().split("\t");
                books.add(new Book(arr[0], Double.parseDouble(arr[1])));
            }
            s.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        String s = "";
        for (Book b : books) s += b.toString();
        return s;
    }
    
    public static void main (String[] args) {
        Books.getInstance().recall();
//        Books.getInstance().addBook(new Book ("The 40 year old virgin", 11.99));
  //      Books.getInstance().read();
    //    Books.getInstance().deleteBook(0);
    //    Books.getInstance().read();
      //  Books.getInstance().addBook(new Book ("The Giver", 19.99));
        //Books.getInstance().addBook(new Book ("iBoy", 13.99));
        Books.getInstance().read();
    }
}