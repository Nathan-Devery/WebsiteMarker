package model.htmlTests;

import jdk.nashorn.api.tree.CompilationUnitTree;
import model.TestResult;
import model.Testable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.ArrayList;

/***
 * Checks all images contain a 'alt' attribute.
 * Checks all videos (iframe) contain an 'alt' attribute.
 * Half marks: At least one use of the alt attribute.
 * Half marks: All images and videos (iframe) have an alt attribute.
 */
public class AltTextTest extends Testable {

    String report = "";

    public AltTextTest() {
        super("ALT TEXT");
    }

    @Override
    public String getDescription() {
        return  "Checks all images contain a 'alt' attribute.\n" +
                "Checks all videos (iframe) contain an 'alt' attribute.\n" +
                "Half marks: At least one use of the alt attribute.\n" +
                "Half marks: All images and videos (iframe) have an alt attribute. ";
    }

    @Override
    public TestResult runTest(ArrayList<Document> documents, ArrayList<Document> xmlDocs, CSSStyleSheet sheet, String cssDocString, CompilationUnitTree tree, double percentage) {
        double result;

        if(findAlts("img", documents) == -1 || findAlts("iframe", documents) == -1){
            report = "All images and video have alt attribute present.\n\n" + report;
            result = percentage;
        }else if(findAlts("img", documents) == 1 || findAlts("iframe", documents) == 1){
            report = "All images and video do not have alt attribute present.\nAt least one alt attribute present.\n\n" + report;;
            result = percentage / 2;
        }else{
            report = "No presence of alt attribute in images and video.\n\n" + report;;
            result = 0;
        }

        TestResult testResult = new TestResult(toString(), result, report);
        reset();
        return testResult;
    }


    /***
     * Finds number of alts in element as identified by search string
     * @param searchString
     * @param documents
     * @return Returns -1 if all elements have alt. Returns 1 if at least one element found. Return 0 if none found.
     */
    private int findAlts(String searchString, ArrayList<Document> documents) {
        int elementCount = 0;
        int altElementCount = 0;

        for (Document document : documents) {
            report += "\n-" + document.location() + "-\n\n";
            Elements elements = document.select(searchString);
            elementCount += elements.size();
            for (Element element : elements) {
                if (element.attr("alt").isEmpty()) {
                    report += "Alt is not present\n" + element + "\n";
                } else {
                    report += "Alt is present\n" + element + "\n";
                    altElementCount++;
                }
            }
        }

        if(elementCount > 0 && elementCount == altElementCount){
            return -1;
        }else if(altElementCount > 0){
            return 1;
        }else{
            return 0;
        }
    }


    private void reset() {
        this.report = "";
    }

}
