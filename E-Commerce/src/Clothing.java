//Clothing.java

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Clothing extends Product {
    private String size;
    private String material;

    public Clothing(int id, String name, double price, int quantity, String size, String material) {
        super(id, name, price, quantity);
        this.size = size;
        this.material = material;
    }
    public void displayProductDetails() {
        System.out.println("Product Details: ");
        System.out.println("Id: "+getId());
        System.out.println("Name:"+getName());
        System.out.println("Price: "+getPrice());
        System.out.println("State: "+getState());
        System.out.println("Quantity: "+getQuantity());
        System.out.println("Size: "+size);
        System.out.println("Material: "+material);
    }
    public String getSize() {
        return size;
    }
    public String getMaterial() {
        return material;
    }
    public Clothing(Clothing originalProduct) {
        super(originalProduct.getId(), originalProduct.getName(), originalProduct.getPrice(), originalProduct.getQuantity());
        this.size = originalProduct.getSize();
        this.material = originalProduct.getMaterial();
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    // Write additional information specific to the Clothing class
    out.writeObject(this.getSize());
    out.writeObject(this.getMaterial());
}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Read additional information specific to the Clothing class
        this.size = (String) in.readObject();
        this.material = (String) in.readObject();
    }
}
