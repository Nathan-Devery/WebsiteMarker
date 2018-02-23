package model;

public class TestResult {

    private String testName;
    //private int result;
    private boolean result;   //TODO create numerical result system
    private String evidenceLog;

    public TestResult(String testName, boolean result, String evidenceLog) {
        this.testName = testName;
        this.result = result;
        this.evidenceLog = evidenceLog;
    }

    public String getTestName() {
        return testName;
    }

    public boolean getResult() {
        return result;
    }

    public String getEvidenceLog() {
        return evidenceLog;
    }
}
