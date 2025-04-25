package org.Temirjohn.Mediator;

public class CargoPlane extends Aircraft {
    private String status = "Normal";

    public CargoPlane(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    @Override
    public void receive(String msg) {
        System.out.println(id + " (Cargo): Received: " + msg);
        if (msg.contains("MAYDAY")) {
            status = "Holding";
        } else if (msg.contains("Cleared")) {
            status = "Cleared";
        }
    }

    @Override
    public String getStatus() {
        return status;
    }
}
