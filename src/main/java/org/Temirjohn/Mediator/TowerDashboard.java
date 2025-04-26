package org.Temirjohn.Mediator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class TowerDashboard extends Application {
    private static ListView<String> landingList;
    private static ListView<String> takeoffList;
    private static TextArea logArea;
    private static Label runwayStatus;
    private static boolean launched = false;

    public static void updateRunway(String status) {
        Platform.runLater(() -> {
            if (runwayStatus != null) {
                runwayStatus.setText("Runway: " + status);
            }
        });
    }

    public static void updateQueues(List<String> landing, List<String> takeoff) {
        Platform.runLater(() -> {
            if (landingList != null && takeoffList != null) {
                landingList.getItems().setAll(landing);
                takeoffList.getItems().setAll(takeoff);
            }
        });
    }

    public static void log(String msg) {
        Platform.runLater(() -> {
            if (logArea != null) {
                logArea.appendText(msg + "\n");
            }
        });
    }

    public static void launchDashboard() {
        if (!launched) {
            launched = true;
            new Thread(() -> Application.launch(TowerDashboard.class)).start();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        landingList = new ListView<>();
        takeoffList = new ListView<>();
        logArea = new TextArea();
        runwayStatus = new Label("Runway: Idle");

        VBox root = new VBox(10);
        HBox queues = new HBox(10);

        VBox landingBox = new VBox(new Label("Landing Queue"), landingList);
        VBox takeoffBox = new VBox(new Label("Takeoff Queue"), takeoffList);
        queues.getChildren().addAll(landingBox, takeoffBox);

        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefHeight(200);

        root.getChildren().addAll(runwayStatus, queues, new Label("Event Log:"), logArea);
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Airport Tower Dashboard");
        primaryStage.show();
    }
}