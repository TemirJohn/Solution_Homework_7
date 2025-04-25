package org.Temirjohn.Mediator;

import java.util.ArrayDeque;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new ArrayDeque<>();
    private final Queue<Aircraft> takeoffQueue = new ArrayDeque<>();
    private Aircraft currentRunwayUser = null;
    private boolean emergencyInProgress = false;
    private final TowerDashboard dashboard;

    public ControlTower(TowerDashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void broadcast(String msg, Aircraft sender) {
        String log = "Tower broadcasts from " + sender.getId() + ": " + msg;
        System.out.println(log);
        dashboard.logMessage(log);
        if (msg.equals("MAYDAY")) {
            handleEmergencyRequest(sender);
        }
    }

    @Override
    public boolean requestRunway(Aircraft a) {
        if (emergencyInProgress && !a.isEmergency()) {
            a.receive("Hold: Emergency in progress");
            return false;
        }

        if (a.isEmergency()) {
            return handleEmergencyRequest(a);
        }

        if (a.getStatus().contains("Landing")) {
            landingQueue.add(a);
        } else {
            takeoffQueue.add(a);
        }

        updateDashboard();
        return processQueues();
    }

    private boolean handleEmergencyRequest(Aircraft a) {
        emergencyInProgress = true;
        broadcast("MAYDAY: Emergency landing for " + a.getId(), a);
        landingQueue.clear();
        takeoffQueue.clear();
        if (currentRunwayUser != null) {
            currentRunwayUser.receive("Clear runway: Emergency");
        }
        currentRunwayUser = a;
        a.receive("Cleared for emergency landing");
        emergencyInProgress = false;
        updateDashboard();
        return true;
    }

    private boolean processQueues() {
        if (currentRunwayUser != null) return false;

        if (!landingQueue.isEmpty()) {
            currentRunwayUser = landingQueue.poll();
            currentRunwayUser.receive("Cleared for landing");
            updateDashboard();
            return true;
        } else if (!takeoffQueue.isEmpty()) {
            currentRunwayUser = takeoffQueue.poll();
            currentRunwayUser.receive("Cleared for takeoff");
            updateDashboard();
            return true;
        }
        return false;
    }

    public void releaseRunway() {
        currentRunwayUser = null;
        updateDashboard();
        processQueues();
    }

    private void updateDashboard() {
        dashboard.updateRunwayStatus(currentRunwayUser != null ? currentRunwayUser.getId() : "Free");
        dashboard.updateQueueLengths(landingQueue.size(), takeoffQueue.size());
    }
}