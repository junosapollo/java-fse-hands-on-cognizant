package com.example.adapter;

interface PaymentProcessor {
    void processPayment(double amount);
}

class StripeGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe Gateway.");
    }
}

class PayPalGateway {
    public void sendPayment(double amount) {
        System.out.println("Sending payment of $" + amount + " via PayPal Gateway.");
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        // Translate the call to the adaptee's specific method
        stripeGateway.makePayment(amount);
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        // Translate the call to the adaptee's specific method
        payPalGateway.sendPayment(amount);
    }
}

public class PaymentProcessorPattern {
    public static void main(String[] args) {
        // Using Stripe through the adapter
        StripeGateway stripeGateway = new StripeGateway();
        PaymentProcessor stripeProcessor = new StripeAdapter(stripeGateway);
        stripeProcessor.processPayment(150.75);

        // Using PayPal through the adapter
        PayPalGateway payPalGateway = new PayPalGateway();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPalGateway);
        payPalProcessor.processPayment(200.50);
    }
}
