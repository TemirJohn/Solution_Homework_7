package org.Temirjohn.Mediator;

public abstract class Aircraft {
    protected final String id;
    protected final int fuelLevel; // 0-100, low fuel < 20 triggers emergency

    public Aircraft(String id, int fuelLevel) {
        this.id = id;
        this.fuelLevel = fuelLevel;
    }

    public abstract void receive(String msg);

    public void send(String msg, TowerMediator mediator) {
        mediator.broadcast(msg, this);
    }

    public String getId() {
        return id;
    }

    public boolean isEmergency() {
        return fuelLevel < 20 || "MAYDAY".equals(getStatus());
    }

    public abstract String getStatus();
}
