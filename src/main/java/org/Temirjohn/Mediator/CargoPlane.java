package org.Temirjohn.Mediator;

public class CargoPlane extends Aircraft {
    public CargoPlane(String id, TowerMediator t, boolean landing) {
        super(id, t, landing);
    }
    @Override
    public void receive(String msg) {
        System.out.println("CargoPlane " + getId() + " hears: " + msg);
    }
}
