
package com.example;

import java.util.List;

public class CO2Analyzer {

    public static void analyze(List<EmissionRecord> records) {

        // List of countries
        String[] countries = {
            "United States", "Mexico", "Canada", "China", "India"
        };

        // Arrays to store CO2 information
        double[] emissions2000 = new double[countries.length];
        double[] emissions2022 = new double[countries.length];
        double[] perCapita2022 = new double[countries.length];

        // Get data from EmissionRecord
        for (EmissionRecord record : records) {
            for (int i = 0; i < countries.length; i++) {

                if (record.getCountry().equals(countries[i])) {

                    if (record.getYear() == 2000) {
                        emissions2000[i] = record.getCo2();
                    }

                    if (record.getYear() == 2022) {
                        emissions2022[i] = record.getCo2();
                        perCapita2022[i] = record.getCo2PerCapita();
                    }
                }
            }
        }

        System.out.println("CO2 Emissions Analysis");
        System.out.println("======================");

        // Step 1: Show each country's numbers
        for (int i = 0; i < countries.length; i++) {

            double change = emissions2022[i] - emissions2000[i];
            double perPerson = perCapita2022[i];

            System.out.println();
            System.out.println(countries[i]);
            System.out.println("Emissions in 2000: " + emissions2000[i]);
            System.out.println("Emissions in 2022: " + emissions2022[i]);
            System.out.println("Change: " + change);
            System.out.println("Tonnes per person: " + perPerson);
        }

        // Step 2: Find the country with the most emissions
        int biggest = 0;

        for (int i = 1; i < countries.length; i++) {
            if (emissions2022[i] > emissions2022[biggest]) {
                biggest = i;
            }
        }

        // Step 3: Find the most emissions per person
        int highestPerPerson = 0;

        for (int i = 1; i < countries.length; i++) {
            if (perCapita2022[i] > perCapita2022[highestPerPerson]) {
                highestPerPerson = i;
            }
        }

        // Step 4: Add up all five countries
        double total = 0;

        for (int i = 0; i < countries.length; i++) {
            total = total + emissions2022[i];
        }

        // Display the results
        System.out.println();
        System.out.println("Results");
        System.out.println("-------");
        System.out.println("Most total CO2: " + countries[biggest]);
        System.out.println("Most CO2 per person: " + countries[highestPerPerson]);
        System.out.println("Total for all 5 countries: " + total + " million tonnes");
    }
}
