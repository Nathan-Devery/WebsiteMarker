package model;

/**
 * Data structure which encompasses the results of a single test.
 */
public class TestResult {

    private String testName;
    private double result;
    private String evidenceLog;

    public TestResult(String testName, double result, String evidenceLog) {
        this.testName = testName;
        this.result = result;
        this.evidenceLog = evidenceLog;
    }

    public String getTestName() {
        return testName;
    }

    public double getResult() {
        return result;
    }

    public String getEvidenceLog() {
        if(evidenceLog == "") return "No evidence";
        return evidenceLog;
    }
}
