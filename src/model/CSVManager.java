package model;

import java.io.*;
import java.util.ArrayList;

public class CSVManager {

    ArrayList<String[]> csvArray = new ArrayList<>();

    public void loadCsv(File file){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\\t";

        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                csvArray.add(line.split(cvsSplitBy));
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
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
        System.out.println("hello");
    }

    public String[] getColumns(){
        if(csvArray.size() > 0){
            return csvArray.get(0);
        }
        return new String[0];
    }

    /*
    public void createCSV(int studentIdCol, int gradeCol, ){

    }
    */

    public void createCSV(){
        for(String[] row: csvArray){

        }
    }

}
