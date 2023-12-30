//Customer.java
import java.io.File;
import java.util.Scanner;
public class Customer extends User {
    private ShoppingCart shoppingCart;
    public Order orderHistory;
    private File shoppingCartFile;
    private File orderHistoryFile;
    public Customer() {
        super();
    }
    public Customer(String username, String password) {
        super(username, password);
        shoppingCart = new ShoppingCart();
        orderHistory = new Order();
        createCustomerDirectory();
        orderHistoryFile = new File(getCustomerDirectory(), "order_history.txt");
        shoppingCartFile = new File(getCustomerDirectory(), "shopping_cart.txt");
    }

    public void customerInterface(Scanner myObj, Inventory mainInventory) {
        int choice;
        String userInput;
        System.out.println("Discount codes are : DISCOUNT5 - SALE10 - SPECIAL20");
        do {
            System.out.println("");
            System.out.println("*******Customer Interface*******");
            System.out.println("1 - View Inventory");
            System.out.println("2 - Add To Cart");
            System.out.println("3 - Remove From Cart");
            System.out.println("4 - Update Quantity of a Product In Cart");
            System.out.println("5 - Display a product's information");
            System.out.println("6 - View Cart");
            System.out.println("7 - Make an order");
            System.out.println("8 - Checkout");
            System.out.println("9 - Search");
            System.out.println("10 - Display your order history");
            System.out.println("11 - Logout");
            System.out.println("*********************************");
            System.out.println("");
            System.out.println("Choose a feature by digit: ");
            choice=myObj.nextInt();
            myObj.nextLine();
            System.out.println("");
            switch (choice) {
                case 1:
                    mainInventory.listDisplay(orderHistory);
                    break;
                case 2:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int i = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Choose desired quantity");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int q = myObj.nextInt();
                    myObj.nextLine();
                    shoppingCart.addToWishlist(i, q,mainInventory.inventory);
                    break;
                case 3:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int a = myObj.nextInt();
                    myObj.nextLine();
                    shoppingCart.removeFromWishlist(a);
                    break;
                case 4:
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
                    shoppingCart.updateQuantity(b, c);
                    break;
                case 5:
                    System.out.println("Choose product number");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int n = myObj.nextInt();
                    myObj.nextLine();
                    mainInventory.displayProductDetails(n);
                    break;
                case 6:
                    shoppingCart.listDisplay(mainInventory.inventory);
                    break;
                case 7:
                    System.out.println("Enter product number in cart to order:");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                    }
                    int pn = myObj.nextInt();
                    myObj.nextLine();
                    double v = shoppingCart.order(pn,mainInventory.inventory,orderHistory,myObj);
                    if (v>0) {
                        processPayment(v,myObj);
                        orderHistory.saveTransactionsToFile(orderHistoryFile.getAbsolutePath());
                    } else {
                        System.out.println("Invalid order");
                    }
                    
                    break;
                case 8:
                    double total =0;
                    if (shoppingCart.Wishlist.productList.isEmpty()) {
                        System.out.println("Shopping cart is empty. Add products before checking out.");
                    } else {
                        int NumItems=shoppingCart.Wishlist.productList.size();
                        for (int h=0;h< NumItems;h++) {
                            total+=shoppingCart.order(0,mainInventory.inventory,orderHistory,myObj);
                        }
                        if (total>0) {
                            processPaymentCheckout(total,myObj,NumItems);
                            orderHistory.saveTransactionsToFile(orderHistoryFile.getAbsolutePath());
                        } else {
                            System.out.println("Invalid order");
                        }
                    }
                    break;
                case 9:
                    mainInventory.search(myObj);
                    break;
                case 10:
                    orderHistory.orderListDisplay(getUsername());
                    break;
                case 11:
                    System.out.println("Logging out and exiting customer interface.");
                    saveShoppingCartToFile();
                    mainInventory.saveInventoryToFile(mainInventory.file);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while(choice!=11);
    }

    private void processPayment(double amount,Scanner scanner) {
        // Get payment method from the user
        System.out.println("Choose payment method:");
        System.out.println("1 - Credit Card");
        System.out.println("2 - PayPal");
        String userInput;
        int paymentMethod;
        do {
            System.out.println("Choose [1] or [2]");
            while (!scanner.hasNextInt()) {
                userInput = scanner.next();
                System.out.println("Invalid input. Please enter an int");
            }
            paymentMethod = scanner.nextInt();
            scanner.nextLine();
        } while (paymentMethod!=1 && paymentMethod!=2);
        // Set the payment strategy based on the chosen method
        PaymentStrategy paymentStrategy;
        switch (paymentMethod) {
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new PayPalPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }
        
        PaymentContext paymentContext = new PaymentContext();
        paymentContext.setPaymentStrategy(paymentStrategy);

        // Discount code
        System.out.println("Do you have a discount code?");
        String response="";
        do {
            System.out.println("[YES] or [NO]");
            response = scanner.nextLine();
        } while(!response.equals("YES") && !response.equals("NO"));

        // Perform the payment
        if (response.equals("YES")) {
            System.out.println("Enter your discount code");
            String code = scanner.nextLine();
            paymentContext.processPayment(amount,code,orderHistory.getTransactions());
        } else {
            paymentContext.processPayment(amount);
        }

        System.out.println("Payment successful. Thank you for your purchase!");
    }
    public void loadShoppingCartFromFile() {
        shoppingCart.Wishlist.loadProductsFromFile(shoppingCartFile.getAbsolutePath());
    }

    public void saveShoppingCartToFile() {
        shoppingCart.Wishlist.saveProductsToFile(shoppingCartFile.getAbsolutePath());
    }
    public void loadOrderHistoryFromFile() {
        orderHistory.loadTransactionsFromFile(orderHistoryFile.getAbsolutePath());
    }
    private void processPaymentCheckout(double amount,Scanner scanner,int itemCount) {
        // Get payment method from the user
        System.out.println("Choose payment method:");
        System.out.println("1 - Credit Card");
        System.out.println("2 - PayPal");
        String userInput;
        int paymentMethod;
        do {
            System.out.println("Choose [1] or [2]");
            while (!scanner.hasNextInt()) {
                userInput = scanner.next();
                System.out.println("Invalid input. Please enter an int");
            }
            paymentMethod = scanner.nextInt();
            scanner.nextLine();
        } while (paymentMethod!=1 && paymentMethod!=2);
        // Set the payment strategy based on the chosen method
        PaymentStrategy paymentStrategy;
        switch (paymentMethod) {
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new PayPalPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }
        
        PaymentContext paymentContext = new PaymentContext();
        paymentContext.setPaymentStrategy(paymentStrategy);

        // Discount code
        System.out.println("Do you have a discount code?");
        String response="";
        do {
            System.out.println("[YES] or [NO]");
            response = scanner.nextLine();
        } while(!response.equals("YES") && !response.equals("NO"));

        // Perform the payment
        if (response.equals("YES")) {
            System.out.println("Enter your discount code");
            String code = scanner.nextLine();
            paymentContext.processPaymentCheckout(amount,code,itemCount,orderHistory.getTransactions());
        } else {
            paymentContext.processPayment(amount);
        }

        System.out.println("Payment successful. Thank you for your purchase!");
    }
/*     public void createOrderHistoryFile() {
        String filePath = getUsername() + "_order_history.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
    private void createCustomerDirectory() {
        File customerDirectory = getCustomerDirectory();
        if (!customerDirectory.exists()) {
            customerDirectory.mkdirs();
        }
    }

    private File getCustomerDirectory() {
        return new File("customers", getUsername());
    }
}


