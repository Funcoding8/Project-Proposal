
package com.example;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class CO2GUI {

    public static void show(List<EmissionRecord> records) {

        // Create a new window
        Stage stage = new Stage();

        // Title
        Label title = new Label("CO2 Emissions Comparison");
        title.setStyle("-fx-font-size: 25px;");

        // Country dropdown
        ComboBox<String> countries = new ComboBox<>();

        countries.getItems().addAll(
            "United States",
            "Mexico",
            "Canada",
            "China",
            "India"
        );

        countries.setValue("United States");

        // Create chart axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Year");
        yAxis.setLabel("CO2 (Million Tonnes)");

        // Create the bar chart
        BarChart<String, Number> chart =
            new BarChart<>(xAxis, yAxis);

        chart.setTitle("CO2 Emissions");
        chart.setLegendVisible(false);

        // Label to display emissions change
        Label result = new Label();

        // Update chart when country changes
        countries.setOnAction(event -> {
            updateChart(records, countries.getValue(),
                        chart, result);
        });

        // Display the first country
        updateChart(records, countries.getValue(),
                    chart, result);

        // Create the layout
        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(
            title,
            countries,
            chart,
            result
        );

        // Display the window
        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("CO2 Data Explorer");
        stage.setScene(scene);
        stage.show();
    }

    // Update the chart with the selected country
    public static void updateChart(
        List<EmissionRecord> records,
        String country,
        BarChart<String, Number> chart,
        Label result) {

        double co2Old = 0;
        double co2New = 0;

        boolean found2000 = false;
        boolean found2022 = false;

        // Find the emissions data
        for (EmissionRecord record : records) {

            if (record.getCountry().equals(country)) {

                if (record.getYear() == 2000) {
                    co2Old = record.getCo2();
                    found2000 = true;
                }

                if (record.getYear() == 2022) {
                    co2New = record.getCo2();
                    found2022 = true;
                }
            }
        }

        // Remove previous chart data
        chart.getData().clear();

        // Check for missing data
        if (!found2000 || !found2022) {
            result.setText("Missing data for " + country);
            return;
        }

        // Add new chart data
        XYChart.Series<String, Number> data =
            new XYChart.Series<>();

        data.getData().add(
            new XYChart.Data<>("2000", co2Old)
        );

        data.getData().add(
            new XYChart.Data<>("2022", co2New)
        );

        chart.getData().add(data);
        chart.setTitle(country + " CO2 Emissions");

        // Calculate the change
        double change = co2New - co2Old;

        result.setText(
            String.format(
                "Change in emissions: %.2f million tonnes",
                change
            )
        );
    }
}
