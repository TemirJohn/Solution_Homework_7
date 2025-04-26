package org.Temirjohn.Mediator;

public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id, TowerMediator t, boolean landing) {
        super(id, t, landing);
    }
    @Override
    public void receive(String msg) {
        System.out.println("PassengerPlane " + getId() + " hears: " + msg);
    }
}