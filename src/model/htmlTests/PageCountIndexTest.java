package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.util.ArrayList;

/**
 * Checks whether number correct number of html files are present.
 * Checks whether index file present.
 * Full marks: Correct number of pages and index file present.
 */
public class PageCountIndexTest extends Testable {

    int requirePageNumber;

    //Must be reset
    String report = "";

    public PageCountIndexTest(int requiredPageNumber) {
        super("PAGE COUNT(" + requiredPageNumber + ") & INDEX PAGE");
        this.requirePageNumber = requiredPageNumber;
    }

    @Override
    public String getDescription() {
        return  "Checks whether number correct number of html files are present.\n" +
                "Checks whether index file present.\n" +
                "Full marks: Correct number of pages and index file present.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        double result = 0;
        if(checkPageCount(documents) && checkIndex(documents)){
            result = percentage;
        }

        report += "\nHtml files found:\n";
        for(Document document: documents){
            report += document.location() + "\n";
        }

        TestResult testResult = new TestResult(toString(), result, report);
        reset();
        return testResult;
    }

    private boolean checkPageCount(ArrayList<Document> documents){
        boolean result;
        if(documents.size() >= requirePageNumber){
            report += "Correct";
            result = true;
        }else{
            report += "Incorrect";
            result = false;
        }
        report += "- expected >=" + requirePageNumber + ", actual: " + documents.size() + "\n";
        return result;
    }

    private boolean checkIndex(ArrayList<Document> documents){
        for(Document document: documents){
            String name = document.location();
            if(name.equals("index.html") || name.equals("Index.html")){
                report += "index.html file present.\n";
                return true;
            }
        }

        report += "No index.html file present\n";
        return false;
    }

    //Resets state for next test
    private void reset(){
        report = "";
    }

}
