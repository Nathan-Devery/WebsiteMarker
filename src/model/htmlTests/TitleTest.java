package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Checks whether the number of titles required are found in the html files provided.
 * Full marks: Number of titles found = number required.
 */
public class TitleTest extends Testable {

    int requiredTitledPages;

    public TitleTest(int requiredTitledPages) {
        super("TITLE TEST");
        this.requiredTitledPages = requiredTitledPages;
    }

    @Override
    public String getDescription() {
        return  "Checks whether the number of titles required are found in the html files provided.\n" +
                "Full marks: Number of titles found = number required.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        String report = "";
        double result = 0;

        int titleCount = 0;
        for(Document document: documents){
            Elements elements = document.select("title");
            if(!elements.isEmpty()){
                titleCount++;
                report += "\n-" + document.location() + "-\n";
                for(org.jsoup.nodes.Element element: elements){
                    report += element + "\n";
                }
            }
        }

        if(titleCount >= requiredTitledPages){
            result = percentage;
        }

        report = ">"+requiredTitledPages + " titled pages required, " + titleCount + " present." + "\n" + report;

        return new TestResult(toString(), result, report);
    }


}
