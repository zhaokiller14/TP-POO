//PaymentContext.java

import java.util.Arrays;
import java.util.List;
class PaymentContext {
    private PaymentStrategy paymentStrategy;
    private List<String> validDiscountCodes = Arrays.asList("DISCOUNT5", "SALE10", "SPECIAL20");
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        paymentStrategy.processPayment(amount);
    }
    public void processPayment(double amount, String discountCode,List<Transaction> orderList) {
        // Verify and apply discount if valid code is provided
        double discountedAmount = verifyAndApplyDiscount(amount, discountCode,orderList);
        paymentStrategy.processPayment(discountedAmount);
    }
        public void processPaymentCheckout(double amount, String discountCode,int i,List<Transaction> orderList) {
        // Verify and apply discount if valid code is provided
        double discountedAmount = verifyAndApplyDiscountCheckout(amount, discountCode,i,orderList);
        paymentStrategy.processPayment(discountedAmount);
    }
    private double verifyAndApplyDiscount(double amount, String discountCode, List<Transaction> orderList) {
        // Check if the discount code is valid
        if (validDiscountCodes.contains(discountCode)) {
            // Apply a 10% discount if the code is valid
            System.out.println("Discount code applied: " + discountCode);
            Transaction T = orderList.get(orderList.size()-1);
            T.getProduct().updatePrice(T.getProduct().getPrice()*0.9);
            return amount * 0.9; // 10% discount
        } else {
            System.out.println("Invalid discount code: " + discountCode);
            return amount;
        }
    }
    private double verifyAndApplyDiscountCheckout(double amount, String discountCode,int i,List<Transaction> orderList) {
        // Check if the discount code is valid
        if (validDiscountCodes.contains(discountCode)) {
            // Apply a 10% discount if the code is valid
            System.out.println("Discount code applied: " + discountCode);
            int NumItems= i;
            for (int j=0;j<NumItems;j++) {
                Transaction T = orderList.get(orderList.size()-1-j);
                T.getProduct().updatePrice(T.getProduct().getPrice()*0.9);
            }
            return amount * 0.9; // 10% discount
        } else {
            System.out.println("Invalid discount code: " + discountCode);
            return amount;
        }
    }
}
