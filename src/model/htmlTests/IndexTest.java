package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks whether html files contain a file name 'index.html'
 * Full marks: index.html present
 */
public class IndexTest extends Testable {

    public IndexTest() {
        super("INDEX PAGE");
    }

    @Override
    public String getDescription() {
        return  "Checks whether html files contain a file name 'index.html'\n" +
                "Full marks: index.html present";
    }
    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        double result = 0;
        String report = "";

        String path = documents.get(0).location();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.getName().equals("index.html")) {
                result = percentage;
                report += "Index present at: " + path + file.getName();
            }
            report += "No index page present in " + path;
        }

        return new TestResult(toString(), result, report);
    }

}