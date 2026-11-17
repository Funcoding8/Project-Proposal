// CO2Analyzer.java
// The program looks at CO2 emissions for 5 countries.

public class CO2Analyzer {

    public static void main(String[] args) {

        // The list of countries
        String[] countries = {"United States", "Mexico", "Canada", "China", "India"};

        // CO2 emissions in 2000 and 2022 (million tonnes)
        // Each number matches the country in the same spot above.
        double[] emissions2000 = {0, 0, 0, 0, 0};
        double[] emissions2022 = {0, 0, 0, 0, 0};

        // Population in 2022 (millions of people)
        double[] population2022 = {0, 0, 0, 0, 0};

        System.out.println("CO2 Emissions Analysis");
        System.out.println("======================");

        // Step 1: Show each country's numbers
        for (int i = 0; i < countries.length; i++) {
            double change = emissions2022[i] - emissions2000[i];
            double perPerson = emissions2022[i] / population2022[i];

            System.out.println();
            System.out.println(countries[i]);
            System.out.println("  Emissions in 2000: " + emissions2000[i] + " million tonnes");
            System.out.println("  Emissions in 2022: " + emissions2022[i] + " million tonnes");
            System.out.println("  Change: " + change + " million tonnes");
            System.out.println("  Tonnes per person (2022): " + perPerson);
        }

        // Step 2: Find the country with the most total emissions
        int biggest = 0;
        for (int i = 1; i < countries.length; i++) {
            if (emissions2022[i] > emissions2022[biggest]) {
                biggest = i;
            }
        }

        // Step 3: Find the country with the most emissions per person
        int highestPerPerson = 0;
        for (int i = 1; i < countries.length; i++) {
            double current = emissions2022[i] / population2022[i];
            double best = emissions2022[highestPerPerson] / population2022[highestPerPerson];
            if (current > best) {
                highestPerPerson = i;
            }
        }

        // Step 4: Add up all five countries
        double total = 0;
        for (int i = 0; i < countries.length; i++) {
            total = total + emissions2022[i];
        }

        System.out.println();
        System.out.println("Results");
        System.out.println("-------");
        System.out.println("Most total CO2: " + countries[biggest]);
        System.out.println("Most CO2 per person: " + countries[highestPerPerson]);
        System.out.println("Total for all 5 countries: " + total + " million tonnes");
    }
}
