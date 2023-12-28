//ProductList.java

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ProductList implements Serializable{
    private static final long serialVersionUID = 1L;
    List<Product> productList;
    
    Map<Integer, Integer> productIdToIndexMap;

    public ProductList() {
        productList  = new ArrayList<>();
        productIdToIndexMap = new HashMap<>();
    }

    public void buildProductIdToIndexMap() {
        productIdToIndexMap = new HashMap<>();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            productIdToIndexMap.put(product.getId(), i);
        }
    }

    public Product getProductById(int productId) {
        Integer index = productIdToIndexMap.get(productId);
        if (index != null) {
            return productList.get(index);
        }
        return null; // Product with the given ID not found
    }
    public void addProduct(Product P) {
        productList.add(P);
        updateProductIdToIndexMap(P);
    }
    public void removeProduct(Product P) {
        productList.remove(P);
        updateProductIdToIndexMapAfterRemoval(P);
    }
    public boolean idSearch(int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return true;
            }
        }
        return false;
    }
    public void loadProductsFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Product> loadedProducts = (List<Product>) ois.readObject();
            productList.addAll(loadedProducts);
        } catch (FileNotFoundException e) {
            System.out.println("No existing database file found. Starting with an empty inventory.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        buildProductIdToIndexMap();
    }

    public void saveProductsToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(productList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateProductIdToIndexMap(Product addedProduct) {
        productIdToIndexMap.put(addedProduct.getId(), productList.size() - 1);
    }

    private void updateProductIdToIndexMapAfterRemoval(Product removedProduct) {
        Integer removedIndex = productIdToIndexMap.remove(removedProduct.getId());
        updateIndicesAfterRemoval(removedIndex);
    }

    private void updateIndicesAfterRemoval(int removedIndex) {
        for (Map.Entry<Integer, Integer> entry : productIdToIndexMap.entrySet()) {
            int productId = entry.getKey();
            int currentIndex = entry.getValue();

            if (currentIndex > removedIndex) {
                productIdToIndexMap.put(productId, currentIndex - 1);
            }
        }
    }
}
