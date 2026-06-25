package com.example.observer;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String stockName, double price);
}

interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double stockPrice;

    public StockMarket(String stockName, double stockPrice) {
        this.observers = new ArrayList<>();
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void deregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }
}

class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("Mobile App [" + appName + "] Alert: " + stockName + " is now $" + price);
    }
}

class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("Web Dashboard [" + appName + "] Alert: " + stockName + " is now $" + price);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        StockMarket appleStock = new StockMarket("AAPL", 150.00);

        Observer mobileApp1 = new MobileApp("Robinhood");
        Observer webApp1 = new WebApp("Yahoo Finance");

        // Registering observers
        appleStock.register(mobileApp1);
        appleStock.register(webApp1);

        System.out.println("Updating Apple stock price to $155.00");
        appleStock.setStockPrice(155.00);

        System.out.println("\nDeregistering Web App...");
        appleStock.deregister(webApp1);

        System.out.println("Updating Apple stock price to $160.50");
        appleStock.setStockPrice(160.50);
    }
}
