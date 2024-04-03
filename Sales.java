import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class SalesAnalyzer {
    public static void main(String[] args) {
        List<Product> sales = new ArrayList<>();
        // Assuming sales list is populated with Product objects

        // Populating the sales list with example data
        sales.add(new Product("Product1", 45.50));
        sales.add(new Product("Product2", 75.20));
        sales.add(new Product("Product3", 120.80));
        sales.add(new Product("Product4", 25.30));
        sales.add(new Product("Product5", 180.0));

        // Defining price ranges
        double[] priceRanges = {0, 50, 100, 200}; // Ranges: [0-50), [50-100), [100-200), [200+)

        // Initialize counters for each price range
        int[] productCount = new int[priceRanges.length];
        double[] revenue = new double[priceRanges.length];

        // Count products and calculate revenue for each price range
        for (Product product : sales) {
            double price = product.getPrice();
            for (int i = 0; i < priceRanges.length - 1; i++) {
                if (price >= priceRanges[i] && price < priceRanges[i + 1]) {
                    productCount[i]++;
                    revenue[i] += price;
                    break;
                }
            }
            // Handling products with price greater than the last range
            if (price >= priceRanges[priceRanges.length - 1]) {
                productCount[priceRanges.length - 1]++;
                revenue[priceRanges.length - 1] += price;
            }
        }

        // Displaying results
        for (int i = 0; i < priceRanges.length - 1; i++) {
            System.out.println("$%.2f - $%.2f: %d products, Total Revenue: $%.2f\n",
                    priceRanges[i], priceRanges[i + 1], productCount[i], revenue[i]);
        }
        // Displaying results for the last range
        System.out.println("$%.2f and above: %d products, Total Revenue: $%.2f\n",
                priceRanges[priceRanges.length - 1], productCount[priceRanges.length - 1],
                revenue[priceRanges.length - 1]);
    }
}
