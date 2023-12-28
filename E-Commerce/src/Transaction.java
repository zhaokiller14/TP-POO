//Transaction.java
import java.io.Serializable;
import java.util.Date;
class Transaction implements Serializable{
    private Product product;
    private Date transactionTime;

    public Transaction(Product product, Date transactionTime) {
        this.product = product;
        this.transactionTime = transactionTime;
    }

    public Product getProduct() {
        return product;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }
}