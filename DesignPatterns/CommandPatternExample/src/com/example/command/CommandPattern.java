package com.example.command;

interface Command {
    void execute();
}

class Light {
    private String room;
    
    public Light(String room) {
        this.room = room;
    }
    
    public void turnOn() {
        System.out.println(room + " Light is ON");
    }

    public void turnOff() {
        System.out.println(room + " Light is OFF");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command assigned to the button.");
        }
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        // receivers
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");

        // concrete commands
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        
        Command bedroomLightOn = new LightOnCommand(bedroomLight);
        Command bedroomLightOff = new LightOffCommand(bedroomLight);

        // invoker
        RemoteControl remote = new RemoteControl();

        System.out.println("Testing Living Room Light:");
        remote.setCommand(livingRoomLightOn);
        remote.pressButton();
        
        remote.setCommand(livingRoomLightOff);
        remote.pressButton();

        System.out.println("\nTesting Bedroom Light:");
        remote.setCommand(bedroomLightOn);
        remote.pressButton();
        
        remote.setCommand(bedroomLightOff);
        remote.pressButton();
    }
}
