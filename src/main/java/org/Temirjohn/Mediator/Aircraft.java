package org.Temirjohn.Mediator;

public abstract class Aircraft {
    private final String id;
    private boolean emergency, landing;
    protected final TowerMediator tower;

    public Aircraft(String id, TowerMediator tower, boolean landing) {
        this.id = id; this.tower = tower; this.landing = landing;
        AircraftRegistry.register(this);
    }
    public String getId() { return id; }
    public boolean isEmergency() { return emergency; }
    public boolean isLanding() { return landing; }
    public void declareEmergency() {
        emergency = true;
        tower.requestRunway(this);
    }
    public void requestRunway() {
        tower.requestRunway(this);
    }
    public void send(String msg) { tower.broadcast(msg, this); }
    public abstract void receive(String msg);
}
