package com.example.decorator;

interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

// concrete decorators
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSlackMessage(message);
    }

    private void sendSlackMessage(String message) {
        System.out.println("Sending Slack Message: " + message);
    }
}

public class NotifierPattern {
    public static void main(String[] args) {
        // base notifier email only
        Notifier emailNotifier = new EmailNotifier();
        
        System.out.println("Testing base email notifier:");
        emailNotifier.send("Hello World!");
        
        System.out.println("\nTesting email + SMS notifier:");
        Notifier smsAndEmailNotifier = new SMSNotifierDecorator(emailNotifier);
        smsAndEmailNotifier.send("Important System Update!");
        
        System.out.println("\nTesting email + SMS + Slack notifier:");
        Notifier fullNotifier = new SlackNotifierDecorator(smsAndEmailNotifier);
        fullNotifier.send("Critical Server Failure!");
    }
}
