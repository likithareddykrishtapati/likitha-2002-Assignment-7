import java.util.*;

class House {
    private double price;
    private double squareFootage;

    public House(double price, double squareFootage) {
        this.price = price;
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public double getSquareFootage() {
        return squareFootage;
    }
}

public class HousingAnalyzer {
    public static void main(String[] args) {
        List<House> houses = new ArrayList<>();
        // Assuming houses list is populated with House objects

        // Populating the houses list with example data
        houses.add(new House(150000, 1200));
        houses.add(new House(180000, 1500));
        houses.add(new House(220000, 1700));
        houses.add(new House(250000, 1800));
        houses.add(new House(300000, 2000));
        houses.add(new House(350000, 2100));
        houses.add(new House(400000, 2200));

        // Defining price ranges
        double[] priceRanges = {100000, 200000, 300000, 400000, Double.POSITIVE_INFINITY}; // Ranges: [100000-200000), [200000-300000), [300000-400000), [400000+)

        // Initialize counters for each price range
        int[] houseCount = new int[priceRanges.length];
        double[] squareFootageSum = new double[priceRanges.length];

        // Count houses and calculate square footage sum for each price range
        for (House house : houses) {
            double price = house.getPrice();
            double squareFootage = house.getSquareFootage();
            for (int i = 0; i < priceRanges.length - 1; i++) {
                if (price >= priceRanges[i] && price < priceRanges[i + 1]) {
                    houseCount[i]++;
                    squareFootageSum[i] += squareFootage;
                    break;
                }
            }
            // Handling houses with price greater than the last range
            if (price >= priceRanges[priceRanges.length - 1]) {
                houseCount[priceRanges.length - 1]++;
                squareFootageSum[priceRanges.length - 1] += squareFootage;
            }
        }

        // Calculate average square footage for each price range and display results
        for (int i = 0; i < priceRanges.length - 1; i++) {
            double averageSquareFootage = houseCount[i] == 0 ? 0 : squareFootageSum[i] / houseCount[i];
            System.out.println("$%.2f - $%.2f: %d houses, Average Square Footage: %.2f sq.ft.\n",
                    priceRanges[i], priceRanges[i + 1], houseCount[i], averageSquareFootage);
        }
        // Displaying results for the last range
        double averageSquareFootage = houseCount[priceRanges.length - 1] == 0 ? 0 :
                squareFootageSum[priceRanges.length - 1] / houseCount[priceRanges.length - 1];
        System.out.println("$%.2f and above: %d houses, Average Square Footage: %.2f sq.ft.\n",
                priceRanges[priceRanges.length - 1], houseCount[priceRanges.length - 1], averageSquareFootage);
    }
}
