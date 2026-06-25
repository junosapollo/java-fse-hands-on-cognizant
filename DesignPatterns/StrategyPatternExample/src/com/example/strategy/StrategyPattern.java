package com.example.strategy;

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String name;
    private String cardNumber;

    public CreditCardPayment(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card (ending in " + cardNumber.substring(cardNumber.length() - 4) + ")");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal (account: " + email + ")");
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // We can inject the strategy via constructor or setter
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method first.");
            return;
        }
        paymentStrategy.pay(amount);
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // User decides to pay using Credit Card
        System.out.println("Checkout using Credit Card:");
        context.setPaymentStrategy(new CreditCardPayment("John Doe", "1234567890123456"));
        context.executePayment(250.00);

        // Later, user changes preference to PayPal
        System.out.println("\nCheckout using PayPal:");
        context.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        context.executePayment(55.50);
    }
}
