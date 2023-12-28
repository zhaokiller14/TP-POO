//PaypalPayment.java

class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        // Implement PayPal payment logic
        System.out.println("Processing PayPal payment. Amount: $" + amount);
    }
}