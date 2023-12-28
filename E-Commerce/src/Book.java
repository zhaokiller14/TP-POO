//Book.java

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Book extends Product {
    private String author;
    private String genre;

    public Book(int id, String name, double price, int quantity, String author, String genre) {
        super(id, name, price, quantity);
        this.author = author;
        this.genre = genre;
    }
    public void displayProductDetails() {
        System.out.println("Product Details: ");
        System.out.println("Id: "+getId());
        System.out.println("Name:"+getName());
        System.out.println("Price: "+getPrice());
        System.out.println("State: "+getState());
        System.out.println("Quantity: "+getQuantity());
        System.out.println("Author: "+author);
        System.out.println("Genre: "+genre);
    }

    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public Book(Book originalProduct) {
        super(originalProduct.getId(), originalProduct.getName(), originalProduct.getPrice(), originalProduct.getQuantity());
        this.author = originalProduct.getAuthor();
        this.genre = originalProduct.getGenre();
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    // Write additional information specific to the Book class
    out.writeObject(this.getAuthor());
    out.writeObject(this.getGenre());
}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Read additional information specific to the Book class
        this.author = (String) in.readObject();
        this.genre = (String) in.readObject();
    }
}

