package model;

import model.htmlTests.TagSearchTest;
import model.htmlTests.TagType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Defines the selected tests and their percentages. An empty config would cause the default options in the
 * OptionsPane to not be overridden.
 */
public class Config {

    List<String> selectedTestsNames = new ArrayList<>();
    List<Double> selectedTestPercentages = new ArrayList<>();

    public Config(List<Testable> selectedTests, List<Double> selectedTestPercentages) {
        for(Testable test: selectedTests){
            this.selectedTestsNames.add(test.toString());
        }

        for(Double d: selectedTestPercentages){
            this.selectedTestPercentages.add(d);
        }

        //this.selectedTestsNames.add(TagType.UNORDEREDLIST.name());
        //this.selectedTestPercentages.add(10.0);
    }

    public List<String> getSelectedTestsNames() {
        return selectedTestsNames;
    }

    public List<Double> getSelectedTestPercentages() {
        return selectedTestPercentages;
    }

    public void loadConfig(File file) throws IllegalOperationException{
        clearFields();

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(cvsSplitBy);
                selectedTestsNames.add(lineParts[0]);
                selectedTestPercentages.add(Double.valueOf(lineParts[1]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearFields(){
        this.selectedTestPercentages.clear();
        this.selectedTestsNames.clear();
    }

}
