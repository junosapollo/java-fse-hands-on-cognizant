package com.example.builder;

public class Computer {
    // required parameters
    private String CPU;
    private String RAM;

    // optional parameters
    private String storage;
    private String GPU;
    private boolean isBluetoothEnabled;
    private boolean isWiFiEnabled;

    // private constructor taking builder as parameter
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
        this.isWiFiEnabled = builder.isWiFiEnabled;
    }

    // getters
    public String getCPU() { return CPU; }
    public String getRAM() { return RAM; }
    public String getStorage() { return storage; }
    public String getGPU() { return GPU; }
    public boolean isBluetoothEnabled() { return isBluetoothEnabled; }
    public boolean isWiFiEnabled() { return isWiFiEnabled; }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + storage + 
               ", GPU=" + GPU + ", Bluetooth=" + isBluetoothEnabled + ", WiFi=" + isWiFiEnabled + "]";
    }

    // static nested builder class
    public static class Builder {
        // required parameters
        private String CPU;
        private String RAM;

        // optional parameters initialized to default values
        private String storage = "256GB SSD";
        private String GPU = "Integrated";
        private boolean isBluetoothEnabled = false;
        private boolean isWiFiEnabled = false;

        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Builder setWiFiEnabled(boolean isWiFiEnabled) {
            this.isWiFiEnabled = isWiFiEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
