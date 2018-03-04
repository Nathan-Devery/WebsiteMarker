package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Created by Nathan on 24/11/2017.
 */
public class HtmlStructureTest extends Testable {

    public HtmlStructureTest() {
        super("HTML STRUCTURE");
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, CSSStyleSheet sheet, CompilationUnitTree tree, double percentage) {
        //TODO redo but call the getChild() methods in the element
        double result = 0;
        String report = "";

        int correct = 0;
        for (Document document : documents) {
            //Check composite structure
            for (Element element : document.select("html")) {
                if (!element.select("head").isEmpty() && !element.select("body").isEmpty() && !element.select("title").isEmpty()) {
                    correct++;
                }
            }
        }
        if (correct == documents.size()) {
            result = percentage;
            report += "Html structure correct for all " + documents.size() + " pages: (html, head, title, body)";
        } else {
            report += "Wrong html structure for " + (documents.size() - correct) + "pages";
        }

        return new TestResult(toString(), result, report);
    }
}
