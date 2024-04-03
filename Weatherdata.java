import java.util.*;

class WeatherData {
    private double temperature;
    private double humidity;

    public WeatherData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

public class WeatherAnalyzer {
    public static void main(String[] args) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        // Assuming weather data list is populated with WeatherData objects

        // Populating the weather data list with example data
        weatherDataList.add(new WeatherData(15, 60));
        weatherDataList.add(new WeatherData(5, 70));
        weatherDataList.add(new WeatherData(25, 50));
        weatherDataList.add(new WeatherData(10, 65));
        weatherDataList.add(new WeatherData(8, 75));
        weatherDataList.add(new WeatherData(-5, 80));
        weatherDataList.add(new WeatherData(18, 55));

        // Defining temperature ranges
        int[] temperatureRanges = {-10, 0, 10, 20, 30}; // Ranges: [-∞, -10), [-10, 0), [0, 10), [10, 20), [20, 30), [30, ∞)

        // Initialize counters for each temperature range
        int[] daysCount = new int[temperatureRanges.length + 1]; // Adding one for temperatures above the last range
        double[] humiditySum = new double[temperatureRanges.length + 1]; // Adding one for temperatures above the last range

        // Count days and calculate humidity sum for each temperature range
        for (WeatherData weatherData : weatherDataList) {
            double temperature = weatherData.getTemperature();
            double humidity = weatherData.getHumidity();
            boolean found = false;
            for (int i = 0; i < temperatureRanges.length; i++) {
                if (temperature < temperatureRanges[i]) {
                    daysCount[i]++;
                    humiditySum[i] += humidity;
                    found = true;
                    break;
                }
            }
            if (!found) { // If temperature is above the last range
                daysCount[temperatureRanges.length]++;
                humiditySum[temperatureRanges.length] += humidity;
            }
        }

        // Displaying results
        for (int i = 0; i < temperatureRanges.length; i++) {
            System.out.println("%d°C - %d°C: %d days, Average Humidity: %.2f%%\n",
                    (i == 0 ? Integer.MIN_VALUE : temperatureRanges[i - 1]), temperatureRanges[i],
                    daysCount[i], daysCount[i] == 0 ? 0 : humiditySum[i] / daysCount[i]);
        }
        // Displaying results for temperatures above the last range
        System.out.println("%d°C and above: %d days, Average Humidity: %.2f%%\n",
                temperatureRanges[temperatureRanges.length - 1], daysCount[temperatureRanges.length],
                daysCount[temperatureRanges.length] == 0 ? 0 : humiditySum[temperatureRanges.length] / daysCount[temperatureRanges.length]);
    }
}
