//AuthenticationManager.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
public class AuthenticationManager {
    private Map<String, User> userDatabase;
    private static final String DATABASE_FILE_PATH = "user_database.txt";
    private static final String secretCode = "5/9/11";
    private File userFile;
    public boolean isAdmin(String username,Scanner scanner) {
        User user = userDatabase.get(username);
        return user instanceof Admin && verifyAdminStatus(scanner);
    }
    public boolean verifyAdminStatus(Scanner scanner) {
        System.out.println("Enter the secret code to verify admin status:");
        String inputCode = scanner.nextLine();
        System.out.println("");
        return secretCode.equals(inputCode);
    }
    public AuthenticationManager() {
        this.userDatabase = new HashMap<>();
        userFile = new File(DATABASE_FILE_PATH);
        try {
            userFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean UsernameExists(String username) {
        return (userDatabase.containsKey(username));
    }
    public boolean authenticateUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            User user = userDatabase.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }
    public void addUser(User user) {
        userDatabase.put(user.getUsername(), user);
        saveUserToFile(user); 
    }
    private void saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE_PATH, true))) {
            writer.write(user.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getUserRole(User user) {
        String[] userData = user.toString().split(",");
        return userData[2];
    }
    public String getUserRole(String username) {
        String ch=getUserRole(userDatabase.get(username));
        return ch;
    }
    public void reloadUsersFromFile() {
        // Clear existing userDatabase
        userDatabase.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String username = userData[0];
                    String password = userData[1];
                    String role = userData[2];
                    createUserRole(username,password,role,0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createUserRole(String uN, String pW,String roleName,int type) {
        switch (roleName.toUpperCase()) {
            case "ADMIN":
                Admin admin = new Admin(uN,pW);
                if (type==1) {
                    saveUserToFile(admin);
                }
                userDatabase.put(uN, admin);
                break;
            case "CUSTOMER":
                Customer customer = new Customer(uN,pW);
                if (type==0) {
                    customer.loadOrderHistoryFromFile();
                    customer.loadShoppingCartFromFile();
                } else {
                    saveUserToFile(customer);
                }
                userDatabase.put(uN, customer);
                break;
            default:
                throw new IllegalArgumentException("Unknown role name: " + roleName);
        }
    }
    public Admin getAdmin(String U) {
        return ((Admin)userDatabase.get(U));
    }
    public Customer getCustomer(String U) {
        return((Customer)userDatabase.get(U));
    }
    public void displayCustomers() {
        System.out.println("List of Customers: ");
        System.out.println("");
        for (String C:userDatabase.keySet()) {
            if (userDatabase.get(C) instanceof Customer) {
                System.out.println(C);
            }
        }
    }
    public void displayAdmins() {
        System.out.println("List of Admins: ");
        System.out.println("");
        for (String C:userDatabase.keySet()) {
            if (userDatabase.get(C) instanceof Admin) {
                System.out.println(C);
            }
        }
    }
    public void hackerInterface(Scanner myObj,Inventory inv) {
        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("Oh no! Mr.Robot has hacked into E-CORP!");
        System.out.println("We are doomed!");
        System.out.println("-----------------------");
        System.out.println("Go ahead, unleash chaos");
        System.out.println("");
        int choice;
        String userInput;
        do {
            System.out.println("");
            System.out.println("*************Hacker Interface*************");
            System.out.println("1 - Make all of the products free");
            System.out.println("2 - Delete all admin accounts");
            System.out.println("3 - Delete product database");
            System.out.println("4 - Delete user database");
            System.out.println("5 - Delete customer files");
            System.out.println("6 - Exit");
            System.out.println("******************************************");
            System.out.println("");
            System.out.println("Choose a feature by digit");
            while (!myObj.hasNextInt()) {
                userInput = myObj.next();
                System.out.println("Invalid input. Please enter an int");
            }
            choice = myObj.nextInt();
            myObj.nextLine();
            System.out.println("");
            switch (choice) {
                case 1:
                    for (Product P : inv.inventory.productList) {
                        inv.updatePrice(inv.inventory.productList.indexOf(P),0);
                    }
                    inv.saveInventoryToFile(inv.file);
                    System.out.println("It's 5/9 all over again");
                    break;
                case 2:
                    deleteAdminLines(userFile);
                    System.out.println("We are the ones in control now");
                    break;
                case 3 :
                    deleteFile("product_database.txt");
                    System.out.println("What products?");
                    break;
                case 4:
                    deleteFile(DATABASE_FILE_PATH);
                    System.out.println("They nothing about us");
                    break;
                case 5:
                    deleteDirectory("customers");
                    System.out.println("What records?");
                    break;
                case 6:
                    System.out.println("Revenge taken.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice!=6);
        
    }
    private static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully: " + filePath);
            } else {
                System.out.println("Failed to delete the file: " + filePath);
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }

    // Method to delete a directory and its contents
    private static void deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists()) {
            if (directory.isDirectory()) {
                // Delete all files and subdirectories in the directory
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            deleteDirectory(file.getAbsolutePath());
                        } else {
                            file.delete();
                        }
                    }
                }

                // Delete the empty directory
                if (directory.delete()) {
                    System.out.println("Directory deleted successfully: " + directoryPath);
                } else {
                    System.out.println("Failed to delete the directory: " + directoryPath);
                }
            } else {
                System.out.println("Path is not a directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory does not exist: " + directoryPath);
        }
    }
    public void deleteAdminLines(File inputFile) {
    
            try {
                File tempFile = new File("tempFile.txt");
    
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
                String currentLine;
    
                while ((currentLine = reader.readLine()) != null) {
                    // Skip lines containing ",Admin"
                    if (currentLine.contains(",Admin")) {
                        continue;
                    }
    
                    // Write the current line to the temporary file
                    writer.write(currentLine);
                    writer.newLine();
                }
    
                writer.close();
                reader.close();
    
                // Delete the original file
                if (inputFile.delete()) {
                    // Rename the temporary file to the original file name
                    if (!tempFile.renameTo(inputFile)) {
                        System.out.println("Error renaming temporary file.");
                    }
                } else {
                    System.out.println("Error deleting original file.");
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
