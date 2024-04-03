import java.util.*;

class EmployeeWorkHours {
    private int[] dailyHours;

    public EmployeeWorkHours(int[] dailyHours) {
        this.dailyHours = dailyHours;
    }

    public int[] getDailyHours() {
        return dailyHours;
    }
}

public class WorkHoursAnalyzer {
    public static void main(String[] args) {
        List<EmployeeWorkHours> workHoursList = new ArrayList<>();
        // Assuming workHoursList is populated with EmployeeWorkHours objects

        // Populating the workHoursList with example data
        workHoursList.add(new EmployeeWorkHours(new int[]{8, 8, 8, 8, 8})); // 40 hours
        workHoursList.add(new EmployeeWorkHours(new int[]{7, 8, 9, 7, 9})); // more than 40 hours
        workHoursList.add(new EmployeeWorkHours(new int[]{6, 6, 6, 6, 6})); // less than 40 hours
        workHoursList.add(new EmployeeWorkHours(new int[]{8, 8, 8, 8, 7})); // less than 40 hours
        workHoursList.add(new EmployeeWorkHours(new int[]{9, 9, 9, 9, 4})); // less than 40 hours

        // Initialize counters for each group
        int moreThan40Count = 0;
        int exactly40Count = 0;
        int lessThan40Count = 0;
        int[] moreThan40TotalHours = new int[5];
        int[] lessThan40TotalHours = new int[5];

        // Count employees and calculate total hours for each group
        for (EmployeeWorkHours employeeWorkHours : workHoursList) {
            int[] dailyHours = employeeWorkHours.getDailyHours();
            int totalHours = 0;
            for (int hour : dailyHours) {
                totalHours += hour;
            }
            if (totalHours > 40) {
                moreThan40Count++;
                for (int i = 0; i < 5; i++) {
                    moreThan40TotalHours[i] += dailyHours[i];
                }
            } else if (totalHours == 40) {
                exactly40Count++;
            } else {
                lessThan40Count++;
                for (int i = 0; i < 5; i++) {
                    lessThan40TotalHours[i] += dailyHours[i];
                }
            }
        }

        // Calculate average hours worked per day for each group
        double[] moreThan40AverageHours = calculateAverageHours(moreThan40TotalHours, moreThan40Count);
        double[] lessThan40AverageHours = calculateAverageHours(lessThan40TotalHours, lessThan40Count);

        // Display results
        System.out.println("More than 40 hours:");
        System.out.println("Number of employees: " + moreThan40Count);
        System.out.println("Average hours per day:");
        displayDailyAverageHours(moreThan40AverageHours);

        System.out.println("\nExactly 40 hours:");
        System.out.println("Number of employees: " + exactly40Count);

        System.out.println("\nLess than 40 hours:");
        System.out.println("Number of employees: " + lessThan40Count);
        System.out.println("Average hours per day:");
        displayDailyAverageHours(lessThan40AverageHours);
    }

    // Helper method to calculate average hours worked per day
    private static double[] calculateAverageHours(int[] totalHours, int totalCount) {
        double[] averageHours = new double[5];
        if (totalCount == 0) {
            return averageHours;
        }
        for (int i = 0; i < 5; i++) {
            averageHours[i] = (double) totalHours[i] / totalCount;
        }
        return averageHours;
    }

    // Helper method to display average hours per day
    private static void displayDailyAverageHours(double[] averageHours) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Day %d: %.2f hours\n", i + 1, averageHours[i]);
        }
    }
}
