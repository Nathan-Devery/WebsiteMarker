package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Created by Nathan on 25/11/2017.
 */
public class PageCountTest extends Testable {

    int requirePageNumber;

    public PageCountTest(int requiredPageNumber) {
        super("PAGE COUNT(" + requiredPageNumber + ")");
        this.requirePageNumber = requiredPageNumber;
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet, CompilationUnitTree tree, double percentage) {
        double result = 0;
        String report = "";
        if(documents.size() >= requirePageNumber){
            result = percentage;
            report += "Correct";
        }else{
            report += "Incorrect";
        }
        report += "- expected >=" + requirePageNumber + ", actual: " + documents.size();

        return new TestResult(toString(), result, report);
    }
}
