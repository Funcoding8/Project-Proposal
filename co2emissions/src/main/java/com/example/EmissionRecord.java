package com.example;

public class EmissionRecord {

    // Variables to store CO2 information
    private String country;
    private int year;
    private double co2;
    private double co2PerCapita;

    // Constructor to initialize the record
    public EmissionRecord(String country, int year,
                          double co2, double co2PerCapita) {

        this.country = country;
        this.year = year;
        this.co2 = co2;
        this.co2PerCapita = co2PerCapita;
    }

    // Get country name
    public String getCountry() {
        return country;
    }

    // Get year
    public int getYear() {
        return year;
    }

    // Get total CO2 emissions
    public double getCo2() {
        return co2;
    }

    // Get CO2 emissions per person
    public double getCo2PerCapita() {
        return co2PerCapita;
    }

    // Display the information
    @Override
    public String toString() {
        return "Country: " + country +
               "\nYear: " + year +
               "\nCO2 Emissions: " + co2 + " million tonnes" +
               "\nCO2 Per Capita: " + co2PerCapita + " tonnes per person";
    }
}
