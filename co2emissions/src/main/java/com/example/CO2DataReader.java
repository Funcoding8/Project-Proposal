
package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CO2DataReader {

    public static List<EmissionRecord> readData(String fileName) {

        // Create a list to store the records
        List<EmissionRecord> records = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));

            // Read the column names
            String[] header = scanner.nextLine().split(",");

            int countryIndex = -1;
            int yearIndex = -1;
            int co2Index = -1;
            int perCapitaIndex = -1;

            // Find the correct columns
            for (int i = 0; i < header.length; i++) {

                if (header[i].equals("country")) {
                    countryIndex = i;
                }

                if (header[i].equals("year")) {
                    yearIndex = i;
                }

                if (header[i].equals("co2")) {
                    co2Index = i;
                }

                if (header[i].equals("co2_per_capita")) {
                    perCapitaIndex = i;
                }
            }

            // Check that the columns exist
            if (countryIndex == -1 || yearIndex == -1 ||
                co2Index == -1 || perCapitaIndex == -1) {

                System.out.println("Missing CSV columns.");
                scanner.close();
                return records;
            }

            // Read every line in the file
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] data = line.split(",", -1);

                try {
                    // Get information from the CSV
                    String country = data[countryIndex];
                    int year = Integer.parseInt(data[yearIndex]);
                    double co2 = Double.parseDouble(data[co2Index]);
                    double co2PerCapita =
                        Double.parseDouble(data[perCapitaIndex]);

                    // Create an EmissionRecord object
                    EmissionRecord record = new EmissionRecord(
                        country, year, co2, co2PerCapita
                    );

                    // Add the object to the list
                    records.add(record);

                } catch (NumberFormatException |
                         ArrayIndexOutOfBoundsException e) {
                    // Skip missing or invalid data
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found.");
        }

        // Return all the records
        return records;
    }
}
