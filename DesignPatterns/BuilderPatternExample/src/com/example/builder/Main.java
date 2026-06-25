package com.example.builder;

public class Main {
    public static void main(String[] args) {
        // building a basic computer
        Computer basicComputer = new Computer.Builder("Intel i3", "8GB").build();
        
        // building a gaming computer with optional parameters
        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9", "32GB")
                .setStorage("2TB NVMe SSD")
                .setGPU("NVIDIA RTX 4090")
                .setWiFiEnabled(true)
                .setBluetoothEnabled(true)
                .build();
        
        // building an office computer
        Computer officeComputer = new Computer.Builder("Intel i5", "16GB")
                .setStorage("512GB SSD")
                .setWiFiEnabled(true)
                .build();

        System.out.println("Basic Computer Configuration:");
        System.out.println(basicComputer);
        
        System.out.println("\nGaming Computer Configuration:");
        System.out.println(gamingComputer);
        
        System.out.println("\nOffice Computer Configuration:");
        System.out.println(officeComputer);
    }
}
