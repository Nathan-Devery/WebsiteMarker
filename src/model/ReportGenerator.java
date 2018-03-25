package model;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class ReportGenerator {

    public static void createReports(Model model){
        String parentPath = model.getFilePath();
        String pathName = parentPath + createDirectoryName();

        createDirectory(pathName);
        try{
            model.getConfig().createConfigFile(new File(pathName));
        }catch(Exception e){
            e.printStackTrace();
        }

        String attentionReport = createAttentionReport(model.getUnmarkables(), model.getFilePath());
        saveToFile(attentionReport, new File(pathName + "/UNMARKABLE.ini"));

        for(Assignment assignment: model.getAssignments()){
            String report = generateReportString(assignment);
            File file = new File(pathName + "/" + assignment.getNameID() + "_Report.txt");
            saveToFile(report, file);
        }
    }

    private static String createAttentionReport(ArrayList<Malformed> unmarkables,
                                                 String pathName){

        String report = "";
        report += "UNMARKABLE:\n";
        for(Malformed unmarkable: unmarkables){
            report += unmarkable.getAssignmentName() + ", " + unmarkable.getReason() + "\n";
        }
        return report;
    }

    private static String generateReportString(Assignment assignment){
        String report = "";
        report += "STUDENT:" + assignment.getNameID() + "\n";
        report += "TOTAL:" + assignment.getTotalPercentage() + "\n\n";

        for(TestResult testResult: assignment.getResults()){
            report += formatTestString(testResult);
        }
        return report;
    }

    /***
     * Creates a String report for a given result
     * @param testResult
     * @return
     */
    private static String formatTestString(TestResult testResult){
        String resultString = "";
        resultString += "----------------------------------------------------------------\n\n";
        resultString += "TEST:" + testResult.getTestName() + "\n";
        resultString += "AWARDED:" + testResult.getResult() + "\n\n";
        resultString += "EVIDENCE:" + "\n" + testResult.getEvidenceLog() + "\n\n";

        return resultString;
    }

    private static String createDirectoryName(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_s");
        String dateFormatted = LocalDateTime.now().format(formatter);
        String pathName = "REPORT_" + dateFormatted;
        return pathName;
    }

    private static void createDirectory(String pathName){
        File theDir = new File(pathName);

        if (!theDir.exists()) {
            try{
                theDir.mkdir();
            }
            catch(SecurityException se){
                se.printStackTrace();
            }
        }

    }

    private static void saveToFile(String report, File path){
        try {
            PrintWriter out = new PrintWriter(path, "UTF-8");
            out.println(report);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
