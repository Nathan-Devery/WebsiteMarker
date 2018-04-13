package model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Manages csv.
 * Allows input of existing csv (output from blackboard), this containing the columns in which to append grades to.
 * The csv must contain the pupil's details for appending.
 */
public class CSVManager {

    Model model;
    ArrayList<String[]> csvArray = new ArrayList<>();
    ArrayList<String[]> outputCSV = new ArrayList<>();
    File filepath;

    private ArrayList<Malformed> unpairables = new ArrayList<>();

    public CSVManager(Model model) {
        this.model = model;
    }

    public void loadCsv(File file) throws IllegalOperationException{
        if(file == null) return;
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

        } catch (Exception e) {
            emptyFields();
            throw new IllegalOperationException("Invalid CSV");
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

    /***
     * Creates CSV holding the grades of the assignments, and generate a report for what users who were not found.
     * @param usernameCol
     * @param studentIdCol
     * @param gradeCol
     * @param assignmentMap
     * @throws IllegalOperationException
     */
    public void createCSV(int usernameCol, int studentIdCol, int gradeCol, Map<String, Assignment> assignmentMap) throws IllegalOperationException{
        if(usernameCol == studentIdCol || usernameCol == gradeCol || studentIdCol == gradeCol)
            throw new IllegalOperationException("Chosen columns are not distinct");

        addColumnsToCSV(usernameCol, studentIdCol, gradeCol);

        Map<String, Assignment> assignmentMapClone = new HashMap<>(assignmentMap);
        for(int i = 1; i < csvArray.size(); i++){   //Starts from one as the first row in CVS is the column declarations
            String[] row = csvArray.get(i);
            String rowStudentID = row[studentIdCol];
            Assignment assignment = assignmentMapClone.get(rowStudentID);
            if(assignment != null) {
                //Username, studentID, Grade
                String[] outputRow = new String[3];
                outputRow[0] = csvArray.get(i)[usernameCol];
                outputRow[1] = csvArray.get(i)[studentIdCol];
                outputRow[2] = "\""  + String.valueOf(assignment.getTotalPercentage() + "\"");
                outputCSV.add(outputRow);
                assignmentMapClone.remove(rowStudentID); //Remove assignment, so only the non-found assignments are left.
            }
        }
        addUnpairables(assignmentMapClone);
        outputCSV();
        outputCsvReport();
    }

    public ArrayList<Malformed> getUnpairables() {
        return unpairables;
    }

    private void addColumnsToCSV(int usernameCol, int studentIdCol, int gradeCol){
        String[] columnNames = new String[3];
        columnNames[0] = csvArray.get(0)[usernameCol];
        columnNames[1] = csvArray.get(0)[studentIdCol];
        columnNames[2] = csvArray.get(0)[gradeCol];

        outputCSV.add(columnNames);
    }

    private void addUnpairables(Map<String, Assignment> assignmentMap){
        ArrayList<Malformed> unpairables = new ArrayList<>();
        for(Assignment assignment: assignmentMap.values()){
            unpairables.add(new Malformed(assignment.getNameID(),
                    "StudentID not in CSV"));
        }
        this.unpairables = unpairables;
    }

    private void outputCSV(){
        try {
            String fileNameNoExt = filepath.getName().replaceFirst("[.][^.]+$", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm");
            String dateFormatted = LocalDateTime.now().format(formatter);

            String path = filepath.getParentFile().getAbsolutePath() + "/" + fileNameNoExt + "_Graded_" +
                    dateFormatted + ".csv";
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

    private void outputCsvReport(){
        String fileNameNoExt = filepath.getName().replaceFirst("[.][^.]+$", "");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm");
        String dateFormatted = LocalDateTime.now().format(formatter);

        String path = filepath.getParentFile().getAbsolutePath() + "/" + fileNameNoExt + "_CSV_NotFoundReport_" +
                dateFormatted + ".txt";
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.print("No matching student found in template CSV for following assignments:\n\n");

            ArrayList<Malformed> unpairables = model.getUnpairables();
            for(Malformed unpairable: unpairables){
                writer.println(unpairable.getAssignmentName() + "\t\t" + unpairable.getReason());
            }
            writer.close();
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void emptyFields(){
        this.csvArray = new ArrayList<>();
        this.outputCSV = new ArrayList<>();
        this.filepath = null;
        this.unpairables = new ArrayList<>();
    }

}
