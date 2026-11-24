package com.example;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // Main title
        Label title = new Label("CO2 Emissions Around the World");

        title.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        // Project description
        Label description = new Label(
            "Explore and analyze carbon dioxide emissions across countries."
        );

        description.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #B0BEC5;"
        );

        // Team members
        Label team = new Label("Created by Aidan Garcia and Kenan");

        team.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #4FC3F7;"
        );

        // Button
        Button exploreButton = new Button("Explore CO2 Data");

        exploreButton.setStyle(
            "-fx-background-color: #2196F3;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-padding: 12px 25px;" +
            "-fx-background-radius: 8px;"
        );



// Button action
exploreButton.setOnAction(event -> {

    // Read the CSV file
    List<EmissionRecord> records =
        CO2DataReader.readData(
            "src/main/resources/owid-co2-data.csv"
        );

    if (!records.isEmpty()) {

        // Run the analysis
        CO2Analyzer.analyze(records);

        // Open the new GUI
        CO2GUI.show(records);

    } else {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("No CO2 data found.");
        alert.showAndWait();
    }
});



        // Center layout
        VBox center = new VBox(25);

        center.setAlignment(Pos.CENTER);

        center.getChildren().addAll(
            title,
            description,
            exploreButton,
            team
        );

        center.setPadding(new Insets(30));

        // Main layout
        BorderPane root = new BorderPane();

        root.setCenter(center);

        root.setStyle(
            "-fx-background-color: #101820;"
        );

        // Window
        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("World CO2 Emissions Dashboard");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
