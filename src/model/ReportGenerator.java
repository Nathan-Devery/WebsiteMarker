package model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class ReportGenerator {

    public static void createReports(Collection<Assignment> assignments, String parentPath){
        String pathName = parentPath + "Report" ;
        createDirectory(pathName);

        for(Assignment assignment: assignments){
            String report = generateReportString(assignment);
            File file = new File(pathName + "/" + assignment.getNameID() + "_Report.txt");
            saveToFile(report, file);
        }

    }

    private static String generateReportString(Assignment assignment){
        String report = "";
        report += assignment.getNameID() + "\n";

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
        resultString += "----------------------------------------------------------------";
        resultString += testResult.getTestName() + "\n";
        resultString += testResult.getResult() + "\n";
        resultString += testResult.getEvidenceLog() + "\n";

        return resultString;
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
