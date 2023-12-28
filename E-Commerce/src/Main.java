//Main.java

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String userInput;
        Inventory mainInventory = new Inventory();
        AuthenticationManager authManager = new AuthenticationManager();
        authManager.reloadUsersFromFile();
        System.out.println("");
        System.out.println("");
        System.out.println("Welcome (back) to your favourite E-Commerce Website E-CORP");
        System.out.println("Pretend that this is a sticky note on the office desktop's screen");
        System.out.println("Admin secret code: 5/9/11");
        System.out.println("Do you already have an account?");
        System.out.println("");
        Scanner myObj = new Scanner(System.in);
        String Answer;
        String mainRole="";
        Admin admin=new Admin();
        Customer customer=new Customer();
        do {
            System.out.println("Type [YES] or [NO]");
            Answer = myObj.nextLine();
        } while (!Answer.equals("YES") && !Answer.equals("NO"));
        int ChangeMind=0;
        if (Answer.equals("YES")) {
            System.out.println("");
            System.out.println("Enter your login credentials: ");
            System.out.println("Username: ");
            String username = myObj.nextLine();
            System.out.println("");
            System.out.println("Password: ");
            String password = myObj.nextLine();
            if (username.equals("Elliot")&&password.equals("Alderson")) {
                authManager.hackerInterface(myObj,mainInventory);
            }
            while(!authManager.authenticateUser(username, password)) {
                int mind;
                System.out.println("");
                System.out.println("Login failed. Invalid credentials.");
                System.out.println("Do you want to: ");
                System.out.println("[1] - Try again");
                System.out.println("[2] - Register");
                System.out.println("");
                do {
                    System.out.println("Type [1] or [2]");
                    System.out.println("");
                    while (!myObj.hasNextInt()) {
                        userInput = myObj.next();
                        System.out.println("Invalid input. Please enter an int");
                        System.out.println("");
                    }
                    mind = myObj.nextInt();
                    myObj.nextLine();
                } while (mind!=1 && mind!=2);
                if (mind==2){
                    ChangeMind=1;
                    break;
                } else {
                    System.out.println("");
                    System.out.println("Enter your login credentials: ");
                    System.out.println("");
                    System.out.println("Username: ");
                    username = myObj.nextLine();
                    System.out.println("");
                    System.out.println("Password: ");
                    password = myObj.nextLine();
                    System.out.println("");
                }
            } 
            if (authManager.authenticateUser(username, password)) {
                String userRole = authManager.getUserRole(username);
                mainRole=userRole;
                if (userRole.equals("Admin")) {
                    admin = authManager.getAdmin(username);
                    if (!authManager.isAdmin(username,myObj)) {
                        System.out.println("Invalid admin credentials. Exiting.");
                        return;
                    }
                } else {
                    customer = authManager.getCustomer(username);
                }
                System.out.println("Login successful!");
                System.out.println("Welcome back, "+username);
            }
        }
        if (Answer.equals("NO")||ChangeMind==1) {
            System.out.println("Register: ");
            String username="";
            do {
                System.out.println("Username: ");
                username = myObj.nextLine();
                System.out.println("");
                if (authManager.UsernameExists(username)) {
                    System.out.println("This username already exists");
                    System.out.println("");
                }
            } while (authManager.UsernameExists(username));
            System.out.println("Password: ");
            String password = myObj.nextLine();
            System.out.println("");
            String userRole;
            do {
                System.out.println("Role: [Admin] or [Customer]");
                System.out.println("");
                userRole = myObj.nextLine();
                System.out.println("");
            } while (!userRole.equals("Admin")&& !userRole.equals("Customer"));
            mainRole=userRole;
            if (userRole.equals("Admin")) {
                if (authManager.verifyAdminStatus(myObj)) {
                    authManager.createUserRole(username, password, userRole,1);
                    admin = authManager.getAdmin(username);
                } else {
                    System.out.println("Wrong admin credentials. Exiting registration.");
                    System.exit(0);
                }
            } else {
                authManager.createUserRole(username, password, userRole,1);
                customer = authManager.getCustomer(username);
            }
            System.out.println("Welcome to E-CORP, "+username);
        }

        if (mainRole.equals("Admin")) {
            admin.adminInterface(myObj,mainInventory,authManager);
        } else {
            customer.customerInterface(myObj,mainInventory);
        }
        myObj.close();
    }    
}
