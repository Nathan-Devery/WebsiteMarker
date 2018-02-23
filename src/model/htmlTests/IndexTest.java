package model.htmlTests;

import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nathan on 25/11/2017.
 */
public class IndexTest extends Testable {

    public IndexTest() {
        super("INDEX PAGE");
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet) {
        boolean result = false;
        String report = "";

        String path = documents.get(0).location();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.getName().equals("index.html")) {
                result = true;
                report += "Index present at: " + path + file.getName();
            }
        }
        report += "No index page present in " + path;

        return new TestResult(toString(), result, report);
    }
}
