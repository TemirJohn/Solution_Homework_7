package org.Temirjohn.Mediator;

public class Helicopter extends Aircraft {
    public Helicopter(String id, TowerMediator t, boolean landing) {
        super(id, t, landing);
    }
    @Override
    public void receive(String msg) {
        System.out.println("Helicopter " + getId() + " hears: " + msg);
    }
}
