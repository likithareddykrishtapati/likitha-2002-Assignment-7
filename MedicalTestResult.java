import java.util.*;

class MedicalTestResult {
    private double result;

    public MedicalTestResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}

public class MedicalTestAnalyzer {
    public static void main(String[] args) {
        List<MedicalTestResult> testResults = new ArrayList<>();
        // Assuming testResults list is populated with MedicalTestResult objects

        // Populating the testResults list with example data
        testResults.add(new MedicalTestResult(120));
        testResults.add(new MedicalTestResult(85));
        testResults.add(new MedicalTestResult(100));
        testResults.add(new MedicalTestResult(140));
        testResults.add(new MedicalTestResult(95));
        testResults.add(new MedicalTestResult(160));
        testResults.add(new MedicalTestResult(110));
        testResults.add(new MedicalTestResult(75));
        testResults.add(new MedicalTestResult(130));

        // Define result ranges and corresponding categories
        Map<String, List<Double>> ranges = new HashMap<>();
        ranges.put("Normal", Arrays.asList(70.0, 130.0));
        ranges.put("Borderline", Arrays.asList(130.0, 150.0));
        ranges.put("High", Arrays.asList(150.0, Double.POSITIVE_INFINITY));

        // Initialize counters for each category
        Map<String, Integer> patientCount = new HashMap<>();
        Map<String, Double> totalResultSum = new HashMap<>();

        // Initialize counters and sums
        for (String category : ranges.keySet()) {
            patientCount.put(category, 0);
            totalResultSum.put(category, 0.0);
        }

        // Count patients and calculate total result sum for each category
        for (MedicalTestResult testResult : testResults) {
            double result = testResult.getResult();
            for (Map.Entry<String, List<Double>> entry : ranges.entrySet()) {
                String category = entry.getKey();
                List<Double> range = entry.getValue();
                if (result >= range.get(0) && result < range.get(1)) {
                    patientCount.put(category, patientCount.get(category) + 1);
                    totalResultSum.put(category, totalResultSum.get(category) + result);
                    break;
                }
            }
        }

        // Display results
        for (String category : ranges.keySet()) {
            int count = patientCount.get(category);
            double average = count == 0 ? 0 : totalResultSum.get(category) / count;
            System.out.println("%s: %d patients, Average Result: %.2f\n", category, count, average);
        }
    }
}
