package org.Temirjohn.Mediator;

import java.util.*;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new ArrayDeque<>();
    private final Queue<Aircraft> takeoffQueue = new ArrayDeque<>();

    @Override
    public synchronized void broadcast(String msg, Aircraft sender) {
        TowerDashboard.log(sender.getId() + ": " + msg);
        TowerDashboard.updateQueues(queueToList(landingQueue), queueToList(takeoffQueue));
        for (Aircraft a : AircraftRegistry.getAll()) {
            if (a != sender) a.receive(msg);
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        if (a.isEmergency()) {
            landingQueue.remove(a);
            ((ArrayDeque<Aircraft>) landingQueue).addFirst(a);
            TowerDashboard.log(a.getId() + " has EMERGENCY – granted runway!");
            TowerDashboard.updateRunway(a.getId() + " (EMERGENCY)");
            return true;
        }

        if (a.isLanding()) {
            if (!landingQueue.contains(a)) landingQueue.offer(a);
            if (landingQueue.peek() == a) {
                landingQueue.poll();
                TowerDashboard.updateRunway(a.getId() + " (Landing)");
                broadcast("Cleared to land", a);
                return true;
            }
        } else {
            if (!takeoffQueue.contains(a)) takeoffQueue.offer(a);
            if (landingQueue.isEmpty() && takeoffQueue.peek() == a) {
                takeoffQueue.poll();
                TowerDashboard.updateRunway(a.getId() + " (Takeoff)");
                broadcast("Cleared for takeoff", a);
                return true;
            }
        }

        TowerDashboard.updateQueues(queueToList(landingQueue), queueToList(takeoffQueue));
        return false;
    }

    private List<String> queueToList(Queue<Aircraft> queue) {
        List<String> list = new ArrayList<>();
        for (Aircraft a : queue) list.add(a.getId());
        return list;
    }
}