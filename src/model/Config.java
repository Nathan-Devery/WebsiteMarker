package model;

import java.util.ArrayList;
import java.util.List;

/***
 * Defines the selected tests and their percentages. An empty config would cause the default options in the
 * OptionsPane to not be overridden.
 */
public class Config {

    List<Testable> selectedTests;
    List<Double> selectedTestPercentages;

    public Config(List<Testable> selectedTests, List<Double> selectedTestPercentages) {
        this.selectedTests = selectedTests;
        this.selectedTestPercentages = selectedTestPercentages;
    }

    public List<Testable> getSelectedTests() {
        return selectedTests;
    }

    public List<Double> getSelectedTestPercentages() {
        return selectedTestPercentages;
    }
}
