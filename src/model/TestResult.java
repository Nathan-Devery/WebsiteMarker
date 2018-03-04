package model;

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
        return evidenceLog;
    }
}
