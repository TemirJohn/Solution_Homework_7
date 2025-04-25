package org.Temirjohn.Mediator;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationDriver extends Application {
    private final List<Aircraft> aircraft = new ArrayList<>();
    private ControlTower tower;
    private ScheduledExecutorService executor;

    @Override
    public void start(Stage primaryStage) {
        TowerDashboard dashboard = new TowerDashboard(primaryStage);
        tower = new ControlTower(dashboard);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int fuel = random.nextInt(100);
            String id = "AC" + (i + 1);
            Aircraft a;
            switch (random.nextInt(3)) {
                case 0:
                    a = new PassengerPlane(id, fuel);
                    break;
                case 1:
                    a = new CargoPlane(id, fuel);
                    break;
                default:
                    a = new Helicopter(id, fuel);
            }
            aircraft.add(a);
        }

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            Aircraft a = aircraft.get(random.nextInt(aircraft.size()));
            String request = random.nextBoolean() ? "Request landing" : "Request takeoff";
            if (random.nextInt(100) < 5) {
                a.send("MAYDAY", tower);
            } else {
                a.send(request, tower);
                tower.requestRunway(a);
            }
            tower.releaseRunway();
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}