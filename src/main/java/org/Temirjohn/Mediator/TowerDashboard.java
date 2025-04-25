package org.Temirjohn.Mediator;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TowerDashboard {
    private final Label runwayStatusLabel;
    private final Label landingQueueLabel;
    private final Label takeoffQueueLabel;
    private final TextArea logArea;

    public TowerDashboard(Stage stage) {
        runwayStatusLabel = new Label("Runway: Free");
        landingQueueLabel = new Label("Landing Queue: 0");
        takeoffQueueLabel = new Label("Takeoff Queue: 0");
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);

        VBox layout = new VBox(10, runwayStatusLabel, landingQueueLabel, takeoffQueueLabel, new ScrollPane(logArea));
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Airport Tower Dashboard");
        stage.show();
    }

    public void updateRunwayStatus(String status) {
        Platform.runLater(() -> runwayStatusLabel.setText("Runway: " + status));
    }

    public void updateQueueLengths(int landingSize, int takeoffSize) {
        Platform.runLater(() -> {
            landingQueueLabel.setText("Landing Queue: " + landingSize);
            takeoffQueueLabel.setText("Takeoff Queue: " + takeoffSize);
        });
    }

    public void logMessage(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
