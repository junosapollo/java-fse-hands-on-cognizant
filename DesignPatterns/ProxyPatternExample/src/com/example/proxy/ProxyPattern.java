package com.example.proxy;

interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer(filename);
    }

    private void loadFromRemoteServer(String filename) {
        System.out.println("Loading image from remote server: " + filename);
        try {
            // Simulating network delay
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        // Lazy initialization: create RealImage only when needed
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("high_res_photo1.jpg");
        Image image2 = new ProxyImage("high_res_photo2.jpg");

        // The real image is loaded from the remote server when display() is called for the first time
        System.out.println("First call to display image1:");
        image1.display();
        
        System.out.println("\nSecond call to display image1 (should be cached):");
        // The real image is already cached, so it's not loaded from the remote server again
        image1.display();

        System.out.println("\nFirst call to display image2:");
        image2.display();
    }
}
