package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.w3c.dom.css.CSSStyleSheet;

import javax.lang.model.util.Elements;
import java.util.ArrayList;
import org.jsoup.select.*;

import static junit.TestUtility.getXMLDocument;

/**
 * Checks html for presence of !doctype, html, head, body tags.
 * Correct order is not required, only opening tags required.
 * Half marks: At least half of required html files contain correct structure.
 * Half marks: All html files contain correct structure.
 */
public class HtmlStructureTest extends Testable {

    public HtmlStructureTest() {
        super("HTML STRUCTURE");
    }

    @Override
    public String getDescription() {
        return  "Checks html for presence of !doctype, html, head, body tags.\n" +
                "Half marks: At least half of required html files contain correct structure.\n" +
                "Half marks: All html files contain correct structure.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        //TODO allow this to work with capitalized elements, maybe make everything lowercase?
        double result = 0;
        String report = "";

        int correct = 0;

        for (Document document : xmlDocs) { //'xmlDocs' is used as the html structure is automatically appended to normal 'documents'
            if (checkDoctype(document) && !document.select("html").isEmpty()
                    && !document.select("head").isEmpty() && !document.select("body").isEmpty()) {
                report += "Correct structure:" + document.location() + "\n";
                correct++;
            }else{
                report += "Incorrect structure:" + document.location() + "\n";
            }
        }

        if (correct == xmlDocs.size()) {
            result = percentage;
            report = "Html structure correct for all " + xmlDocs.size() + " pages: (doctype, html, head, body)\n\n" + report;
        } else if (correct > 0 && correct >= xmlDocs.size() / 2) {
            result = percentage / 2;
            report = "Html structure correct for " + correct + "/" + xmlDocs.size() + " pages: (doctype, html, head, body)\n\n" + report;
        } else {
            report = "Incorrect html structure for " + (xmlDocs.size() - correct) + " pages: (doctype, html, head, body)\n\n" + report;
        }

        return new TestResult(toString(), result, report);
    }

    private boolean checkDoctype(Document xmlDoc){
            for(Node child: xmlDoc.childNodes()){
                if(child.nodeName().equals("#doctype")){
                    return true;
                }
        }
        return false;
    }

}
