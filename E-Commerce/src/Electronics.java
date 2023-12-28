//Electronics.java

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Electronics extends Product {
    private String brand;
    private double powerConsumption;

    public Electronics(int id, String name, double price, int quantity, String brand, double powerConsumption) {
        super(id, name, price, quantity);
        this.brand = brand;
        this.powerConsumption = powerConsumption;
    }
    public void displayProductDetails() {
        System.out.println("Product Details: ");
        System.out.println("Id: "+getId());
        System.out.println("Name:"+getName());
        System.out.println("Price: "+getPrice());
        System.out.println("State: "+getState());
        System.out.println("Quantity: "+getQuantity());
        System.out.println("Brand: "+brand);
        System.out.println("Power Consumption:"+powerConsumption);
    }
    public String getBrand() {
        return brand;
    }
    public double getPowerConsumption() {
        return powerConsumption;
    }
    public Electronics(Electronics originalProduct) {
        super(originalProduct.getId(), originalProduct.getName(), originalProduct.getPrice(), originalProduct.getQuantity());
        this.brand = originalProduct.getBrand();
        this.powerConsumption = originalProduct.getPowerConsumption();
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        try {
        out.defaultWriteObject();
        out.writeObject(this.getBrand());
        out.writeDouble(this.getPowerConsumption());
        } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception as needed
        }
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        try {
            in.defaultReadObject();
            this.brand = (String) in.readObject();
            this.powerConsumption = in.readDouble();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
