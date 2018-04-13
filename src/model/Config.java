package model;

import model.htmlTests.TagSearchTest;
import model.htmlTests.TagType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        if(file == null) return;

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

        } catch (Exception e) {
            clearFields();
           throw new IllegalOperationException("Invalid config");
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

    public void createConfigFile(File path){
        try {
            /*
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");
            String dateFormatted = LocalDateTime.now().format(formatter);

            String file = path + "/config" +
                    dateFormatted + ".ini";
            */

            String file = path + "/config.ini";
            PrintWriter writer = new PrintWriter(file, "UTF-8");

            for(int i = 0; i < selectedTestsNames.size(); i++){
                writer.println(selectedTestsNames.get(i) + "," + selectedTestPercentages.get(i));
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearFields(){
        this.selectedTestPercentages.clear();
        this.selectedTestsNames.clear();
    }

}
