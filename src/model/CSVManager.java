package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class CSVManager {

    ArrayList<String[]> csvArray = new ArrayList<>();
    ArrayList<String[]> outputCSV = new ArrayList<>();

    File filepath;

    public void loadCsv(File file) throws IllegalOperationException{
        this.emptyFields();

        filepath = file;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                csvArray.add(line.split(cvsSplitBy));
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

    public String[] getColumns(){
        if(csvArray.size() > 0){
            return csvArray.get(0);
        }
        return new String[0];
    }

    /*
    public void createCSV(int studentIdCol, int gradeCol, Map<String, Assignment> assignmentMap){
        for(int i = 1; i < csvArray.size(); i++){   //Starts from one as the first row in CVS is the column declarations
            String[] row = csvArray.get(i);
            Assignment assignment = assignmentMap.get(row[studentIdCol]);
            if(assignment != null) {
                row[gradeCol] = String.valueOf(assignment.getTotalPercentage());
                row[gradeCol] = String.valueOf(assignment.getTotalPercentage());
            }
        }
        outputCSV();
    }
    */

    public void createCSV(int usernameCol, int studentIdCol, int gradeCol,Map<String, Assignment> assignmentMap) throws IllegalOperationException{
        if(usernameCol == studentIdCol || usernameCol == gradeCol || studentIdCol == gradeCol)
            throw new IllegalOperationException("Chosen columns are not distinct");

        String[] columnNames = new String[3];
        columnNames[0] = csvArray.get(0)[usernameCol];
        columnNames[1] = csvArray.get(0)[studentIdCol];
        columnNames[2] = csvArray.get(0)[gradeCol];

        outputCSV.add(columnNames);

        for(int i = 1; i < csvArray.size(); i++){   //Starts from one as the first row in CVS is the column declarations
            String[] row = csvArray.get(i);
            Assignment assignment = assignmentMap.get(row[studentIdCol]);
            if(assignment != null) {
                //row[gradeCol] = String.valueOf(assignment.getTotalPercentage());
                //outputCSV.get(i)[gradeCol] = String.valueOf(assignment.getTotalPercentage());

                //Username studentID Grade
                String[] outputRow = new String[3];
                outputRow[0] = csvArray.get(i)[usernameCol];
                outputRow[1] = csvArray.get(i)[studentIdCol];
                outputRow[2] = "\""  + String.valueOf(assignment.getTotalPercentage() + "\"");
                outputCSV.add(outputRow);
            }
        }
        outputCSV();
    }

    private void outputCSV(){
        try {
            String path = filepath.getParentFile().getAbsolutePath() + "/" + "GRADED.csv";
            PrintWriter writer = new PrintWriter(path, "UTF-8");

            for(int i = 0; i < outputCSV.size(); i++){
                for(int j = 0; j < outputCSV.get(i).length; j++){
                    writer.print(outputCSV.get(i)[j]);
                    if(j < outputCSV.get(i).length - 1) writer.print(",");
                }
                writer.println("");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void emptyFields(){
        this.csvArray = new ArrayList<>();
        this.outputCSV = new ArrayList<>();
    }

}
