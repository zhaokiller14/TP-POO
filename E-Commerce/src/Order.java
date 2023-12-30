//Order.java

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L; // Added for versioning

    private List<Transaction> transactions;

    public Order() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Product product) {
        transactions.add(new Transaction(product, new Date()));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void saveTransactionsToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.err.println("Error saving transactions to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTransactionsFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Transaction> loadedTransactions = (List<Transaction>) ois.readObject();
            transactions.addAll(loadedTransactions);
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading transactions from file: " + e.getMessage());
        }
    }
    public void orderListDisplay(String Cu) {
        if (transactions.isEmpty()) {
            System.out.println("This Customer hasn't ordered anything yet");
        } else {
            System.out.println("Order history of customer "+Cu+": ");
            for (int i=0;i<transactions.size();i++) {
                Transaction T=transactions.get(i);
                Product P = T.getProduct();
                System.out.println(i+" - Product name: "+P.getName()+" | Quantity: "+P.getQuantity()+" | Total Price: "+P.getPrice()*P.getQuantity()+" | Time: "+T.getTransactionTime());
            }
        }
    }
    public String getFaveCategory() {
        String ch="none";
        int nb_Elc=0;
        int nb_Cl=0;
        int nb_Bk=0;
        if (!transactions.isEmpty()) {
            for (Transaction T : transactions) {
                if (T.getProduct() instanceof Electronics) {
                    nb_Elc++;
                } else if (T.getProduct() instanceof Clothing) {
                    nb_Cl++;
                } else {
                    nb_Bk++;
                }
            }
            if (nb_Elc > nb_Cl && nb_Elc > nb_Bk) {
                ch="Electronics";
            } else if (nb_Cl > nb_Elc && nb_Cl > nb_Bk){
                ch="Clothing";
            } else if (nb_Bk > nb_Cl && nb_Elc < nb_Bk){
                ch="Book";
            }
        }
        return ch;
    }
}
