//Inventory.java

import java.io.*;
import java.util.Scanner;
public class Inventory  {
    public ProductList inventory;
    private static final String DATABASE_FILE_PATH = "product_database.txt";
    File file;
    public Inventory() {
        this.inventory = new ProductList();
    
        file = new File(DATABASE_FILE_PATH);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Failed to create the product database file.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
    
        loadInventoryFromFile(file);
        inventory.buildProductIdToIndexMap();
    }

    public void addProduct(Product P) {
        inventory.addProduct(P);
    }

    public void removeProduct(int i) {
        if (inventory.productList.isEmpty()) {
            System.out.println("Inventory is empty");
        } else if (i<inventory.productList.size()) {
            inventory.removeProduct(inventory.productList.get(i));
        } else {
            System.out.println("Inventory only has "+inventory.productList.size()+" products");
        }
    }

    public void updatePrice(int i,double Pr) {
        if (inventory.productList.isEmpty()) {
            System.out.println("Inventory is empty");
        } else if (i<inventory.productList.size()) {
            if (Pr>=0) {
                inventory.productList.get(i).updatePrice(Pr);
            } else {
                System.out.println("Invalid price input");
            }
        } else {
            System.out.println("Inventory only has "+inventory.productList.size()+" items");
        }
    }

    public void updateQuantity(int i, int Q) {
        if (inventory.productList.isEmpty()) {
            System.out.println("Inventory is empty");
        } else if (i<inventory.productList.size()) {
            if (Q>0){
                inventory.productList.get(i).updateQuantity(Q);
                inventory.productList.get(i).updateStockState();
            } else {
                System.out.println("Invalid quantity input");
            }
        } else {
            System.out.println("Inventory only has "+inventory.productList.size()+" items");
        }
    }

    public void listDisplay() {
        if (inventory.productList.isEmpty()) {
            System.out.println("Inventory is empty");
        } else {
            System.out.println("List of products in inventory: ");
            for (int i=0;i<inventory.productList.size();i++) {
                Product P=inventory.productList.get(i);
                System.out.println(i+" - Name: "+P.getName()+" | Price: "+P.getPrice()+" | State: "+P.getState());
                if (P.getState()==StockState.LOW_STOCK) {
                    System.out.println("         Quantity: "+P.getQuantity());
                }
                
            }
        }
    }

    public void search(Scanner scanner) {
        String userInput;
        // Get search criteria from the user
        System.out.println("Enter search criteria:");
        System.out.print("Name (enter 'any' for any): ");
        String name = scanner.nextLine().trim();

        System.out.print("Minimum Price (enter 'any' for any): ");
        double minPrice;
        try {
            while (!scanner.hasNextDouble() && !scanner.next().equals("any")) {
                userInput = scanner.next();
                System.out.println("Invalid input. Please enter a double");
            }
            minPrice = Double.parseDouble(scanner.nextLine().trim());
            scanner.nextLine();
            /* scanner.nextLine(); */
        } catch (NumberFormatException e) {
            minPrice = Double.NEGATIVE_INFINITY;
        }

        System.out.print("Maximum Price (enter 'any' for any): ");
        double maxPrice;
        try {
            while (!scanner.hasNextDouble() && !scanner.next().equals("any")) {
                userInput = scanner.next();
                System.out.println("Invalid input. Please enter a double");
            }
            maxPrice = Double.parseDouble(scanner.nextLine().trim());
            scanner.nextLine();
            /* scanner.nextLine(); */
        } catch (NumberFormatException e) {
            maxPrice = Double.POSITIVE_INFINITY;
        }
        
        System.out.print("State [IN_STOCK,LOW_STOCK_OUT_OF_STOCK] (case insensitive) (enter 'any' for any): ");
        String state = scanner.nextLine().trim();

        System.out.print("Category [Electronics,Book,Clothing] (case insensitive) (enter 'any' for any): ");
        String category = scanner.nextLine().trim();

        // Perform the search
        ProductList searchResults = new ProductList();
        for (Product product : inventory.productList) {
            boolean nameMatch = "any".equalsIgnoreCase(name) || product.getName().equalsIgnoreCase(name);
            boolean priceMatch = minPrice <= product.getPrice() && product.getPrice() <= maxPrice;
            boolean categoryMatch = isCategoryMatch(product, category);
            boolean stateMatch = isStateMatch(product, state);

            if (nameMatch && priceMatch && categoryMatch && stateMatch) {
                searchResults.addProduct(product);
            }
        }

        // Display the search results
        System.out.println("Search Results:");
        if (searchResults.productList.isEmpty()) {
            System.out.println("No matching products found.");
        } else {
            for (int i = 0; i < searchResults.productList.size(); i++) {
                System.out.println(i + " - " + searchResults.productList.get(i).getName());
            }
        }
    }

    private boolean isCategoryMatch(Product product, String category) {
        // Check if the product's class name matches the specified category
        return "any".equalsIgnoreCase(category) || product.getClass().getSimpleName().equalsIgnoreCase(category);
    }

    private boolean isStateMatch(Product product, String state) {
        // Check if the product's state matches the specified state
        try {
            StockState stockState = StockState.valueOf(state.toUpperCase());
            return product.getState() == stockState;
        } catch (IllegalArgumentException e) {
            return "any".equalsIgnoreCase(state);
        }
    }

    public void loadInventoryFromFile(File file) {
        if (inventory!=null) {
            inventory.loadProductsFromFile(file.getPath());
        }
    }

    public void saveInventoryToFile(File file) {
        inventory.saveProductsToFile(file.getPath());
    }
    public void displayProductDetails(int i) {
        if (inventory.productList.isEmpty()) {
            System.out.println("Inventory is empty");
        } else if (i<inventory.productList.size()) {
            inventory.productList.get(i).displayProductDetails();
        } else {
            System.out.println("Inventory only has "+inventory.productList.size()+" items");
        }
    }
}
