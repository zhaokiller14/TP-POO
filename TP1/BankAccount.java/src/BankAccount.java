import java.util.Scanner;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private int balance;

    public BankAccount(String aN, String aHN, int b) {
        this.accountNumber = aN;
        this.accountHolderName = aHN;
        this.balance = b;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance :(");
        }
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber + "\nName: " + accountHolderName + "\nBalance: " + balance + "\n");
    }
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BankAccount BA = new BankAccount("1984", "Amine Jerbi", 1722);
    BA.display();

    System.out.println("Enter amount to deposit: ");
    int amount1 = sc.nextInt();
    BA.deposit(amount1);
    BA.display();

    System.out.println("Enter amount to withdraw: ");
    int amount2 = sc.nextInt();
    BA.withdraw(amount2);
    BA.display();
    }

}

