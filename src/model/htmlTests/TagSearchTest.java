package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/**
 * Checks whether the tagType tag is found in the html.
 * Checks if the number required is present.
 * Half marks: At least one found
 * Half marks: Number found equals number required.
 */
public class TagSearchTest extends Testable {

    TagType tagType;
    int numberRequired;

    public TagSearchTest(TagType tagType, int numberRequired) {
        super(tagType.name());
        this.tagType = tagType;
        this.numberRequired = numberRequired;
    }

    @Override
    public String getDescription() {
        return  "Checks whether the " + tagType.name() + " tag is found in the html." +
                "\nChecks if " + numberRequired + " of the elements are present." +
                "\nHalf marks: At least one found" +
                "\nHalf marks: Number found equals number required.";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        String report = "";
        int elementCount = 0;
        for (Document document : documents) {
            Elements elements = document.select(tagType.searchString);
            if (!elements.isEmpty()) {
                report += "\n-" + document.location() + "-\n\n";
                elementCount += elements.size();
                for (Element element : elements) {
                    report += element;
                    report += "\n";
                }

            }
        }

        double result = 0;
        if(numberRequired <= elementCount){
            report = "Element(s) present.\n\n" + report;
            result = percentage;
        }else if(numberRequired/2 > 0 && numberRequired/2 <= elementCount){
            report = "Elements not present in number required.\n\n" + report;
            result = percentage/2;
        }else{
            report = "Element(s) not present.\n\n" + report;
        }

        return new TestResult(this.toString(), result, report);
    }
}
