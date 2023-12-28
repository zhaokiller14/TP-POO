//CreditCardPayment.java

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        // Implement credit card payment logic
        System.out.println("Processing credit card payment. Amount: $" + amount);
    }
}
