// Admin.java

import java.util.Scanner;

public class Admin extends User {
    public Admin() {
        super();
    }
    public Admin(String username, String password) {
        super(username, password);
    }
    public Book bookInput(Scanner scanner) {
        String userInput;
        System.out.println("Enter Product details:");

        System.out.print("Product ID: ");
        while (!scanner.hasNextInt()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter an int");
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Product Price: ");
        while (!scanner.hasNextDouble()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter a double");
        }
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Product Quantity: ");
        while (!scanner.hasNextInt()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter an int");
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Product Author: ");
        String author = scanner.nextLine();

        System.out.print("Product Genre: ");
        String genre = scanner.nextLine();
        
        return new Book(id, name, price, quantity, author, genre);
}
    public Clothing clothingInput(Scanner scanner) {
        String userInput;
        System.out.println("Enter Product details:");

        System.out.print("Product ID: ");
        while (!scanner.hasNextInt()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter an int");
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Product Price: ");
        while (!scanner.hasNextDouble()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter a double");
        }
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Product Quantity: ");
        while (!scanner.hasNextInt()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter an int");
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Product Size: ");
        String size = scanner.nextLine();

        System.out.print("Product Material: ");
        String material = scanner.nextLine();

        return new Clothing(id, name, price, quantity, size, material);
    }
    public Electronics electronicInput(Scanner scanner) {
        String userInput;
        System.out.println("Enter Product details:");

        System.out.print("Product ID: ");
        while (!scanner.hasNextInt()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter an int");
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Product Price: ");
        while (!scanner.hasNextDouble()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter a double");
        }
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Product Quantity: ");
        while (!scanner.hasNextInt()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter an int");
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Product Brand: ");
        String brandString = scanner.nextLine();

        System.out.print("Product Power Consumption: ");
        while (!scanner.hasNextDouble()) {
            userInput = scanner.next();
            System.out.println("Invalid input. Please enter a double");
        }
        double pS = scanner.nextDouble();
        scanner.nextLine();

        return new Electronics(id, name, price, quantity, brandString, pS);
    }
    public void adminInterface(Scanner myObj, Inventory mainInventory, AuthenticationManager authManager) {
        int choice;
        String userInput;
        do {
            System.out.println("");
            System.out.println("*******Admin Interface*******");
            System.out.println("1 - View Inventory");
            System.out.println("2 - Add  Product To Inventory");
            System.out.println("3 - Remove From Inventory");
            System.out.println("4 - Display a product's information");
            System.out.println("5 - Update Price of a product in inventory");
            System.out.println("6 - Update Quantity of a product in inventory");
            System.out.println("7 - Display list of customers");
            System.out.println("8 - Display list of admins");
            System.out.println("9 - Display a customer's order history");
            System.out.println("10 - Logout");
            System.out.println("*********************************");
            System.out.println("");
            System.out.println("Choose a feature by digit: ");
            while (!myObj.hasNextInt()) {
                userInput = myObj.next();
                System.out.println("Invalid input. Please enter an int");
            }
            choice = myObj.nextInt();
            myObj.nextLine();
            System.out.println("");
            switch (choice) {
                case 1:
                    mainInventory.listDisplay();
                    break;
                case 2:
                    System.out.println("Select product type:");
                    System.out.println("1. Electronics");
                    System.out.println("2. Book");
                    System.out.println("3. Clothing");
                    System.out.println("Type [1], [2] or [3]");
                    int x ;
                    do {
                        while (!myObj.hasNextInt()) {
                            userInput = myObj.next();
                            System.out.println("Invalid input. Please enter an int");
                        }
                        x = myObj.nextInt();
                        myObj.nextLine();
                    } while (x!=1 && x!=2 && x!=3);
                    switch(x) {
                        case 1:
                            Electronics E = electronicInput(myObj);
                            mainInventory.addProduct(E);
                            break;
                        case 2:
                            Book B = bookInput(myObj);
                            mainInventory.addProduct(B);
                            break;
                        case 3:
                            Clothing C = clothingInput(myObj);
                            mainInventory.addProduct(C);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int a = myObj.nextInt();
                    myObj.nextLine();
                    mainInventory.removeProduct(a);
                    break;
                case 4:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int n = myObj.nextInt();
                    myObj.nextLine();
                    mainInventory.displayProductDetails(n);
                    break;
                case 5:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int d = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Choose new price");
                    while (!myObj.hasNextDouble()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter a double");
                    }
                    double e = myObj.nextDouble();
                    myObj.nextLine();
                    mainInventory.updatePrice(d,e);
                    break;
                case 6:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int b = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Choose new quantity");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int c = myObj.nextInt();
                    myObj.nextLine();
                    mainInventory.updateQuantity(b, c);
                    break;
                case 7:
                    authManager.displayCustomers();
                    break;
                case 8:
                    authManager.displayAdmins();
                    break;
                case 9:
                    System.out.println("Enter customer username");
                    String Cu= myObj.nextLine();
                    if (authManager.UsernameExists(Cu)) {
                        Customer customer = authManager.getCustomer(Cu);
                        customer.orderHistory.orderListDisplay(Cu);
                    } else {
                        System.out.println("This username does not exist");
                    }
                    break;
                case 10:
                    System.out.println("Logging out and exiting admin interface.");
                    mainInventory.saveInventoryToFile(mainInventory.file);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while(choice!=10);
    }
}  

