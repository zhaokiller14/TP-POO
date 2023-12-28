//Product.java

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
abstract class Product implements Serializable {
    private int Id;
    private String Name;
    private double Price;
    private StockState State;
    private int Quantity;

    public Product(int id, String name, double price, int quantity) {
        Id = id;
        Name = name;
        Price = price;
        Quantity = quantity;
        updateStockState();
    }
    public double getPrice() {
        return Price;
    }
    public int getQuantity() {
        return Quantity;
    }
    public StockState getState() {
        return State;
    }
    public int getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }
    public void updateStockState() {
        if (Quantity > 10) {
            State = StockState.IN_STOCK;
        } else if (Quantity > 0 && Quantity <= 10) {
            State = StockState.LOW_STOCK;
        } else {
            State = StockState.OUT_OF_STOCK;
        }
    }
    abstract void displayProductDetails();
    public void updateQuantity(int Q) {
        Quantity=Q;
    }
    public void updatePrice(double P) {
        Price=P;
    }
    public void updateName(String S) {
        Name=S;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    // Write additional information specific to the Product subclass
    // (e.g., brand, powerConsumption for Electronics)
    out.writeObject(this.getName());
    out.writeDouble(this.getPrice());
    out.writeInt(this.getQuantity());
}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Read additional information specific to the Product subclass
        // (e.g., brand, powerConsumption for Electronics)
        this.updateName((String) in.readObject());
        this.updatePrice(in.readDouble());
        this.updateQuantity(in.readInt());
    }
}
